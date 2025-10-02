package com.company.serviceImpl;

import com.company.entity.Developer;
import com.company.exception.DeveloperNotFoundException;
import com.company.helper.DeveloperGeneratorId;
import com.company.helper.ExcelHelper;
import com.company.repository.DeveloperRepository;
import com.company.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeveloperServiceImpl implements DeveloperService
{
    @Autowired
    private DeveloperRepository developerRepository;


    @Override
    public String saveDeveloper(Developer developer) {
      String developerId = DeveloperGeneratorId.generatDeveloperId(developer);
      developer.setDeveloperId(developerId);
      developerRepository.save(developer);
      Developer saveDeveloper = developerRepository.save(developer);
        return "Developer saved";
    }

    @Override
    public List<Developer> getAllDeveloper() {
        List<Developer> developerList = developerRepository.findAll();
        return developerList;
    }

    @Override
    public Developer getDeveloperById(int id) {
        Developer developer = developerRepository.findById(id).orElseThrow(() -> new DeveloperNotFoundException("Developer with id not found " +id));

        return developer;
    }

    @Override
    public String deleteById(int id) {
        developerRepository.deleteById(id);
        return "Developer Deleted";
    }

    @Override
    public Developer UpdateDeveloper(int id, Developer NewData) {
        Developer developer = developerRepository.findById(id).orElseThrow(() -> new NullPointerException("Developer update" +id));
        developer.setFName(NewData.getFName());
        developer.setLName(NewData.getLName());
        developer.setAge(NewData.getAge());
        developer.setCity(NewData.getCity());
        developer.setSalary(NewData.getSalary());
        Developer updateDevloper = developerRepository.save(developer);
        return updateDevloper;
    }

    @Override
    public List<Developer> filterByCity(String city) {
        List<Developer> developerList = developerRepository.findAll();
        List<Developer> filterList = developerList.stream()
                .filter(developer ->
                        developer.getCity().equalsIgnoreCase(city)).collect(Collectors.toList());
        return filterList;
    }

    @Override
    public List<Developer> filterDataByGender(String gender) {
        List<Developer> developerList = developerRepository.findAll();
        List<Developer> filterList = developerList.stream().filter(developer -> developer.getGender().equalsIgnoreCase(gender)).collect(Collectors.toList());
        return filterList;
    }

    @Override
    public String saveExcell(MultipartFile file) {
        try{
            List<Developer> developers = ExcelHelper.convertExcelToDeveloper(file.getInputStream());
            for (Developer developer: developers){
                String developerId = DeveloperGeneratorId.getDeveloperIdForExcel(developer);

                developer.setDeveloperId(developerId);
            }
            developerRepository.saveAll(developers);
            return "developer saved successfully";
        }catch (Exception e){
            e.printStackTrace();
            return "Error:" + e.getMessage();
        }

    }

    @Override
    public List<Developer> getDeveloperByAge(int age) {
        List<Developer> deve = developerRepository.findByAge(age);
        return deve;
    }


}

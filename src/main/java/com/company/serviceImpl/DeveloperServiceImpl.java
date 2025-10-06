package com.company.serviceImpl;

import com.company.entity.Developer;
import com.company.repository.DeveloperRepository;
import com.company.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeveloperServiceImpl implements DeveloperService
{
    @Autowired
    private DeveloperRepository developerRepository;


    @Override
    public String saveDeveloper(Developer developer) {
        Developer saveDeveloper = developerRepository.save(developer);
        return "developer saved";
    }

    @Override
    public List<Developer> getAllDeveloper() {
        List<Developer> developerList = developerRepository.findAll();
        System.out.println();
        return developerList;
    }

    @Override
    public Developer getDeveloperById(int id) {
        Developer developer = developerRepository.findById(id).orElseThrow(() -> new NullPointerException("Developer with id not found " +id));

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
        developer.setfName(NewData.getfName());
        developer.setlName(NewData.getlName());
        developer.setAge(NewData.getAge());
        developer.setCity(NewData.getCity());
        developer.setSalary(NewData.getSalary());
        Developer updateDevloper = developerRepository.save(developer);
        return updateDevloper;
    }

}

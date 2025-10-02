package com.company.service;
//"/id=" ""


import com.company.entity.Developer;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface DeveloperService {

    String saveDeveloper(Developer developer);

    List<Developer> getAllDeveloper();

    Developer getDeveloperById(int id);

    String deleteById(int id);

    Developer UpdateDeveloper(int id , Developer NewData);

    List<Developer> filterByCity(String city);

    List<Developer> filterDataByGender(String gender);


    String saveExcell(MultipartFile file);

    List<Developer> getDeveloperByAge(int age);



}

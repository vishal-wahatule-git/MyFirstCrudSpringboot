package com.company.service;
//"/id=" ""


import com.company.entity.Developer;

import java.util.List;

public interface DeveloperService {

    String saveDeveloper(Developer developer);

    List<Developer> getAllDeveloper();

    Developer getDeveloperById(int id);

    String deleteById(int id);

    Developer UpdateDeveloper(int id , Developer NewData);


}

package com.example.testDB.repository;

import com.example.testDB.model.Person;

import java.util.List;

public interface PersonDAO extends BaseDAO <Person>{
    List<Person> findByName (String name);
}

package com.example.testDB.repository.impl;

import com.example.testDB.connection.JDBCConnector;
import com.example.testDB.model.Person;
import com.example.testDB.repository.PersonDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonDAOImpl implements PersonDAO {

    private static final String CREATE_PERSON = "INSERT INTO person (name,address,age) VALUES (?,?,?)";
    private static final String FIND_ALL_PERSON = "SELECT * FROM person";
    private JDBCConnector connector;

    public PersonDAOImpl(JDBCConnector connector){
        this.connector = connector;
    }


    @Override
    public List<Person> findAll() {
        List<Person> personList = new ArrayList<>();
        try(Connection connection = connector.getConnection();
            Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(FIND_ALL_PERSON);

            while (resultSet.next()){
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAdress(resultSet.getString("address"));
                person.setAge(resultSet.getInt("age"));
                personList.add(person);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return personList;
    }

    @Override
    public Optional<Person> findByID(int id) {
        return Optional.empty();
    }

    @Override
    public boolean create(Person entity) {
        try(Connection connection1 = connector.getConnection();
            PreparedStatement statement = connection1.prepareStatement(CREATE_PERSON, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1,entity.getName());
            statement.setString(2,entity.getAdress());
            statement.setInt(3,entity.getAge());

          return statement.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Person entity) {
        return false;
    }

    @Override
    public boolean deleteByID(int id) {
        return false;
    }

    @Override
    public List<Person> findByName(String name) {
        return null;
    }
}

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
    private static final String FIND_BY_ID = "SELECT * FROM person WHERE id = ? ";
    private static final String USER_UPDATE = "UPDATE person SET name = ?, address = ?, age = ? WHERE id = ?";
    private JDBCConnector connector;

    public PersonDAOImpl(JDBCConnector connector) {
        this.connector = connector;
    }


    @Override
    public List<Person> findAll() {
        List<Person> personList = new ArrayList<>();
        try (Connection connection = connector.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(FIND_ALL_PERSON);

            while (resultSet.next()) {
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
        Person person = new Person();
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAdress(resultSet.getString("address"));
                person.setAge(resultSet.getInt("age"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.of(person);
    }

    @Override
    public boolean create(Person entity) {
        try (Connection connection1 = connector.getConnection();
             PreparedStatement statement = connection1.prepareStatement(CREATE_PERSON, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, entity.getName());
            statement.setString(2, entity.getAdress());
            statement.setInt(3, entity.getAge());

            return statement.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Person entity) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(USER_UPDATE)) {
            preparedStatement.setString(1,entity.getName());
            preparedStatement.setString(2,entity.getAdress());
            preparedStatement.setInt(3,entity.getAge());
            preparedStatement.setInt(4,entity.getId());

            System.out.println("Данные пользователя успешно изменены.");
            return preparedStatement.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }       //to do

    @Override
    public boolean deleteByID(int id) {         //to do
        return false;
    }

    @Override
    public List<Person> findByName(String name) {
        return null;
    }      //to do
}

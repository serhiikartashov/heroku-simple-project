package org.example.repository;

import org.example.entity.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;

public class UserRepository {

    private final DataSource dataSource;

    public UserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<User> getUsers() {
        try {
            // test connection here
            Connection connection = dataSource.getConnection();
            connection.close();
        } catch (Exception e){

        }
        return List.of(new User());
    }

}

package org.example.service;

import org.example.entity.User;
import org.example.repository.UserRepository;

import javax.sql.DataSource;
import java.util.List;

public class UserService {

    private final UserRepository repo;

    public UserService(DataSource dataSource) {
        this.repo = new UserRepository(dataSource);
    }

    public List<User> getUsers(){
        return repo.getUsers();
    }
}

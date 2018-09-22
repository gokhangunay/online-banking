package com.banking.user.dao;

import com.banking.user.domain.User;
//import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Long> {

    User findByUserName(String userName);
    User findByEmail(String email);

}

/*
public interface UserDao extends JpaRepository<User, Long> {

    User findByUserName(String userName);
    User findByEmail(String email);

}
*/
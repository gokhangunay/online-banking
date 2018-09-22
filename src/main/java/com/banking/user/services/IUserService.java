package com.banking.user.services;

import com.banking.user.domain.User;
import com.banking.user.domain.security.UserRole;

import java.util.List;
import java.util.Set;

public interface IUserService {

    User findByUserName(String userName);

    User findByEmail(String email);

    boolean checkUserExists(String userName, String email);

    boolean checkUserNameExists(String userName);

    boolean checkUserEmailExists(String email);

    void save(User user);

    User createUser(User user, Set<UserRole> userRoles);

    User createUser(User user);

    User saveUser(User user);

    List<User> findUserList();

    void enableUser(String userName);

    void disableUser(String userName);

}

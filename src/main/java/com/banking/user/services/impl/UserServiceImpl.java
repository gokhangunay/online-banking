package com.banking.user.services.impl;

import com.banking.user.dao.RoleDao;
import com.banking.user.dao.UserDao;
import com.banking.user.dao.UserRoleDao;
import com.banking.user.domain.User;
import com.banking.user.domain.security.UserRole;
import com.banking.user.services.AccountService;
import com.banking.user.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AccountService accountService;

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public boolean checkUserExists(String username, String email) {
        boolean result = false;
        if(checkUsernameExists(username) || checkUserEmailExists(email)){
            result = true;
        }
        return result;
    }

    @Override
    public boolean checkUsernameExists(String username) {
        boolean result = false;
        if(findByUsername(username)!= null){
            result = true;
        }
        return result;
    }

    @Override
    public boolean checkUserEmailExists(String email) {
        boolean result = false;
        if(findByEmail(email)!= null){
            result = true;
        }
        return result;
    }

    @Override
    public void save(User user) {
        // userDao.save(user); passwordu encoding edip kaydetmek için artık bu yapıyı farklı bir methoda yönlendirdim.

    }

    @Override
    public User createUser(User user, Set<UserRole> userRoles) {

        User createUserInfo = findByUsername(user.getUsername());

        if(createUserInfo!=null){
            LOG.info("Kullanıcı adı " + user.getUsername() + " olan kullanıcı zaten var!");
        }else{

            String encryptedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encryptedPassword);

            for(UserRole userRole : userRoles){
                roleDao.save(userRole.getRole());
            }

            user.getUserRoles().addAll(userRoles);
            user.setPrimaryAccount(accountService.createPrimaryAccount());
            user.setSavingsAccount(accountService.createSavingsAccount());

            createUserInfo = userDao.save(user);
        }

        return createUserInfo;
    }

    @Override
    public User createUser(User user) {
        User userInfo = findByUsername(user.getUsername());
        if(userInfo!=null){
            LOG.info("Kullanıcı adı " + user.getUsername() + " olan kullanıcı zaten var!");
        }else{
            //String encryptedPassword = passwordEncoder.encode(user.getPassword());
            //user.setPassword(encryptedPassword);
        }

        return null;
    }

    @Override
    public User saveUser(User user) {
        return userDao.save(user);
    }

    @Override
    public List<User> findUserList() {
        return userDao.findAll();
    }

    @Override
    public void enableUser(String username) {
        User user = findByUsername(username);
        user.setEnabled(true);
        userDao.save(user);
    }

    @Override
    public void disableUser(String username) {
        User user = findByUsername(username);
        user.setEnabled(false);
        userDao.save(user);
    }
}

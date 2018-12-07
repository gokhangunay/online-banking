package com.banking.user.dao;


import com.banking.user.domain.security.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleDao extends JpaRepository<UserRole, Long> {

}

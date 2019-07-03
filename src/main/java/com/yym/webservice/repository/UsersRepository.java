package com.yym.webservice.repository;

import com.yym.webservice.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
  Users findByUsername(String username);
}

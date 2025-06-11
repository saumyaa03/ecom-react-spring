package com.saumya.ecom.proj.repository;

import com.saumya.ecom.proj.model.Users;
import com.saumya.ecom.proj.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {

    com.saumya.ecom.proj.model.Users findByUsername(String username);
}

package com.example.ForeignExchangeSystem.repository;

import com.example.ForeignExchangeSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByEmail(String email); //select * from user where email = ?
}

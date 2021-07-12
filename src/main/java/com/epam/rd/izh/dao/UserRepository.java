package com.epam.rd.izh.dao;

import com.epam.rd.izh.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}

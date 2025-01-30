package com.ashutosh.multipleDataBase.userdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashutosh.multipleDataBase.userdb.entity.User;




@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}


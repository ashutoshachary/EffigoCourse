package com.ashutosh.multipleDataBase.accountdb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ashutosh.multipleDataBase.accountdb.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

	@Query("SELECT a FROM Account a WHERE a.user_id = :user_id")
	List<Account> findByUser_id(Long user_id);
}

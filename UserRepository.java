package com.example.Sudoku.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Sudoku.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

	@Query(value="select u.password from user u ",nativeQuery=true)
	List<String> findPasswords();
}

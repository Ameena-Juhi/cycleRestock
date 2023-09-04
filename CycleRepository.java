package com.example.Sudoku.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Sudoku.entity.Cycle;

@Repository
public interface CycleRepository extends JpaRepository<Cycle, Integer>{

}

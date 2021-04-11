package com.dandigam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dandigam.entity.ProjectEntity;

@Repository
public interface FileReaderRepository extends JpaRepository<ProjectEntity, Integer>{

}

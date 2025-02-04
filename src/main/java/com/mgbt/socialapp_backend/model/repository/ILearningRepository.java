package com.mgbt.socialapp_backend.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mgbt.socialapp_backend.model.entity.Learning;

@Repository
public interface ILearningRepository extends JpaRepository<Learning, Long> {

}

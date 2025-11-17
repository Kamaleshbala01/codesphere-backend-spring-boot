package com.kamaleshbala.codesphere.repository;

import com.kamaleshbala.codesphere.model.SubmissionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepo extends JpaRepository<SubmissionModel,String> {
}

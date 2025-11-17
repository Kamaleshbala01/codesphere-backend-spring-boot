package com.kamaleshbala.codesphere.repository;

import com.kamaleshbala.codesphere.model.ContestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContestRepo extends JpaRepository<ContestModel,String> {
}

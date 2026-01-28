package com.kamaleshbala.codesphere.repository;

import com.kamaleshbala.codesphere.model.ViolationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViolationRepo extends JpaRepository<ViolationModel,String> {
    ViolationModel findByUser_idAndContest_id(String userId, String contestID);
}

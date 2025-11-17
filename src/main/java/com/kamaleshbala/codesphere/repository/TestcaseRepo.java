package com.kamaleshbala.codesphere.repository;

import com.kamaleshbala.codesphere.model.TestcaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestcaseRepo extends JpaRepository<TestcaseModel,String> {
    public List<TestcaseModel> findByProblem_id(String problem_id);
}

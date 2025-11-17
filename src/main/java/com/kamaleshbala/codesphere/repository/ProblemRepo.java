package com.kamaleshbala.codesphere.repository;

import com.kamaleshbala.codesphere.enums.Testcase;
import com.kamaleshbala.codesphere.model.ProblemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemRepo extends JpaRepository<ProblemModel,String> {
    @Query("SELECT p FROM ProblemModel p JOIN FETCH p.testcases t " +
            "WHERE p.id = :problemId AND t.type = :type")
    ProblemModel findProblemWithSampleTestcases(
            @Param("problemId") String problemId,
            @Param("type") Testcase type
    );
}

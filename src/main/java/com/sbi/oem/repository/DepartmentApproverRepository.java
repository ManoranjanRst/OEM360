package com.sbi.oem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbi.oem.model.DepartmentApprover;

@Repository
public interface DepartmentApproverRepository extends JpaRepository<DepartmentApprover, Long> {

}

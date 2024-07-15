package com.simplogics.hospitalManagement.repository;

import com.simplogics.hospitalManagement.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface IPatientRepository extends JpaRepository<Patient,Long> {
}

package com.simplogics.hospitalManagement.repository;

import com.simplogics.hospitalManagement.model.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProcedureRepository extends JpaRepository<Procedure,Long> {
}

package com.simplogics.hospitalManagement.repository;
import com.simplogics.hospitalManagement.constants.Queries;
import com.simplogics.hospitalManagement.model.PatientProcedure;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface IPatientProcedureRepository extends JpaRepository<PatientProcedure,Long> {
    @Modifying
    @Transactional
    @Query(value = Queries.INSERT_INTO_PATIENT_PROCEDURE, nativeQuery = true)
    int updateProcedure(@Param("procedureId") Integer procedureId, @Param("patientId") Integer patientId, @Param("start_date") Date date);

    @Query(nativeQuery = true,value = Queries.FETCH_PATIENT_PROCEDURE_COSTS)
    List<Object[]> fetchPatientProcedureDetails(@Param("patientId") Integer patientId);

    @Query(nativeQuery = true,value =Queries.GET_STAFF_SALARY_TOTAL)
    List<Object[]> fetchStaffTotalDetails(@Param("patientId") Integer patientId,@Param("procedureId") Integer procedureId);

    @Query(nativeQuery = true,value = Queries.GET_EQUIPMENTS_TOTAL)
    List<Object[]> fetchEquipmentTotalDetails(@Param("patientId") Integer patientId,@Param("procedureId") Integer procedureId);
    @Query(nativeQuery = true,value = Queries.GET_PATIENT_PROCEDURE)
    List<Integer> fetchPatientProcedure(@Param("patientId") Integer patientId);
}
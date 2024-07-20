package com.simplogics.hospitalManagement.constants;

public class Queries {
    public static final String INSERT_INTO_PATIENT_PROCEDURE = "insert into patient_procedure (procedure_id, patient_id, start_date) values (:procedureId, :patientId, :start_date)";

    public static final String FETCH_PATIENT_PROCEDURE_COSTS = "select " +
            "    p.patient_id, " +
            "    p.name, " +
            "    pt.procedure_id, " +
            "    pt.name, " +
            "    pt.total_cost, " +
            "    pt.total_salary_cost, " +
            "    pt.total_equipment_cost, " +
            "pp.start_date "+
            "from " +
            "    patients p " +
            "join " +
            "    patient_procedure pp ON p.patient_id = pp.patient_id " +
            "join " +
            "    ( " +
            "        select " +
            "            sc.name, " +
            "            sc.procedure_id, " +
            "            sc.total_salary_cost, " +
            "            ec.total_equipment_cost, " +
            "            (sc.total_salary_cost + ec.total_equipment_cost) as total_cost " +
            "        from " +
            "            ( " +
            "                select " +
            "                    pd.name, " +
            "                    pd.procedure_id, " +
            "                    sum(sps.salary_per_hour * pd.duration) as total_salary_cost " +
            "                from " +
            "                    \"procedure\" pd " +
            "                join " +
            "                    staff_procedure_salary sps on pd.procedure_id = sps.procedure_id " +
            "                group by " +
            "                    pd.name, " +
            "                    pd.procedure_id " +
            "            ) sc " +
            "        join " +
            "            ( " +
            "                select " +
            "                    pd.name, " +
            "                    sum(q.quantity * e.cost_per_use) as total_equipment_cost " +
            "                from " +
            "                    \"procedure\" pd " +
            "                join " +
            "                    quantity q on pd.procedure_id = q.procedure_id " +
            "                join " +
            "                    equipments e on q.equipment_id = e.equip_id " +
            "                group by " +
            "                    pd.name " +
            "            ) ec on sc.name = ec.name " +
            "    ) pt on pp.procedure_id = pt.procedure_id " +
            "where p.patient_id = :patientId " +
            "group by " +
            "    p.patient_id, " +
            "    p.name, " +
            "    pt.procedure_id, " +
            "    pt.name, " +
            "    pt.total_cost, " +
            "    pt.total_salary_cost, " +
            "    pt.total_equipment_cost,"+
            "pp.start_date;";
    public static final String GET_EQUIPMENTS_TOTAL = "SELECT " +
            "pd.name AS procedure_name, " +
            "e.equip_name AS equipment_name, " +
            "q.quantity AS number_of_equipment, " +
            "e.cost_per_use AS cost_per_use, " +
            "q.quantity * e.cost_per_use AS total " +
            "FROM " +
            "patients p " +
            "JOIN " +
            "patient_procedure pp ON p.patient_id = pp.patient_id " +
            "JOIN " +
            "\"procedure\" pd ON pp.procedure_id = pd.procedure_id " +
            "JOIN " +
            "quantity q ON pd.procedure_id = q.procedure_id " +
            "JOIN " +
            "equipments e ON q.equipment_id = e.equip_id " +
            "WHERE " +
            "p.patient_id = :patientId and pp.procedure_id = :procedureId  " +
            "ORDER BY " +
            "pd.name";
    public static final String GET_STAFF_SALARY_TOTAL = "SELECT " +
            "pd.name AS procedure_name, " +
            "s.staff_name AS staff_name, " +
            "pd.duration AS duration, " +
            "sps.salary_per_hour AS salary_per_hour, " +
            "pd.duration * sps.salary_per_hour AS total " +
            "FROM " +
            "patients p " +
            "JOIN " +
            "patient_procedure pp ON p.patient_id = pp.patient_id " +
            "JOIN " +
            "\"procedure\" pd ON pp.procedure_id = pd.procedure_id " +
            "JOIN " +
            "staff_procedure_salary sps ON pd.procedure_id = sps.procedure_id " +
            "JOIN " +
            "staff s ON sps.staff_id = s.staff_id " +
            "WHERE " +
            "p.patient_id = :patientId and pp.procedure_id = :procedureId " +
            "ORDER BY " +
            "pd.name;";
    public static final String GET_PATIENT_PROCEDURE = "select p2.procedure_id from patients p " +
            "join patient_procedure pp on p.patient_id =pp.patient_id " +
            "join procedure p2 on pp.procedure_id =p2.procedure_id " +
            "where p.patient_id =1;";

}
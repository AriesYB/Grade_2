package edu.neu.dao;

import edu.neu.entity.Patient;

import java.util.List;

/**
 * ClassName:UserDAO
 * Package:edu.neu.dao
 * Description:
 *
 * @Date:2019/8/21 17:10
 * @Author:HetFrame
 */
public interface PatientDAO {
    List<Patient> findAll();

    Patient findById(int id);

    Patient findById_number(String id_number);

    int findIdById_number(String id_number);

    void insertPatient(Patient patient);

    void updatePatient(Patient patient);

    void deletePatientById(int id);
//    List<Patient> findByDoctorDate(int id, Date date1, Date date2);
}

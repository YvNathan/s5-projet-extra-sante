package itu.s5.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import itu.dao.GeneriqueDAO;
import itu.s5.model.Patient;

@Repository
public class PatientRepository {

    public List<Patient> findAll() throws Exception {
        return GeneriqueDAO.findAll(Patient.class);
    }

    public Patient findById(Integer id) throws Exception {
        return GeneriqueDAO.findById(Patient.class, id);
    }

    public Patient save(Patient patient) throws Exception {
        GeneriqueDAO.save(patient);
        return patient;
    }

    public Patient update(Patient patient) throws Exception {
        GeneriqueDAO.update(patient);
        return patient;
    }

    public void deleteById(Integer id) throws Exception {
        GeneriqueDAO.deleteById(Patient.class, id);
    }
}
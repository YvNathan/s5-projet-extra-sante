package itu.s5.service;

import java.util.List;

import org.springframework.stereotype.Service;

import itu.s5.model.Patient;
import itu.s5.repository.PatientRepository;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> findAll() throws Exception {
        return patientRepository.findAll();
    }

    public Patient findById(Integer id) throws Exception {
        return patientRepository.findById(id);
    }

    public Patient create(Patient patient) throws Exception {
        validate(patient);
        patient.setId(null);
        return patientRepository.save(patient);
    }

    public Patient update(Integer id, Patient patient) throws Exception {
        Patient existing = patientRepository.findById(id);
        if (existing == null) {
            throw new IllegalArgumentException("Patient introuvable (id=" + id + ")");
        }
        validate(patient);
        patient.setId(id);
        return patientRepository.update(patient);
    }

    public void delete(Integer id) throws Exception {
        patientRepository.deleteById(id);
    }

    private void validate(Patient patient) {
        if (patient == null) {
            throw new IllegalArgumentException("Les données du patient sont obligatoires");
        }
        if (patient.getNom() == null || patient.getNom().isBlank()) {
            throw new IllegalArgumentException("Le nom est obligatoire");
        }
        if (patient.getPrenom() == null || patient.getPrenom().isBlank()) {
            throw new IllegalArgumentException("Le prénom est obligatoire");
        }
        if (patient.getDateNaissance() == null) {
            throw new IllegalArgumentException("La date de naissance est obligatoire");
        }
        if (!"M".equals(patient.getSexe()) && !"F".equals(patient.getSexe())) {
            throw new IllegalArgumentException("Le sexe doit être 'M' ou 'F'");
        }
    }
}
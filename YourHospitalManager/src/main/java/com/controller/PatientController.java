package com.controller;

import com.model.Patient;
import com.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Patients", description = "Manage patient records.")
@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;


    @Operation(summary = "Retrieve all patients",description = "Returns a list of all registered patients.")
    @GetMapping
    public List<Patient> getPatients(){
        return patientService.getAllPatients();
    }

    @Operation(summary = "Retrieve patient by ID", description = "Fetches a single patient record by their unique ID. Returns 404 if not found.")
    @GetMapping("/{id}")
    public Patient getPatient(@PathVariable Long id){
        return patientService.getPatientById(id);

    }


    @Operation(summary = "Register a new patient", description = "Creates a new patient record in the system.")
    @PostMapping
    public Patient registerPatient(@RequestBody Patient patient){
        return patientService.registerPatient(patient);
    }



}


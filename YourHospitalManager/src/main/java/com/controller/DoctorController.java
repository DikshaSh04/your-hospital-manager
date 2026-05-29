package com.controller;


import com.model.Doctor;
import com.service.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Doctors", description = "Manage doctor records")
@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Operation(summary = "Retrieve all doctors", description = "Returns a list of all registered doctors with their specializations and availability hours.")
    @GetMapping
    public List<Doctor> getDoctors(){
        return doctorService.getDoctors();
    }

    @Operation(summary = "Add a new doctor", description = "Creates a new doctor record with availability hours.")
    @PostMapping
    public Doctor addDoctor(@RequestBody Doctor doctor){
        return doctorService.addDoctor(doctor);
    }


}


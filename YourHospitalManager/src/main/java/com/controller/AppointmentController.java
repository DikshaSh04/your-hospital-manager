package com.controller;

import com.model.Appointment;
import com.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Appointments", description = "Manage appointment bookings")
@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Operation(summary= "Get appointments by patient", description = "Retrieves all appointments associated with a given patient ID")
    @GetMapping("/patient/{patientId}")
    public List<Appointment> getAppointmentByPatient(@PathVariable Long patientId){
        return appointmentService.getAppointmentsByPatient(patientId);
    }

    @Operation(summary = "Cancel an appointment", description = "Updates the appointment status to CANCELLED. Returns 404 if appointment not found.")
    @PutMapping("/{id}/cancel")
    public Appointment cancelAppointment(@PathVariable Long id){
        return appointmentService.cancelAppointment(id);
    }

    @Operation(summary = "Book an appointment", description = "Books a slot for a patient with a doctor. Returns 409 if slot is already taken.")
    @PostMapping
    public Appointment bookAppointment(@RequestBody Appointment appointment){
        return appointmentService.bookAppointment(appointment);

    }


}


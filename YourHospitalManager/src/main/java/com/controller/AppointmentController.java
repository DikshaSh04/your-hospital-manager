package com.controller;

import com.dto.AppointmentRequestDto;
import com.model.Appointment;
import com.service.AppointmentService;
import com.service.DoctorService;
import com.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Appointments", description = "Manage appointment bookings")
@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @Operation(summary= "Get appointments by patient", description = "Retrieves all appointments associated with a given patient ID")
    @GetMapping("/patient/{patientId}")
    public List<Appointment> getAppointmentByPatient(@PathVariable Long patientId){
        return appointmentService.getAppointmentsByPatient(patientId);
    }

    @Operation(summary = "Cancel an appointment", description = "Updates the appointment status to CANCELLED.")
    @PutMapping("/{id}/cancel")
    public Appointment cancelAppointment(@PathVariable Long id){
        return appointmentService.cancelAppointment(id);
    }

    @Operation(summary = "Book an appointment", description = "Books a slot for a patient with a doctor. Returns 409 if slot is already taken.")
    @PostMapping
    public ResponseEntity<?> bookAppointment(@RequestBody AppointmentRequestDto request){
        try {
            Appointment appointment = new Appointment();
            appointment.setPatient(patientService.getPatientById(request.getPatientId()));
            appointment.setDoctor(doctorService.getDoctorById(request.getDoctorId()));
            appointment.setSlotDateTime(request.getSlotDateTime());

            Appointment saved = appointmentService.bookAppointment(appointment);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }


}


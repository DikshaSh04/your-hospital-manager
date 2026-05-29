package com.service;

import com.model.Appointment;
import com.model.Doctor;
import com.model.Status;
import com.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public Appointment getAppointmentById(Long id){
        return appointmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Appointment not found"));
    }

    public Appointment bookAppointment(Appointment appointment){
        if(appointmentRepository.existsByDoctorAndSlotDateTime(appointment.getDoctor(),appointment.getSlotDateTime())){
            throw new RuntimeException("Doctor already booked for this slot!");
        }
        else{
            return appointmentRepository.save(appointment);
        }
    }

    public Appointment cancelAppointment(Long id){
        Appointment A= getAppointmentById(id);
        A.setStatus(Status.CANCELLED);

        return appointmentRepository.save(A);
    }

    public List<Appointment> getAppointmentsByPatient(Long patientId) {
        return appointmentRepository.findByPatient_Id(patientId);
    }







}


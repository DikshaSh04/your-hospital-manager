package com.repository;

import com.model.Appointment;
import com.model.Doctor;
import com.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {

    boolean existsByDoctorAndSlotDateTime(Doctor doctor, LocalDateTime slotDateTime);

    List<Appointment> findByPatient_Id(Long patientId);


}

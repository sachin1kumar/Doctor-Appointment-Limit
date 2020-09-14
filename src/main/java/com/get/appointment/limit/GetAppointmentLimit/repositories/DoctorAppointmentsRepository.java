package com.get.appointment.limit.GetAppointmentLimit.repositories;

import com.get.appointment.limit.GetAppointmentLimit.model.DoctorAppointments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface DoctorAppointmentsRepository extends JpaRepository<DoctorAppointments, BigInteger> {
}

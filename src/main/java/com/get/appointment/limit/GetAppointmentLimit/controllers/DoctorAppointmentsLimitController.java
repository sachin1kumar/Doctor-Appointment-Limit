package com.get.appointment.limit.GetAppointmentLimit.controllers;

import com.get.appointment.limit.GetAppointmentLimit.model.DoctorAppointments;
import com.get.appointment.limit.GetAppointmentLimit.model.ResponseLimit;
import com.get.appointment.limit.GetAppointmentLimit.repositories.DoctorAppointmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/doctor-appointment-limit")
public class DoctorAppointmentsLimitController {

    @Autowired
    private DoctorAppointmentsRepository doctorAppointmentsRepository;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<ResponseLimit> getAppointmentLimit(@RequestBody DoctorAppointments doctorAppointments) {
        List<DoctorAppointments> listOfAppointments = doctorAppointmentsRepository.findAll();
        ResponseLimit responseLimit = new ResponseLimit();
        for (DoctorAppointments doctorAppointment : listOfAppointments) {
            if(doctorAppointment.getToday_date().equalsIgnoreCase(doctorAppointments.getToday_date()) &&
                    doctorAppointment.getId().equals(doctorAppointments.getId())) {
                responseLimit.setAppointment_limit(doctorAppointment.getAppointment_limit());
            }
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(responseLimit);
    }



}

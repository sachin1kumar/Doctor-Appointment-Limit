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
            String dataFromTable = "";
            if(doctorAppointment.getAppointment_date() != null){
                 dataFromTable = doctorAppointment.getAppointment_date().getDate()
                        +"-"+doctorAppointment.getAppointment_date().getMonth()
                        +"-"+doctorAppointment.getAppointment_date().getYear();
            }
            String dataFromRequest = doctorAppointments.getAppointment_date().getDate()
                    +"-"+doctorAppointments.getAppointment_date().getMonth()
                    +"-"+doctorAppointments.getAppointment_date().getYear();
            if(dataFromTable.equalsIgnoreCase(dataFromRequest) && doctorAppointment.getAppointment_date() != null &&
                    doctorAppointment.getDoctor_id().equals(doctorAppointments.getDoctor_id())) {
                responseLimit.setAppointment_limit(doctorAppointment.getAppointment_limit());
            }
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(responseLimit);
    }



}

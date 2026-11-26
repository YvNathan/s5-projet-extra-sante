package itu.s5.controller;

import java.util.List;

import org.springframework.context.ApplicationContext;

import itu.myframework.annotation.APIMethod;
import itu.myframework.annotation.Controller;
import itu.myframework.annotation.URL;
import itu.myframework.routing.ResponseEntity;
import itu.s5.model.Patient;
import itu.s5.service.PatientService;

@Controller
@URL("/api/patients")
public class PatientApiController {

    @APIMethod
    @URL("")
    public ResponseEntity<List<Patient>> getAll(ApplicationContext ctx) throws Exception {
        PatientService service = ctx.getBean(PatientService.class);
        return ResponseEntity.ok(service.findAll());
    }
}
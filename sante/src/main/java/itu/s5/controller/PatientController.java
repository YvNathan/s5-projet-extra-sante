package itu.s5.controller;

import java.util.List;

import org.springframework.context.ApplicationContext;

import itu.myframework.annotation.Controller;
import itu.myframework.annotation.PathVariable;
import itu.myframework.annotation.RequestModel;
import itu.myframework.annotation.URL;
import itu.myframework.routing.ModelAndView;
import itu.myframework.routing.RequestMethod;
import itu.s5.model.Patient;
import itu.s5.service.PatientService;

@Controller
@URL("/patients")
public class PatientController {

    @URL("")
    public ModelAndView list(ApplicationContext ctx) throws Exception {
        PatientService service = ctx.getBean(PatientService.class);
        List<Patient> patients = service.findAll();

        ModelAndView mv = new ModelAndView("patients/list");
        mv.addData("patients", patients);
        return mv;
    }

    @URL("/new")
    public ModelAndView createForm() {
        ModelAndView mv = new ModelAndView("patients/form");
        mv.addData("patient", new Patient());
        mv.addData("isEdit", false);
        return mv;
    }

    @URL(value = "", requestMethod = RequestMethod.POST)
    public ModelAndView create(@RequestModel Patient patient, ApplicationContext ctx) throws Exception {
        PatientService service = ctx.getBean(PatientService.class);
        try {
            service.create(patient);
            return new ModelAndView("redirect:/patients");
        } catch (IllegalArgumentException e) {
            ModelAndView mv = new ModelAndView("patients/form");
            mv.addData("patient", patient);
            mv.addData("isEdit", false);
            mv.addData("error", e.getMessage());
            return mv;
        }
    }

    @URL("/{id}/edit")
    public ModelAndView editForm(@PathVariable("id") Integer id, ApplicationContext ctx) throws Exception {
        PatientService service = ctx.getBean(PatientService.class);
        Patient patient = service.findById(id);
        if (patient == null) {
            return new ModelAndView("redirect:/patients");
        }
        ModelAndView mv = new ModelAndView("patients/form");
        mv.addData("patient", patient);
        mv.addData("isEdit", true);
        return mv;
    }

    @URL(value = "/{id}", requestMethod = RequestMethod.POST)
    public ModelAndView update(
            @PathVariable("id") Integer id,
            @RequestModel Patient patient,
            ApplicationContext ctx) throws Exception {
        PatientService service = ctx.getBean(PatientService.class);
        try {
            service.update(id, patient);
            return new ModelAndView("redirect:/patients");
        } catch (IllegalArgumentException e) {
            patient.setId(id);
            ModelAndView mv = new ModelAndView("patients/form");
            mv.addData("patient", patient);
            mv.addData("isEdit", true);
            mv.addData("error", e.getMessage());
            return mv;
        }
    }

    @URL(value = "/{id}/delete", requestMethod = RequestMethod.POST)
    public ModelAndView delete(@PathVariable("id") Integer id, ApplicationContext ctx) throws Exception {
        PatientService service = ctx.getBean(PatientService.class);
        service.delete(id);
        return new ModelAndView("redirect:/patients");
    }
}
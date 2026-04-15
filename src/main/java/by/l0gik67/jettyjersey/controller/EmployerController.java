package by.l0gik67.jettyjersey.controller;


import by.l0gik67.jettyjersey.service.EmployerService;
import by.l0gik67.jettyjersey.dto.Employer;
import by.l0gik67.jettyjersey.dto.EmployerCreation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

@RestController
@RequestMapping("/employer")
public class EmployerController {

    private final EmployerService employerService;

    @Autowired
    public EmployerController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @GetMapping
    public List<Employer> getEmployers() {
        return employerService.getAllEmployers();
    }

    @PostMapping
    public Employer createEmployer(@RequestBody EmployerCreation employerCreation) {
        return employerService.save(employerCreation);
    }

    @GetMapping("/{id}")
    public Employer getEmployer(@PathVariable Integer id) {
        return employerService.getEmployerById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employer not found"));
    }
}

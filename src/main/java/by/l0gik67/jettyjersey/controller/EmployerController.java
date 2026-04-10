package by.l0gik67.jettyjersey.controller;


import by.l0gik67.jettyjersey.dao.EmployerService;
import by.l0gik67.jettyjersey.dto.Employer;
import by.l0gik67.jettyjersey.dto.EmployerCreation;
import jakarta.inject.Inject;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/employer")
public class EmployerController {

    private final EmployerService employerService;

    @Inject
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
        return employerService.getEmployerById(id).orElse(null);
    }
}

package by.l0gik67.jettyjersey.controller;


import by.l0gik67.jettyjersey.dao.EmployerDao;
import by.l0gik67.jettyjersey.dto.Employer;
import by.l0gik67.jettyjersey.dto.EmployerCreation;
import jakarta.inject.Inject;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/employer")
public class EmployerController {

    private final EmployerDao employerDao;

    @Inject
    public EmployerController(EmployerDao employerDao) {
        this.employerDao = employerDao;
    }

    @GetMapping
    public List<Employer> getEmployers() {
        return employerDao.getAllEmployers();
    }

    @PostMapping
    public Employer createEmployer(@RequestBody EmployerCreation employerCreation) {
        return employerDao.save(employerCreation);
    }

    @GetMapping("/{id}")
    public Employer getEmployer(@PathVariable Integer id) {
        return employerDao.getEmployerById(id).orElse(null);
    }
}

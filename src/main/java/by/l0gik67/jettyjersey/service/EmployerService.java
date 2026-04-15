package by.l0gik67.jettyjersey.service;

import by.l0gik67.jettyjersey.dto.Employer;
import by.l0gik67.jettyjersey.dto.EmployerCreation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployerService {


    public List<Employer> getAllEmployers() {
        return List.of(new Employer(1, "Ivan"),
                new Employer(2, "Igor"),
                new Employer(3, "Patrick"));
    }

    public Employer save(EmployerCreation employerCreation) {
        return new Employer(4, employerCreation.name()); // метод заглушка чтобы просто сервер работал
    }

    public Optional<Employer> getEmployerById(Integer id) {
        if (id == 3) return Optional.of(new Employer(3, "Patrick"));
        return Optional.empty(); // опять метод заглушка
    }
}

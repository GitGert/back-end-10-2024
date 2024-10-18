package ee.gert.veebipood.controller;

import ee.gert.veebipood.entity.Person;
import ee.gert.veebipood.repository.PersonRepository;
import ee.gert.veebipood.service.PersonService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")

public class AuthController {

    @Autowired
    PersonService personService;


    @PostMapping("signup")
    public String signup(@RequestBody Person person){
        personService.savePerson(person);
        return "login security token";
    }
}

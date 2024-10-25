package ee.gert.veebipood.controller;

import ee.gert.veebipood.entity.Person;
import ee.gert.veebipood.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    PersonService personService;

    @PostMapping("signup")
    public String signup(@RequestBody Person person){
        personService.savePerson(person);
        return "login security token";
    }
}

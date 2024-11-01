package ee.gert.veebipood.controller;

import ee.gert.veebipood.entity.Person;
import ee.gert.veebipood.model.EmailPassword;
import ee.gert.veebipood.model.Token;
import ee.gert.veebipood.repository.PersonRepository;
import ee.gert.veebipood.service.AuthService;
import ee.gert.veebipood.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    PersonService personService;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    AuthService authService;

    @PostMapping("signup")
    public String signup(@RequestBody Person person){
        String encryptedPassword = encoder.encode(person.getPassword());
        person.setPassword(encryptedPassword);
        personService.savePerson(person);
        return "signup security token";
    }
    @PostMapping("login")
    public Token login(@RequestBody EmailPassword emailPassword){

        Person person = personRepository.findById(emailPassword.getEmail()).orElseThrow(); // orElsethrow because findbyId may be null. Alternative is to use Optional<Person> type.

        if (encoder.matches(emailPassword.getPassword(), person.getPassword())){
            return authService.getToken(person);
        }
        //FIXME: if we dont get token send a poor request response.
        return new Token();
    }
}
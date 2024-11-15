package ee.gert.veebipood.controller;

import ee.gert.veebipood.config.SecurityConfig;
import ee.gert.veebipood.entity.Person;
import ee.gert.veebipood.exception.ValidationException;
import ee.gert.veebipood.model.EmailPassword;
import ee.gert.veebipood.model.Token;
import ee.gert.veebipood.repository.PersonRepository;
import ee.gert.veebipood.service.AuthService;
import ee.gert.veebipood.service.PersonService;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public ResponseEntity<Token> signup(@RequestBody Person person) throws ValidationException {
        authService.validate(person);

        String encryptedPassword = encoder.encode(person.getPassword());
        person.setPassword(encryptedPassword);
        Person dbPerson = personService.savePerson(person);

        return ResponseEntity.ok().body(authService.getToken(dbPerson));
    }


    @PostMapping("login")
    public Token login(@RequestBody EmailPassword emailPassword){

        Person person = personRepository.findById(emailPassword.getEmail()).orElseThrow(); // orElsethrow because findbyId may be null. Alternative is to use Optional<Person> type.

        if (encoder.matches(emailPassword.getPassword(), person.getPassword())){
            return authService.getToken(person);
        }

        //FIXME: if we don't get token send a poor request response.

        return new Token();
    }

    @GetMapping("admin")
    public boolean isAdmin(){
        GrantedAuthority authority = new SimpleGrantedAuthority("admin");

        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(authority);
    }
}
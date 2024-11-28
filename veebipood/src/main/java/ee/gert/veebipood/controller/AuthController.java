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
import java.util.Optional;

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
    public Token login(@RequestBody EmailPassword emailPassword) throws ValidationException {

        Optional<Person> personOptional = personRepository.findById(emailPassword.getEmail()); // orElsethrow because findbyId may be null. Alternative is to use Optional<Person> type.

        if (!personOptional.isPresent()){
            throw new ValidationException("email pole korrektne");
        }
        Person person =personOptional.get();

        if (!encoder.matches(emailPassword.getPassword(), person.getPassword())){
            throw new ValidationException("password on vale");
        }

        return authService.getToken(person);
    }

    @GetMapping("admin")
    public boolean isAdmin(){
        GrantedAuthority authority = new SimpleGrantedAuthority("admin");

        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(authority);
    }
}
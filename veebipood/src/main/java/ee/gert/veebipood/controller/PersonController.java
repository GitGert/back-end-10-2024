package ee.gert.veebipood.controller;


import ee.gert.veebipood.dto.PersonAddressDTO;
import ee.gert.veebipood.dto.PersonDTO;
import ee.gert.veebipood.entity.Address;
import ee.gert.veebipood.entity.Person;
import ee.gert.veebipood.repository.PersonRepository;
import ee.gert.veebipood.service.PersonService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Log4j2
@RestController
//@CrossOrigin(origins = "http://localhost:3000")

public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonService personService;

    @GetMapping("/street/{street}")
    public List<PersonAddressDTO> getAddress(@PathVariable String street){
        log.info("error : {}", street);

        List<Person> dbPersons = personRepository.findByAddress_StreetContainsIgnoreCase(street);
        return personService.getPersonAddressDTO(dbPersons);
    }
}

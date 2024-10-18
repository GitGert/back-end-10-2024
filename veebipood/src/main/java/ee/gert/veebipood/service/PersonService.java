package ee.gert.veebipood.service;

import ee.gert.veebipood.entity.Address;
import ee.gert.veebipood.entity.Order;
import ee.gert.veebipood.entity.Person;
import ee.gert.veebipood.repository.AddressRepository;
import ee.gert.veebipood.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service // -> bean -> used for autoWire
public class PersonService {

    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;

    public PersonService(PersonRepository personRepository,
                         AddressRepository addressRepository) {
        this.personRepository = personRepository;
        this.addressRepository = addressRepository;
    }


    //@Transactional // kui yks päring falib revertitakse kõik teised
    public void savePerson(@RequestBody Person person){
        Address address = addressRepository.save(person.getAddress());
        person.setAddress(address);
        personRepository.save(person);
    }
}

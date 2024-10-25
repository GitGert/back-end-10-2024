package ee.gert.veebipood.service;

import ee.gert.veebipood.dto.PersonAddressDTO;
import ee.gert.veebipood.entity.Address;
import ee.gert.veebipood.entity.Person;
import ee.gert.veebipood.repository.PersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.List;

@Service // -> bean -> used for autoWire
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository){

        this.personRepository = personRepository;
    }


    //@Transactional // kui yks päring falib revertitakse kõik teised
    public void savePerson(@RequestBody Person person){
        personRepository.save(person);
    }


    @Autowired
    ModelMapper modelmapper;

    public List<PersonAddressDTO> getPersonAddressDTO(List<Person> dbPersons) {
//        List<PersonAddressDTO> personAddressDTOS = new ArrayList<>();
//        for (Person person : dbPersons){
//           PersonAddressDTO dto = new PersonAddressDTO();
//           dto.setAddress(person.getAddress());
//           dto.setFirstName(person.getFirstName());
//           dto.setLastName(person.getLastName());
//           personAddressDTOS.add(dto);
//        }
//        ModelMapper modelmapper = new ModelMapper();
        System.out.println(modelmapper);
        PersonAddressDTO[] personAddressDTOS = modelmapper.map(dbPersons, PersonAddressDTO[].class);
        return Arrays.asList(personAddressDTOS);
    }
}

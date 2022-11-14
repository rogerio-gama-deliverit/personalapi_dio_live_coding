package one.digitalinnovation.personapi.service;

import lombok.AllArgsConstructor;
import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.exception.PersonNotFoundException;
import one.digitalinnovation.personapi.mapper.PersonMapper;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service //informa que a classe será responsável pelas regras de negócio
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {
    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

//    @Autowired //indica ao spring para injetar uma implementação do tipo repository para dentro do método
//    public PersonService(PersonRepository personRepository) {
//        this.personRepository = personRepository;
//    } //n precisa pq lombok

    public MessageResponseDTO createPerson(PersonDTO personDTO){
        Person personToSave = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(personToSave);
        return createMessageResponse("Created person ", savedPerson);
    }

    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExists(id);
        Person personToUpdate = personMapper.toModel(personDTO);
        Person updatedPerson = personRepository.save(personToUpdate);
        return createMessageResponse("Updated person ", updatedPerson);
    }

    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = verifyIfExists(id);
        return personMapper.toDTO(person);
    }

    public void deleteById(Long id) throws PersonNotFoundException {
        verifyIfExists(id);
        personRepository.deleteById(id);
    }

    private static MessageResponseDTO createMessageResponse(String message, Person operationPerson) {
        return MessageResponseDTO
                .builder()
                .message(message + operationPerson.getFirstName() + " with ID " + operationPerson.getId())
                .build();
    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException {
//        Optional<Person> optionalPerson = personRepository.findById(id);
//        if(optionalPerson.isEmpty()){
//            throw new PersonNotFoundException(id);
//        }
//        return optionalPerson.get();

        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

}

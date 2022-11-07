package one.digitalinnovation.personapi.service;

import one.digitalinnovation.personapi.dto.MessageResponseDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //informa que a classe será responsável pelas regras de negócio
public class PersonService {
    private PersonRepository personRepository;

    @Autowired //indica ao spring para injetar uma implementação do tipo repository para dentro do método
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(Person person){
        Person savedPerson = personRepository.save(person);
        return MessageResponseDTO
                .builder()
                .message("Created person "+savedPerson.getFirstName()+" with ID "+savedPerson.getId())
                .build();
    }
}

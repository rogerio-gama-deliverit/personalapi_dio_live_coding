package one.digitalinnovation.personapi.controller;

import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController //indica que é um controlador acessado através de REST
@RequestMapping("/api/v1/people")//endereço da request (URI que vai depois do localhost:8080), basicamente o nome
public class PersonController {

    private PersonService personService;

    @Autowired //indica ao spring para injetar uma implementação do tipo repository para dentro do método
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO){ //requestbody informa que ta vindo uma requisição de pessoas
        return personService.createPerson(personDTO);
    }

    @GetMapping //operação http GET
    public String getBook(){
        return "API test funcionando!";
    }
}

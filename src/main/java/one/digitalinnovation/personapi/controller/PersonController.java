package one.digitalinnovation.personapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //indica que é um controlador acessado através de REST
@RequestMapping("/api/v1/people")//endereço da request (URI que vai depois do localhost:8080), basicamente o nome
public class PersonController {

    @GetMapping //operação http GET
    public String getBook(){
        return "API test!";
    }
}

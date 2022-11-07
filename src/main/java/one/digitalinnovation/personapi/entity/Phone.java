package one.digitalinnovation.personapi.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.digitalinnovation.personapi.enums.PhoneType;

import javax.persistence.*;

@Entity //anotação de entidade JPA
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //incrementa o id;
    private Long id;
    @Enumerated(EnumType.STRING) //informa o tipo como enum de string
    @Column(nullable = false) //cria campo como notnull
    private PhoneType type;
    @Column(nullable = false) //cria campo como notnull
    private String number;
}

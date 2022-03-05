package com.felipeshonorato.spring.data.orm;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String nome;
    private String cpf;
    private BigDecimal salario;
    private LocalDate dataContratacao;

    @OneToMany
    private Cargo cargo;

    

}

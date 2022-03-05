package com.felipeshonorato.spring.data.orm;

import javax.persistence.*;

@Entity
@Table(name = "unidade")
public class Unidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String descricao;
    private String endereco;

    @ManyToOne
    private Funcionario funcionario;



}

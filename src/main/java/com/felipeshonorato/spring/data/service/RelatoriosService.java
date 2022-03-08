package com.felipeshonorato.spring.data.service;

import com.felipeshonorato.spring.data.orm.Funcionario;
import com.felipeshonorato.spring.data.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class RelatoriosService {

    private Boolean system = true;
    private final FuncionarioRepository funcionarioRepository;

    public RelatoriosService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial (Scanner scanner) {
        while (system) {
            System.out.println("Qual ação que deseja executar?");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.println("1 - Buscar funcionario por nome");

            int action = scanner.nextInt();

            switch (action){
                case 1:
                    buscaFuncionarioNome(scanner);
                    break;

                default:
                    system = false;
                    break;
            }
        }
    }

    private void buscaFuncionarioNome(Scanner scanner){
        System.out.println("Digite o nome do funcionário que deseja pesquisar");
        String nome = scanner.next();
        List<Funcionario> list = funcionarioRepository.findByNome(nome);
        list.forEach(System.out::println);
    }

}

package com.felipeshonorato.spring.data.service;

import com.felipeshonorato.spring.data.orm.Funcionario;
import com.felipeshonorato.spring.data.orm.FuncionarioProjecao;
import com.felipeshonorato.spring.data.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatoriosService {

    private Boolean system = true;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final FuncionarioRepository funcionarioRepository;

    public RelatoriosService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial (Scanner scanner) {
        while (system) {
            System.out.println("Qual ação que deseja executar?");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.println("1 - Buscar funcionario por nome");
            System.out.println("2 - Buscar funcionario por nome, data contratacao e salario maior");
            System.out.println("3 - Buscar por data de contratação maior");
            System.out.println("4 - Pesquisar funcionario por salario");

            int action = scanner.nextInt();

            switch (action){
                case 1:
                    buscaFuncionarioNome(scanner);
                    break;
                case 2:
                    buscaFuncionarioNomeSalarioMaiorData(scanner);
                    break;
                case 3:
                    buscaFuncionarioDataContratacao(scanner);
                    break;
                case 4:
                    pesquisaFuncionarioSalario();
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

    private void buscaFuncionarioNomeSalarioMaiorData (Scanner scanner){
        System.out.println("Digite o nome do funcionário");
        String nome = scanner.next();

        System.out.println("Digite a data da contratação");
        String data = scanner.next();
        LocalDate localDate = LocalDate.parse(data, formatter);

        System.out.println("Digite o salário deseja pesquisar");
        BigDecimal salario = scanner.nextBigDecimal();

        List<Funcionario> list = funcionarioRepository.findNomeSalarioMaiorDataContratacao(nome, salario, localDate);
        list.forEach(System.out::println);
    }

    private void buscaFuncionarioDataContratacao(Scanner scanner){
        System.out.println("Digite a data da contratação");
        String data = scanner.next();
        LocalDate localDate = LocalDate.parse(data, formatter);

        List<Funcionario> list = funcionarioRepository.findDataContratacaoMaior(localDate);
        list.forEach(System.out::println);
    }

    private void pesquisaFuncionarioSalario(){
        List<FuncionarioProjecao> list = funcionarioRepository.findFuncionarioSalario();
        list.forEach(f -> System.out.println("Funcionário: id: " + f.getId() + "| nome: " + f.getNome() + "| salario: " + f.getSalario()));
    }

}

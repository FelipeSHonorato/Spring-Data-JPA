package com.felipeshonorato.spring.data.service;

import com.felipeshonorato.spring.data.orm.Cargo;
import com.felipeshonorato.spring.data.orm.Funcionario;
import com.felipeshonorato.spring.data.orm.UnidadeTrabalho;
import com.felipeshonorato.spring.data.repository.CargoRepository;
import com.felipeshonorato.spring.data.repository.FuncionarioRepository;
import com.felipeshonorato.spring.data.repository.UnidadeTrabalhoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class CrudFuncionarioService {

    private Boolean system = true;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final CargoRepository cargoRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;

    public CrudFuncionarioService(CargoRepository cargoRepository, FuncionarioRepository unidadeRepository, UnidadeTrabalhoRepository unidadeTrabalhoRepository){
        this.cargoRepository = cargoRepository;
        this.funcionarioRepository = unidadeRepository;
        this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
    }

    public void inicial (Scanner scanner) {
        while (system) {
            System.out.println("Qual ação de funcionarios deseja executar?");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.println("1 - Criar Novo Funcionário");
            System.out.println("2 - Modificar Funcionário");
            System.out.println("3 - Visualizar Funcionários");
            System.out.println("4 - Deletar Funcionário");

            int action = scanner.nextInt();

            switch (action){
                case 1:
                    salvar(scanner);
                    break;
                case 2:
                    atualizar(scanner);
                    break;
                case 3:
                    visualizar();
                    break;
                case 4:
                    deletar(scanner);
                    break;

                default:
                    system = false;
                    break;
            }
        }
    }

    private void salvar(Scanner scanner)  {
        System.out.println("Nome do funcionário");
        String nome = scanner.next();
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);

        System.out.println("Cpf do funcionário");
        String cpf = scanner.next();
        funcionario.setCpf(cpf);

        System.out.println("Salário do funcionário");
        BigDecimal salario = scanner.nextBigDecimal();
        funcionario.setSalario(salario);

        System.out.println("Data da contratação do funcionário (dd/mm/yyyy)");
        String dataContratacao = scanner.next();
        funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));

        System.out.println("Id do cargo do funcionário");
        Integer idCargo = scanner.nextInt();
        Optional<Cargo> cargo = cargoRepository.findById(idCargo);
        funcionario.setCargo(cargo.get());

        List<UnidadeTrabalho> unidades = unidade(scanner);

        funcionarioRepository.save(funcionario);
        System.out.println("Funcionário foi salvo com sucesso!");

    }

    private List<UnidadeTrabalho> unidade(Scanner scanner){
        Boolean isTrue = true;
        List<UnidadeTrabalho> unidades = new ArrayList<>();

        while (isTrue){
            System.out.println("Digite a unidadeId (Para sair digite 0)");
            Integer unidadeId = scanner.nextInt();

            if (unidadeId != 0){
                Optional<UnidadeTrabalho> unidade = unidadeTrabalhoRepository.findById(unidadeId);
                unidades.add(unidade.get());
            } else {
                isTrue = false;
            }
        }
        return unidades;
    }

    private void atualizar(Scanner scanner){
        Funcionario funcionario = new Funcionario();

        System.out.println("Digite o ID do funcionário");
        Integer idFuncionario = scanner.nextInt();
        funcionario.setId(idFuncionario);

        System.out.println("Nome um novo nome para o funcionário");
        String nome = scanner.next();

        funcionario.setNome(nome);

        System.out.println("Digite um novo cpf para o funcionário");
        String cpf = scanner.next();
        funcionario.setCpf(cpf);

        System.out.println("Digite um novo salário para o funcionário");
        BigDecimal salario = scanner.nextBigDecimal();
        funcionario.setSalario(salario);

        System.out.println("Digite uma nova data de contratação do funcionário (dd/mm/yyyy)");
        String dataContratacao = scanner.nextLine();
        funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));

        System.out.println("Digite um novo id do novo cargo do funcionário");
        Integer idCargo = scanner.nextInt();
        Optional<Cargo> cargo = cargoRepository.findById(idCargo);
        funcionario.setCargo(cargo.get());

        funcionarioRepository.save(funcionario);
        System.out.println("Alteração em funcionário foi salvo com sucesso!");
    }

    private void visualizar (){
        Iterable<Funcionario> funcionarios = funcionarioRepository.findAll();
        funcionarios.forEach(funcionario -> System.out.println(funcionario));
    }

    private void deletar(Scanner scanner){
        System.out.println("Informe o ID do funcionário");
        int id = scanner.nextInt();
        funcionarioRepository.deleteById(id);
        System.out.println("Funcionário deletado");
    }
}

package com.felipeshonorato.spring.data.source;

import com.felipeshonorato.spring.data.orm.Cargo;
import com.felipeshonorato.spring.data.repository.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudCargoService {

    private Boolean system = true;
    private final CargoRepository cargoRepository;

    public CrudCargoService(CargoRepository cargoRepository){
        this.cargoRepository = cargoRepository;

    }

    public void inicial (Scanner scanner) {
        while (system) {
            System.out.println("Qual ação de cargo deseja executar?");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.println("1 - Criar Novo Cargo");
            System.out.println("2 - Modificar Cargo");
            System.out.println("3 - Visualizar Cargos");
            System.out.println("4 - Deletar Cargo");

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

    private void salvar(Scanner scanner){
        System.out.println("Descrição do cargo");
        String descricao = scanner.next();
        Cargo cargo = new Cargo();
        cargo.setDescricao(descricao);
        cargoRepository.save(cargo);
        System.out.println("Cargo foi salvo com sucesso!");

    }

    private void atualizar(Scanner scanner){
        System.out.println("Digite o id de qual cargo quer modificar");
        int id = scanner.nextInt();

        System.out.println("Digite o novo nome do cargo");
        String descricao = scanner.next();

        Cargo cargo = new Cargo();
        cargo.setId(id);
        cargo.setDescricao(descricao);
        cargoRepository.save(cargo);

        System.out.println("Cargo foi atualizado com sucesso!");

    }

    private void visualizar (){
        Iterable<Cargo> cargos = cargoRepository.findAll();
        cargos.forEach(cargo -> System.out.println(cargo));
    }

    private void deletar(Scanner scanner){
        System.out.println("Informe o ID");
        int id = scanner.nextInt();
        cargoRepository.deleteById(id);
        System.out.println("Cargo deletado");
    }

}

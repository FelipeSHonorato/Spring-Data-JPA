package com.felipeshonorato.spring.data.source;

import com.felipeshonorato.spring.data.orm.UnidadeTrabalho;
import com.felipeshonorato.spring.data.repository.UnidadeTrabalhoRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudUnidadeTrabalhoService {

    private Boolean system = true;
    private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;

    public CrudUnidadeTrabalhoService(UnidadeTrabalhoRepository unidadeTrabalhoRepository){
        this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
    }

    public void inicial (Scanner scanner){
        while (system) {
            System.out.println("Qual ação de cargo deseja executar?");
            System.out.println("0 - Sair de Unidades de Trabalho");
            System.out.println("1 - Criar Nova Unidade de Trabalho");
            System.out.println("2 - Modificar Unidade de Trabalho");
            System.out.println("3 - Visualizar Unidades de Trabalho");
            System.out.println("4 - Deletar Unidade de Trabalho");

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
        salvar(scanner);
    }

    private void salvar(Scanner scanner){
        UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();

        System.out.println("Descrição da unidade de trabalho");
        String descricao = scanner.next();
        unidadeTrabalho.setDescricao(descricao);

        System.out.println("Endereço da unidade de trabalho");
        String endereco = scanner.next();
        unidadeTrabalho.setEndereco(endereco);

        unidadeTrabalhoRepository.save(unidadeTrabalho);

        System.out.println("Unidade de Trabalho foi salva com sucesso!");

    }

    private void atualizar(Scanner scanner){
        UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();

        System.out.println("Digite o id de qual unidade de trabalho que deseja modificar");
        int id = scanner.nextInt();
        unidadeTrabalho.setId(id);

        System.out.println("Digite o novo nome da unidade de trabalho");
        String nome = scanner.next();
        unidadeTrabalho.setDescricao(nome);

        System.out.println("Digite o novo endereço da unidade de trabalho");
        String endereco = scanner.next();
        unidadeTrabalho.setDescricao(endereco);

        unidadeTrabalhoRepository.save(unidadeTrabalho);

        System.out.println("Unidade de trabalho foi atualizada com sucesso!");

    }

    private void visualizar (){
        Iterable<UnidadeTrabalho> unidades = unidadeTrabalhoRepository.findAll();
        unidades.forEach(cargo -> System.out.println(unidades));
    }

    private void deletar(Scanner scanner){
        System.out.println("Informe o ID");
        int id = scanner.nextInt();
        unidadeTrabalhoRepository.deleteById(id);
        System.out.println("Unidade de trabalho deletada");
    }

}

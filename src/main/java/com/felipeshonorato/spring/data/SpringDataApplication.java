package com.felipeshonorato.spring.data;

import com.felipeshonorato.spring.data.service.CrudCargoService;
import com.felipeshonorato.spring.data.service.CrudFuncionarioService;
import com.felipeshonorato.spring.data.service.CrudUnidadeTrabalhoService;
import com.felipeshonorato.spring.data.service.RelatoriosService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import java.util.Scanner;

@EnableJpaRepositories
@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private Boolean system = true;

	private final CrudCargoService cargoService;
	private final CrudFuncionarioService funcionarioService;
	private final CrudUnidadeTrabalhoService unidadeTrabalhoService;
	private final RelatoriosService relatoriosService;

	public  SpringDataApplication(CrudCargoService cargoService, CrudFuncionarioService funcionarioService, CrudUnidadeTrabalhoService unidadeTrabalhoService, RelatoriosService relatoriosService){
		this.cargoService = cargoService;
		this.funcionarioService = funcionarioService;
		this.unidadeTrabalhoService = unidadeTrabalhoService;
		this.relatoriosService = relatoriosService;
	}

	public static void main(String[] args) {
		//Ação adquirida do CommandLineRunner onde o método run será exectuado logo no início da aplicação
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) {

		Scanner scanner = new Scanner(System.in);

		while(system){
			System.out.println("Qual ação a ser tomada?");
			System.out.println("0 - Sair da Aplicação");
			System.out.println("1 - Opções para Cargos");
			System.out.println("2 - Opções para Funcionários");
			System.out.println("3 - Opções para Unidades de Trabalho");
			System.out.println("4 - Consulta por relatórios");

			Integer function = scanner.nextInt();

			switch (function){
				case 1:
					cargoService.inicial(scanner);
					break;
				case 2:
					funcionarioService.inicial(scanner);
					break;
				case 3:
					unidadeTrabalhoService.inicial(scanner);
					break;
				case 4:
					relatoriosService.inicial(scanner);
					break;
				default:
					System.out.println("Programa Finalizado");
					system = false;
					break;
			}
		}
	}
}

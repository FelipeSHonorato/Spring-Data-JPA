package com.felipeshonorato.spring.data;

import com.felipeshonorato.spring.data.orm.Cargo;
import com.felipeshonorato.spring.data.repository.CargoRepository;
import com.felipeshonorato.spring.data.source.CrudCargoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private final CrudCargoService cargoService;

	private Boolean system = true;

	public  SpringDataApplication(CrudCargoService cargoService){
		this.cargoService = cargoService;
	}

	public static void main(String[] args) {

		//Ação adquirida do CommandLineRunner onde o método run será exectuado logo no início da aplicação
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Scanner scanner = new Scanner(System.in);

		while(system){
			System.out.println("Qual ação a ser tomada");
			System.out.println("0 - Sair da Aplicação");
			System.out.println("1 - Opções para Cargos");

			int action = scanner.nextInt();
			if(action == 1){
				cargoService.inicial(scanner);}
			else {
				system = false;
				System.out.println("Programa encerrado");
			}
		}
	}
}

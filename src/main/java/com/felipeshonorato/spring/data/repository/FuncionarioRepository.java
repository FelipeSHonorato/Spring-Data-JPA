package com.felipeshonorato.spring.data.repository;

import com.felipeshonorato.spring.data.orm.Funcionario;
import com.felipeshonorato.spring.data.orm.FuncionarioProjecao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Integer> {

    List<Funcionario> findByNome(String nome);

    /** Modelo abaixo seria para um filtro mais complexo onde seria procurado por nome e um salário maior que o passado e sua data de contratação

     * List<Funcionario> findByNomeAndSalarioGreaterThanAndDataContratacao(String nome, BigDecimal salario, LocalDate data);

     * Os nomes dos métodos nesses casos são primordiais para o Spring JPA faça por sí só a Query a ser utilizada no BD, porem podemos reduzir o nome, utilizando @Query **/

    @Query("SELECT f FROM Funcionario f WHERE f.nome = :nome AND f.salario >= :salario AND f.dataContratacao = :data")
    List<Funcionario> findNomeSalarioMaiorDataContratacao (String nome, BigDecimal salario, LocalDate data);

    /** Para fazermos consultas relacionais também pode ser utilizado o Derived Query Methods:
     *
     *  List<Funcionario> findByCargoPelaDescricao(String descricao);
     *
     *  ou
     *
     * @Query("SELECT f FROM Funcionario f JOIN f.cargo c WHERE c.descricao = :descricao")
     * List<Funcionario> metodoMenor(String descricao);
     *
     * Caso a entidade tenha um nome duplo como por exemplo UnidadeTrabalho, para utilizarmos o Derived Query nesse caso devemos utilizado underline para separar:
     *
     *  List<Funcionario> findByUnidadeTRabalho_Descricao(String descricao);
     *
     *  Que é a mesma coisa que a query abaixo:
     *
     * @Query("SELECT f FROM Funcionario f JOIN f.unidadeTrabalho u WHERE u.descricao = :descricao")
     *
     * Podemos também fazer uma Query utilizando a linguagem nativa do Banco de Dados, no caso MariaDB, para isso devemos efetuar o @Query da seguinte maneira:
     * **/

    @Query(value = "SELECT * FROM funcionarios f WHERE f.data_contratacao >= :data", nativeQuery = true)
    List<Funcionario> findDataContratacaoMaior(LocalDate data);

    //Retorno de pesquisa através de projeção, onde somente oq for inserido na Interface FuncionarioProjecao será demonstrado no relátorio final
    @Query(value= "SELECT f.id, f.nome, f.salario FROM funcionarios f", nativeQuery = true)
    List<FuncionarioProjecao> findFuncionarioSalario ();

}

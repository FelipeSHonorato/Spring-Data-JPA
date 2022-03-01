package com.felipeshonorato.spring.data.repository;

import com.felipeshonorato.spring.data.orm.Cargo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends CrudRepository<Cargo, Integer> {



}

package net.guides.springboot.crud.repository;

import net.guides.springboot.crud.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonRepository extends MongoRepository<Person, Long>{

}

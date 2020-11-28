package net.guides.springboot.crud.repository;

import net.guides.springboot.crud.model.Testassets;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TestassetsRepository extends MongoRepository<Testassets, Long>{

}

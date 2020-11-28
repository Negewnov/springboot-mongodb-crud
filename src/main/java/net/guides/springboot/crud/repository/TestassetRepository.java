package net.guides.springboot.crud.repository;

import net.guides.springboot.crud.model.Testasset;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TestassetRepository extends MongoRepository<Testasset, Long>{

}

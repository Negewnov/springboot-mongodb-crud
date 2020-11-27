package net.guides.springboot.crud.repository;

import net.guides.springboot.crud.model.Colour;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColourRepository extends MongoRepository<Colour, Long>{

}

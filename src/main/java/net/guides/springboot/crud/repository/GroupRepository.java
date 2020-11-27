package net.guides.springboot.crud.repository;

import net.guides.springboot.crud.model.Group;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends MongoRepository<Group, Long>{

}

package net.guides.springboot.crud.repository;

import net.guides.springboot.crud.model.Membership;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository extends MongoRepository<Membership, Long>{

}

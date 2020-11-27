package net.guides.springboot.crud.repository;

import net.guides.springboot.crud.model.Membership;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembershipRepository extends MongoRepository<Membership, Long>{

    List<Membership> findByPersonID(long id);

}

package com.example.account.repository;

import com.example.account.model.Compte;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompteRepository extends MongoRepository<Compte, String> {
    Compte findByRib(String rib);
    boolean existsByRib(String rib);

}

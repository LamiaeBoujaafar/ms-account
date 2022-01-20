package com.example.account.service.Impl;

import com.example.account.model.Compte;
import com.example.account.repository.CompteRepository;
import com.example.account.service.CompteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@AllArgsConstructor
@Service
public class CompteServiceImpl implements CompteService {

    final CompteRepository compteRepository;

    @Override
    public Compte delete(String id) {
        Compte account = compteRepository.findById(id).orElse(null);
        compteRepository.delete(account);
        return account;
    }

    @Override
    public List<Compte> findAll() {
        return compteRepository.findAll();
    }

    @Override
    public Compte findById(String id) {
        return compteRepository.findById(id).orElse(null);
    }

    @Override
    public Compte save(Compte compte) {
        if (compte == null) return null;
        return compteRepository.save(compte);
    }

    @Override
    public Compte update(Compte compte) {
        if (compte == null) return null;
        return compteRepository.save(compte);
    }

    @Override
    public Compte findByRIB(String rib) {
        return compteRepository.findByRib(rib);
    }

    @Override
    public Compte saveDefaultAccount() {
        Compte compte = new Compte();
        compte.setSolde((double) 0);
        compte.setRib(generateRib());
        compteRepository.save(compte);
        return compte;
    }

    @Override
    public Compte updateByRib(String rib,Double solde) {
        Compte compte = compteRepository.findByRib(rib);
        compte.setSolde(solde);
        compteRepository.save(compte);
        return compte;
    }


    public String generateNumber() {
        Random random = new Random();
        Long number = random.nextLong();
        if (number < 0) {
            number = -number;
        }
        return number.toString();
    }

    public String generateRib() {
        String rib;
        do {
            rib = generateNumber();
        } while (compteRepository.existsByRib(rib));
        return rib;
    }
}

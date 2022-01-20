package com.example.account.service;

import com.example.account.facades.*;
import com.example.account.model.Compte;

import java.util.List;

public interface CompteService extends Save<Compte>, Delete<String,Compte>, FindAll<List<Compte>>, Update<Compte>, FindById<Compte,String> {
    Compte findByRIB(String rib);
    Compte saveDefaultAccount();
    Compte updateByRib(String rib,Double solde);
}

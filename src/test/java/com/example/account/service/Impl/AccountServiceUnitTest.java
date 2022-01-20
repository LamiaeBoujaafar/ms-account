package com.example.account.service.Impl;

import com.example.account.model.Compte;
import com.example.account.repository.CompteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceUnitTest {
    @Mock
    private CompteRepository compteRepository;
    @InjectMocks
    private CompteServiceImpl compteService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void whenGivenId_shouldDeleteAccount_ifFound() {
        //GIVEN
        Compte compte = new Compte();
        compte.setId("125e896d");
        compte.setSolde((double) 100000);
        compte.setRib("125896321589");

        //WHEN
        when(compteRepository.findById(compte.getId())).thenReturn(Optional.of(compte));
        Compte expected = compteService.delete(compte.getId());

        //THEN
        Mockito.verify(compteRepository).delete(compte);

    }

    @Test
    public void should_throw_exception_when_account_doesnt_exist() {
        Compte compte = new Compte();
        compte.setId("125e896d");
        compte.setSolde((double) 100000);
        compte.setRib("125896321589");

        given(compteRepository.findById(anyString())).willReturn(Optional.ofNullable(null));
        compteService.delete(compte.getId());
    }

    @Test
    void shouldReturnAllAccounts() {
        //GIVEN
        List<Compte> users = new ArrayList();
        users.add(new Compte("125e896d", 100000.0, "125896321589"));

        //WHEN
        given(compteRepository.findAll()).willReturn(users);
        List<Compte> expected = compteService.findAll();

        //THEN
        assertEquals(expected, users);
        Mockito.verify(compteRepository).findAll();
    }

    @Test
    void whenGivenId_shouldReturnAccount_ifFound() {
        //GIVEN
        Compte compte = new Compte();
        compte.setId("125e896d");

        //WHEN
        when(compteRepository.findById(compte.getId())).thenReturn(Optional.of(compte));
        Compte expected = compteService.findById(compte.getId());

        //THEN
        assertThat(expected).isSameAs(compte);
        Mockito.verify(compteRepository).findById(compte.getId());
    }

    @Test
    void createAccount_whenPostMethod() {

        //GIVEN
        Compte compte = new Compte();
        compte.setSolde((double) 100000);
        compte.setRib("125896321589");

        //WHEN
        when(compteRepository.save(any(Compte.class))).then(returnsFirstArg());
        Compte savedAccount = compteService.save(compte);

        //THEN
        assertThat(savedAccount.getSolde()).isSameAs(compte.getSolde());
        assertThat(savedAccount.getRib()).isSameAs(compte.getRib());
    }

    @Test
    void whenGivenAccount_shouldUpdate_ifFound() {
        //GIVEN
        Compte compte = new Compte();
        compte.setId("125e896d");
        compte.setSolde((double) 100000);
        compte.setRib("125896321589");

        Compte newCompte = new Compte();
        newCompte.setId("125e896d");
        newCompte.setSolde((double) 120000);
        newCompte.setRib("125896321589");

        when(compteRepository.save(any(Compte.class))).then(returnsFirstArg());
        Compte expected = compteService.update(newCompte);

        //THEN
        assertThat(expected.getId()).isSameAs(newCompte.getId());
        assertThat(expected.getRib()).isSameAs(newCompte.getRib());
        assertThat(expected.getSolde()).isSameAs(newCompte.getSolde());
    }


    @Test
    void whenGivenRib_shouldReturnAccount_ifFound() {

        //GIVEN
        Compte compte = new Compte();
        compte.setId("125e896d");
        compte.setSolde((double) 100000);
        compte.setRib("125896321589");

        //WHEN
        when(compteRepository.findByRib(compte.getRib())).thenReturn(compte);
        Compte expected = compteService.findByRIB(compte.getRib());

        //THEN
        assertThat(expected).isSameAs(compte);
        Mockito.verify(compteRepository).findByRib(compte.getRib());
    }


}
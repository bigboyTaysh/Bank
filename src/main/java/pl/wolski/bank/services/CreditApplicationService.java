package pl.wolski.bank.services;


import pl.wolski.bank.models.CreditApplication;

import java.util.List;

public interface CreditApplicationService {
// Własne metody
    void save(CreditApplication creditApplication, String username);

    List<CreditApplication> findAll();
}

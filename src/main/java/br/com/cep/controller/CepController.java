package br.com.cep.controller;

import br.com.cep.model.Cep;
import br.com.cep.repository.CepRepository;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cep")
public class CepController {

    @Autowired
    CepRepository cepRepository;

    @GetMapping("/{cep}")
    public Cep findCep(@PathVariable(value = "cep") String cep) {
        try {
            Cep getCep = cepRepository.findById(cep).get();
            return getCep;
        } catch (NoSuchElementException ex) {
            return Cep.cadastraCep(cep, cepRepository);
        }
    }
}

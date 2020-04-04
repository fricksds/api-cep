package br.com.cep.controller;

import br.com.cep.model.Cep;
import br.com.cep.repository.CepRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class CepController {

    @Autowired
    CepRepository cepRepository;

    @GetMapping("cep/{cep}")
    public Cep findCep(@PathVariable(value = "cep") String cep) {
        try {
            Cep getCep = cepRepository.findById(String.format("%s-%s",
                    cep.substring(0, 5), cep.substring(5))).get();
            
            return getCep;
            
        } catch (NoSuchElementException ex) {
            return Cep.cadastraCep(cep, cepRepository);
        }
    }

    @GetMapping("/ceps")
    public List<String> findCeps(@RequestParam("ibge") String ibge,
            @RequestParam("uf") String uf) {
        return cepRepository.findCepsByIBGEandUF(ibge, uf).get();
    }
}

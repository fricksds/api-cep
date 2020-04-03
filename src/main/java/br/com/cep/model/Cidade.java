package br.com.cep.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TCidade")
@SuppressWarnings("unused")
public class Cidade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String ibge;

    private String uf;

    private String localidade;

    @OneToMany(mappedBy = "cidade", cascade = CascadeType.ALL)
    private List<Cep> ceps;

    public Cidade() {
    }

    public Cidade(String ibge, String uf, String localidade) {
        this.ibge = ibge;
        this.uf = uf;
        this.localidade = localidade;
    }

    public List<Cep> getCeps() {
        return ceps;
    }
}

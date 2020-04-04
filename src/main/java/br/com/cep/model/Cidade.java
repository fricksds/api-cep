package br.com.cep.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "TCidade")
@SuppressWarnings("unused")
public class Cidade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String ibge;

    private String uf;

    private String localidade;

    @OneToMany(mappedBy = "cidade", fetch = FetchType.LAZY)
    private List<Cep> ceps;

    public Cidade() {
    }

    public Cidade(String ibge, String uf, String localidade) {
        this.ibge = ibge;
        this.uf = uf;
        this.localidade = localidade;
    }

    public String getIbge() {
        return ibge;
    }

    public void setIbge(String ibge) {
        this.ibge = ibge;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public List<Cep> getCeps() {
        return ceps;
    }

    public void setCeps(List<Cep> ceps) {
        this.ceps = ceps;
    }

    
}

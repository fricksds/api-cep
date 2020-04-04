package br.com.cep.model;

import br.com.cep.repository.CepRepository;
import br.com.cep.util.ConnectionHttp;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.json.JSONObject;

@Entity
@Table(name = "TCep")
@SuppressWarnings("unused")
public class Cep implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String cep;

    private String logradouro;

    private String complemento;

    private String bairro;

    @JoinColumn
    @ManyToOne(cascade = CascadeType.ALL)
    private Cidade cidade;

    public Cep() {
    }

    public Cep(String cep, String logradouro, String complemento, String bairro, Cidade cidade) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    /**
     * É invocado quando o cep buscado não for encontrado na base de dados.
     * Chama o método para pesquisa na base de dados da ViaCep e o cadastra em
     * seguida.
     *
     * @param cep cep que o usuário está buscando.
     * @param cepRepository objeto para persistência no banco.
     * @return se não houver falha na comunicação e se o cep for encontrado na
     * ViaCep, então retorna o Cep encontrado, ou retorna null caso contrário.
     * @author renan.santos
     */
    public static Cep cadastraCep(String cep, CepRepository cepRepository) {
        // define os dados
        String respostaConnectionHttp = ConnectionHttp.getHttpGET(cep);

        if (respostaConnectionHttp == null) {
            return null;
        }

        JSONObject obj = new JSONObject(respostaConnectionHttp);

        if (!obj.has("erro")) {
            Cidade novaCidade = new Cidade(obj.getString("ibge"),
                    obj.getString("uf"),
                    obj.getString("localidade"));

            Cep novoCEP = new Cep(obj.getString("cep"),
                    obj.getString("logradouro"),
                    obj.getString("complemento"),
                    obj.getString("bairro"),
                    novaCidade);

            //novaCidade.getCeps().add(novoCEP);
            
            // cepRepository.save(novoCEP);

            return novoCEP;
        }

        return null;
    }
}

package br.com.cep.model;

import br.com.cep.repository.CepRepository;
import br.com.cep.util.ConnectionHttp;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.json.JSONObject;

@Entity
@Table(name = "TCep")
@SuppressWarnings("unused")
public class Cep implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String cep;

    private String logradouro;

    private String complemento;

    private String bairro;

    @ManyToOne(fetch = FetchType.LAZY)
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

            cepRepository.save(novoCEP);

            return novoCEP;
        }

        return null;
    }
}

package br.com.cep.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.springframework.web.util.UriUtils;

/**
 *
 * @author renan.santos
 */
public class ConnectionHttp {

    /**
     * Nétodo para enviar a requisição de dados do CEP
     *
     * @param cep cep que o usuário está buscando.
     * @return string do json dos dados da ViaCep
     */
    public static String getHttpGET(String cep) {
        String urlCep = "http://viacep.com.br/ws/" + cep + "/json/unicode/";
        StringBuilder result = new StringBuilder();

        try {
            URL url = new URL(urlCep);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(UriUtils.decode(line, "ISO-8859-1"));
            }

        } catch (IOException ex) {
            return null;
        }

        return result.toString();
    }

}

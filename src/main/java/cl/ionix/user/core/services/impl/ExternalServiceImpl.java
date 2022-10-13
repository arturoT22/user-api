package cl.ionix.user.core.services.impl;

import cl.ionix.user.core.services.ExternalService;
import cl.ionix.user.util.CommonUtilities;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service

public class ExternalServiceImpl implements ExternalService {

    @Autowired
    RestTemplate template;

    String urlExternalService = "https://my.api.mockaroo.com/test-tecnico/search";

    @Override
    public String search(String param) {
        String encryptedParam = CommonUtilities.cipher(param);
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", "f2f719e0");
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        try{
            ResponseEntity<Object> response = template.exchange(
                    urlExternalService+"/"+ encryptedParam, HttpMethod.GET, requestEntity, Object.class);
            return response.getBody().toString();
        }
        catch(Exception e){
            return "error"+e;
        }
    }
}

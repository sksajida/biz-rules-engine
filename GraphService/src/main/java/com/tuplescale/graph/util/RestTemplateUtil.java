package com.tuplescale.graph.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
public class RestTemplateUtil {

    @Autowired
    RestTemplate restTemplate;

    public <T> ResponseEntity<T> postRequest(String url,
                                             @Nullable HttpHeaders headers,
                                             Object requestBody,
                                             Class<T> responseType) {
        if (Objects.isNull(headers)) {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
        }
        HttpEntity request = new HttpEntity(requestBody, headers);
        return restTemplate.postForEntity(url, request, responseType);
    }
}

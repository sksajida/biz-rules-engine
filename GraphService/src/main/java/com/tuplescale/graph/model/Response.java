package com.tuplescale.graph.model;

import lombok.*;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Response<T> {
    private HttpStatus status;
    private T body;
    private String reasons;

    public Response(HttpStatus status, T body) {
        this.status = status;
        this.body = body;
    }
}

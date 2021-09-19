package com.tuplescale.graph.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class JobPostVo {
    private String status;
    private String jobId;
    private String error;
}

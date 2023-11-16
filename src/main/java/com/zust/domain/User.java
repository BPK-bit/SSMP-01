package com.zust.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private Integer id;
    private String name;
    private String password;
}

package com.zust.domain.VO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserVO {
    private Integer id;
    private String name;
    private String password;
    private String token;
}
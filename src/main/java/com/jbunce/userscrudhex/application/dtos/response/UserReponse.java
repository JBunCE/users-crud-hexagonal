package com.jbunce.userscrudhex.application.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserReponse {  
    public String id;
    public String name;
    public String email;
    public String createdAt;
}

package com.mgbt.socialapp_backend.model.entity;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;

@Data
public class Chat  implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    
    private String question;
    
    private String answer = "";
    
    private Boolean isHarmful = null;
    
    private String harmfulType = "";
}

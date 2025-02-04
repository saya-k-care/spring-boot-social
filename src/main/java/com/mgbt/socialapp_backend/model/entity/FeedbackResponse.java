package com.mgbt.socialapp_backend.model.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
public class FeedbackResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    
    private String question = "";
    
    private String answer = "";
    
    private Boolean isValidApplicationSuggestion = null;
    
    private Boolean isHarmful = null;
    
    private String harmfulType = "";

}

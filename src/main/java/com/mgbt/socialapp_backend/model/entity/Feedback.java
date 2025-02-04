package com.mgbt.socialapp_backend.model.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.io.*;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@Data
@Entity
@Table(name = "feedback")
public class Feedback implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_feedback")
    private Long id;

    @Column(name = "type")
    private Integer feedbackType;
    
    @Column(name = "msg", length = 2000)
    private String msg;

    @Column(name = "creation_date", insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","description",
            "photo","creationDate","deletionDate","status"})
    @JoinColumn(name = "id_user", nullable = false)
    private UserApp user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_learning", nullable = false)
    private Learning learning;
    
    @JsonInclude()
    @Transient
    private FeedbackResponse feedbackResponse;
}

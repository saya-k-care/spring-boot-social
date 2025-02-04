package com.mgbt.socialapp_backend.model.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.io.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity
@Table(name = "learning")
public class Learning implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_learning")
    private Long id;

    @Column(name = "ques", length = 2000)
    private String ques;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","description",
            "photo","creationDate","deletionDate","status"})
    @JoinColumn(name = "id_post")
    private Post post;
}

package com.mgbt.socialapp_backend.model.entity.notification;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mgbt.socialapp_backend.model.entity.Post;
import lombok.*;
import jakarta.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DiscriminatorValue("like_post_type")
public class NotificationLikePost extends Notification {

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","text","photo","date","user","likes"})
    @JoinColumn(name = "id_post")
    private Post post;

    @Override
    public String getType() {
        return "like_post_type";
    }
}

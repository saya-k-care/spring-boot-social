package com.mgbt.socialapp_backend.model.entity.notification;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mgbt.socialapp_backend.model.entity.*;
import lombok.*;
import jakarta.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DiscriminatorValue("comment_type")
@NoArgsConstructor
public class NotificationComment extends Notification {

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","text","post","date","hasReplies"})
    @JoinColumn(name = "id_comment")
    private Comment comment;

    public NotificationComment(UserApp userReceiver, Comment comment) {
        super(userReceiver);
        this.comment = comment;
    }

    @Override
    public String getType() {
        return "comment_type";
    }
}

package com.mgbt.socialapp_backend.model.entity.notification;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mgbt.socialapp_backend.model.entity.UserApp;
import lombok.*;
import jakarta.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DiscriminatorValue("friend_type")
@NoArgsConstructor
public class NotificationFriend extends Notification {

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","username","description",
            "creationDate","deletionDate","photo","isChecked","status"})
    @JoinColumn(name = "id_user_friend")
    private UserApp friend;

    public NotificationFriend(UserApp userReceiver, UserApp friend) {
        super(userReceiver);
        this.friend = friend;
    }

    @Override
    public String getType() {
        return "friend_type";
    }
}

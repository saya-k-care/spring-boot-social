package com.mgbt.socialapp_backend.model.repository;

import com.mgbt.socialapp_backend.model.entity.UserApp;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.List;

@Component
@Repository
public interface IUserRepository extends JpaRepository<UserApp, Long> {
    UserApp findByUsername(String username);

    @Query(value = "SELECT * FROM users u WHERE " +
            "(u.name LIKE CONCAT('%',?1,'%') OR u.surname LIKE CONCAT('%',?1 ,'%')) AND " +
            "u.username NOT LIKE CONCAT('%',?2,'%') AND " +
            "u.deletion_date IS NULL " +
            "LIMIT 0,5",
            nativeQuery = true)
    List<UserApp> filter(String name, String keycloakUsername);

    @Query(value = "SELECT * FROM users u WHERE " +
            "(u.name LIKE CONCAT('%',?1,'%') OR u.surname LIKE CONCAT('%',?1 ,'%')) AND " +
            "u.username NOT LIKE CONCAT('%',?2,'%') AND " +
            "u.deletion_date IS NULL",
            nativeQuery = true)
    List<UserApp> filterWithoutLimit(String name, String keycloakUsername);

    @Query(value = "SELECT u.* FROM users u " +
            "INNER JOIN friendships f ON u.id_user = f.id_user_transmitter " +
            "OR u.id_user = f.id_user_receiver " +
            "WHERE (f.id_user_transmitter = ?1 OR f.id_user_receiver = ?1) " +
            "AND u.id_user != ?1 AND f.status = 1",
            nativeQuery = true)
    List<UserApp> findFriendsByUser(Long idUser);

    @Query(value = "SELECT u.* FROM users u " +
            "INNER JOIN followerships f ON u.id_user = f.id_user_follower " +
            "WHERE f.id_user_checked = ?",
            nativeQuery = true)
    List<UserApp> findFollowersByUser(Long idUser);

    @Query(value = "SELECT u.* FROM users u " +
            "INNER JOIN followerships f ON u.id_user = f.id_user_checked " +
            "WHERE f.id_user_follower = ?",
            nativeQuery = true)
    List<UserApp> findFollowingByUser(Long idUser);

    @Query(value = "SELECT u.* FROM users u " +
            "INNER JOIN friendships f ON u.id_user = f.id_user_transmitter " +
            "OR u.id_user = f.id_user_receiver " +
            "WHERE (f.id_user_transmitter = ?1 OR f.id_user_receiver = ?1) " +
            "AND (f.id_user_transmitter != ?2 AND f.id_user_receiver != ?2) " +
            "AND u.id_user != ?1 AND f.status = 1 " +
            "ORDER BY RAND() LIMIT 5",
            nativeQuery = true)
    List<UserApp> findUsersYouMayKnowByUser(Long idUser, Long idKeycloakUser);

    Page<UserApp> findByNameContainingOrSurnameContainingOrUsernameContaining(String name, String surname, String username, Pageable pageable);

    @Query(value = "SELECT * FROM users WHERE deletion_date IS NOT NULL " +
            "AND DATEDIFF(current_timestamp(), deletion_date) >= 14",
            nativeQuery = true)
    List<UserApp> findByDeletionDateIsNotNull();
}

package com.mgbt.socialapp_backend.model.service;

import com.mgbt.socialapp_backend.model.entity.*;
import com.mgbt.socialapp_backend.model.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Component
@Service("userDetailsService")
public class UserService implements IService<UserApp> {

    @Autowired
    IUserRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<UserApp> toList() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public UserApp save(UserApp entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional
    public void delete(UserApp entity) {
        repository.delete(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public UserApp findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public UserApp findByUsername(String username) { return repository.findByUsername(username); }

    @Transactional
    public UserApp checkIfUserIsPersisted(UserApp userFound, UserApp userFromToken) {
        if (!userFound.getName().equals(userFromToken.getName()) ||
                !userFound.getSurname().equals(userFromToken.getSurname())) {
            userFound.setName(userFromToken.getName());
            userFound.setSurname(userFromToken.getSurname());
            this.save(userFound);
        }
        return userFound;
    }

    @Transactional(readOnly = true)
    public List<UserApp> filter(String name, String keycloakUsername) {
        return repository.filter(name, keycloakUsername);
    }

    @Transactional(readOnly = true)
    public List<UserApp> filterWithoutLimit(String name, String keycloakUsername) {
        return repository.filterWithoutLimit(name, keycloakUsername);
    }

    @Transactional(readOnly = true)
    public List<UserApp> getFriends(Long idUser) {
        return repository.findFriendsByUser(idUser);
    }

    @Transactional(readOnly = true)
    public List<UserApp> getFollowers(Long idUser) {
        return repository.findFollowersByUser(idUser);
    }

    @Transactional(readOnly = true)
    public List<UserApp> getFollowing(Long idUser) {
        return repository.findFollowingByUser(idUser);
    }

    @Transactional(readOnly = true)
    public List<UserApp> getUsersYouMayKnow(Long idUser, Long idKeycloakUser) {
        return repository.findUsersYouMayKnowByUser(idUser, idKeycloakUser);
    }

    @Transactional(readOnly = true)
    public Page<UserApp> getByNameOrSurnameOrUsername(String name, Pageable pageable) {
        return repository.findByNameContainingOrSurnameContainingOrUsernameContaining(name, name, name, pageable);
    }

    @Transactional(readOnly = true)
    public List<UserApp> getUsersWhoseDeletionDateIsNotNull() {
        return repository.findByDeletionDateIsNotNull();
    }
    
    public UserApp getJWTUser() {
    	
    	Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    	if (obj instanceof Jwt) {
    	    Jwt jwt = (Jwt) obj;
    	    // Extract email from the JWT claims (assuming email is present as a claim)
    	    String email = jwt.getClaimAsString("email");
            
            UserApp userFound = findByUsername(email);
            return userFound;

    	}
    	return null;
    	
    }
}

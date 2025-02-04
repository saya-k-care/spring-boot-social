package com.edw.config;


import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.ChatClient.CallResponseSpec;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mgbt.socialapp_backend.model.entity.Feedback;
import com.mgbt.socialapp_backend.model.entity.FeedbackResponse;
import com.mgbt.socialapp_backend.model.entity.Learning;
import com.mgbt.socialapp_backend.model.entity.UserApp;
import com.mgbt.socialapp_backend.model.service.ChatService;
import com.mgbt.socialapp_backend.model.service.FeedbackService;
import com.mgbt.socialapp_backend.model.service.LearningService;
import com.mgbt.socialapp_backend.model.service.UserService;


@RestController
@RequestMapping(value = "/api/foos")
public class FooController {
	
	@Autowired
	private ChatService chatService;
	
    @Autowired
    private UserService userService;
    
    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private LearningService learningService;
    
    @Autowired
    MessageSource messageSource;

    private final ChatClient chatClient = null;

    @GetMapping(value = "/askAI")
    public ResponseEntity<?> askAI() throws JsonProcessingException {
        
    	Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    	if (obj instanceof Jwt) {
    	    Jwt jwt = (Jwt) obj;
    	    // Extract email from the JWT claims (assuming email is present as a claim)
    	    String email = jwt.getClaimAsString("email");
    	    System.out.println("email from JWT: " + email);
    	    
            System.out.println("email-->" + email);
            
            UserApp userFound = userService.findByUsername(email);
            
            Learning learning = new Learning();
            learning.setQues("test");
            
            try {
            	learningService.save(learning);
            } catch (Exception e) {
            	System.out.println(e);
            }
            
            
    	} else if (obj instanceof OAuth2User) {
    	    OAuth2User oauth2User = (OAuth2User) obj;
    	    // Extract email from OAuth2User's attributes
    	    String email = oauth2User.getAttribute("email");
    	    System.out.println("Email from OAuth2User: " + email);
    	} else {
    	    System.out.println("Principal is neither a Jwt nor an OAuth2User.");
    	}
    	
    	//OAuth2User user = ((OAuth2User)SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        

        Map<String, Object> response = new HashMap<>();
        try {
        	ObjectMapper objectMapper = new ObjectMapper();
        	String aString = objectMapper.writeValueAsString(learningService.toList());
            response.put("messages", aString);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            response.put("message", messageSource.getMessage("error.database", null, null));
            response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/learning")
    public ResponseEntity<?> getQues() throws JsonProcessingException {
        
    	Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    	if (obj instanceof Jwt) {
    	    Jwt jwt = (Jwt) obj;
    	    // Extract email from the JWT claims (assuming email is present as a claim)
    	    String email = jwt.getClaimAsString("email");
    	    System.out.println("email from JWT: " + email);
    	    
            System.out.println("email-->" + email);
            
            UserApp userFound = userService.findByUsername(email);
            
            Learning learning = new Learning();
            learning.setQues("test");
            
            try {
            	learningService.save(learning);
            } catch (Exception e) {
            	System.out.println(e);
            }
            
            
    	} else if (obj instanceof OAuth2User) {
    	    OAuth2User oauth2User = (OAuth2User) obj;
    	    // Extract email from OAuth2User's attributes
    	    String email = oauth2User.getAttribute("email");
    	    System.out.println("Email from OAuth2User: " + email);
    	} else {
    	    System.out.println("Principal is neither a Jwt nor an OAuth2User.");
    	}
    	
    	//OAuth2User user = ((OAuth2User)SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        System.out.println(" --- AI--> " +  chatService.chatGeneralFeedback("hello"));

        Map<String, Object> response = new HashMap<>();
        try {
        	ObjectMapper objectMapper = new ObjectMapper();
        	String aString = objectMapper.writeValueAsString(learningService.toList());
            response.put("messages", aString);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            response.put("message", messageSource.getMessage("error.database", null, null));
            response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        
    }
    
   // @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id) throws JsonProcessingException {
        
    	Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    	if (obj instanceof Jwt) {
    	    Jwt jwt = (Jwt) obj;
    	    // Extract email from the JWT claims (assuming email is present as a claim)
    	    String email = jwt.getClaimAsString("email");
    	    System.out.println("email from JWT: " + email);
    	    
            System.out.println("email-->" + email);
            
            UserApp userFound = userService.findByUsername(email);
            
            Feedback feedback = new Feedback();
            feedback.setUser(userFound);
            
            try {
                feedbackService.save(feedback);
            } catch (Exception e) {
            	System.out.println(e);
            }
            
            
    	} else if (obj instanceof OAuth2User) {
    	    OAuth2User oauth2User = (OAuth2User) obj;
    	    // Extract email from OAuth2User's attributes
    	    String email = oauth2User.getAttribute("email");
    	    System.out.println("Email from OAuth2User: " + email);
    	} else {
    	    System.out.println("Principal is neither a Jwt nor an OAuth2User.");
    	}
    	
    	//OAuth2User user = ((OAuth2User)SecurityContextHolder.getContext().getAuthentication().getPrincipal());

    	
        Map<String, Object> response = new HashMap<>();
        try {
        	ObjectMapper objectMapper = new ObjectMapper();
        	String aString = objectMapper.writeValueAsString(feedbackService.toList());
            response.put("messages", aString);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            response.put("message", messageSource.getMessage("error.database", null, null));
            response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    @PostMapping("/feedback/update")
    public ResponseEntity<?> updateFeedback(@RequestBody Feedback feedback) throws JsonProcessingException {
    	
    	System.out.println("feedback-->" + feedback);
    	
    	UserApp userApp = userService.getJWTUser();
    	
    	System.out.println("userApp-->" + userApp.getName());
    	
    	FeedbackResponse feedbackResponse = chatService.chatGeneralFeedback(feedback.getMsg());
    	//ChatResponse responseAI = call.chatResponse();
    	//call.chatResponse().
    	System.out.println(" --- AI--> " +  feedbackResponse);
    	
    	
    	feedback.setUser(userApp);
    	Feedback feedbackSaved = feedbackService.save(feedback);
    	feedbackSaved.setFeedbackResponse(feedbackResponse);
    	Map<String, Object> response = new HashMap<>();
        if (feedbackSaved != null) {
            //response.put("message", messageSource.getMessage("appController.login.userFound", null, locale));
            response.put("status", HttpStatus.OK.value());
            response.put("feedback", feedbackSaved);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
        	response.put("message", messageSource.getMessage("error.database", null, null));
            response.put("error", "Error saving feedback");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserApp user, Locale locale) {
    	System.out.println("login");
        UserApp userFound = userService.findByUsername(user.getUsername());
        Map<String, Object> response = new HashMap<>();
        System.out.println("yserfound-->" + userFound.getName());
        if (userFound != null) {
            userFound = userService.checkIfUserIsPersisted(userFound, user); //Needed if the user updates their first and last name from Keycloak
            //response.put("message", messageSource.getMessage("appController.login.userFound", null, locale));
            response.put("status", HttpStatus.OK.value());
            response.put("user", userFound);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            userService.save(user);
            //response.put("message", messageSource.getMessage("appController.login.userCreated", null, locale));
            response.put("status", HttpStatus.CREATED.value());
            response.put("user", user);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
    }
 
}
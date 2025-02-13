package com.mgbt.socialapp_backend.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mgbt.socialapp_backend.model.entity.Feedback;
import com.mgbt.socialapp_backend.model.entity.FeedbackResponse;
import com.mgbt.socialapp_backend.model.entity.UserApp;
import com.mgbt.socialapp_backend.model.service.FeedbackService;
import com.mgbt.socialapp_backend.model.service.LearningService;
import com.mgbt.socialapp_backend.model.service.UserService;

@RestController
@RequestMapping("api/feedbacks/")
@PreAuthorize("isAuthenticated()")
public class FeedbackController {

	private static final Logger logger = LoggerFactory.getLogger(FeedbackController.class);

	//@Autowired
	//private ChatService chatService;
	
    @Autowired
    private UserService userService;
    
    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private LearningService learningService;
    
    @Autowired
    MessageSource messageSource;
    
    @PostMapping("/update")
    public ResponseEntity<?> updateFeedback(@RequestBody Feedback feedback) throws JsonProcessingException {
    	
    	if (feedback.getUser() == null || feedback.getUser().getIdUser() == null) {
        	UserApp userApp = userService.getJWTUser();
        	
        	feedback.setUser(userApp);
    	}

    	logger.info("user ID feedback:" + feedback.getUser().getIdUser());
    	//FeedbackResponse feedbackResponse = chatService.chatGeneralFeedback(feedback.getMsg());
    	
    	feedback.setAnswer(feedback.getFeedbackResponse().getAnswer());
    	Feedback feedbackSaved = feedbackService.save(feedback);
    	//feedbackSaved.setFeedbackResponse(feedback.getFeedbackResponse()
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

}

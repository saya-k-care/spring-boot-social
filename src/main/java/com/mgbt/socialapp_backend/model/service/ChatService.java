package com.mgbt.socialapp_backend.model.service;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mgbt.socialapp_backend.model.entity.FeedbackResponse;

@Service
public class ChatService {

	
	private final ChatClient chatClient;

    public ChatService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public FeedbackResponse chatGeneralFeedback(String message) throws JsonProcessingException {
    	
    	FeedbackResponse feedbackResponse = new FeedbackResponse();
    	
    	ObjectMapper objectMapper = new ObjectMapper();
    	feedbackResponse.setQuestion(message);
    	String aString = objectMapper.writeValueAsString(feedbackResponse);

    	System.out.println("feedbackAI-->" + aString);

        String content = this.chatClient.prompt()
        .user("fill in the blank, do not change the json elements and return in this json format only for java processing " + aString)
        .call().content();
        
        System.out.println("content-->" + content);
        
        String regex = "```json\\s*(.*?)\\s*```";

        // Create a Pattern object
        Pattern pattern = Pattern.compile(regex);

        // Create a Matcher object
        Matcher matcher = pattern.matcher(content);
        
        if (matcher.find()) {
            // Extract the matched group (the JSON content)
            String extractedJson = matcher.group(1); 

            System.out.println("Extracted JSON: " + extractedJson); 
            FeedbackResponse aiResponse = objectMapper.readValue(extractedJson, FeedbackResponse.class);
            System.out.println ("aiResponse-->" + aiResponse.getAnswer());

            return aiResponse;
        } else {
            System.out.println("No JSON content found within backticks.");
        }
        
        return null;
    }
    
    public static void main (String args[]) {
        // Path to the file (replace with your desired path)
        Path filePath = Paths.get("output.json"); 

        try {
            // Write the string to the file


            // Read the string from the file
            String readString = Files.readString(filePath);
            System.out.println("Read from file: " + readString); 
            
            String regex = "```json\\s*(.*?)\\s*```";

            // Create a Pattern object
            Pattern pattern = Pattern.compile(regex);

            // Create a Matcher object
            Matcher matcher = pattern.matcher(readString);
            
            if (matcher.find()) {
                // Extract the matched group (the JSON content)
                String extractedJson = matcher.group(1); 

                System.out.println("Extracted JSON: " + extractedJson); 
              //  FeedbackAI aiResponse = objectMapper.readValue(extractedJson, FeedbackAI.class);
             //   System.out.println ("aiResponse-->" + aiResponse.getAnswer());
            } else {
                System.out.println("No JSON content found within backticks.");
            }
            
            

        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
        
    }

}
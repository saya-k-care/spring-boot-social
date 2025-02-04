package com.mgbt.socialapp_backend.utility_classes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AIMessageUtil {

	public static String extractAIRawJSON(String msg) {
        String regex = "```json\\s*(.*?)\\s*```";

        // Create a Pattern object
        Pattern pattern = Pattern.compile(regex);

        // Create a Matcher object
        Matcher matcher = pattern.matcher(msg);
        
        if (matcher.find()) {
            // Extract the matched group (the JSON content)
            return matcher.group(1); 
        } 
        return msg;
	}
}

/*
 * package com.mgbt.socialapp_backend.model.service;
 * 
 * import java.io.IOException; import java.nio.file.Files; import
 * java.nio.file.Path; import java.nio.file.Paths; import
 * java.util.regex.Matcher; import java.util.regex.Pattern;
 * 
 * import org.slf4j.Logger; import org.slf4j.LoggerFactory; import
 * org.springframework.ai.chat.client.ChatClient; import
 * org.springframework.ai.chat.client.ChatClient.CallResponseSpec; import
 * org.springframework.ai.chat.metadata.RateLimit; import
 * org.springframework.ai.chat.metadata.Usage; import
 * org.springframework.ai.chat.model.ChatResponse; import
 * org.springframework.stereotype.Service;
 * 
 * import com.fasterxml.jackson.core.JsonProcessingException; import
 * com.fasterxml.jackson.databind.ObjectMapper; import
 * com.fasterxml.jackson.databind.json.JsonMapper; import
 * com.google.api.cloudquotas.v1.CloudQuotasClient; import
 * com.google.api.cloudquotas.v1.GetQuotaInfoRequest; import
 * com.google.api.cloudquotas.v1.QuotaInfo; import
 * com.mgbt.socialapp_backend.model.entity.Chat; import
 * com.mgbt.socialapp_backend.model.entity.FeedbackResponse; import
 * com.mgbt.socialapp_backend.utility_classes.AIMessageUtil;
 * 
 * @Service public class ChatService {
 * 
 * private static final Logger logger =
 * LoggerFactory.getLogger(ChatService.class);
 * 
 * private final ChatClient chatClient;
 * 
 * public ChatService(ChatClient.Builder chatClientBuilder) { this.chatClient =
 * chatClientBuilder.build(); }
 * 
 * public Chat generalChat(Chat chat) throws Exception {
 * 
 * CallResponseSpec callResponseSpec; ObjectMapper objectMapper = new
 * ObjectMapper(); String content = null; try {
 * //ChatService.syncGetQuotaInfo(); String aString =
 * objectMapper.writeValueAsString(chat);
 * 
 * 
 * callResponseSpec = this.chatClient.prompt().user(
 * "fill in the blank, do not change the json elements and return in this json format only for java processing "
 * + aString) .call();
 * 
 * ChatResponse chatResponse2 = callResponseSpec.chatResponse(); RateLimit
 * rateLimit = chatResponse2.getMetadata().getRateLimit(); Usage usage =
 * chatResponse2.getMetadata().getUsage(); ObjectMapper mapper =
 * JsonMapper.builder().findAndAddModules().build();
 * 
 * String rateLimitString = mapper.writeValueAsString(rateLimit); String
 * usageString = mapper.writeValueAsString(usage);
 * 
 * logger.info("rateLimitString = {} ", rateLimitString);
 * logger.info("usageString = {} ", usageString); content =
 * callResponseSpec.content(); } catch (RuntimeException e) {
 * logger.error("ResourceExhaustedException {} ", e.getCause()); throw new
 * Exception(e); } catch (Exception e) { logger.error("general exception {} ",
 * e.getCause()); throw new Exception(e); } String response =
 * AIMessageUtil.extractAIRawJSON(content);
 * 
 * if (response != null) {
 * 
 * Chat chatResponse = objectMapper.readValue(response, Chat.class);
 * 
 * return chatResponse; } else {
 * logger.info("No JSON content found within backticks."); }
 * 
 * return null; }
 * 
 * public FeedbackResponse chatGeneralFeedback(String message) throws
 * JsonProcessingException {
 * 
 * FeedbackResponse feedbackResponse = new FeedbackResponse();
 * 
 * ObjectMapper objectMapper = new ObjectMapper();
 * feedbackResponse.setQuestion(message); String aString =
 * objectMapper.writeValueAsString(feedbackResponse);
 * 
 * String content = this.chatClient.prompt().user(
 * "fill in the blank, do not change the json elements and return in this json format only for java processing "
 * + aString) .call().content();
 * 
 * String response = AIMessageUtil.extractAIRawJSON(content);
 * 
 * if (response != null) {
 * 
 * FeedbackResponse aiResponse = objectMapper.readValue(response,
 * FeedbackResponse.class);
 * 
 * return aiResponse; } else {
 * logger.info("No JSON content found within backticks."); }
 * 
 * return null; }
 * 
 * public static void syncGetQuotaInfo() throws Exception { // This snippet has
 * been automatically generated and should be regarded as a code template only.
 * // It will require modifications to work: // - It may require
 * correct/in-range values for request initialization. // - It may require
 * specifying regional endpoints when creating the service client as shown in //
 * https://cloud.google.com/java/docs/setup#
 * configure_endpoints_for_the_client_library try (CloudQuotasClient
 * cloudQuotasClient = CloudQuotasClient.create()) { GetQuotaInfoRequest request
 * = GetQuotaInfoRequest.newBuilder() // .setName( //
 * QuotaInfoName.ofProjectLocationServiceQuotaInfoName( //
 * "vernal-foundry-444909-j6", "asia-southeast1", "aiplatform.googleapis.com",
 * "LIMIT") // .toString()) .build(); QuotaInfo response =
 * cloudQuotasClient.getQuotaInfo(request);
 * 
 * logger.info("response" + response); } }
 * 
 * public static void main(String args[]) throws Exception { // Path to the file
 * (replace with your desired path) Path filePath = Paths.get("output.json");
 * 
 * try { ChatService.syncGetQuotaInfo();
 * 
 * // Write the string to the file
 * 
 * // Read the string from the file String readString =
 * Files.readString(filePath); System.out.println("Read from file: " +
 * readString);
 * 
 * String regex = "```json\\s*(.*?)\\s*```";
 * 
 * // Create a Pattern object Pattern pattern = Pattern.compile(regex);
 * 
 * // Create a Matcher object Matcher matcher = pattern.matcher(readString);
 * 
 * if (matcher.find()) { // Extract the matched group (the JSON content) String
 * extractedJson = matcher.group(1);
 * 
 * System.out.println("Extracted JSON: " + extractedJson); // FeedbackAI
 * aiResponse = objectMapper.readValue(extractedJson, // FeedbackAI.class); //
 * System.out.println ("aiResponse-->" + aiResponse.getAnswer()); } else {
 * System.out.println("No JSON content found within backticks."); }
 * 
 * } catch (IOException e) { System.err.println("An error occurred: " +
 * e.getMessage()); }
 * 
 * }
 * 
 * }
 */
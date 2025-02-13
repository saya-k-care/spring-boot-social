/*
 * package com.mgbt.socialapp_backend.controller;
 * 
 * import java.util.HashMap; import java.util.Map;
 * 
 * import org.slf4j.Logger; import org.slf4j.LoggerFactory; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.context.MessageSource; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.security.access.prepost.PreAuthorize; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import com.mgbt.socialapp_backend.model.entity.Chat; import
 * com.mgbt.socialapp_backend.model.service.ChatService;
 * 
 * @RestController
 * 
 * @RequestMapping("api/chat/")
 * 
 * @PreAuthorize("isAuthenticated()") public class ChatController {
 * 
 * private static final Logger logger =
 * LoggerFactory.getLogger(ChatController.class);
 * 
 * @Autowired private ChatService chatService;
 * 
 * @Autowired MessageSource messageSource;
 * 
 * @PostMapping("/general") public ResponseEntity<?> generalChat(@RequestBody
 * Chat chat) throws Exception {
 * 
 * chat = chatService.generalChat(chat);
 * 
 * Map<String, Object> response = new HashMap<>(); if (chat.getAnswer() != null)
 * { response.put("status", HttpStatus.OK.value()); response.put("chat", chat);
 * return new ResponseEntity<>(response, HttpStatus.OK); } else {
 * response.put("message", messageSource.getMessage("error.database", null,
 * null)); response.put("error", "Error saving feedback"); return new
 * ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR); }
 * 
 * } }
 */
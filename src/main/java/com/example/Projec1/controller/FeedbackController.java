package com.example.Projec1.controller;

import com.example.Projec1.dao.FeedbackALLResponse;
import com.example.Projec1.dao.FeedbackResponse;
import com.example.Projec1.entity.Feedback;
import com.example.Projec1.services.IFeedbackSevices;
import com.example.Projec1.util.AuthMiddleware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/book_store/feedback")
@RestController
public class FeedbackController {

    @Autowired
    IFeedbackSevices feedbackSevices;

    @CrossOrigin(origins = "http://localhost:60517")
    @GetMapping("/{Bid}")
    public FeedbackALLResponse showFeedbackById(@RequestHeader("Authorization") String token,@PathVariable Long Bid){
        String role = AuthMiddleware.getRoleFromToken(token);
        if(role.equals("user") || role.equals("admin")){
            return feedbackSevices.showFeedbackById(Bid);
        }
        return new FeedbackALLResponse(HttpStatus.BAD_REQUEST,null,"You are not the authorised user to access this ..");
    }

    @CrossOrigin(origins = "http://localhost:60517")
    @PostMapping("/add/{Bid}")
    public FeedbackResponse addFeedback(@RequestHeader("Authorization") String token, @RequestBody Feedback body,@PathVariable Long Bid){
        Long id = AuthMiddleware.getIdFromToken(token);
        String role = AuthMiddleware.getRoleFromToken(token);
        if(role.equals("user")){
            FeedbackResponse feedbackResponse = feedbackSevices.addFeedback(id, body, Bid);
            return feedbackResponse;
        }
        return new FeedbackResponse(HttpStatus.BAD_REQUEST,null,"You are not authorised");
    }
}

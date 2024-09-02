package com.example.Projec1.services;

import com.example.Projec1.dao.FeedbackALLResponse;
import com.example.Projec1.dao.FeedbackResponse;
import com.example.Projec1.entity.Feedback;

public interface IFeedbackSevices {
    FeedbackResponse addFeedback(Long id, Feedback body, Long Bid);
    FeedbackALLResponse showFeedbackById(Long Bid);
}

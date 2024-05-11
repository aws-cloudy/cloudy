package com.s207.cloudy.domain.chatbot.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;

public class ChatId {
    private String userId;
    private String regAt;

    @DynamoDBHashKey
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @DynamoDBRangeKey
    public String getRegAt() {
        return regAt;
    }

    public void setRegAt(String regAt) {
        this.regAt = regAt;
    }
}

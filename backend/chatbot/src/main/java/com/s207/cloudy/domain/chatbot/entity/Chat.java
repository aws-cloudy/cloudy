package com.s207.cloudy.domain.chatbot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@DynamoDbBean
public class Chat {
    private String userId;

    private String regAt;
    private boolean isUserSent;
    private String content;

    @DynamoDbPartitionKey
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setRegAt(String regAt) {
        this.regAt = regAt;
    }

    @DynamoDbAttribute("isUserSent")
    public boolean getIsUserSent() {
        return isUserSent;
    }

    public void setUserSent(boolean userSent) {
        isUserSent = userSent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @DynamoDbSortKey
    public String getRegAt() {
        return regAt;
    }

}


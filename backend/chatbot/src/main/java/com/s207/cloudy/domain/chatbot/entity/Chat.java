package com.s207.cloudy.domain.chatbot.entity;

//import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
//import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
//import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
//import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "Chat")
public class Chat {
    @Id
    private ChatId chatId;
    @DynamoDBAttribute(attributeName = "isUserSent")
    private Boolean isUserSent;
    @DynamoDBAttribute
    private String content;

    @DynamoDBHashKey
    public String getUserId() {
        return chatId.getUserId();
    }

    public void setUserId(String userId) {
        if (chatId == null) {
            chatId = new ChatId();
        }
        chatId.setUserId(userId);
    }

    @DynamoDBRangeKey
    public String getRegAt() {
        return chatId.getRegAt();
    }

    public void setRegAt(String regAt) {
        chatId.setRegAt(regAt);
    }

    public Boolean getUserSent() {
        return isUserSent;
    }

    public void setUserSent(Boolean userSent) {
        isUserSent = userSent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}


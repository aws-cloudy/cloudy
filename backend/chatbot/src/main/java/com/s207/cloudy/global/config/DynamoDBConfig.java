//package com.s207.cloudy.global.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
//import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
//import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
//import software.amazon.awssdk.regions.Region;
//import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
//
//import java.net.URI;
//
//@Configuration
//public class DynamoDBConfig {
//    @Value("${aws.dynamodb.accessKey}")
//    private String accessKey;
//
//    @Value("${aws.dynamodb.secretKey}")
//    private String secretKey;
//
//    @Value("${aws.dynamodb.endpoint}")
//    private String endpoint;
//
//    @Bean
//    public DynamoDbClient getDynamoDbClient() {
//        return DynamoDbClient.builder()
//                .endpointOverride(URI.create(endpoint))
//                .region(Region.AP_NORTHEAST_2)
//                .credentialsProvider(StaticCredentialsProvider.create(
//                        AwsBasicCredentials.create(accessKey, secretKey)))
//                .build();
//    }
//
//    @Bean
//    public DynamoDbEnhancedClient getDynamoDbEnhancedClient() {
//        return DynamoDbEnhancedClient.builder()
//                .dynamoDbClient(getDynamoDbClient())
//                .build();
//    }
//}

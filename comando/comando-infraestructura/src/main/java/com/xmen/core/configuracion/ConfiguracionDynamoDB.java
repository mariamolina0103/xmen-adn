package com.xmen.core.configuracion;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!test")
@Configuration
@EnableDynamoDBRepositories(basePackages = "com.xmen.core.repositorio")
public class ConfiguracionDynamoDB {

    @Value("${amazon.aws.accesskey}")
    private String amazonAccessKey;

    @Value("${amazon.aws.secretkey}")
    private String amazonSecretKey;

    public AWSCredentialsProvider awsCredentialsProvider()
    {
        return new AWSStaticCredentialsProvider(amazonAWSCredentials());
    }

    @Bean
    public AWSCredentials amazonAWSCredentials()
    {
        return new BasicAWSCredentials(amazonAccessKey, amazonSecretKey);
    }

    @Bean
    public AmazonDynamoDB amazonDynamoDB()
    {
        return AmazonDynamoDBClientBuilder.standard().withCredentials(awsCredentialsProvider()).withRegion(Regions.US_EAST_1).build();
    }

}

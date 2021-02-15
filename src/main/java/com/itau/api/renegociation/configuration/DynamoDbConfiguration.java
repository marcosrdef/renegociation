package com.itau.api.renegociation.configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itau.api.renegociation.model.CustomerModel;
import com.itau.api.renegociation.model.DebtModel;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.itau.api.renegociation")
public class DynamoDbConfiguration {

    @Value("${amazon.dynamodb.endpoint}")
    private String amazonDynamoDBEndpoint;

    @Value("${amazon.dynamodb.region}")
    private String amazonAWSRegion;

    @Value("${amazon.dynamodb.key}")
    private String amazonKey;

    @Value("${amazon.dynamodb.secret}")
    private String amazonSecret;


    @Bean
    public AmazonDynamoDB amazonDynamoDB() throws IOException {
        AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(amazonAWSCredentials()))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(amazonDynamoDBEndpoint, amazonAWSRegion))
                .build();

        DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

        createTableCustomers(amazonDynamoDB, dynamoDBMapper);
        createTableDebtModel(amazonDynamoDB, dynamoDBMapper);
        createTableSimulation(amazonDynamoDB, dynamoDBMapper);
        createTableRenegociation(amazonDynamoDB, dynamoDBMapper);
        createTableOffersCustomer(amazonDynamoDB, dynamoDBMapper);

        return amazonDynamoDB;
    }

    @Bean
    public AWSCredentials amazonAWSCredentials() {
        return new BasicAWSCredentials(
                amazonKey, amazonSecret);
    }

    private void createTableRenegociation(AmazonDynamoDB amazonDynamoDB
            ,DynamoDBMapper dynamoDBMapper) {
        CreateTableRequest tableRequest = dynamoDBMapper
                .generateCreateTableRequest(com.itau.api.renegociation.model.EffectiveRenegociationModel.class);
        tableRequest.setProvisionedThroughput(
                new ProvisionedThroughput(1L, 1L));
        TableUtils.createTableIfNotExists(amazonDynamoDB, tableRequest);
    }



    private void createTableSimulation(AmazonDynamoDB amazonDynamoDB
            ,DynamoDBMapper dynamoDBMapper) {
        CreateTableRequest tableRequest = dynamoDBMapper
                .generateCreateTableRequest(com.itau.api.renegociation.model.SimulationModel.class);
        tableRequest.setProvisionedThroughput(
                new ProvisionedThroughput(1L, 1L));
        TableUtils.createTableIfNotExists(amazonDynamoDB, tableRequest);
    }

    private void createTableOffersCustomer(AmazonDynamoDB amazonDynamoDB
            ,DynamoDBMapper dynamoDBMapper) {
        CreateTableRequest tableRequest = dynamoDBMapper
                .generateCreateTableRequest(com.itau.api.renegociation.model.OffersCustomerModel.class);
        tableRequest.setProvisionedThroughput(
                new ProvisionedThroughput(1L, 1L));
        TableUtils.createTableIfNotExists(amazonDynamoDB, tableRequest);
    }

    private void createTableCustomers(AmazonDynamoDB amazonDynamoDB
            ,DynamoDBMapper dynamoDBMapper) throws IOException {
        CreateTableRequest tableRequest = dynamoDBMapper
                .generateCreateTableRequest(CustomerModel.class);
        tableRequest.setProvisionedThroughput(
                new ProvisionedThroughput(1L, 1L));
        if(TableUtils.createTableIfNotExists(amazonDynamoDB, tableRequest)) {
            ClassLoader classLoader = getClass().getClassLoader();
            byte[] dataArr = FileCopyUtils.copyToByteArray(classLoader.getResourceAsStream("customers.json"));
            String content = new String(dataArr, StandardCharsets.UTF_8);
            List<CustomerModel> listCustomers= new ObjectMapper().readValue(content, new TypeReference<List<CustomerModel>>(){});
            if (!listCustomers.isEmpty()) {
                dynamoDBMapper.batchSave(listCustomers);
            }


        }
    }

    private void createTableDebtModel(AmazonDynamoDB amazonDynamoDB
            ,DynamoDBMapper dynamoDBMapper) throws IOException {
        CreateTableRequest tableRequest = dynamoDBMapper
                .generateCreateTableRequest(DebtModel.class);
        tableRequest.setProvisionedThroughput(
                new ProvisionedThroughput(1L, 1L));
        if(TableUtils.createTableIfNotExists(amazonDynamoDB, tableRequest)) {
            ClassLoader classLoader = getClass().getClassLoader();
            byte[] dataArr = FileCopyUtils.copyToByteArray(classLoader.getResourceAsStream("debts.json"));
            String content = new String(dataArr, StandardCharsets.UTF_8);
            List<DebtModel> listDebts= new ObjectMapper().readValue(content, new TypeReference<List<DebtModel>>(){});
            if (!listDebts.isEmpty()) {
                dynamoDBMapper.batchSave(listDebts);
            }

        }
    }

}

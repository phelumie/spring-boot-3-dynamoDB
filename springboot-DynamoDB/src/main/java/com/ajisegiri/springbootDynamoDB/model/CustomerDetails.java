package com.ajisegiri.springbootDynamoDB.model;


import com.ajisegiri.springbootDynamoDB.converter.LocalDateConverter;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDate;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBDocument
public class CustomerDetails implements Serializable{
    @DynamoDBAttribute
    @DynamoDBTypeConverted(converter = LocalDateConverter.class)
    private LocalDate dob;
    @DynamoDBAttribute
    private String occupation;
    @DynamoDBAttribute
    private String maritalStatus;
    @DynamoDBAttribute
    private String disability;


}

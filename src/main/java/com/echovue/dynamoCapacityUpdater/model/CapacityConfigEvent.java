package com.echovue.dynamoCapacityUpdater.model;

import com.amazonaws.services.lambda.runtime.events.ConfigEvent;

public class CapacityConfigEvent extends ConfigEvent {
    private String dynamoDBTableName;
    private Long readCapacityUnits;
    private Long writeCapacityUnits;

    public String getDynamoDBTableName() {
        return dynamoDBTableName;
    }

    public void setDynamoDBTableName(String dynamoDBTableName) {
        this.dynamoDBTableName = dynamoDBTableName;
    }

    public Long getReadCapacityUnits() {
        return readCapacityUnits;
    }

    public void setReadCapacityUnits(Long readCapacityUnits) {
        this.readCapacityUnits = readCapacityUnits;
    }

    public Long getWriteCapacityUnits() {
        return writeCapacityUnits;
    }

    public void setWriteCapacityUnits(Long writeCapacityUnits) {
        this.writeCapacityUnits = writeCapacityUnits;
    }
}

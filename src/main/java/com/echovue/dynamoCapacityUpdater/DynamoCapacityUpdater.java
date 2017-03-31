package com.echovue.dynamoCapacityUpdater;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.echovue.dynamoCapacityUpdater.model.CapacityConfigEvent;

public class DynamoCapacityUpdater implements RequestHandler<CapacityConfigEvent, String> {
    private AmazonDynamoDBClient amazonDynamoDBClient;
    private DynamoDB dynamoDB;

    @Override
    public String handleRequest(CapacityConfigEvent event, Context context) {

        Table table = getDynamoDB().getTable(event.getDynamoDBTableName());
        ProvisionedThroughput provisionedThroughput = new ProvisionedThroughput()
                .withReadCapacityUnits(event.getReadCapacityUnits())
                .withWriteCapacityUnits(event.getWriteCapacityUnits());
        table.updateTable(provisionedThroughput);

        return "Scaled Table (" + event.getDynamoDBTableName()
                + ") to [" + event.getReadCapacityUnits() + ":"
                + event.getWriteCapacityUnits() + "]";
    }

    private DynamoDB getDynamoDB() {
        if (amazonDynamoDBClient == null || dynamoDB == null) {
            amazonDynamoDBClient = new AmazonDynamoDBClient();
            dynamoDB = new DynamoDB(amazonDynamoDBClient);
        }
        return dynamoDB;
    }
}

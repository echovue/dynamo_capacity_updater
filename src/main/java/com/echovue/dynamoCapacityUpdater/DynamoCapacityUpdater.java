package com.echovue.dynamoCapacityUpdater;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.echovue.dynamoCapacityUpdater.model.CapacityConfigEvent;

public class DynamoCapacityUpdater implements RequestHandler<CapacityConfigEvent, String> {
    private AmazonDynamoDBClient amazonDynamoDBClient;
    private DynamoDB dynamoDB;
    private Region region = Region.getRegion(Regions.US_WEST_2);

    @Override
    public String handleRequest(CapacityConfigEvent event, Context context) {
        LambdaLogger logger = context.getLogger();
        Table table = getDynamoDB().getTable(event.getDynamoDBTableName());
        ProvisionedThroughput provisionedThroughput = new ProvisionedThroughput()
                .withReadCapacityUnits(event.getReadCapacityUnits())
                .withWriteCapacityUnits(event.getWriteCapacityUnits());
        logger.log("Updating " + event.getDynamoDBTableName() + " in region " +
                region.toString());
        table.updateTable(provisionedThroughput);

        return "Scaled Table (" + event.getDynamoDBTableName()
                + ") to [" + event.getReadCapacityUnits() + ":"
                + event.getWriteCapacityUnits() + "]";
    }

    private DynamoDB getDynamoDB() {
        if (amazonDynamoDBClient == null || dynamoDB == null) {
            amazonDynamoDBClient = new AmazonDynamoDBClient().withRegion(region);
            dynamoDB = new DynamoDB(amazonDynamoDBClient);
        }
        return dynamoDB;
    }
}

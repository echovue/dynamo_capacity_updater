package com.echovue.dynamoCapacityUpdater;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DynamoCapacityUpdaterTest {
    @Test
    public void testValidator() throws Exception {
        Context context = mock(Context.class);
        LambdaLogger logger = mock(LambdaLogger.class);
        when(context.getLogger()).thenReturn(logger);
        DynamoCapacityUpdater dynamoCapacityUpdater = new DynamoCapacityUpdater();
/*        DynamodbEvent ddbEvent = new DynamodbEvent();
        ddbEvent.setRecords(getInsertRecord());
        String result = addressValidator.handleRequest(ddbEvent, context);
        assertEquals("Validated 1 records.", result);*/
    }
}
package com.chtrembl.petstore.order.api;

import com.azure.cosmos.*;
import com.azure.cosmos.models.*;
import com.azure.cosmos.util.CosmosPagedIterable;
import com.chtrembl.petstore.order.model.Order;

import java.util.*;

public class CosmosOrderService {
    private final CosmosClient cosmosClient;
    private final CosmosContainer container;

    public CosmosOrderService(String endpoint, String key, String databaseName, String containerName) {
        this.cosmosClient = new CosmosClientBuilder()
                .endpoint(endpoint)
                .key(key)
                .consistencyLevel(ConsistencyLevel.EVENTUAL)
                .buildClient();

        CosmosDatabase database = cosmosClient.getDatabase(databaseName);
        this.container = database.getContainer(containerName);
       
    }

    public void saveOrder(Order order) {
        container.upsertItem(order);
    }

    public void close() {
        cosmosClient.close();
    }
}

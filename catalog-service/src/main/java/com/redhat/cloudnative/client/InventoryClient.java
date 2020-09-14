package com.redhat.cloudnative.client;

import com.redhat.cloudnative.model.Inventory;
import feign.hystrix.FallbackFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import feign.hystrix.FallbackFactory;


@FeignClient(name="inventory",fallbackFactory = InventoryClient.InventoryClientFallbackFactory.class)
public interface InventoryClient {

    @RequestMapping(method = RequestMethod.GET, value = "/services/inventory/{itemId}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    String getInventoryStatus(@PathVariable("itemId") String itemId);

    @Component
    class InventoryClientFallbackFactory implements FallbackFactory<InventoryClient> {
        @Override
        public InventoryClient create(Throwable cause) {
            return itemId -> "[{'quantity':-1}]";
        }
    }
}
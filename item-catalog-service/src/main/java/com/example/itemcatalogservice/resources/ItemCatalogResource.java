package com.example.itemcatalogservice.resources;

import com.example.itemcatalogservice.models.CatalogItem;
import com.example.itemcatalogservice.models.Item;
import com.example.itemcatalogservice.models.Rating;
import com.example.itemcatalogservice.models.UserRating;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class ItemCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){

//        RestTemplate restTemplate = new RestTemplate();
//        Item item = restTemplate.getForObject("http://localhost:8081/items/id", Item.class);//Takes URL and gives Class obj

        //Equiv to creating REST Template But not same
        WebClient.Builder builder = WebClient.builder();

        //get all rated item id's
        //List<Rating>          localhost:8083 => ratings-dataservice @LoadBalanced feature
        UserRating ratings = restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/" + userId, UserRating.class);
//                Arrays.asList(
//                new Rating("1234", 4),
//                new Rating("5678", 3)
//        );



        //return ratings.stream().map(rating -> {
        return ratings.getUserRating().stream().map(rating -> {
            //Create Instance of Rest Template
            //Rest Temp is utility obj which let me make the calls
            //Marshalling from not an object to an object => require Empty Constructor

            //for each item id call item-info service and get details
            Item item = restTemplate.getForObject("http://item-info-service/items/" + rating.getItemId(), Item.class);

//            Item item = webClientBuilder.build()
//                    .get()
//                    .uri("http://localhost:8082/items/" + rating.getItemId())
//                    .retrieve()
//                    .bodyToMono(Item.class)
//                    //Convert the body retrieved into an instance of Item Class
//                    //Mono => reactive way of getting an object sometime in a future. Mono promises this thing(Async)
//                    .block();//Blocks the statement in proceeding further
//            //Gives an Instance of Item

            //Put them all together
            return new CatalogItem(item.getName(), "Test", rating.getRating());
        })
.collect(Collectors.toList());

//        return Collections.singletonList(
//                new CatalogItem("PUMA","Test",3)
//        );

    }
}

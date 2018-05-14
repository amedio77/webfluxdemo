package webflux.example.webfluxdemo;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import webflux.example.handler.TweetHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {WebTestClient.class})
public class TweetControllerTest {

    private WebTestClient testClient;

    @Before
    public  void setUp() throws Exception {
        RouterFunction<?> route = route(GET("/list"), request ->
                ServerResponse.ok().syncBody("It works!"));
        this.testClient = WebTestClient.bindToRouterFunction(route).build();
    }

    @Test
    public  void test() throws Exception {
        this.testClient.get().uri("/list")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("It works!");
    }

}

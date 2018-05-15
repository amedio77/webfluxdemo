package webflux.example.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import webflux.example.handler.TweetHandler;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
@EnableWebFlux
public class TweetRouter {// implements WebFluxConfigurer {
    @Bean
    RouterFunction<ServerResponse> routingFunction(TweetHandler handler, TweetHandler handler2) {

        return RouterFunctions
                .route(GET("/list").and(accept(APPLICATION_JSON)), handler::listAll)
                .andRoute(GET("/list/{id}").and(accept(APPLICATION_JSON)), handler::getTweet)
                .andRoute(POST("/list").and(accept(APPLICATION_JSON)).and(contentType(APPLICATION_JSON)), handler::createPerson)
                .andRoute(GET("/hello").and(accept(APPLICATION_JSON)), req-> ServerResponse.ok().body(fromObject("hello woo test")));
    }

    @Bean
    RouterFunction<ServerResponse> routingFunction2(TweetHandler handler, TweetHandler handler2) {
        return RouterFunctions.route(GET("/hello2").and(accept(APPLICATION_JSON)), req-> ServerResponse.ok().body(fromObject("hello woo test222")))
                .andRoute(GET("/list2").and(accept(APPLICATION_JSON)), handler::listAll)
                .andRoute(GET("/list2/{id}").and(accept(APPLICATION_JSON)), handler2::getTweet);
    }

}

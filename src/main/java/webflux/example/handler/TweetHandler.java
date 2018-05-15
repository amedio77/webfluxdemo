package webflux.example.handler;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import webflux.example.model.Tweet;
import webflux.example.repository.TweetRepository;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Component
@Slf4j
public class TweetHandler {

    @Autowired
    private TweetRepository repository;

    public Mono<ServerResponse> listAll(ServerRequest request) {

        //this.repository.findAll().doOnNext(System.out::println).subscribe(s-> System.out.println(s.getText()));

        Flux<Tweet> tweet = this.repository.findAll();

        return ServerResponse.ok().contentType(APPLICATION_JSON).body(tweet, Tweet.class);
    }

    public Mono<ServerResponse> getTweet(ServerRequest request) {
        String tweetId = request.pathVariable("id");

        Mono<ServerResponse> notFound = ServerResponse.notFound().build();

        Mono<Tweet> personMono = this.repository.findById(tweetId);

        return personMono
                .flatMap(person -> ServerResponse.ok().contentType(APPLICATION_JSON).body(fromObject(person)))
                .switchIfEmpty(notFound);
    }


    public Mono<ServerResponse> createPerson(ServerRequest request) {

        Mono<Tweet> tweet = request.bodyToMono(Tweet.class);

        Mono<Tweet> tweetMono = tweet.flatMap(this.repository::save);

        return ServerResponse.status(HttpStatus.CREATED).body(tweetMono, Tweet.class);
    }

}
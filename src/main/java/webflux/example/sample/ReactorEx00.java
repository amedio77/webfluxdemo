package webflux.example.sample;

import reactor.core.publisher.Flux;
import webflux.example.model.Tweet;

import java.util.Date;

public class ReactorEx00 {

    public static void main(String[] args) {

        // 기본
        Flux.just(
                new Tweet("1", "event1",new Date()),
                new Tweet("2", "event2",new Date())
        );
    }
}

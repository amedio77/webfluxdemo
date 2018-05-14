package webflux.example.webfluxdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import java.util.Arrays;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {WebTestClient.class})
//@ComponentScan(basePackages = { "webflux.example","org.springframework.test.web.reactive.server" })
public class WebfluxTests {

   // @Autowired
   // private WebTestClient webTestClient;


    @Test
    public void contextLoads() {

        System.out.println("===========start !!!");

        Flux.range(1,10)
                .publishOn(Schedulers.newSingle("pub"))
                .log()
                .subscribeOn(Schedulers.newSingle("sub"))
                .subscribe(System.out::println);
/*
        Flux.<Integer>create(e->{
            e.next(1);
            e.next(2);
            e.next(3);
            e.complete();
        })
                .log()
                .map(s->s*10)
                .log()
                //.reduce(0,(a,b)->a+b)
                //.log()
                .subscribe(System.out::println);
*/
    }

}

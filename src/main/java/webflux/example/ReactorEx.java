package webflux.example;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class ReactorEx {

    public static void main(String[] args) {
        log.debug("start !!!");
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
    }
}

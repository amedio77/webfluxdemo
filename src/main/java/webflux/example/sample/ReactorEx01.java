package webflux.example.sample;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class ReactorEx01 {

    public static void main(String[] args) {

        Publisher<Integer> pub = new Publisher<Integer>() {

            Iterable<Integer> iter = Stream.iterate(1, a->a+1).limit(10).collect(Collectors.toList());

            @Override
            public void subscribe(Subscriber<? super Integer> sub) {
                sub.onSubscribe(new Subscription() {
                    @Override
                    public void request(long n) {

                        try{
                            iter.forEach(s->sub.onNext(s));
                            sub.onComplete();
                        }catch (Throwable t){

                        }

                    }

                    @Override
                    public void cancel() {

                    }
                });
            }
        };

        Subscriber<Integer> sub = new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                log.info("onSubscribe !");
                s.request(Long.MAX_VALUE);
            }



            @Override
            public void onNext(Integer i) {
                log.debug("onNext {}",i);
            }

            @Override
            public void onError(Throwable t) {
                log.debug("onError {}",t);
            }

            @Override
            public void onComplete() {
                log.debug("onComplete !");
            }
        };

        pub.subscribe(sub);

    }
}

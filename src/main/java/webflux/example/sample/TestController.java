package webflux.example.sample;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/hello2")
    public Publisher<String> hello(String name){
        return new Publisher<String>() {

            @Override
            public void subscribe(Subscriber<? super String> s) {
                 s.onSubscribe(new Subscription() {
                    @Override
                    public void request(long n) {
                        s.onNext("hello "+name+"::"+n);
                        s.onComplete();
                    }

                    @Override
                    public void cancel() {

                    }
                });
            }

        };
    }
}

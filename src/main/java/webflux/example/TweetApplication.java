package webflux.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import webflux.example.model.Tweet;
import webflux.example.repository.TweetRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SpringBootApplication
public class TweetApplication {


    @Autowired
    private TweetRepository repository;

    private List<String> list = new ArrayList<String>();

    public static void main(String[] args) {

        SpringApplication.run(TweetApplication.class, args);
    }

    @Bean
    CommandLineRunner demo (){
        return args -> {

            repository.deleteAll().subscribe();

            System.out.println("main run !!! ==> ");

            //repository.save(new Tweet("1", "Smith11", new Date())).subscribe();
            //repository.save(new Tweet("2", "Smith12", new Date())).subscribe();
            //repository.save(new Tweet("3", "Smith13", new Date())).subscribe();

            IntStream.iterate(0, i -> i + 1)
                    .limit(100)
                    .forEach(i -> {repository.save(new Tweet(""+i, "Smith"+i, new Date())).subscribe(); });

        };
    }

}

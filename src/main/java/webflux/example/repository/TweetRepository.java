package webflux.example.repository;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import webflux.example.model.Tweet;

@Repository
public interface TweetRepository extends ReactiveMongoRepository<Tweet, String> {

}

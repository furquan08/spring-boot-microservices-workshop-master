package com.furquan.moviecatalogservice.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.furquan.moviecatalogservice.models.CatalogItem;
import com.furquan.moviecatalogservice.models.UserRating;
import com.furquan.moviecatalogservice.services.MovieInfo;
import com.furquan.moviecatalogservice.services.UserRatingInfo;

@RestController
@RequestMapping("/catalog")
public class CatalogResource {


	@Autowired
	WebClient.Builder webClientBuilder;
	
	@Autowired
	MovieInfo movieInfo;
	
	@Autowired
	UserRatingInfo userRatingInfo;

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

		UserRating userRating = userRatingInfo.getUserRating(userId);

		return userRating.getRatings().stream().map(rating -> movieInfo.getCatalogItem(rating)).collect(Collectors.toList());

	}
}

/*
 * Alternative WebClient way Movie movie =
 * webClientBuilder.build().get().uri("http://localhost:8082/movies/"+
 * rating.getMovieId()) .retrieve().bodyToMono(Movie.class).block();
 */
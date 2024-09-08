package com.fitstam.User.UserService.external;

import com.fitstam.User.UserService.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="RATINGSERVICE")
public interface RatingService {
    @GetMapping("/ratings/getRatingByUserId/{userId}")
    List<Rating> getRatingsByUserId(@PathVariable String userId);
}

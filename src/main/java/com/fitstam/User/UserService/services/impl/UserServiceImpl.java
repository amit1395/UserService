package com.fitstam.User.UserService.services.impl;

import com.fitstam.User.UserService.entities.Hotel;
import com.fitstam.User.UserService.entities.Rating;
import com.fitstam.User.UserService.entities.User;
import com.fitstam.User.UserService.exception.ResourceNotFoundException;
import com.fitstam.User.UserService.external.HotelService;
import com.fitstam.User.UserService.external.RatingService;
import com.fitstam.User.UserService.repo.UserRepo;
import com.fitstam.User.UserService.services.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo repo;
    @Autowired
    private RestTemplate restTemplate;

    private org.slf4j.Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);

    //public static  final String RATING_URL="http://localhost:8092/ratings/";
    public static  final String RATING_URL="http://RATINGSERVICE/ratings/";

    // public static  final String HOTEL_URL="http://localhost:8091/hotels/";
    public static  final String HOTEL_URL="http://HOTELSERVICE/hotels/";

    @Autowired
    public HotelService hotelService;

    @Autowired
    public RatingService ratingService;

    @Override
    public User saveUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return repo.save(user);
    }

    @Override
    public List<User> getAllUser() {
       /* List<User> allUsers = repo.findAll();
        List<User> returnUsers=new ArrayList<>();
        for (User user : allUsers) {
            returnUsers.add(this.getUser(user.getUserId()));
        }
        return returnUsers;*/
        return repo.findAll().stream().map(user -> this.getUser(user.getUserId())).collect(Collectors.toList());
    }

    @Override
    public User getUser(String userId) {
        User user = repo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id not found" + userId));
        logger.info("user Object >" +user.toString());
        //create a template call here
       // Rating[] ratingsArray = restTemplate.getForObject(RATING_URL + "getRatingByUserId/" + user.getUserId(), Rating[].class);
       // logger.info("rating list "+ratingsArray.toString());
       // List<Rating> ratingsList = Arrays.stream(ratingsArray).collect(Collectors.toList());
        List<Rating> ratingsList=ratingService.getRatingsByUserId(user.getUserId());
        List<Rating> ratingWithHotelList = ratingsList.stream().map(rating -> {
           // ResponseEntity<Hotel> forEntity = restTemplate.getForEntity(HOTEL_URL + "getHotel/" + rating.getHotelId(), Hotel.class);
           // Hotel hotel = forEntity.getBody();
            Hotel hotel=hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());
        user.setRatings(ratingWithHotelList);
        return user;

    }
}

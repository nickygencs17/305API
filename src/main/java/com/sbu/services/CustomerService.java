package com.sbu.services;

import com.sbu.controller.CustomerController;
import com.sbu.data.AccountRepository;
import com.sbu.data.CustomerRepository;
import com.sbu.data.entitys.Account;
import com.sbu.data.entitys.Movie;
import com.sbu.data.entitys.Order;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static com.sbu.services.ResponseUtil.build200;

/**
 A customer's currently held movies
 A history of all current and past orders a customer has placed
 Movies available with a particular keyword or set of keywords in the movie name
 Movies available starring a particular actor or group of actors
 Best-Seller list of movies
 Personalized movie suggestion list

 Customers should also be able to:

 Rate the movies they have rented
 */
@CrossOrigin
@RestController
@RequestMapping("/storage/customer")
public class CustomerService extends StorageService  {

// This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    @Autowired
    private CustomerRepository customerRepository;


    @Autowired
    private CustomerController customerController;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping(path="/all")
    public @ResponseBody
    Iterable<Account> getAllUsers() {
        // This returns a JSON or XML with the users
        return accountRepository.findAll();
    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/movies/{customerID}", method= RequestMethod.GET)
    public Response getCustomerMoviesByID(@PathVariable("customerID") String customerID) throws IOException {
        Set<Movie> info = customerController.getCustomerMoviesById(customerID);
        return build200(info);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/movies/{type}", method= RequestMethod.GET)
    public Response getMoviesByType(@PathVariable("type") String type) throws IOException {
        Iterable<Movie> movies = customerController.getMoviesByType(type);
        return build200(movies);
    }



    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/queue/{customerID}", method= RequestMethod.GET)
    public Response getCustomerQueueByID(@PathVariable("customerID") String customerID) throws IOException {
        Iterable<Movie> movies= customerController.getCustomerQueueById(customerID);
        return build200(movies);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/account/{customerID}", method= RequestMethod.GET)
    public Response getCustomerAccountByID(@PathVariable("customerID") String customerID) throws IOException {
        Account info = customerController.getCustomerAccountById(customerID);
        return build200(info);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/orders/{customerID}/current", method= RequestMethod.GET)
    public Response getCustomerOrdersByID(@PathVariable("customerID") String customerID) throws IOException {
        Set<Order> info = customerController.getCustomerOrdersById(customerID);
        return build200(info);
    }






    //not implmented






    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/bestSellers", method = RequestMethod.GET)
    public Response getBestSellers() throws Exception {
        //JSONArray slideContent
        JSONObject res = customerController.getMoviesBestSellers();
        return build200(res);

    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/suggestionMovies/{customerID}", method = RequestMethod.GET)
    public Response getSuggestionsById(@PathVariable("customerID") String customerID) throws Exception {
        //JSONArray slideContent
        JSONObject res = customerController.getSuggestions(customerID);
        return build200(res);

    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{movieID}", method = RequestMethod.POST)
    public Response postRating(@PathVariable("movieID") String movieID) throws Exception {
        //JSONArray slideContent
        JSONObject res = customerController.postRating(movieID);
        return build200(res);

    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/moviesByKeywords", method = RequestMethod.GET)
    public Response getMovieByKeyword(@RequestParam(value = "csvStringOfKeywords", required = false) String csvStringOfKeywords) throws Exception {
        //JSONArray slideContent
        List<String> keywordItems = Arrays.asList(csvStringOfKeywords.split("\\s*,\\s*"));
        JSONObject res = customerController.getMoviesByKeywords(keywordItems);
        return build200(res);

    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/moviesByActors", method = RequestMethod.GET)
    public Response getMovieByActor(@RequestParam(value = "csvStringOfActors", required = false) String csvStringOfActors) throws Exception {
        //JSONArray slideContent
        List<String> actors = Arrays.asList(csvStringOfActors.split("\\s*,\\s*"));
        JSONObject res = customerController.getMoviesByActors(actors);
        return build200(res);

    }



}

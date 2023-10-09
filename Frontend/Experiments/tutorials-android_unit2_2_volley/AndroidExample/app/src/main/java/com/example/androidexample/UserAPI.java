package com.example.androidexample;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.androidexample.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;




public interface UserAPI {

    @GET("/users")
    Call<List<User>> getAllUsers();

    @GET("/users/{id}")
    Call<User> getUserByID(@Path("id") int id);

    @GET("/purchase/{id}/{numStocks}")
    Call<User> purchaseById(@Path("id") int id, @Path("numStocks") int numStocks);


    @GET("/sell/{id}/{numStocks}")
    Call<User> sellById(@Path("id") int id, @Path("numStocks") int numStocks);

    @POST("/users")
    Call<User> createUser (@Body User user);

    @POST("/login")
    Call<User> login(@Body LoginAttempt login);

//
//    @PutMapping("/users/{id}")
//    User updateUser(@PathVariable int id, @RequestBody User request){
//        User user = userRepository.findById(id);
//        if(user == null)
//            return null;
//        userRepository.save(request);
//        return userRepository.findById(id);
//    }
//
//    @PutMapping("/users/{userId}/stocks/{stockId}")
//    String assignStockToUser(@PathVariable int userId,@PathVariable int stockId){
//        User user = userRepository.findById(userId);
//        Stock laptop = stockRepository.findById(stockId);
//        if(user == null || laptop == null)
//            return failure;
//        laptop.setUser(user);
//        user.setStock(laptop);
//        userRepository.save(user);
//        return success;
//    }
//
//    @DeleteMapping(path = "/users/{id}")
//    String deleteUser(@PathVariable int id){
//        userRepository.deleteById(id);
//        return success;
//    }

}

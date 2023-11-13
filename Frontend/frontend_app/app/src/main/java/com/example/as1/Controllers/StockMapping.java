package com.example.as1.Controllers;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Interface mapping the endpoints to the server for the Stock class
 */
public interface StockMapping {

    /**
     * Get list of all stocks
     * @return List of all Stock Objects on the server
     */
    @GET("/stocks")
        Call<List<Stock>> getAllStocks();

    /**
     * Get list of all users that have purchased a stock
     * @param id long id of Stock object being accessed
     * @return List of Users
     */
        @GET("/stock/{id}")
        Call<List<StockPurchased>> getAllUsersForStock(@Path("id") long id);

    /**
     * Get Stock Info
     * @param symbol Symbol of stock object being accessed
     * @return Stock object from server
     */
        @GET("/stocksUpdate/{symbol}")
        Call<Stock> getStockAPIInfo(@Path("symbol") String symbol);

    /**
     * Get a stock by ID
     * @param id long ID of stock object being accessed
     * @return Stock object
     */
        @GET("/stocks/{id}")
        Stock getStockById(@Path("id") Long id);

    /**
     * Get current price of Stock object
     * @param id long id of stock object being accesed
     * @return Stock object
     */
        @GET("/stockchange/{id}")
        Call<Stock> getCurrPrice(@Path("id") Long id);

    /**
     * Post a new stock object
     * @param stock Stock to be created
     * @return String success
     */
    @POST("/stocks")
        Call<Stock> createStock(Stock stock);

    /**
     * Update stock by ID
     * @param id id of stock
     * @return String success
     */
    @POST("/stocks/{id}")
    Call<Stock> updateStockById(@Path("id") int id);

    /**
     * Retrieves news articles pertaining to stock (id)
     * @param id id of stock
     * @return String success
     */
    @GET("/stocks/news/{id}")
    Call<String> getStockNews(@Path("id") int id);

    /**
     * Retrieves weekly price history of stock (id)
     * @param id in of stock
     * @return String success
     */
    @GET("/stocks/history/{id}")
    Call<String> getStockHistory(@Path("id") int id);

    /**
     * Delete stock from server
     * @param id long id of stock
     * @return String success
     */
    @DELETE("/stocks/{id}")
        Call<Stock> deleteStock(@Path("id") Long id);



    }

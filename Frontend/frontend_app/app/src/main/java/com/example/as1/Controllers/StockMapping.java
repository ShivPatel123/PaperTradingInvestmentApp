package com.example.as1.Controllers;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface StockMapping {


        @GET("/stocks")
        Call<List<Stock>> getAllStocks();

        @GET("/stock/{id}")
        Call<List<StockPurchased>> getAllUsersForStock(@Path("id") long id);

        @GET("/stocksUpdate/{symbol}")
        Call<Stock> getStockAPIInfo(@Path("symbol") String symbol);

        @GET("/stocks/{id}")
        Stock getStockById(@Path("id") Long id);

        @GET("/stockchange/{id}")
        Call<Stock> getCurrPrice(@Path("id") Long id);

        @POST("/stocks")
        Call<Stock> createStock(Stock stock);

    @POST("/stocks/{id}")
    Call<Stock> updateStockById(@Path("id") int id);

    //Update all stocks in database or update all stocks for one user?
//        @PutMapping(path = "/stocks")
//        String updateAllStocks(){
//            stockAPI.updateAllStocks(stockRepository);
//            return success;
//        }

        @DELETE("/stocks/{id}")
        Call<Stock> deleteStock(@Path("id") Long id);



    }

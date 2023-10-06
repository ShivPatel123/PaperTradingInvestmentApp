package coms309.Stocks;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;



public class StockUpdater{

//    Alpha Vantage API Key: UOICPFOUUT832ZST
    private final HttpClient httpClient = HttpClient.newBuilder().build();

    public String updateStockData(String symbol){
        try{
            String apiRequest = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + symbol + "&apikey=UOICPFOUUT832ZST";
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(apiRequest))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return response.body();
            } else {
                return null;
            }
        } catch (IOException | InterruptedException | java.net.URISyntaxException e) {
            e.printStackTrace();
            return null;
        }

    }

    //parameter is a call to updateStockData function
    public String getUpdatedStockPrice(String stockInfo){

        int priceIndex = stockInfo.indexOf("\"05. price\":");
        String t = stockInfo.substring((priceIndex+14));
        int endPriceIndex = t.indexOf("\"");
        return stockInfo.substring((priceIndex+14), (priceIndex+14) + endPriceIndex);
    }

    public String getUpdatedStockChange(String stockInfo){
        int priceIndex = stockInfo.indexOf("\"10. change percent\": ");
        String t = stockInfo.substring((priceIndex+23));
        int endPriceIndex = t.indexOf("\"");
        return stockInfo.substring((priceIndex+23), (priceIndex+22) + endPriceIndex);
    }

    public String updateStockInfo(int id, StockRepository repo){
        Stock stockToUpdate = repo.findById(id);
        String stockInfo = updateStockData(stockToUpdate.getSymbol());
        stockToUpdate.setCurrValue(Double.valueOf(getUpdatedStockPrice(stockInfo)));
        stockToUpdate.setPrevDayChange(Double.valueOf(getUpdatedStockChange(stockInfo)));
        repo.save(stockToUpdate);
        return "Success";
    }


    //TODO Update in batches of 5 because of api rate limit 5 per minute
    public String updateAllStocks(StockRepository repo){
        List<Stock> allStocks = repo.findAll();
        for(Stock stock: allStocks){
            String stockInfo = updateStockData(stock.getSymbol());
            stock.setCurrValue(Double.valueOf(getUpdatedStockPrice(stockInfo)));
            stock.setPrevDayChange(Double.valueOf(getUpdatedStockChange(stockInfo)));
            repo.save(stock);
        }
        return "Success";
    }


}
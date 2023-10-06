package coms309.Stocks;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


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

    public String getUpdatedStockPrice(String symbol){
        String stockInfo = updateStockData(symbol);
        int priceIndex = stockInfo.indexOf("\"05. price\":");
        String t = stockInfo.substring((priceIndex+14));
        int endPriceIndex = t.indexOf("\"");
        return stockInfo.substring((priceIndex+14), (priceIndex+14) + endPriceIndex);
    }




}
package com.example.as1.Controllers.RecycleViews;

public class StockScrollCard {

    private int imageView;
    private int numPurchased;
    private int stockPrice;
    private String stockName;

    public StockScrollCard(String stockName, int numPurchased, int stockPrice){
        this.stockName = stockName;
        this.numPurchased = numPurchased;
        this.stockPrice = stockPrice;
    }

    public int getNumPurchased() {
        return numPurchased;
    }

    public void setNumPurchased(int numPurchased) {
        this.numPurchased = numPurchased;
    }

    public int getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(int stockPrice) {
        this.stockPrice = stockPrice;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

}

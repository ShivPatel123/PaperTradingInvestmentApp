package com.example.as1.Controllers;

public class ScrollStockCard {

    private int imageView;
    private int numPurchased;
    private int stockPrice;
    private String stockName;

    public ScrollStockCard(String stockName, int numPurchased, int stockPrice, Integer imageView){
        this.imageView = imageView;
        this.stockName = stockName;
        this.numPurchased = numPurchased;
        this.stockPrice = stockPrice;
    }


    public int getImageView() {
        return imageView;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
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

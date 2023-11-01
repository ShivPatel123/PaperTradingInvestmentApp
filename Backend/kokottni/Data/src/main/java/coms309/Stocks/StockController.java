package coms309.Stocks;

import java.util.List;

import coms309.Users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.scheduling.annotation.Scheduled;

import coms309.Users.User;
import coms309.Users.UserRepository;

@RestController
public class StockController {

    @Autowired
    StockRepository stockRepository;

    @Autowired
    UserRepository userRepository;

    StockUpdater stockAPI = new StockUpdater();

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/stocks")
    List<Stock> getAllStocks(){return stockRepository.findAll();}


    @GetMapping(path = "/stocksUpdate/{symbol}")
    String getStockAPIInfo(@PathVariable String symbol){
        return stockAPI.getUpdatedStockChange(symbol);
    }


    @GetMapping(path = "/stocks/{id}")
    Stock getStockById(@PathVariable int id){return stockRepository.findById(id);}

    @GetMapping(path = "/stockchange/{id}")
    double getCurrPrice(@PathVariable int id){return stockRepository.findById(id).getCurrValue();}


    //retrieves news articles pertaining to stock (id)
    @GetMapping(path = "/stocks/news/{id}")
    String getStockNews(@PathVariable int id){
        return stockAPI.getStockNews(stockRepository.findById(id).getSymbol());
    }

    //retrieves weekly price history of stock (id)
    @GetMapping(path = "/stocks/history/{id}")
    String getStockHistory(@PathVariable int id){
        return stockAPI.getStockHistory(stockRepository.findById(id).getSymbol());
    }

    @PostMapping(path = "/stocks")
    String createStock(Stock stock){
        if(stock == null){
            return failure;
        }
        stockRepository.save(stock);
        return success;
    }

//    @PutMapping(path = "/stocks/{id}")
//    Stock updateStockById(@PathVariable int id, @RequestBody Stock request){
//        Stock stock = stockRepository.findById(id);
//        if(stock == null){
//            return null;
//        }
//        stockRepository.save(stock);
//        return stockRepository.findById(id);
//    }

    @PutMapping(path = "/stocks/{id}")
    Stock updateStockById(@PathVariable int id){
        stockAPI.updateStockInfo(id, stockRepository);
        return stockRepository.findById(id);
    }

    //updates stock repo every 15 min
    //TODO: alternate which 5 are being updated
    @Scheduled(fixedRate = 900000)
    @PutMapping(path = "/stocks")
    String updateAllStocks(){
        stockAPI.updateAllStocks(stockRepository);
        return success;
    }


    @DeleteMapping(path = "/stocks/{id}")
    String deleteStock(@PathVariable int id){

        User user = userRepository.findByStockId(id);
        user.setStock(null);
        userRepository.save(user);

        stockRepository.deleteById(id);
        return success;
    }
}

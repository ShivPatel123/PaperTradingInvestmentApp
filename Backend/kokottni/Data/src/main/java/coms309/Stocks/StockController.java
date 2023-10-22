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
    Stock getStockById(@PathVariable Long id){return stockRepository.getOne(id);}

    @GetMapping(path = "/stockchange/{id}")
    double getCurrPrice(@PathVariable Long id){return stockRepository.getOne(id).getCurrValue();}

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
    String updateStockById(@PathVariable Long id){
        stockAPI.updateStockInfo(id, stockRepository);
        return success;
    }
    @PutMapping(path = "/stocks")
    String updateAllStocks(){
        stockAPI.updateAllStocks(stockRepository);
        return success;
    }


    @DeleteMapping(path = "/stocks/{id}")
    String deleteStock(@PathVariable Long id){

        User user = userRepository.getOne(id);
        userRepository.save(user);

        stockRepository.deleteById(id);
        return success;
    }
}

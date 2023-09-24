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

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/stocks")
    List<Stock> getAllStocks(){return stockRepository.findAll();}

    @GetMapping(path = "/stocks/{id}")
    Stock getStockById(@PathVariable int id){return stockRepository.findById(id);}

    @GetMapping(path = "/stocks/{symbol}")
    Stock getStockBySymbol(@PathVariable String symbol){return stockRepository.findBySymbol(symbol);}

    @PostMapping(path = "/stocks")
    String createStock(Stock stock){
        if(stock == null){
            return failure;
        }
        stockRepository.save(stock);
        return success;
    }

    @PutMapping(path = "/stocks/{id}")
    Stock updateStockById(@PathVariable int id, @RequestBody Stock request){
        Stock stock = stockRepository.findById(id);
        if(stock == null){
            return null;
        }
        stockRepository.save(stock);
        return stockRepository.findById(id);
    }

    @PutMapping(path = "/stocks/{symbol}")
    Stock updateStockById(@PathVariable String symbol, @RequestBody Stock request){
        Stock stock = stockRepository.findBySymbol(symbol);
        if(stock == null){
            return null;
        }
        stockRepository.save(stock);
        return stockRepository.findBySymbol(symbol);
    }

    @DeleteMapping(path = "/stocks/{id}")
    String deleteStock(@PathVariable int id){

        User user = userRepository.findByStockId(id);
        user.setStock(null);
        userRepository.save(user);

        Stock stock = stockRepository.findById(id);
        return success;
    }
}

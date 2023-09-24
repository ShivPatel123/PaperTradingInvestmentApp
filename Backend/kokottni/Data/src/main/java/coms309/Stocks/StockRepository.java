package coms309.Stocks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface StockRepository extends JpaRepository<Stock, Long>{
    Stock findBySymbol(String symbol);

    Stock findById(int id);

    @Transactional
    void deleteById(int id);

    @Transactional
    void deleteBySymbol(String symbol);

}

package com.hackerrank.stocktrades.service;

import com.hackerrank.stocktrades.model.StockTrade;
import com.hackerrank.stocktrades.repository.StockTradeRepository;
import com.hackerrank.stocktrades.validation.StockTypeValidator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class StockTradeService {

    private final StockTradeRepository stockTradeRepository;

    public ResponseEntity<StockTrade> createNewStockTrade(StockTrade stockTrade) {
        if (stockTrade.getShares() < 1 || stockTrade.getShares() > 100) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        if (!new StockTypeValidator().isValid(stockTrade.getType(), null)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(stockTradeRepository.save(stockTrade));


    }

    public List<StockTrade> getStockTrades(String type, Integer userId) {
        if (type != null && userId != null) {
            return stockTradeRepository.findStockTradesByTypeAndUserId(type, userId);
        }
        if (type != null) {
            return stockTradeRepository.findStockTradesByType(type);
        }
        if (userId != null) {
            return stockTradeRepository.findStockTradesByUserId(userId);
        }
        return stockTradeRepository.findAll();
    }

    public ResponseEntity<StockTrade> getStockTradeById(Integer id) {

        Optional<StockTrade> stockTradeById = stockTradeRepository.findById(id);
        return stockTradeById.map(stockTrade -> new ResponseEntity<>(stockTrade, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }
}

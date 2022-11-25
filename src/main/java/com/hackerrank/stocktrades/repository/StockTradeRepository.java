package com.hackerrank.stocktrades.repository;

import com.hackerrank.stocktrades.model.StockTrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockTradeRepository extends JpaRepository<StockTrade, Integer> {
    List<StockTrade> findStockTradesByType(String type);

    List<StockTrade> findStockTradesByUserId(Integer userId);

    List<StockTrade> findStockTradesByTypeAndUserId(String type, Integer userId);
}

package com.hackerrank.stocktrades.controller;

import com.hackerrank.stocktrades.model.StockTrade;
import com.hackerrank.stocktrades.service.StockTradeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/trades")
public class StockTradeRestController {

    private final StockTradeService stockTradeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<StockTrade> createStockTrade(@RequestBody StockTrade stockTrade) {
        return stockTradeService.createNewStockTrade(stockTrade);
    }

    @GetMapping
    List<StockTrade> getStockTrades(@RequestParam(required = false) String type, @RequestParam(required = false) Integer userId) {
        return stockTradeService.getStockTrades(type, userId);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<StockTrade> getStockTradeById(@PathVariable Integer id) {
        return stockTradeService.getStockTradeById(id);
    }

    @RequestMapping(value = "/trades/{id}", method = {RequestMethod.DELETE, RequestMethod.PATCH, RequestMethod.PUT})
    public ResponseEntity<StockTrade> updateOrDeleteTrade(@PathVariable(value = "id") Integer id) {
        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }
}
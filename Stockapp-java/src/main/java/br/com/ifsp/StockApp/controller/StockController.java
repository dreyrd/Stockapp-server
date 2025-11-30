package br.com.ifsp.StockApp.controller;

import br.com.ifsp.StockApp.model.predict.*;
import br.com.ifsp.StockApp.model.stock.Stock;
import br.com.ifsp.StockApp.model.stock.StockDataCreation;
import br.com.ifsp.StockApp.model.stock.StockDataResponse;
import br.com.ifsp.StockApp.model.stock.StockRepository;
import br.com.ifsp.StockApp.service.StockPredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;


@RestController
@RequestMapping("/stocks")
public class StockController {
    @Autowired
    StockRepository repository;

    @PostMapping
    public ResponseEntity<StockDataResponse> postStock(@RequestBody StockDataCreation stockDataCreation, UriComponentsBuilder uriComponentsBuilder){
        var newStock = new Stock(stockDataCreation);
        repository.save(newStock);
        var uri = uriComponentsBuilder.path("/stocks/{stockId}").buildAndExpand(newStock.getStockId()).toUri();
        return ResponseEntity.created(uri).body(new StockDataResponse(newStock));
    }

    @GetMapping
    public ResponseEntity<Page<StockDataResponse>> getStock(Pageable pageable){
        var page = repository.findAllByEnableTrue(pageable).map(StockDataResponse::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{stockId}")
    public ResponseEntity<StockDataResponse> getStockById(@PathVariable Integer stockId){
        var stock = repository.getReferenceById(stockId);
        return ResponseEntity.ok(new StockDataResponse(stock));
    }

    @GetMapping("/symbol/{stockSymbol}")
    public ResponseEntity<StockDataResponse> getStockBySymbol(@PathVariable String stockSymbol){
        var stock = repository.getReferenceByStockSymbol(stockSymbol);
        return ResponseEntity.ok(new StockDataResponse(stock));
    }

    @PostMapping("/prediction")
    public ResponseEntity<StockPredictionDataResponse> postStockPrediction(@RequestBody PredictionRequestDataCreation predictionRequestDataCreation) throws IOException, InterruptedException {
        StockPredictionService stockPredictionService = new StockPredictionService();
        PredictionRequest predictionRequest = new PredictionRequest(predictionRequestDataCreation);

        StockPrediction stockPrediction = stockPredictionService.predictionByLr(predictionRequest);

        return ResponseEntity.ok(new StockPredictionDataResponse(stockPrediction));
    }

//    @PostMapping("/suggestion")
//    public ResponseEntity<StockPredictionDataResponse> postStockSuggestion(@RequestBody PredictionRequestDataCreation predictionRequestDataCreation) throws IOException, InterruptedException {
//        StockPredictionService stockPredictionService = new StockPredictionService();
//        PredictionRequest predictionRequest = new PredictionRequest(predictionRequestDataCreation);
//
//        StockPrediction stockPrediction = stockPredictionService.predictionByRf(predictionRequest);
//
//        return ResponseEntity.ok(new StockPredictionDataResponse(stockPrediction));
//    }
}

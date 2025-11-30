package br.com.ifsp.StockApp.model.predict;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockPrediction {
    private Double highPrediction;
    private Double lowPrediction;

    public StockPrediction(StockPredictionDataCreation stockPredictionDataCreation){
        this.highPrediction = stockPredictionDataCreation.h();
        this.lowPrediction = stockPredictionDataCreation.l();
    }
}

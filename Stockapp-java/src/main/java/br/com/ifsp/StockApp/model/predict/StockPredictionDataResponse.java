package br.com.ifsp.StockApp.model.predict;

public record StockPredictionDataResponse(Double highPrediction, Double lowPrediction) {

    public StockPredictionDataResponse(StockPrediction stockPrediction){
        this(stockPrediction.getHighPrediction(), stockPrediction.getLowPrediction());
    }
}

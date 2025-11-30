package br.com.ifsp.StockApp.model.predict;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PredictionRequest {
    private String ticker;
    private StockHistory[] history;

    public PredictionRequest(PredictionRequestDataCreation predictionRequestDataCreation){
        this.ticker = predictionRequestDataCreation.ticker();
        this.history = predictionRequestDataCreation.history();
    }

}

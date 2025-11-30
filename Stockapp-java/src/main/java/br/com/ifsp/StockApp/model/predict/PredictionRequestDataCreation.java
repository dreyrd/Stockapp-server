package br.com.ifsp.StockApp.model.predict;

public record PredictionRequestDataCreation(
    String ticker,
    StockHistory[] history
) { }

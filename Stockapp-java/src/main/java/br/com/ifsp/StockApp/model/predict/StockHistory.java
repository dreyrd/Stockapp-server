package br.com.ifsp.StockApp.model.predict;

public record StockHistory(
    String date,
    Float o,
    Float h,
    Float l,
    Float c,
    Float v
) { }

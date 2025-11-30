package br.com.ifsp.StockApp.model.stock;

import jakarta.validation.constraints.NotBlank;

public record StockDataCreation(
    byte[] stockPhoto,
    @NotBlank String stockName,
    @NotBlank String stockSymbol
) { }

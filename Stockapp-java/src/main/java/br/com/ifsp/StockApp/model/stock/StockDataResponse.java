package br.com.ifsp.StockApp.model.stock;

public record StockDataResponse(Integer stockId, byte[] stockPhoto, String stockName, String stockSymbol, Boolean enable) {

    public StockDataResponse(Stock stock){
        this(stock.getStockId(), stock.getStockPhoto(), stock.getStockName(), stock.getStockSymbol(), stock.getEnable());
    }

}

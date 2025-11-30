package br.com.ifsp.StockApp.model.stock;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Integer> {
    Page<Stock> findAllByEnableTrue(Pageable pageable);
    Stock getReferenceByStockSymbol(String stockSymbol);
}

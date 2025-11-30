package br.com.ifsp.StockApp.model.stock;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@EqualsAndHashCode
@Entity(name = "Stock")
@Table(name = "stocks")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stockId;
    private byte[] stockPhoto;
    private String stockName;
    @Column(unique = true)
    private String stockSymbol;
    private Boolean enable;

    public Stock(StockDataCreation stockDataCreation){
        this.stockPhoto = stockDataCreation.stockPhoto();
        this.stockName = stockDataCreation.stockName();
        this.stockSymbol = stockDataCreation.stockSymbol();
        this.enable = true;
    }
}

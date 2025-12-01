package br.com.ifsp.StockApp.service;

import br.com.ifsp.StockApp.model.predict.PredictionRequest;
import br.com.ifsp.StockApp.model.predict.StockPrediction;
import br.com.ifsp.StockApp.model.predict.StockPredictionDataCreation;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

public class StockPredictionService {

    private HttpClient client;
    private Gson gson;
    private String urlBase;

    public StockPredictionService(){
        this.client = HttpClient.newHttpClient();
        this.gson = new Gson();
        // Usa variável de ambiente ou fallback para localhost (desenvolvimento)
        this.urlBase = System.getenv().getOrDefault("PYTHON_API_URL", "http://127.0.0.1:8000") + "/api/v1";
    }

    public StockPrediction predictionByLr(PredictionRequest predictionRequest) throws IOException, InterruptedException {
        String urlLr = this.urlBase + "/lr/prediction";

        return makePrediction(urlLr, predictionRequest);
    }

    public StockPrediction predictionByRf(PredictionRequest predictionRequest) throws IOException, InterruptedException {
        String urlRf = this.urlBase + "/rf/prediction";

        return makePrediction(urlRf, predictionRequest);
    }

    private StockPrediction makePrediction(String url, PredictionRequest predictionRequest) throws IOException, InterruptedException {
        String bodyPredictionRequest = this.gson.toJson(predictionRequest);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .version(HttpClient.Version.HTTP_1_1)
                .POST(HttpRequest.BodyPublishers.ofString(bodyPredictionRequest, StandardCharsets.UTF_8))
                .build();

        String body = this.client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();

        StockPredictionDataCreation stockPredictionDataCreation = this.gson.fromJson(body, StockPredictionDataCreation.class);
        StockPrediction stockPrediction = new StockPrediction(stockPredictionDataCreation);

        return stockPrediction;
    }

}

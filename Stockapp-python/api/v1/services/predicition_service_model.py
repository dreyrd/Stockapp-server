import joblib
import pandas as pd
import os

# The base class to create a class to execute a trained model form scikit-learn
class PredictionBaseModel:
    def __init__(self, filename: str):
        service_dir = os.path.dirname(os.path.abspath(__file__))
        MODEL_PATH = os.path.join(service_dir, f'../../../ml_models/{filename}.pkl')
        model_load = joblib.load(MODEL_PATH)
        
        self.model = model_load['model']
        self.feature_cols = model_load['feature_cols']

    def predict(self, stock_history: list[dict]) -> dict:
        if len(stock_history) < 5:
            raise ValueError('Quantidade de dados para previsão insuficiente')
        elif len(stock_history) > 5:
            raise ValueError('Quantidade de dados para previsão acima do suficiente')

        history_df = pd.DataFrame(stock_history)
        
        if 'date' in history_df.columns:
            history_df['date'] = pd.to_datetime(history_df['date'])
            history_df = history_df.sort_values('date').reset_index(drop=True)
        else:
            raise ValueError('Campo de data faltando no histórico')    
        
        
        features = {}
        for lag in range(1, 6):
            row = history_df.iloc[-lag]
            for col in ["o", "h", "l", "c", "v"]:
                features[f'{col}_lag{lag}'] = row[col]
        
        X_input = pd.DataFrame([features])[self.feature_cols]
        predict = self.model.predict(X_input)[0]
        high_low_prediction = {
            'h': float(predict[0]),
            'l': float(predict[1])
        }
        
        return high_low_prediction

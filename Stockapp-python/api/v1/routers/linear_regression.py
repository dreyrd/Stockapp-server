from fastapi import APIRouter, status, HTTPException
from schemas.prediction_schema import PredictionCreateBase, PredictionResponseBase
from api.v1.services.linear_regression_service import LinearRegressionPrediction

router = APIRouter(prefix='/lr')

# Endpoint to make a post for a prediction in the random forest model
@router.post('/prediction', response_model=PredictionResponseBase, status_code=status.HTTP_200_OK)
async def linear_regression_prediction(stock_lag: PredictionCreateBase):
    try:
        linear_regression_model = LinearRegressionPrediction()
        prediction_result = linear_regression_model.predict([dict(day) for day in stock_lag.history])
        
        response = PredictionResponseBase(
            h=prediction_result['h'],
            l=prediction_result['l']
        )
    except ValueError as e:
        raise HTTPException(detail=f'Erro de validação: {str(e)}', status_code=status.HTTP_400_BAD_REQUEST)
    except Exception as e:
        raise HTTPException(detail='Ocorreu algum erro ao solicitar sua requisição', status_code=status.HTTP_500_INTERNAL_SERVER_ERROR)
    return response
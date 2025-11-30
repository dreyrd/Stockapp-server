from api.v1.services.predicition_service_model import PredictionBaseModel

# The linear regression model class
class LinearRegressionPrediction(PredictionBaseModel):
    def __init__(self):
        super().__init__('linear_regression_model_features_2025_10_20')

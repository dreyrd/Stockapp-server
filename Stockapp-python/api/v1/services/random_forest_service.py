from api.v1.services.predicition_service_model import PredictionBaseModel

# The linear regression model class
class RandomForestPrediction(PredictionBaseModel):
    def __init__(self):
        super().__init__('random_forest_model_features_2025_10_20')

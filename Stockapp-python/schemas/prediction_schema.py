from pydantic import BaseModel, Field
from typing import List

'''
Creation of the base model for POST, GET, PUT and DELETE

'''

class StockHistory(BaseModel):
    date: str
    o: float
    h: float
    l: float
    c: float
    v: float

class PredictionCreateBase(BaseModel):
    ticker: str
    history: List[StockHistory] = Field(..., description="Histórico de exatamente 5 dias")

class PredictionResponseBase(BaseModel):
    h: float
    l: float

from fastapi import APIRouter
from api.v1.routers import linear_regression, random_forest

'''
Junction of all the routers from the 'router' folder

'''

api_router = APIRouter()

api_router.include_router(linear_regression.router)
api_router.include_router(random_forest.router)
package com.example.taipeizoo.Service;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ZooApiService {
    //https://data.taipei/#/dataset/detail?id=5cb73231-b741-48b3-bec3-2ef57bb10029 動物
    //https://data.taipei/#/dataset/detail?id=1ed45a8a-d26a-4a5f-b544-788a4071eea2 館區
    //https://data.taipei/#/dataset/detail?id=48c4d6a7-4b09-4d1f-9739-ee837d302bd1 植物


    //動物資料
    @GET("/api/v1/dataset/a3e2b221-75e0-45c1-8f97-75acbd43d613?scope=resourceAquire&limit&offset")
    Call<JsonObject> getAnimalData(@Query("limit") int count,@Query("offset") int startCount);
//  Flowable<AnimalData> getAnimalData();


    //館區資料
    @GET("/api/v1/dataset/5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a?scope=resourceAquire&q&limit&offset")
    Call<JsonObject> getPavilionData(@Query("q") String area,@Query("limit") int count,@Query("offset") int startCount);
//    Flowable<PavilionData> getPavilionData();


    //台北市立動物園植物資料
    @GET("/api/v1/dataset/f18de02f-b6c9-47c0-8cda-50efad621c14?scope=resourceAquire&limit&offset")
    Call<JsonObject> getPlantData(@Query("limit") int count,@Query("offset") int startCount);
//    Flowable<PlantData> getPlantData();

}

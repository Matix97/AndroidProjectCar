package com.example.projekt.car.Services;

import com.example.projekt.car.DTOs.Cars;

import com.example.projekt.car.DTOs.Fault;
import com.example.projekt.car.DTOs.Fuel;
import com.example.projekt.car.DTOs.TakeCar;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CarService {

    @GET("cars")
    Call<List<Cars>> getCars();
    @POST("cars/takings")
    Call<Void> takeCar(@Body TakeCar takeCar);
    @POST("fuelings")
    Call<Void> fuel(@Body Fuel fuel);
    @POST("faults")
    Call<Void> reportFault(@Body Fault fault );
    @GET("faults")
    Call<List<Fault>> getFault();

}

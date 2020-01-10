package com.example.projekt.car.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.projekt.car.CarArturAdapter;
import com.example.projekt.car.CarListAdapter;
import com.example.projekt.car.DTOs.Cars;
import com.example.projekt.car.R;
import com.example.projekt.car.Services.CarService;
import com.example.projekt.car.Services.ServiceGenerator;
import com.example.projekt.car.data.Car;
import com.example.projekt.car.data.CarsDataBase;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import com.example.projekt.car.CarListAdapter;


public class MyListFragment extends android.support.v4.app.ListFragment {

    List<Cars> data = new ArrayList<>();
    List<Cars> finalData = new ArrayList<>();
    private ArrayList<Car> carArrayList = new ArrayList<>();

    public static MyListFragment newInstance() {
        MyListFragment fragment = new MyListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        carArrayList = new CarsDataBase().getCarArrayList();
        CarListAdapter adapter1 = new CarListAdapter(getActivity(), R.layout.car_item_list_adapter, carArrayList);
        setListAdapter(adapter1);
        // downloadCars();
    }

    void downloadCars() {
        CarService carService = ServiceGenerator.createAuthorizedService(CarService.class);
        Call<List<Cars>> call = carService.getCars();
        call.enqueue(new Callback<List<Cars>>() {
            @Override
            public void onResponse(Call<List<Cars>> call, Response<List<Cars>> response) {
                if (response.isSuccessful()) {

                    data = response.body();
                    for (int i = 0; i < data.size(); i++) {
                        if (!data.get(i).isTaken() && data.get(i).isOk())
                            finalData.add(data.get(i));
                    }
                    CarArturAdapter arturAdapter = new CarArturAdapter(getContext(), R.layout.cars_adapter, finalData);
                    setListAdapter(arturAdapter);
                } else
                    Toast.makeText(getContext(), "Error in GET cars ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Cars>> call, Throwable t) {
                Toast.makeText(getContext(), "FAILURE Error in GET cars ", Toast.LENGTH_SHORT).show();
            }
        });
    }


}

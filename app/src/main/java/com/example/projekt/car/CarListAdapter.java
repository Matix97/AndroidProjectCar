package com.example.projekt.car;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projekt.car.data.Car;

import java.util.List;

public class CarListAdapter extends ArrayAdapter<Car> {

    List<Car> carList;
    Context context;
    int resource;

    public CarListAdapter(Context context, int resource, List<Car> objects) {
        super(context, resource, objects);
        this.carList = objects;
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(resource, null, false);

        ImageView imageView = view.findViewById(R.id.imageView);
        TextView textViewName = view.findViewById(R.id.carTextView);
        TextView textViewTeam = view.findViewById(R.id.fuelTextview);
        Button button = view.findViewById(R.id.button_car_list_adapter);

        Car car = carList.get(position);

        Car idCar=getItem(position);
        button.setOnClickListener(v -> newActivity(idCar));

        imageView.setImageResource(car.getImage());
        textViewName.setText("CarId: " + car.getCarsID());
        textViewTeam.setText("Fuel:" + car.getFuel().toString());


        return view;
    }



    private void newActivity(Car car) {
        Intent intent = new Intent(getContext(), ChoseCar.class);
        Bundle bundle=new Bundle();
        bundle.putString("idCar", car.getCarsID());
        intent.putExtras(bundle);
        context.startActivity(intent);
    }


}

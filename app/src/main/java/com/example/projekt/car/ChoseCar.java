package com.example.projekt.car;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projekt.car.data.Car;
import com.example.projekt.car.data.CarsDataBase;

public class ChoseCar extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chose_car_layout);

        String idCar = getIntent().getExtras().getString("idCar");
        ImageView imageView = this.findViewById(R.id.map);

        TextView textViewName = this.findViewById(R.id.chose_car_textview);


        Car car = null;


        car = new CarsDataBase().getCar(idCar);

        Button tank = this.findViewById(R.id.tankowanie);
        Car finalCar = car;
        imageView.setImageResource(car.getImage());
        tank.setOnClickListener(v -> {
            Intent intent = new Intent(ChoseCar.this, RentedCarActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("idCar", finalCar.getCarsID());
            bundle.putString("registrationNumber", finalCar.getCarsID());
            bundle.putString("model", finalCar.getCarsID());
            bundle.putString("what", "tank");
            intent.putExtras(bundle);
            startActivity(intent);
        });
        Button fault = this.findViewById(R.id.usterka);
        fault.setOnClickListener(v -> {
            Intent intent = new Intent(ChoseCar.this, RentedCarActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("idCar", finalCar.getCarsID());
            bundle.putString("registrationNumber", finalCar.getCarsID());
            bundle.putString("model", finalCar.getCarsID());
            bundle.putString("what", "fault");
            intent.putExtras(bundle);
            startActivity(intent);
        });


        textViewName.setText("CarId: " + car.getCarsID());


    }

}

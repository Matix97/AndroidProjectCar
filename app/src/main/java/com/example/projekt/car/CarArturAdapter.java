package com.example.projekt.car;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projekt.car.DTOs.Cars;

import com.example.projekt.car.DTOs.TakeCar;
import com.example.projekt.car.Services.CarService;
import com.example.projekt.car.Services.ServiceGenerator;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.LOCATION_SERVICE;

public class CarArturAdapter extends ArrayAdapter<Cars> {

    List<Cars> carList;
    Context context;
    int resource;
    boolean ifResponseSuccessful;

    public CarArturAdapter(@NonNull Context context, int resource, @NonNull List<Cars> objects) {
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

        TextView modelText = view.findViewById(R.id.modelText);
        TextView registrationText = view.findViewById(R.id.registrationText);
        //TextView fuelText = view.findViewById(R.id.fuelText);
        //ImageView imageView = view.findViewById(R.id.imageView3);
        Cars car = carList.get(position);
        Cars idCar = getItem(position);
        //it's stupid but it's look better... but it's soooooo slooooowww
/*
        if(car.getRegistrationNumber().equals("EL 3G456")){
            imageView.setImageResource(R.drawable.avensis);
        }else if(car.getRegistrationNumber().equals("EL FG456")){
            imageView.setImageResource(R.drawable.kia_ceed);
        }else if(car.getRegistrationNumber().equals("EL FG458")){
            imageView.setImageResource(R.drawable.kia_rio);
        }else if(car.getRegistrationNumber().equals("EL T87GH")){
            imageView.setImageResource(R.drawable.ford_fiesta);
        }else if(car.getRegistrationNumber().equals("EZG 32456")){
            imageView.setImageResource(R.drawable.seat_toledo2);
        }else{
            imageView.setImageResource(R.drawable.default_car);
        }
*/

        view.setOnClickListener(v -> newActivity(car));


        modelText.setText("" + car.getModel());
        registrationText.setText("Registration: " + car.getRegistrationNumber());
       // fuelText.setText("Fuel: " + car.getFuelAmount());


        return view;
    }


    private void newActivity(Cars car) {
      /*  Intent intent = new Intent(getContext(), ChoseCar.class);
        Bundle bundle=new Bundle();
        bundle.putString("idCar", car.getModel());
        intent.putExtras(bundle);
        context.startActivity(intent);*/
       // Toast.makeText(getContext(), car.toString(), Toast.LENGTH_LONG).show(); //todo delete in final version

        CarService carService = ServiceGenerator.createAuthorizedService(CarService.class);
        //////////////////////////////////////////////////////get location and time
        double longitiude;
        double latitiude;
        long timestamp;
        Date date = new Date();
        timestamp = date.getTime();
        Criteria kr = new Criteria();
        LocationManager locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        String theBestSupplier = locationManager.getBestProvider(kr, true);

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(theBestSupplier);
        if(location==null){
            longitiude = 19.455916;
            latitiude = 51.747085;
        }else{
            longitiude = location.getLongitude();
            latitiude = location.getLatitude();
        }


        ////////////////////////////////////


        TakeCar takeCar = new TakeCar(car.getId(), true/* biore*/, longitiude, latitiude, timestamp);
        Call<Void> call = carService.takeCar(takeCar);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
               // Toast.makeText(getContext(), response.message()+"\n "+response.isSuccessful(), Toast.LENGTH_LONG).show();
                if (response.isSuccessful()) {
                 //   ifResponseSuccessful=true;
                    Intent intent = new Intent(getContext(), RentedCarActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("carID", car.getId());
                    bundle.putString("model", car.getModel());
                    bundle.putString("registrationNumber", car.getRegistrationNumber());
                    intent.putExtras(bundle);
                    //   Toast.makeText(getContext(), "ID: "+car.getId()+"\nModel: "+car.getModel()+"\nRegistration: "+car.getRegistrationNumber(), Toast.LENGTH_LONG).show();
                    context.startActivity(intent);
                } else {
                    Toast.makeText(getContext(), "Failure in getting car\n(This shouldn't be open)\n"+response.message(), Toast.LENGTH_LONG).show();
                   // ifResponseSuccessful=false;
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getContext(), "Failure 2", Toast.LENGTH_LONG).show();
            }
        });
        Toast.makeText(getContext(), "Response: "+ifResponseSuccessful, Toast.LENGTH_LONG).show(); //todo delete in final version
        if(ifResponseSuccessful) {
            Intent intent = new Intent(getContext(), RentedCarActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("carID", car.getId());
            bundle.putString("model", car.getModel());
            bundle.putString("registrationNumber", car.getRegistrationNumber());
            intent.putExtras(bundle);
            //   Toast.makeText(getContext(), "ID: "+car.getId()+"\nModel: "+car.getModel()+"\nRegistration: "+car.getRegistrationNumber(), Toast.LENGTH_LONG).show();
            context.startActivity(intent);
        }

    }
}
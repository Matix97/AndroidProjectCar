package com.example.projekt.car;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projekt.car.DTOs.Fault;
import com.example.projekt.car.DTOs.Fuel;
import com.example.projekt.car.DTOs.TakeCar;
import com.example.projekt.car.Services.CarService;
import com.example.projekt.car.Services.ServiceGenerator;
import com.example.projekt.car.fragments.HelloFragment;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RentedCarActivity extends AppCompatActivity {

    public static int j = 0;
    private TextView fuelAmount, faultDescription, registrationText, modelText, fuelingPrice;
    private Button fuelButton, faultButton;
    private Switch isCritical;
    private CarService carService = ServiceGenerator.createAuthorizedService(CarService.class);
    private int carID;
    private boolean isCriticalAnswer;
    private String CHANNEL_ID = "2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rented_car);

        fuelAmount = findViewById(R.id.fuelAmount);
        faultDescription = findViewById(R.id.faultDescritpion);
        fuelButton = findViewById(R.id.fuelButton);
        faultButton = findViewById(R.id.faultButton);
        isCritical = findViewById(R.id.isCritical);
        registrationText = findViewById(R.id.registrationText);
        modelText = findViewById(R.id.modelText);
        fuelingPrice = findViewById(R.id.fuelingPrice);


        isCritical.setOnCheckedChangeListener((buttonView, isChecked) -> isCriticalAnswer = isChecked);
        fuelButton.setOnClickListener(v -> fueling(v));
        faultButton.setOnClickListener(v -> fault(v));

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        // carID = bundle.getString("carID");
        String reg = bundle.getString("registrationNumber");
        String mod = bundle.getString("model");
        // Toast.makeText(this, "ID: "+carID+"\nModel: "+mod+"\nRegistration: "+reg, Toast.LENGTH_LONG).show();
        registrationText.setText("" + reg);
        modelText.setText("" + mod);
        if (bundle.getString("what").equals("tank")) {
            fuelAmount.setEnabled(true);
            faultDescription.setEnabled(false);
            fuelButton.setEnabled(true);
            faultButton.setEnabled(false);
            isCritical.setEnabled(false);
            fuelingPrice.setEnabled(true);
        }
        if (bundle.getString("what").equals("fault")) {
            fuelAmount.setEnabled(false);
            faultDescription.setEnabled(true);
            fuelButton.setEnabled(false);
            faultButton.setEnabled(true);
            isCritical.setEnabled(true);
            fuelingPrice.setEnabled(false);
        }


    }

    private void fueling(View v) {
        try {
            Double fuel = Double.parseDouble(fuelAmount.getText().toString());
            Double price = Double.parseDouble(fuelingPrice.getText().toString());
            //Toast.makeText(this, String.valueOf(fuel), Toast.LENGTH_SHORT).show();//todo remove-only tetsing
            //////////////////////////////////////////////////////get location and time
            double longitiude;
            double latitiude;
            long timestamp;
            Date date = new Date();
            timestamp = date.getTime();
            Criteria kr = new Criteria();
            LocationManager locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
            String theBestSupplier = locationManager.getBestProvider(kr, true);

            if (ActivityCompat.checkSelfPermission(RentedCarActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(RentedCarActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
            longitiude = location.getLongitude();
            latitiude = location.getLatitude();
            Call<Void> call = carService.fuel(new Fuel(carID, fuel, new Date().getTime(), latitiude, longitiude, price));
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(RentedCarActivity.this, "good fueling", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RentedCarActivity.this, "error in sending fuel", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(RentedCarActivity.this, "something go wrong fuel", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(this, "some error", Toast.LENGTH_SHORT).show();
        }
    }

    private void fault(View v) {
        String descripiton = " ";

        descripiton = faultDescription.getText().toString();
        // descripiton= String.valueOf(isCriticalAnswer);
        //  Toast.makeText(this, descripiton, Toast.LENGTH_SHORT).show();
        if (ServiceGenerator.role.equals("admin")) {
            Intent i = new Intent(RentedCarActivity.this, HelloFragment.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(RentedCarActivity.this, 0, i, 0);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(RentedCarActivity.this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.icon)
                    .setContentTitle("Cars")
                    .setContentText(descripiton)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(RentedCarActivity.this);
            notificationManager.notify(++j, builder.build());
        }
        Call<Void> call = carService.reportFault(new Fault(carID, isCriticalAnswer, descripiton, new Date().getTime()));
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(RentedCarActivity.this, "good faulting", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RentedCarActivity.this, "error in sending fault", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(RentedCarActivity.this, "something go wrong fault", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CarService carService = ServiceGenerator.createAuthorizedService(CarService.class);
        //////////////////////////////////////////////////////get location and time
        double longitiude;
        double latitiude;
        long timestamp;
        Date date = new Date();
        timestamp = date.getTime();
        Criteria kr = new Criteria();
        LocationManager locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        String theBestSupplier = locationManager.getBestProvider(kr, true);

        if (ActivityCompat.checkSelfPermission(RentedCarActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(RentedCarActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
        longitiude = location.getLongitude();
        latitiude = location.getLatitude();

        ////////////////////////////////////


        TakeCar takeCar = new TakeCar(carID, false/* oddaje*/, longitiude, latitiude, timestamp);
        Call<Void> call = carService.takeCar(takeCar);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // ifResponseSuccessful=true;
                    //Toast.makeText(getContext(), response.body().toString(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(RentedCarActivity.this, "Failure in getting car\n(This shouldn't be open)\n" + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(RentedCarActivity.this, "Failure 2", Toast.LENGTH_LONG).show();
            }
        });
    }


}

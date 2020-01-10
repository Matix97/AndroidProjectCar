package com.example.projekt.car.data;

import android.content.Context;
import android.util.Log;

import com.example.projekt.car.R;
import com.example.projekt.car.Services.ServiceGenerator;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class CarsDataBase {
    private static ArrayList<Car> carArrayList;

    public CarsDataBase(ArrayList<Car> carArrayList) {
        CarsDataBase.carArrayList = carArrayList;
    }

    public CarsDataBase() {
        carArrayList = new ArrayList<>();

        init();
    }

    void init() {
        carArrayList.add(new Car(51.8745, 19.363, true, 54.0, "Seat Toledo", false, false, R.drawable.seat_toledo));
        carArrayList.add(new Car(51.77, 19.48, false, 50.0, "Audi", false, false, R.drawable.auto2));
        carArrayList.add(new Car(51.78, 19.44, true, 69.0, "Jeep", false, false, R.drawable.jeap));
        carArrayList.add(new Car(51.73, 19.47, false, 48.0, "Mercedes", false, false, R.drawable.mercedes));
        carArrayList.add(new Car(51.739458, 19.313909, false, 55.0, "Fiat Tipo", false, false, R.drawable.fiat_tipo));
        carArrayList.add(new Car(51.5745, 19.11, false, 70.0, "Toyota Auris", false, false, R.drawable.toyota_auris));
        carArrayList.add(new Car(51.6745, 19.852, false, 20.5, "KIA Ceed GT", false, false, R.drawable.kia_ceed_gt));
        carArrayList.add(new Car(51.4435, 19.742, false, 54.0, "Seat Toledo2", false, false, R.drawable.seat_toledo));
        carArrayList.add(new Car(51.1335, 19.246, false, 50.0, "Audi2", false, false, R.drawable.auto2));
        carArrayList.add(new Car(51.1421, 19.846, false, 69.0, "Jeep2", false, false, R.drawable.jeap));
        carArrayList.add(new Car(51.5532, 19.888, false, 48.0, "Mercedes2", false, false, R.drawable.mercedes));
        carArrayList.add(new Car(51.7989, 19.945, false, 55.0, "Fiat Tipo2", false, false, R.drawable.fiat_tipo));
        carArrayList.add(new Car(51.9999, 19.521, false, 70.0, "Toyota Auris2", false, false, R.drawable.toyota_auris));
        carArrayList.add(new Car(51.3246, 19.537, false, 20.5, "KIA Ceed GT2", false, false, R.drawable.kia_ceed_gt));
    }

    public void addCar(Car car) {
        carArrayList.add(car);
    }

    public Boolean deleteCar(String carsID) {

        for (Car c : carArrayList) {
            if (c.getCarsID().equals(carsID)) {
                return carArrayList.remove(c);
            }

        }
        return false;
    }

    public Car getCar(String carsID) {
        //  Car car=null;
        for (int i = 0; i < carArrayList.size(); i++) {
            Log.i("getCat", carsID + "     " + carArrayList.get(i).getCarsID());
            if ((carArrayList.get(i).getCarsID()).equals(carsID)) {
                return carArrayList.get(i);
            }

        }
        return null;
    }

    public Boolean saveData(Context ctx) {
        String filename = "CarData";
        FileOutputStream outputStream;

        try {
            outputStream = ctx.openFileOutput(filename, Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(carArrayList);
            outputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void readData(Context ctx) {
        String fileName = "CarData";

        try {
            FileInputStream fileInputStream;
            fileInputStream = ctx.openFileInput(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            carArrayList = (ArrayList<Car>) objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Car> getCarArrayList() {
        if (ServiceGenerator.role.equals("admin"))
            return carArrayList;
        else {
            ArrayList<Car> c = new ArrayList<>();
            for (Car s : carArrayList) {
                if (s.isTaken.equals(false))
                    c.add(s);
            }
            return c;
        }
    }
}

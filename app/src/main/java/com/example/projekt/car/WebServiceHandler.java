package com.example.projekt.car;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.projekt.car.DTOs.Cars;
import com.example.projekt.car.MainActivity;
import com.example.projekt.car.Services.CarService;
import com.example.projekt.car.Services.ServiceGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


class WebServiceHandler extends AsyncTask<Void, Void, List<Cars>> {

    // okienko dialogowe, które każe użytkownikowi czekać
    // private ProgressDialog dialog = new ProgressDialog();


    // metoda wykonywana jest zaraz przed główną operacją (doInBackground())
    // mamy w niej dostęp do elementów UI
    @Override
    protected void onPreExecute() {
        // wyświetlamy okienko dialogowe każące czekać
        //  dialog.setMessage("Czekaj...");
        // dialog.show();
    }

    // główna operacja, która wykona się w osobnym wątku
    // nie ma w niej dostępu do elementów UI
    @Override
    protected List<Cars> doInBackground(Void... voids) {

        CarService carService = ServiceGenerator.createAuthorizedService(CarService.class);
        Call<List<Cars>> call = carService.getCars();
        call.enqueue(new Callback<List<Cars>>() {
            @Override
            public void onResponse(Call<List<Cars>> call, Response<List<Cars>> response) {
                if (response.isSuccessful()) {
                    // Toast.makeText(,response.body().get(0).getModel(),Toast.LENGTH_SHORT).show();
                } else {
                    //   Toast.makeText(MainActivity.this,"Error in GET cars ",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Cars>> call, Throwable t) {
                // Toast.makeText(MainActivity.this,"FAILURE Error in GET cars ",Toast.LENGTH_SHORT).show();
            }
        });

        return null;
    }

    // metoda wykonuje się po zakończeniu metody głównej,
    // której wynik będzie przekazany;
    // w tej metodzie mamy dostęp do UI
    @Override
    protected void onPostExecute(List<Cars> result) {

        // chowamy okno dialogowe
        //  dialog.dismiss();

        try {
            // reprezentacja obiektu JSON w Javie
            // JSONObject json = new JSONObject(result);

            // pobranie pól obiektu JSON i wyświetlenie ich na ekranie
            //  ((TextView) findViewById(R.id.response_id)).setText("id: "
            //        + json.optString("id"));
            //((TextView) findViewById(R.id.response_name)).setText("name: "
            //      + json.optString("name"));

        } catch (Exception e) {
            // obsłuż wyjątek
            Log.d(MainActivity.class.getSimpleName(), e.toString());
        }
    }

    // konwersja z InputStream do String
    public static String streamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;

        try {

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }

            reader.close();

        } catch (IOException e) {
            // obsłuż wyjątek
            Log.d(MainActivity.class.getSimpleName(), e.toString());
        }

        return stringBuilder.toString();
    }
}

package com.example.projekt.car;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.projekt.car.DTOs.Register;
import com.example.projekt.car.Services.NotificationService;
import com.example.projekt.car.Services.ServiceGenerator;
import com.example.projekt.car.Services.UserService;
import com.example.projekt.car.data.PeopleDataBase;
import com.example.projekt.car.data.Person;

import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends Activity {
    Button b1, b2;
    EditText ed1, ed2;
    private String TAG = "Tag: ";
    PeopleDataBase peopleDataBase = new PeopleDataBase();

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState");
    }

    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_z_internet);
        this.userService = ServiceGenerator.createService(UserService.class);

        b1 = findViewById(R.id.button);
        ed1 = findViewById(R.id.editText);
        ed2 = findViewById(R.id.editText2);
        b2 = findViewById(R.id.button2);

        b1.setOnClickListener(v -> {

                tryLogin();

        });
        b2.setOnClickListener(v -> tryRegister());
    }

    private void tryRegister() {
//        Register register = new Register(ed1.getText().toString(), ed1.getText().toString(), ed2.getText().toString(), ed2.getText().toString());
//        Call<Person> call = userService.register(register);
//
//        call.enqueue(new Callback<Person>() {
//            @Override
//            public void onResponse(Call<Person> call, Response<Person> response) {
//                if (response.isSuccessful()) {
//                    Toast.makeText(LoginActivity.this, "Registration succeeded", Toast.LENGTH_SHORT).show();
//
//                } else
//                    Toast.makeText(LoginActivity.this, "Be patient\n sometimes we have some bugs\nor your internet conection isn't perfect ", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<Person> call, Throwable t) {
//                Toast.makeText(LoginActivity.this, "error", Toast.LENGTH_SHORT).show();
//            }
//        });
        peopleDataBase.addPerson(new Person( ed1.getText().toString(),"surname", ed1.getText().toString(),ed2.getText().toString(),"normal"));
        Toast.makeText(LoginActivity.this, "register successful, try to login ", Toast.LENGTH_SHORT).show();
    }

    private void tryLogin()  {
//GDY BACK DZIALA NORMALNIE TO ODKOMNTUEJ
//        Login login = new Login(ed1.getText().toString(), ed2.getText().toString());
//
//        Call<BearerToken> call = userService.login(Credentials.basic(ed1.getText().toString(), ed2.getText().toString()));
//        call.enqueue(new Callback<BearerToken>() {
//            @Override
//            public void onResponse(Call<BearerToken> call, Response<BearerToken> response) {
//                if (response.isSuccessful()) {
//                    ServiceGenerator.bearerToken = response.body().getString();
//                    Toast.makeText(LoginActivity.this, "Login succeeded", Toast.LENGTH_SHORT).show();
//                    newActivity();
//
//                } else
//                    Toast.makeText(LoginActivity.this, "error Jeb", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<BearerToken> call, Throwable t) {
//                Toast.makeText(LoginActivity.this, "error", Toast.LENGTH_SHORT).show();
//            }
//        });

        Boolean loginOK = false;


        for (int i = 0; i < peopleDataBase.size(); i++) {
            if (peopleDataBase.isExist(ed1.getText().toString())) {
                if (peopleDataBase.getPerson(ed1.getText().toString()).getPassword().equals(ed2.getText().toString())) {
                    loginOK = true;
                    ServiceGenerator.role=peopleDataBase.getPerson(ed1.getText().toString()).getRole();
                    break;
                }
            }
        }

        if (loginOK) {
            if(ServiceGenerator.role.equals("admin")){
                Intent i= new Intent(LoginActivity.this, NotificationService.class);
                i.putExtra("token",ServiceGenerator.bearerToken);
                LoginActivity.this.startService(i);
            }

            newActivity();
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "You haven't account or password is incorrect", Toast.LENGTH_LONG);
            toast.show();
        }


    }

    private void newActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Value1", ed1.getText().toString());
        startActivity(intent);
    }
}

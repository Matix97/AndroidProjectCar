package com.example.projekt.car.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.projekt.car.R;
import com.example.projekt.car.data.PeopleDataBase;


public class HelloFragment extends Fragment implements View.OnClickListener {
    private View view;
    private Button nfc;

    public static HelloFragment newInstance() {
        HelloFragment fragment = new HelloFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hello_fragment, container, false);

        ImageView imageView = view.findViewById(R.id.imageView2);
        imageView.setImageResource(R.drawable.sample_face);


        PeopleDataBase peopleDataBase = new PeopleDataBase();

        this.view = view;
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        nfc = view.findViewById(R.id.nfcButton);
        nfc.setOnClickListener(this::onClick);

    }

    @Override//todo Testing get cars-- working correctly
    public void onClick(View v) {
/*        CarService carService =ServiceGenerator.createAuthorizedService(CarService.class);
        Call<List<Cars>> call=carService.getCars();
        call.enqueue(new Callback<List<Cars>>() {
            @Override
            public void onResponse(Call<List<Cars>> call, Response<List<Cars>> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getActivity(),response.body().get(0).getModel(),Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getActivity(),"Error in GET cars ",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Cars>> call, Throwable t) {
                Toast.makeText(getActivity(),"FAILURE Error in GET cars ",Toast.LENGTH_SHORT).show();
            }
        });*/

        newActivity();
        //Toast.makeText(getActivity(),"NFC hear ",Toast.LENGTH_SHORT).show();
    }


    private void newActivity() {
        new AlertDialog.Builder(getContext())
                .setTitle("INFO")
                .setMessage("It's a feature... in the future")
                .setNegativeButton(android.R.string.ok, null)
                .show();
    }

}


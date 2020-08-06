package com.example.madameantoinette;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class Profil extends Fragment {



    public Profil() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profil, container, false);

        final TextView emailF = (TextView) v.findViewById(R.id.email);
        final TextView conseil = (TextView) v.findViewById(R.id.conseil);


        Future<JsonObject> jsonObjectFuture = Ion.with(Profil.this)
                .load("http://10.0.2.2:3000:3000/login")
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        // do stuff with the result or error
                        if (e == null) {
                                Toast.makeText(getActivity(), "" + result, Toast.LENGTH_LONG).show();

                                String email = result.get("email").getAsString();


                                emailF.setText(email);


                            } else {
                                Toast.makeText(getActivity(), "The name does not exist! \n" + e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }


                });

        return v;
    }
}
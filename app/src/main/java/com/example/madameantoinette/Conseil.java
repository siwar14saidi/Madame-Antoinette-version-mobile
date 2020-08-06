package com.example.madameantoinette;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class Conseil extends Fragment {


    public Conseil() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_conseil, container, false);
        final Button buttostart = (Button) v.findViewById(R.id.buttonstart);
        final TextView magic = (TextView) v.findViewById(R.id.magic);
        final TextView text = (TextView) v.findViewById(R.id.text);
        buttostart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String start = buttostart.getText().toString();
                Future<JsonObject> jsonObjectFuture = Ion.with(Conseil.this)
                        .load("http://10.0.2.2:3000/randomquote")
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {
                                if (e == null) {


                                    String email = result.get("email").getAsString();

                                    magic.setText(email);
                                } else {
                                    Toast.makeText(getActivity(), "The name does not exist! \n" + e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
        return v;

    }

}
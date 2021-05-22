package com.example.vaccinenotifier;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.Vector;

public class pincodeFragment extends Fragment {

    private Vector<Integer> center_id;
    private Vector<String> center_name;
    private Vector<Integer> available_capacity;
    private Vector<String> vaccine_name;

    private String date;
    private int pincode;
    private String URL_pincode;

    public pincodeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pincode, container, false);
        EditText et_date = view.findViewById(R.id.editTextDate);
        EditText et_pincode = view.findViewById(R.id.editTextPincode);
        Button btn = view.findViewById(R.id.fg_pincode_btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // action after button
                date = String.valueOf(et_date.getText());
                pincode = Integer.parseInt(String.valueOf(et_pincode.getText()));
                URL_pincode = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin?"+"pincode="+pincode+"&date="+date;

                Toast.makeText(getActivity(), get_string(URL_pincode), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    String get_string(String str){
        return str;
    }

    void get_pinAPIRequest(String URL_pincode){
        //RequestQueue queue = Volley.newRequestQueue(MainActivity.class);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL_pincode, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // get data and store into vector
                        try {
                            JSONArray jsonArray = response.getJSONArray("sessions");
                            for(int i=0; i<jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                // set the fetched data to vector array
                                center_id.add(Integer.parseInt((String) jsonObject.get("center_id")));
                                center_name.add((String) jsonObject.get("name"));
                                available_capacity.add(Integer.parseInt((String) jsonObject.get("available_capacity")));
                                vaccine_name.add((String) jsonObject.get("vaccine"));

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(, "Error to get Request", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
package com.example.vaccinenotifier;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class pincodeFragment extends Fragment {

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
        RecyclerView pin_rc_view = view.findViewById(R.id.pin_rc_list);
        EditText et_date = view.findViewById(R.id.editTextDate);
        EditText et_pincode = view.findViewById(R.id.editTextPincode);
        Button btn = view.findViewById(R.id.fg_pincode_btn);

        // set layout to view holder
        pin_rc_view.setLayoutManager(new LinearLayoutManager(getContext()));

        // set onclick listner
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // action after button
                date = String.valueOf(et_date.getText());
                pincode = Integer.parseInt(String.valueOf(et_pincode.getText()));
                URL_pincode = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin?"+"pincode="+pincode+"&date="+date;

                get_pinAPIRequest(URL_pincode, pin_rc_view);

                Toast.makeText(getActivity(), "geting data", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    void get_pinAPIRequest(String URL_pincode, RecyclerView rc_view){
        RequestQueue queue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL_pincode, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // get data and store into vector
                        try {
                            JSONArray jsonArray = response.getJSONArray("sessions");

                            // data to fetch
                            String[] center_id = new String[jsonArray.length()];
                            String[] center_name = new String[jsonArray.length()];
                            String[] available_capacity = new String[jsonArray.length()];
                            String[] vaccine_name = new String[jsonArray.length()];
                            String[] address = new String[jsonArray.length()];
                            String[] min_age_limit = new String[jsonArray.length()];

                            for(int i=0; i<jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                // set the fetched data to vector array
                                center_id[i] = jsonObject.getString("center_id");
                                center_name[i] = jsonObject.getString("name");
                                available_capacity[i] = jsonObject.getString("available_capacity");
                                vaccine_name[i] = jsonObject.getString("vaccine");
                                address[i] = jsonObject.getString("address");
                                min_age_limit[i] = jsonObject.getString("min_age_limit");

                            }
                            rc_view.setAdapter(new pinAdapter(
                                    center_id, center_name, available_capacity, vaccine_name,address, min_age_limit
                            ));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Error to get Request "+error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonObjectRequest);
    }
}
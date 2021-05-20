package com.example.vaccinenotifier;

import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class getRequest extends AppCompatActivity {
    private String URL_states = "https://cdn-api.co-vin.in/api/v2/admin/location/states";
    private String URL_dist_base = "https://cdn-api.co-vin.in/api/v2/admin/location/districts/";
    private String URL_base = "";

    RequestQueue requestQueue = Volley.newRequestQueue(getRequest.this);

    public int get_state_id(){
        // get state id form URL_state
        JsonObjectRequest mjsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL_states, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray mjsonArray = response.getJSONArray("states");
                            for(int i=0; i<mjsonArray.length(); i++){
                                JSONObject mjsonObject = mjsonArray.getJSONObject(i);

                                // json filds
                                int state_id = mjsonObject.getInt("state_id");
                                String state_name = mjsonObject.getString("state_name");
                                System.out.println("######## State Name: "+state_name);
                                System.out.println("######## State id: "+state_id);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error to get URL_state");
            }
        });
        return 0;
    }
}

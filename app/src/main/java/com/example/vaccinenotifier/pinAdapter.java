package com.example.vaccinenotifier;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class pinAdapter extends RecyclerView.Adapter<pinAdapter.pinviewHolder> {

    private String[] center_id, center_name, available_capacity, vaccine_name, address, min_age_limit;

    public pinAdapter(
            String[] center_id,
            String[] center_name,
            String[] available_capacity,
            String[] vaccine_name,
            String[] address,
            String[] min_age_limit
    ){
        this.center_id = center_id;
        this.center_name = center_name;
        this.available_capacity = available_capacity;
        this.vaccine_name = vaccine_name;
        this.address = address;
        this.min_age_limit = min_age_limit;
    }

    @NonNull
    @Override
    public pinviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_layout, parent, false);
        return (new pinviewHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull pinAdapter.pinviewHolder holder, int position) {
        String str_center_id = center_id[position];
        String str_center_name = center_name[position];
        String str_available_capacity = available_capacity[position];
        String str_vaccine_name = vaccine_name[position];
        String str_address = address[position];
        String str_age_limit = min_age_limit[position];

        holder.center_id.setText("Center ID :"+str_center_id);
        holder.center_name.setText("Center Name : "+str_center_name);
        holder.available_capacity.setText("Available Capacity : "+str_available_capacity);
        holder.vaccine_name.setText("Vaccine Name : "+str_vaccine_name);
        holder.address.setText("Address : "+str_address);
        holder.age_limit.setText("Minimum age limit : "+str_age_limit);
    }

    @Override
    public int getItemCount() {
        return center_id.length;
    }

    public class pinviewHolder extends RecyclerView.ViewHolder{
        TextView center_id;
        TextView center_name;
        TextView available_capacity;
        TextView vaccine_name;
        TextView address;
        TextView age_limit;
        public pinviewHolder(@NonNull View itemView) {
            super(itemView);
            center_id = itemView.findViewById(R.id.list_center_id);
            center_name = itemView.findViewById(R.id.list_center_name);
            available_capacity = itemView.findViewById(R.id.list_available_capacity);
            vaccine_name = itemView.findViewById(R.id.list_vaccine_name);
            address = itemView.findViewById(R.id.list_address);
            age_limit = itemView.findViewById(R.id.list_age_limit);
        }
    }
}

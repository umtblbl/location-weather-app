package com.example.locationweatherapp.Ui.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.locationweatherapp.R;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.MyViewHolder> {

    Context context;
    List<String> citiesList;
    List<String> checkedList;
    ItemClickListener clickListener;
    int checkedCount;

    public CitiesAdapter(Context context, List<String> citiesList,List<String> checkedList, int checkedCount, ItemClickListener clickListener) {
        this.context = context;
        this.citiesList = citiesList;
        this.clickListener = clickListener;
        this.checkedCount = checkedCount;
        this.checkedList = checkedList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cities, parent, false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.checkBoxCity.setText(citiesList.get(position));

        for (String city:checkedList) {
                    if(holder.checkBoxCity.getText().equals(city)) {
                        holder.checkBoxCity.setChecked(true);
                    }

        }

        holder.checkBoxCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //En az 1 şehrin seçili kalması için tasarladığım algoritma
                if(holder.checkBoxCity.isChecked()) {
                    checkedCount++;
                }
                else if(!holder.checkBoxCity.isChecked()) {
                    if(checkedCount <= 1) {
                        Toast.makeText(context, "At least one city must remain selected.", Toast.LENGTH_SHORT).show();
                        holder.checkBoxCity.setChecked(true);
                    }
                    else
                        checkedCount--;
                }

                clickListener.CityItemClick(position, String.valueOf(holder.checkBoxCity.getText()), holder.checkBoxCity.isChecked(), checkedCount);
            }
        });

    }

    @Override
    public int getItemCount() {
        return citiesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        CheckBox checkBoxCity;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            checkBoxCity = itemView.findViewById(R.id.checkBoxCity);

        }

    }



}

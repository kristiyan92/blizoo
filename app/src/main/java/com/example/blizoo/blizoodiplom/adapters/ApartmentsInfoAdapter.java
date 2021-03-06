package com.example.blizoo.blizoodiplom.adapters;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.blizoo.blizoodiplom.R;
import com.example.blizoo.blizoodiplom.models.ApartmentsInfo;

import java.util.ArrayList;


import android.widget.ImageView;
import android.widget.Toast;

public class ApartmentsInfoAdapter extends ArrayAdapter<ApartmentsInfo> {
    private String TAG = "NearbyClients Adapter";

    private Activity mContext;
    private ArrayList<ApartmentsInfo> mApartmentsList;

    public ApartmentsInfoAdapter(Activity context,
                                 ArrayList<ApartmentsInfo> contractList) {
        super(context, R.layout.apartments_info_grid_item, contractList);
        this.mContext = context;
        this.mApartmentsList = contractList;
    }

    public int getCount() {
        return mApartmentsList.size();
    }

    public ApartmentsInfo getItem(int position) {
        return mApartmentsList.get(position);
    }

    static class ViewHolder {

        public ImageView imgGridItem;
        public TextView apartmentNumber;


    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = mContext.getLayoutInflater();
            rowView = inflater.inflate(R.layout.apartments_info_grid_item, null);
            ViewHolder viewHolder = new ViewHolder();

            viewHolder.imgGridItem = (ImageView) rowView.findViewById(R.id.imgGridItem);
            viewHolder.apartmentNumber = (TextView)rowView.findViewById(R.id.apartment_number);
            // viewHolder.tvLastName = (TextView) rowView.findViewById(R.id.client_last_name_textview);
//            viewHolder.tvPhone = (TextView) rowView.findViewById(R.id.client_phone_textview);

            rowView.setTag(viewHolder);
        }

        ViewHolder holder = (ViewHolder) rowView.getTag();

        ApartmentsInfo apartmentsObject = mApartmentsList.get(position);

        holder.imgGridItem.setImageResource(R.drawable.ic_launcher);
        holder.apartmentNumber.setText(apartmentsObject.getNumber());

        if(apartmentsObject.getNumber().equals(apartmentsObject.getApartmentOfSelectedClient())) {
            holder.imgGridItem.setImageResource(R.drawable.ic_launcher);
            holder.apartmentNumber.setTextColor(Color.RED);
            holder.imgGridItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,"Това съм аз, ... Ааанастасия :)",Toast.LENGTH_LONG).show();
                }
            });
        } else {
            holder.imgGridItem.setImageResource(R.drawable.ic_launcher);
        }
        //holder.imgGridItem.setText(clientContractObject.getName());
      /*  holder.tvLastName.setText(clientContractObject.getFamily());
        holder.tvPhone.setText(clientContractObject.getPhone());*/
        return rowView;
    }
}
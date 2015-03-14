package com.example.blizoo.blizoodiplom.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.blizoo.blizoodiplom.R;
import com.example.blizoo.blizoodiplom.models.NearbyClientsContracts;


import java.util.ArrayList;

public class NearbyClientsContractsAdapter extends ArrayAdapter<NearbyClientsContracts> {

    private String TAG = "NearbyClients Adapter";

    private Activity mContext;
    private ArrayList<NearbyClientsContracts> mContractsList;

    public NearbyClientsContractsAdapter(Activity context,
                                         ArrayList<NearbyClientsContracts> contractList) {
        super(context, R.layout.nearby_client_contract_list_item, contractList);
        this.mContext = context;
        this.mContractsList = contractList;
    }

    public int getCount() {
        return mContractsList.size();
    }

    public NearbyClientsContracts getItem(int position) {
        return mContractsList.get(position);
    }

    static class ViewHolder {

        public TextView tvName, tvLastName, tvPhone;


    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = mContext.getLayoutInflater();
            rowView = inflater.inflate(R.layout.nearby_client_contract_list_item, null);
            ViewHolder viewHolder = new ViewHolder();

            viewHolder.tvName = (TextView) rowView.findViewById(R.id.client_name_textview);
            viewHolder.tvLastName = (TextView) rowView.findViewById(R.id.client_last_name_textview);
            viewHolder.tvPhone = (TextView) rowView.findViewById(R.id.client_phone_textview);

            rowView.setTag(viewHolder);
        }

        ViewHolder holder = (ViewHolder) rowView.getTag();

        NearbyClientsContracts clientContractObject = mContractsList.get(position);

        holder.tvName.setText(clientContractObject.getName());
        holder.tvLastName.setText(clientContractObject.getFamily());
        holder.tvPhone.setText(clientContractObject.getPhone());
        return rowView;
    }

}

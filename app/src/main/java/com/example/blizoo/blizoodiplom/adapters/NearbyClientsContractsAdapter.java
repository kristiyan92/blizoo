package com.example.blizoo.blizoodiplom.adapters;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.blizoo.blizoodiplom.AparmentsInfoFragment;
import com.example.blizoo.blizoodiplom.MainActivity;
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
        public RelativeLayout rootClientContractLayout;


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
            viewHolder.rootClientContractLayout = (RelativeLayout) rowView.findViewById(R.id.root_client_contract_layout);


            rowView.setTag(viewHolder);
        }

        ViewHolder holder = (ViewHolder) rowView.getTag();

      final NearbyClientsContracts clientContractObject = mContractsList.get(position);

        holder.tvName.setText(clientContractObject.getName());
        holder.tvLastName.setText(clientContractObject.getFamily());
        holder.tvPhone.setText(clientContractObject.getPhone());

        holder.rootClientContractLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("id", clientContractObject.getClientId());

                Fragment apartmentInfo = new AparmentsInfoFragment();
                apartmentInfo.setArguments(bundle);
                FragmentTransaction transaction = ((FragmentActivity) mContext)
                        .getSupportFragmentManager().beginTransaction();
                MainActivity.backStack.add(1);
                transaction.addToBackStack("AparmentsInfoFragment");
                transaction.replace(R.id.content_frame, apartmentInfo).commit();
            }
        });
        return rowView;
    }

}

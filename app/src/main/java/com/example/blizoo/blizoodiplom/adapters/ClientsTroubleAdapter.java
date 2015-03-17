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
import com.example.blizoo.blizoodiplom.models.ClientsTrouble;
import com.example.blizoo.blizoodiplom.models.NearbyClientsContracts;

import java.util.ArrayList;

public class ClientsTroubleAdapter extends ArrayAdapter<ClientsTrouble> {

    private String TAG = "NearbyClients Adapter";

    private Activity mContext;
    private ArrayList<ClientsTrouble> mTroubleList;

    public ClientsTroubleAdapter(Activity context,
                                 ArrayList<ClientsTrouble> contractList) {
        super(context, R.layout.client_trouble_list_item, contractList);
        this.mContext = context;
        this.mTroubleList = contractList;
    }

    public int getCount() {
        return mTroubleList.size();
    }

    public ClientsTrouble getItem(int position) {
        return mTroubleList.get(position);
    }

    static class ViewHolder {

        public TextView tvName, tvLastName, tvPhone;
      //  public RelativeLayout rootClientContractLayout;


    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = mContext.getLayoutInflater();
            rowView = inflater.inflate(R.layout.client_trouble_list_item, null);
            ViewHolder viewHolder = new ViewHolder();

            viewHolder.tvName = (TextView) rowView.findViewById(R.id.tb_client_first_name);
            viewHolder.tvLastName = (TextView) rowView.findViewById(R.id.tb_client_last_name);
            viewHolder.tvPhone = (TextView) rowView.findViewById(R.id.client_phone_textview);
//            viewHolder.rootClientContractLayout = (RelativeLayout) rowView.findViewById(R.id.root_client_contract_layout);


            rowView.setTag(viewHolder);
        }

        ViewHolder holder = (ViewHolder) rowView.getTag();

      final ClientsTrouble  clientsTroubleObject = mTroubleList.get(position);

        holder.tvName.setText(clientsTroubleObject.getName());
        holder.tvLastName.setText(clientsTroubleObject.getFamily());
        holder.tvPhone.setText(clientsTroubleObject.getPhone());

   /*     holder.rootClientContractLayout.setOnClickListener(new View.OnClickListener() {
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
        });*/
        return rowView;
    }

}

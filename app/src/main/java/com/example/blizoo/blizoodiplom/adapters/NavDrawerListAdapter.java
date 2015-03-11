package com.example.blizoo.blizoodiplom.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.blizoo.blizoodiplom.R;
import com.example.blizoo.blizoodiplom.models.NavDrawerItem;

import java.util.ArrayList;

public class NavDrawerListAdapter extends BaseAdapter {

    private Context context;

    // create array which hold elements from object from NavDrawerItem, elements in this case
    // are items in slide menu
    private ArrayList<NavDrawerItem> navDrawerItems;

    public NavDrawerListAdapter(Context context, ArrayList<NavDrawerItem> navDrawerItems) {
        this.context = context;
        this.navDrawerItems = navDrawerItems;
    }

    //Get size of slide menu items
    @Override
    public int getCount() {
        return navDrawerItems.size();
    }

    //Get position of every items in slide menu
    @Override
    public Object getItem(int position) {
        return navDrawerItems.get(position);
    }

    //Get id of every item in slide menu
    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        private ImageView imgIcon;
        private TextView txtTitle;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.menu_list_item, null);

            ViewHolder viewHolder = new ViewHolder();
            //Initialize slide menu elements
            viewHolder.imgIcon = (ImageView) convertView.findViewById(R.id.icon);
            viewHolder.txtTitle = (TextView) convertView.findViewById(R.id.title);
            convertView.setTag(viewHolder);
        }

        ViewHolder holder = (ViewHolder) convertView.getTag();
        //set to items there values
        holder.imgIcon.setImageResource(navDrawerItems.get(position).getIcon());
        holder.txtTitle.setText(navDrawerItems.get(position).getTitle());

        NavDrawerItem item = navDrawerItems.get(position);
        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.title);

        // If the item is selected change it text color and image
        if (item.isSelected()) {
            imgIcon.setImageResource(item.getIcon_selected());
            txtTitle.setTextColor(context.getResources().getColor(R.color.blue));
        } else {
            imgIcon.setImageResource(item.getIcon());
            txtTitle.setTextColor(Color.BLACK);

        }

        // Set item title
        txtTitle.setText(item.getTitle());


        return convertView;
    }

}

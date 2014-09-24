package com.example.jks.databse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jks.tabswithswipe.R;

import java.util.ArrayList;

/**
 * Created by jks on 22.09.14.
 */
public class StorageAdapter extends BaseAdapter{

    private ArrayList<FoodData> listData;

    private LayoutInflater layoutInflater;

    public StorageAdapter(Context context, ArrayList listData){
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.item_storage_layout, null);
            holder = new ViewHolder();
            holder.nameView = (TextView) convertView.findViewById(R.id.name);
            holder.quantityView = (TextView) convertView.findViewById(R.id.quantity);
            holder.addedTimeView = (TextView) convertView.findViewById(R.id.added);
            holder.shoppingListView = (TextView) convertView.findViewById(R.id.shoplist);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.nameView.setText(listData.get(position).getName());
        holder.quantityView.setText(Integer.toString(listData.get(position).getQuantity()));
        holder.addedTimeView.setText(listData.get(position).getAdded_time());
        holder.shoppingListView.setText(listData.get(position).getShopping_flag());

        return convertView;

    }

    static class ViewHolder{
        TextView nameView;
        TextView quantityView;
        TextView addedTimeView;
        TextView shoppingListView;
    }



    @Override
    public void notifyDataSetChanged(){
        super.notifyDataSetChanged();
    }
}

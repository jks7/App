package com.example.jks.tabswithswipe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jks.databse.DatabaseHandler;
import com.example.jks.databse.FoodData;
import com.example.jks.databse.MyDateHandler;
import com.example.jks.databse.ShoppingListAdapter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jks on 16.09.14.
 */
public class ShoppingListFragment extends Fragment{
    private ListView myListView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_shoppinglist, container, false);
        final DatabaseHandler db = new DatabaseHandler(getActivity().getApplicationContext());

        myListView = (ListView) rootView.findViewById(R.id.listViewShoppingForDb);

        List myListData = db.getAllShoppingNames();

        myListView.setAdapter(new ShoppingListAdapter(getActivity().getApplicationContext(),(ArrayList) myListData));

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final FoodData foodData = (FoodData) myListView.getItemAtPosition(position);

                try {
                    Toast.makeText(getActivity(), MyDateHandler.compareDates(foodData.getAdded_time()), Toast.LENGTH_LONG).show();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                myListView.invalidateViews();
            }
        });
        return rootView;
    }
}

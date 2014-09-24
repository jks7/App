package com.example.jks.tabswithswipe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.jks.databse.DatabaseHandler;
import com.example.jks.databse.FoodData;
import com.example.jks.databse.StorageAdapter;

import java.util.List;

/**
 * Created by jks on 16.09.14.
 */
public class StorageFragment extends Fragment {

    private ListView myListView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_storage, container, false);
        final DatabaseHandler db = new DatabaseHandler(getActivity().getApplicationContext());
        List myListData = db.getAllFoodData();

        myListView = (ListView) rootView.findViewById(R.id.listViewStorageForDb);
        final StorageAdapter storageAdapter;
        storageAdapter = new StorageAdapter(getActivity().getApplicationContext(), (java.util.ArrayList) myListData);
        myListView.setAdapter(storageAdapter);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder alert = new AlertDialog.Builder(container.getContext());
                final FoodData foodData = (FoodData) myListView.getItemAtPosition(position);
                alert.setTitle("Test");
                alert.setMessage("Change Quantity of " + foodData.getName());
                final EditText input = new EditText(container.getContext());
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                alert.setView(input);
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int srt = Integer.parseInt(String.valueOf(input.getText()));
                        db.updateQuantitiy(foodData.getName(), srt);
                    }
                });
                alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alert.setNeutralButton("DELETE", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.deleteItem(foodData.getName());
                    }
                });

                alert.show();
                storageAdapter.notifyDataSetChanged();
                List list = db.getAllFoodData();
                StorageAdapter storageAdapter2 = new StorageAdapter(getActivity().getApplicationContext(), (java.util.ArrayList) list);
                myListView.setAdapter(storageAdapter2);
                myListView.invalidateViews();
            }
        });


        Button btn_delete = (Button) rootView.findViewById(R.id.btn_storage_deleteAll);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteAll();
            }
        });
        Button btn_refresh = (Button) rootView.findViewById(R.id.btn_storage_refresh);
        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List list = db.getAllFoodData();
                StorageAdapter storageAdapter2 = new StorageAdapter(getActivity().getApplicationContext(), (java.util.ArrayList) list);
                myListView.setAdapter(storageAdapter2);
                myListView.invalidateViews();
            }
        });


        return rootView;
    }
}

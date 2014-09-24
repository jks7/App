package com.example.jks.tabswithswipe;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jks.databse.DatabaseHandler;
import com.example.jks.databse.MyDateHandler;

import java.util.List;

/**
 * Created by jks on 16.09.14.
 */
public class FoodInputFragment extends Fragment{

    Spinner spinner;
    Button button;
    EditText editTextName;
    EditText editTextQuantity;
    CheckBox checkBox;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_foodinput, container, false);
        spinner = (Spinner) rootView.findViewById(R.id.spinner);
        button = (Button) rootView.findViewById(R.id.btn_add);
        checkBox = (CheckBox) rootView.findViewById(R.id.checkbox_shopList);
        loadSpinnerData();

        editTextName = (EditText) rootView.findViewById(R.id.input_name);
        editTextQuantity = (EditText) rootView.findViewById(R.id.input_quantity);
     //   spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextName.getText().toString().length()<=0){
                    Toast.makeText(getActivity(), "Insert Input",Toast.LENGTH_LONG).show();
                }else {
                    String foodName = editTextName.getText().toString();
                    int foodQuantity = Integer.parseInt(editTextQuantity.getText().toString());
                    String foodShoppingList;
                    if (checkBox.isChecked()) {
                        foodShoppingList = "True";
                    } else {
                        foodShoppingList = "False";
                    }

                        String foodAddedtime = MyDateHandler.getDateTime();

                        if (foodName.trim().length() > 0) {
                            DatabaseHandler db = new DatabaseHandler(getActivity().getApplicationContext());
                            db.insertFood(foodName, foodQuantity, foodShoppingList, foodAddedtime);
                            editTextName.setText("");
                            editTextQuantity.setText("");

                            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(editTextName.getWindowToken(), 0);
                            imm.hideSoftInputFromWindow(editTextQuantity.getWindowToken(), 0);

                            loadSpinnerData();
                        } else {
                            Toast.makeText(getActivity(), "Insert Input", Toast.LENGTH_LONG).show();
                   }
               }
            }



        });
        return rootView;
    }

    private void loadSpinnerData() {

        DatabaseHandler db = new DatabaseHandler(getActivity().getApplicationContext());

        List<String> foodname = db.getAllNames();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, foodname);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);

    }
}

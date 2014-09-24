package com.example.jks.tabswithswipe;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class AutomaticFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_automatic_fragment, container, false);
        final Context context = getActivity().getApplicationContext();
        Button button = (Button) rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(container.getContext());
                builder.setTitle("TTITEL");
                builder.setItems(new CharSequence[]{"but1", "but2" , "but3", "but4"},new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                Toast.makeText(context,"1",Toast.LENGTH_LONG).show();
                                break;
                            case 1:
                                Toast.makeText(context,"2",Toast.LENGTH_LONG).show();
                                break;
                            case 2:
                                Toast.makeText(context,"3",Toast.LENGTH_LONG).show();
                                break;
                            case 3:
                                Toast.makeText(context,"4",Toast.LENGTH_LONG).show();
                                break;

                        }
                    }
                });
                builder.show();
            }
        });

        return rootView;
    }
}

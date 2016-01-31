package ru.sgtraf.mymkala;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by gad on 30.01.2016.
 */
public class Fragment1 extends Fragment {
    private ArrayList<HashMap<String, Object>> result;
    View v;
    String temp;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.fragment1, container, false);
// искать view до создания
        TextView text = (TextView) v.findViewById(R.id.textView5);


        text.setText(temp);


        return v;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            result =  new RequestWether().test();
        } catch (IOException e) {
            e.printStackTrace();
        }

        temp = result.get(0).get("temperature").toString();
        System.out.println(result.get(0).get("temperature").toString());



    }
}

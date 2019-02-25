package com.example.danie.klkl;

import android.app.ListActivity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ListActivity {

    private static final List<People> peoples = new ArrayList<People>();
    private String[] names ;
    private String[] countries;


    public String makePath(String b){
        char[] a = b.toCharArray();
        for (int i = 0; i < a.length; i++) {
            if(a[i]>='A' && a[i]<='Z'){
                a[i] -= 'A'-'a';
            }else if(a[i]==' '){
                a[i] = '_';
            }
        }
        return "drawable/flag_"+String.copyValueOf(a)+".png";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        names = getResources().getStringArray(
                R.array.Names);
        countries = this.getResources().getStringArray(
                R.array.countries);
        for (int i = 0; i < names.length; i++) {

            peoples.add(new People(names[i],
                    BitmapFactory.decodeFile(makePath(countries[i]))
            ));

        }
        ListView lv = (ListView)findViewById(R.id.list);
        ArrayAdapter<People> adapter = new PeopleAdapter(this);

        lv.setAdapter(adapter);
    }

    private static class People {
        public final String name;
        public final Bitmap country;

        public People(String name, Bitmap country) {
            this.name = name;
            this.country = country;
        }
    }

    private class PeopleAdapter extends ArrayAdapter<People> {

        public PeopleAdapter(Context context) {
            super(context, R.layout.activity_main , peoples);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            People people = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext())
                        .inflate(R.layout.activity_main, null);
            }
            ((TextView) convertView.findViewById(R.id.namss))
                    .setText(people.name);
            ((ImageView) convertView.findViewById(R.id.flg))
                    .setImageBitmap(people.country);
            return convertView;
        }

    }
}
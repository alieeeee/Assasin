package com.parse.starter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kuenj on 1/22/2016.
 */
public class WaitingRoom extends Activity {
    private ArrayList<String> data = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waiting_room);
        ListView victimList = (ListView) findViewById(R.id.chooseVictimList);
        generateListContent();
        victimList.setAdapter(new MyListAdapter(this, R.layout.victim_list_item, data));
        //ArrayAdapter<String> chooseVictimListAdapter = new ArrayAdapter<String>(this, android.R.layout.victim_list_item, )
    }

    private void generateListContent(){
        for(int i = 0; i < 10; i++)
        {
            data.add("this is a row" + i);
        }
    }

    private class MyListAdapter extends ArrayAdapter<String> {
        private int layout;

        private MyListAdapter(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
            layout = resource;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder mainViewholder = null;
            if(convertView == null){
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.button = (Button) convertView.findViewById(R.id.victim_item_btn);
                viewHolder.button.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "Matching you with victim", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent("android.intent.action.AssassinActivity"));
                    }
                });
                convertView.setTag(viewHolder);
            }
            else{
                mainViewholder = (ViewHolder) convertView.getTag();
            }

            return convertView;
        }
    }
    public class ViewHolder{
        Button button;
    }
}



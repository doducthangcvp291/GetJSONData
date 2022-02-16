package com.example.json;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class avatarAdapter extends BaseAdapter {

    private  Context context;
    private List<avatar> avatarList;
    private  int layout;

    public avatarAdapter(Context context, List<avatar> avatarList, int layout) {
        this.context = context;
        this.avatarList = avatarList;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return avatarList.size();
    }


    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView =  inflater.inflate(layout, null);
        ImageView avatarx = (ImageView)  convertView.findViewById(R.id.imageView);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView email = (TextView) convertView.findViewById(R.id.email);

        avatar data = avatarList.get(position);
        name.setText(data.getName());
        email.setText(data.getEmail());
        String link=data.getLinkavatar();

        Picasso.get().load(link).into(avatarx);
            return convertView;
    }
}
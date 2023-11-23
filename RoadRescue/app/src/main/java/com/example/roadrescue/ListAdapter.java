package com.example.roadrescue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.roadrescue.User;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<User> {
    private Context mContext;
    private int mResource;

    public ListAdapter(Context context, int resource, ArrayList<User> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
        }

        User user = getItem(position);

        if (user != null) {
            ImageView profileImage = convertView.findViewById(R.id.profile_pic);
            TextView testName = convertView.findViewById(R.id.testName);
            TextView testMessage = convertView.findViewById(R.id.testMassage);

            profileImage.setImageResource(user.getImageId());
            testName.setText(user.getName());
            testMessage.setText(user.getLastMassage());
        }

        return convertView;
    }
}

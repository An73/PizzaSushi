package com.dkotenko.pizzasushi.pizzasushi;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class InfoFragment extends Fragment {


    public InfoFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String name = getArguments().getString(MyCursorAdapter.KEY_NAME);
        String cost = getArguments().getString(MyCursorAdapter.KEY_COST);
        String description = getArguments().getString(MyCursorAdapter.KEY_DESCRIPTION);
        int    image_id = getArguments().getInt(MyCursorAdapter.KEY_IMAGE_ID);

        View view = inflater.inflate(R.layout.fragment_info, container, false);
        TextView nameView = (TextView) view.findViewById(R.id.name_info_view);
        TextView costView = (TextView) view.findViewById(R.id.cost_info_view);
        TextView descriptionView = (TextView) view.findViewById(R.id.description_info_view);
        ImageView imageView = (ImageView) view.findViewById(R.id.image_info_view);


        nameView.setText(name);
        costView.setText("Стоимость " + cost);
        descriptionView.setText(description);
        imageView.setImageResource(image_id);
        return view;
    }

}

package com.example.nnnn;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FoodAdapter extends ArrayAdapter<Food> {

    private Context context;
    private ArrayList<Food> foods;

    public FoodAdapter(Context context, int resource, ArrayList<Food> foods) {
        super(context, resource, foods);
        this.context = context;
        this.foods = foods;
    }
// Inside FoodAdapter class

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.food_item, parent, false);
        }

        Food food = foods.get(position);

        TextView nameTextView = convertView.findViewById(R.id.textViewName);
        TextView priceTextView = convertView.findViewById(R.id.textViewPrice);
        ImageView imageView = convertView.findViewById(R.id.imageViewFood);
        ImageView deleteImageView = convertView.findViewById(R.id.imageViewDelete);
        ImageView imageViewEdit = convertView.findViewById(R.id.imageViewEdit);

        nameTextView.setText(food.getName());
        priceTextView.setText(food.getPrice());

        if (food.getImage() != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(food.getImage(), 0, food.getImage().length);
            imageView.setImageBitmap(bitmap);
        }

        // Set up click listener for the edit icon
        imageViewEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).showDialogUpdate((MainActivity) context, position);
            }
        });

        // Set up click listener for the delete icon
        deleteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteHelper sqLiteHelper = new SQLiteHelper(context, "FoodDB.sqlite", null, 1);
                sqLiteHelper.deleteData(food.getId());
                ((MainActivity) context).loadData(); // Refresh the list
            }
        });

        return convertView;
    }

}

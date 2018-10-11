package com.doordash.doordashlite.ui.restaurants;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.doordash.doordashlite.R;
import com.doordash.doordashlite.data.Restaurant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RestaurantsRecyclerAdapter extends RecyclerView.Adapter<RestaurantsRecyclerAdapter.ViewHolder> {

    private Fragment fragment;
    private RestaurantsContract.Presenter presenter;
    private List<Restaurant> restaurants;
    private SharedPreferences sharedPreferences;

    private final OnItemClickListener listener;

    public RestaurantsRecyclerAdapter(Fragment fragment, RestaurantsContract.Presenter presenter,
                                      List<Restaurant> restaurants, OnItemClickListener listener) {
        this.fragment = fragment;
        this.presenter = presenter;
        this.restaurants = restaurants;
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_distance)
        TextView tvDistance;
        @BindView(R.id.tv_restaurant_name)
        TextView tvRestaurantName;
        @BindView(R.id.tv_more_info)
        TextView tvMoreInfo;
        @BindView(R.id.iv_Res_Icon)
        ImageView ivRestaurantIcon;
        @BindView(R.id.tv_button)
        Button button;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public RestaurantsRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        sharedPreferences = context.getSharedPreferences("doordashLite", Context.MODE_PRIVATE);

        View view = inflater.inflate(R.layout.item_restaurant, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.tvRestaurantName = view.findViewById(R.id.tv_restaurant_name);
        viewHolder.tvMoreInfo = view.findViewById(R.id.tv_more_info);
        viewHolder.tvDistance = view.findViewById(R.id.tv_distance);
        viewHolder.ivRestaurantIcon = view.findViewById(R.id.iv_Res_Icon);
        viewHolder.button = view.findViewById(R.id.tv_button);

        view.setOnClickListener(v -> listener.onItemClick(restaurants.get(viewHolder.getAdapterPosition())));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RestaurantsRecyclerAdapter.ViewHolder viewHolder, int position) {

        Restaurant restaurant = restaurants.get(position);


        viewHolder.tvRestaurantName.setText(restaurant.getName());
        viewHolder.tvMoreInfo.setText(restaurant.getDescription());
        viewHolder.tvDistance.setText(restaurant.getStatus());

        Glide.with(fragment).load(restaurant.getImageURL()).placeholder(
                R.drawable.drawable_placeholder).error(
                R.drawable.drawable_placeholder).into(viewHolder.ivRestaurantIcon);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int s = sharedPreferences.getInt(restaurant.getName(),-1);
        if(s == restaurant.getRestaurantId()) {
            viewHolder.button.setText("Liked");
        }else {
            viewHolder.button.setText("Like");
        }
        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int s = sharedPreferences.getInt(restaurant.getName(),-1);
                if(s == restaurant.getRestaurantId()){
                    editor.remove(restaurant.getName());
                    editor.apply();
                    viewHolder.button.setText("Like");
                }else {
                    viewHolder.button.setText("Liked");
                    editor.putInt(restaurant.getName(), restaurant.getRestaurantId());
                    editor.apply();
                }
                //editor.commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public void clear() {
        int size = getItemCount();
        //photos.clear();
        notifyItemRangeRemoved(0, size);
    }

    public void addAll(List<Restaurant> photos) {
        //int prevSize = getItemCount();
        this.restaurants.addAll(photos);
        notifyDataSetChanged();
        //notifyItemRangeInserted(prevSize, photos.size());
    }

    public interface OnItemClickListener {
        void onItemClick(Restaurant restaurant);
    }
}

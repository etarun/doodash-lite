package com.doordash.doordashlite.ui.restaurants;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public RestaurantsRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_restaurant, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.tvRestaurantName = view.findViewById(R.id.tv_restaurant_name);
        viewHolder.tvMoreInfo = view.findViewById(R.id.tv_more_info);
        viewHolder.tvDistance = view.findViewById(R.id.tv_distance);
        viewHolder.ivRestaurantIcon = view.findViewById(R.id.iv_Res_Icon);

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

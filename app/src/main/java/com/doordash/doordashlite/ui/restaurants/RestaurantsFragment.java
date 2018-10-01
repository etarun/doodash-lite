package com.doordash.doordashlite.ui.restaurants;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.doordash.doordashlite.BaseFragmentInteractionListener;
import com.doordash.doordashlite.R;
import com.doordash.doordashlite.dagger.AppComponent;
import com.doordash.doordashlite.data.Restaurant;
import com.doordash.doordashlite.mvp.BaseView;
import com.doordash.doordashlite.ui.restaurantDetails.RestaurantDetailActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.doordash.doordashlite.Properties.RESTAURANT;


/**
 * The {@link Fragment} that receives restaurant data from its {@link RestaurantsContract.Presenter} and
 * renders a list of restaurants and also handles user actions, such as clicks on restaurant,
 * and passes it to its {@link RestaurantsContract.Presenter}.
 */
public class RestaurantsFragment extends BaseView implements RestaurantsContract.View {

    RecyclerView rvRestaurants;
    ProgressBar progressBar;

    @Inject
    RestaurantsContract.Presenter presenter;
    private RestaurantsRecyclerAdapter recyclerAdapter;

    private RestaurantsComponent restaurantsComponent;
    private BaseFragmentInteractionListener fragmentInteractionListener;
    protected AppComponent appComponent;
    private List<Restaurant> restaurants;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentInteractionListener = (BaseFragmentInteractionListener) getActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initMemberVariables();
        setRetainInstance(true);
    }

    private void initMemberVariables() {
        restaurants = new ArrayList<>();
    }

    @Override
    public void showRestaurants(List<Restaurant> restaurants) {
        recyclerAdapter.addAll(restaurants);
    }

    @Override
    public void setProgressBar(boolean show) {
        if (show)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMessage() {
        showToastMessage("Unable to Load Restaurants Data");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initDepedencyInjection();
        View rootView = inflater.inflate(R.layout.fragment_restaurants, container, false);
        rvRestaurants = rootView.findViewById(R.id.rvRestaurants);
        progressBar = rootView.findViewById(R.id.progressBar);

        initViews(rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState == null) {
            initData();
        }
    }

    private void initData() {
        refreshPhotos();
    }

    private void refreshPhotos() {
        presenter.getRestaurants();
    }

    private void initViews(View rootView) {
        recyclerAdapter = new RestaurantsRecyclerAdapter(this, presenter, restaurants, restaurant -> startDetailsActivity(restaurant));
        rvRestaurants.setAdapter(recyclerAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvRestaurants.getContext(),
                linearLayoutManager.getOrientation());
        rvRestaurants.addItemDecoration(dividerItemDecoration);
        rvRestaurants.setLayoutManager(linearLayoutManager);
    }

    private void startDetailsActivity(Restaurant restaurant) {
        presenter.getRestaurantData(restaurant.getRestaurantId());
    }

    @Override
    public void navigateToRestaurantDetails(Restaurant restaurant) {
        Intent i = new Intent(this.getContext(), RestaurantDetailActivity.class);
        i.putExtra(RESTAURANT, restaurant);
        startActivity(i);
    }

    private void initDepedencyInjection() {
        restaurantsComponent = DaggerRestaurantsComponent.builder()
                .appComponent(fragmentInteractionListener.getAppComponent())
                .restaurantsModule(new RestaurantsModule(this))
                .build();

        restaurantsComponent.inject(this);
    }
}

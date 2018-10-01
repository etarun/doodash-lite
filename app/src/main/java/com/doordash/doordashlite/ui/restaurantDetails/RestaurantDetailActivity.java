package com.doordash.doordashlite.ui.restaurantDetails;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.doordash.doordashlite.BaseActivity;
import com.doordash.doordashlite.R;
import com.doordash.doordashlite.data.Restaurant;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.doordash.doordashlite.Properties.RESTAURANT;

/**
 * This Activity displays the details of the restaurant selected.
 * Restaurant data is passed via a {@link Bundle} and from its
 * {@link RestaurantDetailContract.Presenter}. It then renders the restaurant data and its details.
 */
public class RestaurantDetailActivity extends BaseActivity implements RestaurantDetailContract.View {

    @BindView(R.id.imageView)
    ImageView ivRestaurantImage;
    @BindView(R.id.tv_name)
    TextView tvRestaurantName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_delivery_fee)
    TextView tvDeliveryFee;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private Restaurant restaurant;
    RestaurantDetailsComponent restaurantDetailsComponent;
    @Inject
    RestaurantDetailContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initRestaurantDetailsDaggerComponent();
        setContentView(R.layout.activity_restaurant_details);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        restaurant = getIntent().getParcelableExtra(RESTAURANT);
        getSupportActionBar().setTitle(restaurant.getName());
        presenter.onCreate(restaurant);
    }

    private void initRestaurantDetailsDaggerComponent() {
        super.initDaggerComponent();
        if (restaurantDetailsComponent == null) {
            restaurantDetailsComponent = DaggerRestaurantDetailsComponent.builder()
                    .appComponent(appComponent)
                    .restaurantDetailsModule(new RestaurantDetailsModule(this))
                    .build();

            restaurantDetailsComponent.inject(this);
        }
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(this, "Unable to Load Restaurant Data", Toast.LENGTH_LONG).show();
        this.finish();
    }

    @Override
    public void setProgressBar(boolean show) {

    }

    @Override
    public void showRestaurantImage(String url) {
        Glide.with(this).load(url).placeholder(
                R.drawable.drawable_placeholder).error(
                R.drawable.drawable_placeholder).into(ivRestaurantImage);
    }

    @Override
    public void setResName(String name) {
        tvRestaurantName.setText(name);
    }

    @Override
    public void setResPhone(String phone) {
        tvPhone.setText(phone);
    }

    @Override
    public void setResDeliveryFee(String deliveryFee) {
        tvDeliveryFee.setText(deliveryFee);
    }


}

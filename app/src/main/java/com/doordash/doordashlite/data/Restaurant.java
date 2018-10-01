package com.doordash.doordashlite.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Restaurant implements Parcelable {

    @SerializedName("id")
    @Expose
    private int restaurantId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("cover_img_url")
    @Expose
    private String imageURL;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("delivery_fee")
    @Expose
    private String deliveryFee;
    @SerializedName("phone_number")
    @Expose
    private String phone;

    protected Restaurant(Parcel in) {
        restaurantId = in.readInt();
        name = in.readString();
        imageURL = in.readString();
        status = in.readString();
        description = in.readString();
        deliveryFee = in.readString();
        phone = in.readString();
    }

    public static final Creator<Restaurant> CREATOR = new Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(String deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(restaurantId);
        parcel.writeString(name);
        parcel.writeString(imageURL);
        parcel.writeString(status);
        parcel.writeString(description);
        parcel.writeString(deliveryFee);
        parcel.writeString(phone);
    }
}

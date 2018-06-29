package com.example.vaddisa.walmartproducts.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product implements Parcelable {
    @SerializedName("productId")
    @Expose
    private String productId;
    @SerializedName("productName")
    @Expose
    private String productName;
    @SerializedName("shortDescription")
    @Expose
    private String shortDescription;
    @SerializedName("longDescription")
    @Expose
    private String longDescription;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("productImage")
    @Expose
    private String productImage;
    @SerializedName("reviewRating")
    @Expose
    private float reviewRating;
    @SerializedName("reviewCount")
    @Expose
    private Integer reviewCount;
    @SerializedName("inStock")
    @Expose
    private Boolean inStock;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public float getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(Integer reviewRating) {
        this.reviewRating = reviewRating;
    }

    public Integer getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

    public Boolean getInStock() {
        return inStock;
    }

    public void setInStock(Boolean inStock) {
        this.inStock = inStock;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.productId);
        dest.writeString(this.productName);
        dest.writeString(this.shortDescription);
        dest.writeString(this.longDescription);
        dest.writeString(this.price);
        dest.writeString(this.productImage);
        dest.writeFloat(this.reviewRating);
        dest.writeValue(this.reviewCount);
        dest.writeValue(this.inStock);
    }

    public Product() {
    }

    protected Product(Parcel in) {
        this.productId = in.readString();
        this.productName = in.readString();
        this.shortDescription = in.readString();
        this.longDescription = in.readString();
        this.price = in.readString();
        this.productImage = in.readString();
        this.reviewRating = in.readFloat();
        this.reviewCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.inStock = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}





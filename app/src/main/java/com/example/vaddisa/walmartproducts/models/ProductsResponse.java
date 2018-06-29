package com.example.vaddisa.walmartproducts.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ProductsResponse implements Parcelable{
    @SerializedName("products")
    @Expose
    private List<Product> products = null;
    @SerializedName("totalProducts")
    @Expose
    private Integer totalProducts;
    @SerializedName("pageNumber")
    @Expose
    private Integer pageNumber;
    @SerializedName("pageSize")
    @Expose
    private Integer pageSize;
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Integer getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(Integer totalProducts) {
        this.totalProducts = totalProducts;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.products);
        dest.writeValue(this.totalProducts);
        dest.writeValue(this.pageNumber);
        dest.writeValue(this.pageSize);
        dest.writeValue(this.statusCode);
    }

    public ProductsResponse() {
    }

    protected ProductsResponse(Parcel in) {
        this.products = new ArrayList<Product>();
        in.readList(this.products, Product.class.getClassLoader());
        this.totalProducts = (Integer) in.readValue(Integer.class.getClassLoader());
        this.pageNumber = (Integer) in.readValue(Integer.class.getClassLoader());
        this.pageSize = (Integer) in.readValue(Integer.class.getClassLoader());
        this.statusCode = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Creator<ProductsResponse> CREATOR = new Creator<ProductsResponse>() {
        @Override
        public ProductsResponse createFromParcel(Parcel source) {
            return new ProductsResponse(source);
        }

        @Override
        public ProductsResponse[] newArray(int size) {
            return new ProductsResponse[size];
        }
    };

}

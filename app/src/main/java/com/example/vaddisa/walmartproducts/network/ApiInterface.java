package com.example.vaddisa.walmartproducts.network;

import com.example.vaddisa.walmartproducts.models.ProductsResponse;
import com.example.vaddisa.walmartproducts.common.Utils;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface ApiInterface {
    @GET("/walmartproducts/{pageNumber}/" + Utils.MAX_PRODUCTS)
    Call<ProductsResponse>getProductResponse(@Path("pageNumber") String pageNumber);
}



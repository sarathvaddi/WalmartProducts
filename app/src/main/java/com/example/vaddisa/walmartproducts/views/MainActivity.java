package com.example.vaddisa.walmartproducts.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ProgressBar;

import com.example.vaddisa.walmartproducts.R;
import com.example.vaddisa.walmartproducts.common.PaginationScrollListener;
import com.example.vaddisa.walmartproducts.common.ProductListAdapter;
import com.example.vaddisa.walmartproducts.common.Utils;
import com.example.vaddisa.walmartproducts.models.ProductsResponse;
import com.example.vaddisa.walmartproducts.network.ApiInterface;
import com.example.vaddisa.walmartproducts.network.ClientApi;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProductListAdapter adapter;
    ProductsResponse productsResponse;


    private static final int PAGE_START = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int currentPage = PAGE_START;

    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.products_list);
        adapter = new ProductListAdapter(getApplicationContext());
        makeAserverCall(String.valueOf(currentPage));
        setProductList();

    }

    private void setProductList() {
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        addScrollListenerToList(layoutManager);
    }

    private void makeAserverCall(String pageNumber) {
        ApiInterface apiService = ClientApi.getRetrofitInstance().create(ApiInterface.class);

        Call<ProductsResponse> call = apiService.getProductResponse(pageNumber);
        call.enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                Log.e("TAG", "response: "+new Gson().toJson(response.body()) );
                adapter.addAll(response.body().getProducts());
                updateProductList(response);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("TAG", "onFailure: "+t.toString() );
            }
        });
    }

    private void updateProductList(Response<ProductsResponse> response) {
        productsResponse = response.body();

    }

    private void addScrollListenerToList(final LinearLayoutManager layoutManager) {
        recyclerView.addOnScrollListener(new PaginationScrollListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;
                loadNextPage(String.valueOf(currentPage));
            }

            @Override
            public int getTotalPageCount() {
                return calculateTotalPages();
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
    }

    private int calculateTotalPages() {
        return (productsResponse.getTotalProducts()/ Integer.valueOf(Utils.MAX_PRODUCTS))+1;
    }


    private void loadNextPage(String currentPage) {

        makeAserverCall(currentPage);
        adapter.removeLoadingFooter();
        isLoading = false;


        if (this.currentPage != calculateTotalPages()) adapter.addLoadingFooter();  // 5
        else isLastPage = true;
    }
}

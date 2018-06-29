package com.example.vaddisa.walmartproducts.common;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.vaddisa.walmartproducts.R;
import com.example.vaddisa.walmartproducts.models.Product;
import com.example.vaddisa.walmartproducts.views.DetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    private boolean isLoadingAdded = false;
    private List<Product> products;

    private static final int ITEM = 0;
    private static final int LOADING = 1;

    public ProductListAdapter(Context context) {
        this.context = context;
        products = new ArrayList<>();;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ITEM:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_item, parent, false);
                viewHolder = new MainViewHolder(view);
                break;
            case LOADING:
                View v2 = inflater.inflate(R.layout.item_progress_bar, parent, false);
                viewHolder = new LoadingVH(v2);
                break;
        }
        return viewHolder;


    }

    @Override
    public int getItemViewType(int position) {
        return (position == products.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        switch (getItemViewType(position)) {
            case ITEM:
                final MainViewHolder viewHolder = (MainViewHolder) holder;
                Product product = products.get(position);
                viewHolder.title.setText(product.getProductName());
                viewHolder.price.setText(product.getPrice());
                Glide.with(context).load(Utils.constructImageUrl(product.getProductImage())).into(viewHolder.imageView);
                setOnclick(viewHolder, position);
                break;
            case LOADING:
                break;
        }


    }

    private void setOnclick(@NonNull MainViewHolder holder, final int position) {
        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailsActivity.class);
                i.putExtra("PRODUCT", products.get(position));
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (products != null) ? products.size() : 0;
    }


    public class MainViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView title;
        TextView price;
        LinearLayout itemLayout;


        public MainViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.product_image);
            title = (TextView) itemView.findViewById(R.id.product_title);
            price = (TextView) itemView.findViewById(R.id.product_price);
            itemLayout = (LinearLayout) itemView.findViewById(R.id.item_layout);
        }
    }

    protected class LoadingVH extends RecyclerView.ViewHolder {

        public LoadingVH(View itemView) {
            super(itemView);
        }
    }


    public void add(Product mc) {
        products.add(mc);
        notifyItemInserted(products.size() - 1);
    }

    public void addAll(List<Product> productList) {
        for (Product mc : productList) {
            add(mc);
        }
    }

    public void remove(Product product) {
        int position = products.indexOf(product);
        if (position > -1) {
            products.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new Product());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = products.size() - 1;
        Product item = getItem(position);

        if (item != null) {
            products.remove(position);
            notifyItemRemoved(position);
        }
    }

    public Product getItem(int position) {
        return products.get(position);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

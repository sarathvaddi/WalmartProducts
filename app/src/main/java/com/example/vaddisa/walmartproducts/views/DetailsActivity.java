package com.example.vaddisa.walmartproducts.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.vaddisa.walmartproducts.R;
import com.example.vaddisa.walmartproducts.common.Utils;
import com.example.vaddisa.walmartproducts.models.Product;

public class DetailsActivity extends AppCompatActivity {

    TextView shortDescription;
    TextView description;
    RatingBar ratingBar;
    TextView title;
    TextView price;
    ImageView image;
    Product product;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null)
            product = (Product) extras.get("PRODUCT");
        setContentView(R.layout.activity_details);
        loadUI();
        setupUI();
    }

    private void loadUI() {
        image = (ImageView) findViewById(R.id.product_image);
        title = (TextView) findViewById(R.id.product_title);
        price = (TextView) findViewById(R.id.product_price);
        ratingBar = (RatingBar) findViewById(R.id.product_rating);
        description = (TextView) findViewById(R.id.description);
        shortDescription = (TextView) findViewById(R.id.short_description);
    }

    private void setupUI() {
        if (product != null) {
            title.setText(Utils.notNull(product.getProductName()));
            price.setText(Utils.notNull(product.getPrice()));
            description.setText(Utils.getHtmlFormattedText(product.getLongDescription()));
            shortDescription.setText(Utils.getHtmlFormattedText(product.getShortDescription()));
            Glide.with(getApplicationContext()).load(Utils.constructImageUrl(product.getProductImage())).into(image);
            ratingBar.setRating(product.getReviewRating());
        }
    }
}

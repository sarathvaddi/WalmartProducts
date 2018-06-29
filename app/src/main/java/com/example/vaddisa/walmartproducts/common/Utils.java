package com.example.vaddisa.walmartproducts.common;

import android.text.Html;

import com.example.vaddisa.walmartproducts.network.ClientApi;

public final class Utils {


    public static final String MAX_PRODUCTS = "30";

    public static String notNull(String description) {
        return description != null ? description : "";
    }

    public static String getHtmlFormattedText(String text) {
        return text != null ? Html.fromHtml(text).toString() : "";
    }

    public static String constructImageUrl(String url) {
        return ClientApi.BASE_URL + url;

    }
}

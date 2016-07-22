package com.sitespect.sitespectdemo.details;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.sitespect.sdk.SiteSpect;
import com.sitespect.sitespectdemo.BaseSiteSpectActivity;
import com.sitespect.sitespectdemo.R;
import com.sitespect.sitespectdemo.cart.ShoppingCartActivity;

public class ProductDetailsActivity extends BaseSiteSpectActivity {
    private static final String KEY_SELECTED_ITEM_NAME = "SELECTED_ITEM_NAME";
    private static final String KEY_SELECTED_ITEM_PRICE = "SELECTED_ITEM_PRICE";

    private ViewHolder viewHolder;

    public static Intent createIntent(Context context, String itemName, float itemPrice) {
        Intent intent = new Intent(context, ProductDetailsActivity.class);
        intent.putExtra(KEY_SELECTED_ITEM_NAME, itemName);
        intent.putExtra(KEY_SELECTED_ITEM_PRICE, itemPrice);
        return intent;
    }

    private String getFeaturedItemTitle() {
        return getIntent().getStringExtra(KEY_SELECTED_ITEM_NAME);
    }

    private float getFeaturedItemPrice() {
        return getIntent().getFloatExtra(KEY_SELECTED_ITEM_PRICE, 0.0f);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        setTitle(getFeaturedItemTitle());
        initializeBackButton();

        viewHolder = new ViewHolder(this);

        viewHolder.productDetailsTitle.setText(getFeaturedItemTitle());
        viewHolder.productDetailsDiscountPrice.setText(getString(R.string.itemPriceDisplayText, getFeaturedItemPrice()));
        viewHolder.productDetailsRetailPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        initializeFloatingActionBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_details_menu, menu);
        return true;
    }

    @Override
    public void siteSpectCodeEditorChange() {
        viewHolder.addToCartFab.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.green_500)));
    }

    private void initializeFloatingActionBar() {
        viewHolder.addToCartFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String notificationText = getResources().getString(R.string.details_snackbarSuccessNotification, getFeaturedItemTitle());
                displaySnackbar(notificationText);
            }
        });
    }

    private void displaySnackbar(String message) {
        Snackbar snackbar = Snackbar
                .make(viewHolder.productDetailsCoordinatorLayout, message, Snackbar.LENGTH_SHORT)
                .setAction(R.string.details_snackbarViewCart, new View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View v) {
                        startActivity(ShoppingCartActivity.createIntent(v.getContext()));
                    }
                });
        snackbar.show();
    }

    private static class ViewHolder {
        final CoordinatorLayout productDetailsCoordinatorLayout;
        final TextView productDetailsTitle;
        final TextView productDetailsDiscountPrice;
        final TextView productDetailsRetailPrice;
        final FloatingActionButton addToCartFab;

        public ViewHolder(Activity activity) {
            productDetailsCoordinatorLayout = (CoordinatorLayout) activity.findViewById(R.id.activity_details_coordinatorLayout);
            productDetailsTitle = (TextView) activity.findViewById(R.id.activity_details_featuredItemTitle);
            productDetailsDiscountPrice = (TextView) activity.findViewById(R.id.activity_details_discountPrice);
            productDetailsRetailPrice = (TextView) activity.findViewById(R.id.activity_details_retailPrice);
            addToCartFab = (FloatingActionButton) activity.findViewById(R.id.activity_details_addToCartFab);
        }
    }
}

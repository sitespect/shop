package com.sitespect.sitespectdemo.cart;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sitespect.sitespectdemo.BaseSiteSpectActivity;
import com.sitespect.sitespectdemo.R;
import com.sitespect.sitespectdemo.checkout.CheckoutActivity;

public class ShoppingCartActivity extends BaseSiteSpectActivity implements ShoppingCartListAdapter.ShoppingCartUpdateListener {
    private ViewHolder viewHolder;
    private ShoppingCartListAdapter recyclerViewAdapter;

    public static Intent createIntent(Context context) {
        return new Intent(context, ShoppingCartActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        setTitle(R.string.shopping_cart_title);
        initializeBackButton();

        viewHolder = new ViewHolder(this);
        initializeShoppingCartItems();
        initializeBottomBar();
    }

    private void initializeShoppingCartItems() {
        viewHolder.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter = new ShoppingCartListAdapter(this, getShoppingCartItems());
        viewHolder.recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void initializeBottomBar() {
        viewHolder.bottomBarLabel.setText(getResources().getString(R.string.shopping_cart_cartTotalLabel));
        viewHolder.bottomBarTotalPrice.setText(getString(R.string.itemPriceDisplayText, getTotalShoppingCartPrice()));
        viewHolder. bottomBarActionButton.setText(getResources().getString(R.string.shopping_cart_checkoutButton));
        viewHolder.bottomBarActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(CheckoutActivity.createIntent(v.getContext()));
            }
        });
    }

    @Override
    public void updateCartTotal() {
        viewHolder.bottomBarTotalPrice.setText(getString(R.string.itemPriceDisplayText, getTotalShoppingCartPrice()));
    }

    @Override
    public void siteSpectCodeEditorChange() {
        if (recyclerViewAdapter != null) {
            recyclerViewAdapter.setAltDeleteIcon(R.drawable.icn_cart_x_normal);
        }
    }

    private static class ViewHolder {
        final TextView bottomBarLabel;
        final TextView bottomBarTotalPrice;
        final TextView bottomBarActionButton;
        final RecyclerView recyclerView;

        public ViewHolder(Activity activity) {
            recyclerView = (RecyclerView) activity.findViewById(R.id.activity_shopping_cart_recyclerView);
            bottomBarLabel = (TextView) activity.findViewById(R.id.view_bottom_bar_totalPriceLabel);
            bottomBarTotalPrice = (TextView) activity.findViewById(R.id.view_bottom_bar_totalPrice);
            bottomBarActionButton = (TextView) activity.findViewById(R.id.view_bottom_bar_actionButton);

        }
    }
}

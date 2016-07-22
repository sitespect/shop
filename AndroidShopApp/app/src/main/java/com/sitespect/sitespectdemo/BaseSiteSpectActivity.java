package com.sitespect.sitespectdemo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.sitespect.sitespectdemo.cart.ShoppingCartActivity;
import com.sitespect.sitespectdemo.data.ShoppingCartItem;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("Registered")
public abstract class BaseSiteSpectActivity extends AppCompatActivity {
    final static int SHOPPING_CART_ITEM_LIMIT = 9;

    private Toolbar toolbar;
    private static ArrayList<ShoppingCartItem> shoppingCartItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (shoppingCartItems == null) {
            shoppingCartItems = new ArrayList<>();
            resetCartItems();
        }
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        initializeToolbar();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else if (item.getItemId() == R.id.shopping_cart) {
            startActivity(ShoppingCartActivity.createIntent(this));
        }
        return super.onOptionsItemSelected(item);
    }

    public void initializeBackButton() {
        if (getSupportActionBar() != null) {
            getToolbar().setNavigationIcon(R.drawable.icn_arrow_back_white);
        }
    }

    protected void resetCartItems() {
        shoppingCartItems.clear();
        addItemToCart(new ShoppingCartItem(getString(R.string.shoppingCartItem_dexterPatioSetName), Float.parseFloat(getString(R.string.shoppingCartItem_dexterPatioSetPrice)), 1, R.drawable.img_cart_product_patio));
        addItemToCart(new ShoppingCartItem(getString(R.string.shoppingCartItem_pillowName), Float.parseFloat(getString(R.string.shoppingCartItem_pillowPrice)), 3, R.drawable.img_cart_product_pillows));
        addItemToCart(new ShoppingCartItem(getString(R.string.shoppingCartItem_candleName), Float.parseFloat(getString(R.string.shoppingCartItem_candlePrice)), 1, R.drawable.img_cart_product_candles));
    }

    /**
     * Include R.id.toolbar toolbar into activity layouts
     */
    private void initializeToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public Toolbar getToolbar() {
        return this.toolbar;
    }

    protected boolean addItemToCart(ShoppingCartItem item) {
        for (int i=0; i < getShoppingCartItems().size(); i++) {
            if (item.getItemName().equals(getShoppingCartItems().get(i).getItemName())) {
                if (getShoppingCartItems().get(i).getItemQuantity() < SHOPPING_CART_ITEM_LIMIT) {
                    getShoppingCartItems().get(i).incrementQuantity();
                    return true;
                } else {
                    return false;
                }
            }
        }
        shoppingCartItems.add(item);
        return true;
    }

    protected List<ShoppingCartItem> getShoppingCartItems() {
        return shoppingCartItems;
    }

    protected float getTotalShoppingCartPrice() {
        float totalPrice = 0.0f;
        for (ShoppingCartItem item : getShoppingCartItems()) {
            totalPrice += item.getUnitPrice() * item.getItemQuantity();
        }
        return totalPrice;
    }

    // Invoked by SiteSpect SDK for code editor changes
    public abstract void siteSpectCodeEditorChange();
}

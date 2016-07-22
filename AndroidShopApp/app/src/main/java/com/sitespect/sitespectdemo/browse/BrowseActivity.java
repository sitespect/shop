package com.sitespect.sitespectdemo.browse;

import android.app.Activity;
import android.app.ActivityOptions;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sitespect.sdk.SiteSpect;
import com.sitespect.sitespectdemo.BaseSiteSpectActivity;
import com.sitespect.sitespectdemo.R;
import com.sitespect.sitespectdemo.details.ProductDetailsActivity;
import com.sitespect.sitespectdemo.widgets.RoomCollectionCellView;

public class BrowseActivity extends BaseSiteSpectActivity {
    private ViewHolder viewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);
        setTitle(R.string.browse);

        viewHolder = new ViewHolder(this);
        initializeDrawer();
        initializeFeaturedItem();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_browse_menu, menu);
        return true;
    }

    @Override
    public void siteSpectCodeEditorChange() {
        viewHolder.kitchenCellView.setCellDrawable(R.drawable.img_browse_category_kitchen_alternate);
        viewHolder.kitchenCellView.setCellTextBackgroundColor(ContextCompat.getColor(this, R.color.red_kitchenCellAltBackground));
        viewHolder.featuredViewItemPrice.setTextColor(ContextCompat.getColor(this, R.color.skyBlue_dark));

        String titleValue = SiteSpect.getLiveVariableString("kitchen_text");
        if ( titleValue != null ) {
            viewHolder.kitchenCellView.setCellText(titleValue);
        }
    }

    private void initializeDrawer() {
        // Drawer locked for demo purposes.
        viewHolder.drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, viewHolder.drawer, getToolbar(),
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
    }

    private void initializeFeaturedItem() {
        final String featuredItemName = getString(R.string.shoppingCartItem_dexterPatioSetName);
        final float featuredItemPrice = Float.parseFloat(getString(R.string.shoppingCartItem_dexterPatioSetPrice));

        viewHolder.featuredViewItemTitle.setText(featuredItemName);
        viewHolder.featuredViewItemPrice.setText(getString(R.string.itemPriceDisplayText, featuredItemPrice));
        viewHolder.featuredViewDetailButton.setOnClickListener(getProductDetailsOnClickListener(featuredItemName, featuredItemPrice));
        viewHolder.featuredViewItemImage.setOnClickListener(getProductDetailsOnClickListener(featuredItemName, featuredItemPrice));
    }

    private View.OnClickListener getProductDetailsOnClickListener(final String featuredItemName, final float featuredItemPrice) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(ProductDetailsActivity.createIntent(v.getContext(), featuredItemName, featuredItemPrice),
                            ActivityOptions.makeSceneTransitionAnimation(BrowseActivity.this).toBundle());
                } else {
                    startActivity(ProductDetailsActivity.createIntent(v.getContext(), featuredItemName, featuredItemPrice));
                }
            }
        };
    }

    private static class ViewHolder {
        final DrawerLayout drawer;
        final TextView featuredViewItemTitle;
        final TextView featuredViewItemPrice;
        final Button featuredViewDetailButton;
        final ImageView featuredViewItemImage;
        final RoomCollectionCellView kitchenCellView;

        public ViewHolder(Activity activity) {
            drawer = (DrawerLayout) activity.findViewById(R.id.activity_browse_drawerLayout);
            featuredViewItemTitle = (TextView) activity.findViewById(R.id.activity_browse_featuredTitle);
            featuredViewItemPrice = (TextView) activity.findViewById(R.id.activity_browse_featuredPrice);
            featuredViewDetailButton = (Button) activity.findViewById(R.id.activity_browse_featureDetailsButton);
            featuredViewItemImage = (ImageView) activity.findViewById(R.id.activity_browse_featuredImage);
            kitchenCellView = (RoomCollectionCellView) activity.findViewById(R.id.activity_browse_kitchenCell);
        }
    }
}

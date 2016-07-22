package com.sitespect.sitespectdemo.checkout;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sitespect.sitespectdemo.BaseSiteSpectActivity;
import com.sitespect.sitespectdemo.R;

@SuppressWarnings("FieldCanBeLocal")
public class CheckoutActivity extends BaseSiteSpectActivity {
    final float TAX_RATE = 0.0625f;
    final float FLAT_SHIPPING_RATE = 150.00f;

    private ViewHolder viewHolder;

    public static Intent createIntent(Context context) {
        return new Intent(context, CheckoutActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        setTitle(R.string.checkout_title);
        initializeBackButton();

        viewHolder = new ViewHolder(this);
        initializeViews();
    }

    @Override
    public void siteSpectCodeEditorChange() {
        viewHolder.bottomBarActionButton.setText(R.string.checkout_buyNowButton);
    }

    private void initializeViews() {
        float salesTaxValue = getTotalShoppingCartPrice() * TAX_RATE;
        float totalPrice = getTotalShoppingCartPrice() + salesTaxValue + FLAT_SHIPPING_RATE;

        viewHolder.subtotalPrice.setText(getString(R.string.itemPriceDisplayText, getTotalShoppingCartPrice()));
        viewHolder.salesTaxPrice.setText(getString(R.string.itemPriceDisplayText, salesTaxValue));
        viewHolder.shippingFeePrice.setText(getString(R.string.itemPriceDisplayText, FLAT_SHIPPING_RATE));

        viewHolder.bottomBarContainer.setBackgroundColor(ContextCompat.getColor(this, R.color.skyBlue_dark));
        viewHolder.bottomBarLabel.setText(getResources().getString(R.string.checkout_orderTotalLabel));
        viewHolder.bottomBarLabel.setTextColor(ContextCompat.getColor(this, R.color.black_A44));
        viewHolder.bottomBarTotalPrice.setTextColor(ContextCompat.getColor(this, R.color.white));
        viewHolder.bottomBarTotalPrice.setText(getString(R.string.itemPriceDisplayText, totalPrice));
        viewHolder.bottomBarActionButton.setText(getResources().getString(R.string.checkout_purchaseButton));
        viewHolder.bottomBarActionButton.setTextColor(ContextCompat.getColor(this, R.color.white));
        viewHolder.bottomBarActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmationDialog();
            }
        });
    }

    private void showConfirmationDialog() {
//        final Dialog dialog = new Dialog(this);
//        dialog.setContentView(R.layout.dialog_confirmation);
//
//        TextView doneButton = (TextView) dialog.findViewById(R.id.dialog_confirmation_doneButton);
//        doneButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                resetCartItems();
//                Intent intent = new Intent(v.getContext(), BrowseActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                v.getContext().startActivity(intent);
//                dialog.dismiss();
//            }
//        });
//        dialog.show();
        Intent i = new Intent(this, ConfirmDialogActivity.class);
        startActivity(i);
    }

    private static class ViewHolder {
        final LinearLayout bottomBarContainer;
        final TextView subtotalPrice;
        final TextView salesTaxPrice;
        final TextView shippingFeePrice;
        final TextView bottomBarLabel;
        final TextView bottomBarTotalPrice;
//        final TextView bottomBarActionButton;
        final Button bottomBarActionButton;

        public ViewHolder(Activity activity) {
            subtotalPrice = (TextView) activity.findViewById(R.id.activity_checkout_subtotalPrice);
            salesTaxPrice = (TextView) activity.findViewById(R.id.activity_checkout_salesTax);
            shippingFeePrice = (TextView) activity.findViewById(R.id.activity_checkout_shippingFee);
            bottomBarContainer = (LinearLayout) activity.findViewById(R.id.view_bottom_bar_layout);
            bottomBarLabel = (TextView) activity.findViewById(R.id.view_bottom_bar_totalPriceLabel);
            bottomBarTotalPrice = (TextView) activity.findViewById(R.id.view_bottom_bar_totalPrice);
//            bottomBarActionButton = (TextView) activity.findViewById(R.id.view_bottom_bar_actionButton);
            bottomBarActionButton = (Button) activity.findViewById(R.id.view_bottom_bar_actionButton);
        }
    }
}

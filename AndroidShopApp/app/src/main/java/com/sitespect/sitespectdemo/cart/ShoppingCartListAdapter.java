package com.sitespect.sitespectdemo.cart;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.sitespect.sitespectdemo.R;
import com.sitespect.sitespectdemo.data.ShoppingCartItem;

import java.util.List;

public class ShoppingCartListAdapter extends RecyclerView.Adapter<ShoppingCartListAdapter.ViewHolder> {
    private Context context;
    private ShoppingCartUpdateListener updateCartListener;
    private List<ShoppingCartItem> dataSet;
    private int removeItemAltDrawable = -1;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView cartListItemTitle;
        public TextView cartListItemPrice;
        public ImageView cartListItemImage;
        public Spinner cartListItemSpinner;
        public ImageButton cartListItemRemoveButton;

        public ViewHolder(View itemView) {
            super(itemView);
            cartListItemTitle = (TextView) itemView.findViewById(R.id.list_item_shopping_cart_itemName);
            cartListItemPrice = (TextView) itemView.findViewById(R.id.list_item_shopping_cart_itemPrice);
            cartListItemImage = (ImageView) itemView.findViewById(R.id.list_item_shopping_cart_image);
            cartListItemSpinner = (Spinner) itemView.findViewById(R.id.list_item_shopping_cart_spinner);
            cartListItemRemoveButton = (ImageButton) itemView.findViewById(R.id.list_item_shopping_cart_removeItem);
        }
    }

    public ShoppingCartListAdapter(Context context, List<ShoppingCartItem> dataSet) {
        this.context = context;
        this.updateCartListener = (ShoppingCartUpdateListener) context;
        this.dataSet = dataSet;
    }
        
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_shopping_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        ShoppingCartItem currentItem = dataSet.get(position);

        holder.cartListItemTitle.setText(currentItem.getItemName());
        holder.cartListItemPrice.setText(context.getString(R.string.itemPriceDisplayText, currentItem.getUnitPrice()));
        holder.cartListItemImage.setImageDrawable(ContextCompat.getDrawable(context, currentItem.getItemDrawableId()));

        ArrayAdapter<CharSequence> spinnerAdapter =
                ArrayAdapter.createFromResource(context, R.array.shopping_cart_spinnerArray, R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        holder.cartListItemSpinner.setAdapter(spinnerAdapter);
        holder.cartListItemSpinner.setSelection(currentItem.getItemQuantity() - 1);
        holder.cartListItemSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int spinnerPosition, long id) {
                int quantity = Integer.parseInt(parent.getItemAtPosition(spinnerPosition).toString());
                dataSet.get(holder.getAdapterPosition()).setItemQuantity(quantity);
                updateCartListener.updateCartTotal();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        if (removeItemAltDrawable != -1) {
            holder.cartListItemRemoveButton.setImageResource(removeItemAltDrawable);
        }
        holder.cartListItemRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Doesn't do anything for demo.
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public interface ShoppingCartUpdateListener {
        void updateCartTotal();
    }

    public void setAltDeleteIcon(@DrawableRes int drawableResourceId) {
        removeItemAltDrawable = drawableResourceId;
    }
}
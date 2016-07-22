package com.sitespect.sitespectdemo.widgets;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sitespect.sitespectdemo.R;

public class RoomCollectionCellView extends LinearLayout {

    private ImageView cellImage;
    private TextView cellText;

    public RoomCollectionCellView(Context context) {
        super(context);
        init(null);
    }

    public RoomCollectionCellView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.RoomCollectionCellView);
        init(arr);
        arr.recycle();
    }

    public RoomCollectionCellView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.RoomCollectionCellView);
        init(arr);
        arr.recycle();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RoomCollectionCellView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.RoomCollectionCellView);
        init(arr);
        arr.recycle();
    }

    private void init(TypedArray arr) {
        inflate(getContext(), R.layout.view_room_collection_cell, this);
        cellImage = (ImageView) findViewById(R.id.view_room_collection_cell_image);
        cellText = (TextView) findViewById(R.id.view_room_collection_cell_text);

        if (arr != null) {
            setCellText(arr.getString(R.styleable.RoomCollectionCellView_cellText));
            setCellTextBackgroundColor(arr.getColor(R.styleable.RoomCollectionCellView_cellColor, -1));
            setCellDrawable(arr.getResourceId(R.styleable.RoomCollectionCellView_cellDrawable, -1));
        }
    }

    public void setCellText(String text) {
        cellText.setText(text);
    }

    public void setCellTextBackgroundColor(int color) {
        if (color != -1) {
            cellText.setBackgroundColor(color);
        }
    }

    public void setCellDrawable(int drawableResourceId) {
        if (drawableResourceId != -1) {
            cellImage.setImageResource(drawableResourceId);
        }
    }
}

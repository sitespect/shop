package com.sitespect.sitespectdemo.checkout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.sitespect.sitespectdemo.R;
import com.sitespect.sitespectdemo.browse.BrowseActivity;


/**
 * Created by raphaelwu on 7/5/16.
 */
public class ConfirmDialogActivity extends Activity{
    CheckoutActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_confirmation);

        Button confirmBtn = (Button)this.findViewById(R.id.dialog_confirmation_doneButton);
        confirmBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(view.getContext(), BrowseActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        view.getContext().startActivity(intent);
                        finish();
                    }
                }
        );

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int screenHeight = (int) (metrics.heightPixels * 0.80);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, screenHeight);
    }
}

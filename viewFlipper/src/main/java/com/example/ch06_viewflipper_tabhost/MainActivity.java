package com.example.ch06_viewflipper_tabhost;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnPrev, btnNext;
        final ViewFlipper vFlipper;

        btnPrev = (Button) findViewById(R.id.btnPrev);
        btnNext = (Button) findViewById(R.id.btnNext);

        vFlipper = (ViewFlipper) findViewById(R.id.viewFlipper1);

        vFlipper.setFlipInterval(1000);

        btnPrev.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
//                vFlipper.showPrevious();

                vFlipper.startFlipping();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
//                vFlipper.showNext();
                vFlipper.stopFlipping();
            }
        });
    }
}

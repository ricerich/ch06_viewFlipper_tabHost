package com.example.tabhost;

import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabHost = getTabHost();

        TabSpec tab1 = tabHost.newTabSpec("TAB1").setIndicator("강아지");
        tab1.setContent(R.id.iv1);
        tabHost.addTab(tab1);

        TabSpec tab2 = tabHost.newTabSpec("TAB2").setIndicator("고양이");
        tab2.setContent(R.id.iv2);
        tabHost.addTab(tab2);

        TabSpec tab3 = tabHost.newTabSpec("TAB3").setIndicator("토끼");
        tab3.setContent(R.id.iv3);
        tabHost.addTab(tab3);
        
        TabSpec tab4 = tabHost.newTabSpec("TAB4").setIndicator("말");
        tab4.setContent(R.id.iv4);
        tabHost.addTab(tab4);

        tabHost.setCurrentTab(1);
    }
}

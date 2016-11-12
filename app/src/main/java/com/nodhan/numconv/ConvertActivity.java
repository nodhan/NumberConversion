package com.nodhan.numconv;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

public class ConvertActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_convert);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        RecyclerView recList = (RecyclerView) findViewById(R.id.recycler_view);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        Intent intent = getIntent();
        String number = intent.getStringExtra("number");
        int type = intent.getIntExtra("type", -1);

        ConvertAdapter ca = new ConvertAdapter(convertNumber(number, type));
        recList.setAdapter(ca);

        textView = (TextView) findViewById(R.id.convert_info);
        textView.setText(new StringBuilder("The equivalents of ").append(number).append(" are:"));
        textView.setTextSize(20);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        textView.setTypeface(typeface);
    }

    private List<ConvertedNumberInfo> convertNumber(String number, int type) {
        List<ConvertedNumberInfo> convertedNumberInfos;
        Converter converter = new Converter(number);
        switch (type) {
            case 0:
                convertedNumberInfos = converter.binaryToOther();
                break;
            case 1:
                convertedNumberInfos = converter.octalToOther();
                break;
            case 2:
                convertedNumberInfos = converter.decimalToOther();
                break;
            case 3:
                convertedNumberInfos = converter.hexaToOther();
                break;
            default:
                convertedNumberInfos = null;
        }

        return convertedNumberInfos;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        drawer.closeDrawer(GravityCompat.START);
        finish();
        if (id == R.id.nav_history) {
            startActivity(new Intent(this, HistoryActivity.class));
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}

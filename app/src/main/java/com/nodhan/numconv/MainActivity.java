package com.nodhan.numconv;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;

    Spinner spinner;
    ArrayAdapter<String> arrayAdapter;
    TextView textView;
    String TAG = "MainAct: NUM_CON";
    int buttonIDS[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        buttonIDS = new int[16];
        loadButtonIDS();

        textView = (TextView) findViewById(R.id.number);
        textView.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf"));

        ArrayList<String> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, "BINARY", "OCTAL", "DECIMAL", "HEXADECIMAL");

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, arrayList);

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(arrayAdapter);

        setButtonProperties(2);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "SELECTED ITEM " + spinner.getSelectedItem().toString());
                Log.d(TAG, "SELECTED ITEM ID " + spinner.getSelectedItemPosition());
                Log.d(TAG, "POSITION " + i);
                setButtons(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        for (int id : buttonIDS) {
            findViewById(id).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String value = textView.getText() + ((Button) view).getText().toString();
                    textView.setText(value);
                }
            });
        }

        findViewById(R.id.clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(null);
            }
        });

        findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = textView.getText().toString();
                if (value.trim().length() > 0)
                    textView.setText(value.substring(0, value.length() - 1));
            }
        });

        findViewById(R.id.convert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = textView.getText().toString();
                if (value.trim().length() > 0) {
                    Snackbar.make(view.getRootView(), "Value: " + value, Snackbar.LENGTH_LONG).show();
                } else {
                    Snackbar.make(view.getRootView(), "Enter a value to continue!", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    /**
     * Adds id's of buttons to array
     */
    private void loadButtonIDS() {
        buttonIDS[0] = R.id.zero;
        buttonIDS[1] = R.id.one;
        buttonIDS[2] = R.id.two;
        buttonIDS[3] = R.id.three;
        buttonIDS[4] = R.id.four;
        buttonIDS[5] = R.id.five;
        buttonIDS[6] = R.id.six;
        buttonIDS[7] = R.id.seven;
        buttonIDS[8] = R.id.eight;
        buttonIDS[9] = R.id.nine;
        buttonIDS[10] = R.id.a;
        buttonIDS[11] = R.id.b;
        buttonIDS[12] = R.id.c;
        buttonIDS[13] = R.id.d;
        buttonIDS[14] = R.id.e;
        buttonIDS[15] = R.id.f;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        drawer.closeDrawer(GravityCompat.START);
        if (id == R.id.nav_history) {
            startActivity(new Intent(this, HistoryActivity.class));
        }

        return true;
    }

    /**
     * Intermediate call to determine count
     *
     * @param position input type index
     */
    private void setButtons(int position) {
        textView.setText(null);
        switch (position) {
            case 2:
            default:
                setButtonProperties(10);
                break;
            case 0:
                setButtonProperties(2);
                break;
            case 1:
                setButtonProperties(8);
                break;
            case 3:
                setButtonProperties(16);
                break;
        }
    }

    /**
     * Sets the properties of buttons of button
     *
     * @param count number of buttons to be affected
     */
    private void setButtonProperties(int count) {
        for (int i = 0; i < buttonIDS.length; i++) {
            Button button = (Button) findViewById(buttonIDS[i]);

            boolean flag = i < count;
            int view = flag ? View.VISIBLE : View.INVISIBLE;

            button.setVisibility(view);
            button.setEnabled(flag);
        }
    }

}

package com.maltsev.labyrinth;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;


public class InfoViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_about_card_activity);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String title = extras.getString("title");
            String subtitle = extras.getString("subtitle");
            String description = extras.getString("description");

            TextView textViewTitle = (TextView) findViewById(R.id.tv_card_main_title);
            textViewTitle.setText(title);

            TextView textViewSubtitle = (TextView) findViewById(R.id.tv_card_main_subtitle);
            textViewSubtitle.setText(subtitle);

            TextView textViewDescription = (TextView) findViewById(R.id.textView);
            textViewDescription.setText(description);
        }

        findViewById(R.id.imageButton2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        hideSystemAndActionBar();
    }

    private void hideSystemAndActionBar() {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
    }
}

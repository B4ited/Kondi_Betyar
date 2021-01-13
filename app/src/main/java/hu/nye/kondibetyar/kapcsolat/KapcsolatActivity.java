package hu.nye.kondibetyar.kapcsolat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

import hu.nye.kondibetyar.MenuActivity;
import hu.nye.kondibetyar.R;
import hu.nye.kondibetyar.etrend.EtrendWebViewerActivity;

public class KapcsolatActivity extends AppCompatActivity {
    private ImageButton menu;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kapcsolat);
        menu=findViewById(R.id.ib_menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivity("Menu");

            }
        });

    }
    public void OpenActivity(String Activity) {
        if (Activity == "Menu") intent = new Intent(this, MenuActivity.class);
        startActivity(intent);

    }
}
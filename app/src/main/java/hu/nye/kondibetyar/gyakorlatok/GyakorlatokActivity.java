package hu.nye.kondibetyar.gyakorlatok;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import hu.nye.kondibetyar.MenuActivity;
import hu.nye.kondibetyar.R;
import hu.nye.kondibetyar.etrend.EtrendWebViewerActivity;

public class GyakorlatokActivity extends AppCompatActivity {

    private TextView title;
    private ImageButton menu;
    private ImageButton check;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyakorlatok);
        getSupportActionBar().setTitle("Gyakorlatok");
        menu=findViewById(R.id.ib_menu);
        check=findViewById(R.id.ib_check);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               OpenActivity("Menu");
            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivity("WebViewerActivity");
            }
        });
    }

    public void OpenActivity(String Activity) {
        if (Activity == "Menu") intent = new Intent(this, Menu.class);
        if (Activity == "WebViewerActivity") intent = new Intent(this, GyakorlatokWebViewerActivity.class);
        startActivity(intent);

    }
}

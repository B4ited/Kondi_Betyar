package hu.nye.kondibetyar.edzes;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import hu.nye.kondibetyar.R;
import hu.nye.kondibetyar.etrend.EtrendWebViewerActivity;

public class EdzesActivty extends AppCompatActivity {
    private Intent intent;
    private ImageButton menu;
    private ImageButton check;
    private Button edzes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edzes_main);
        menu=findViewById(R.id.ib_menu);
        check=findViewById(R.id.ib_check);
        edzes=findViewById(R.id.b_sajat_edzes);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivity("Menu");
            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { OpenActivity("EdzesWebViewerActivity"); }
        });
        edzes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { OpenActivity("SajatEdzesTervekActivity");
            }
        });

        getSupportActionBar().setTitle("Edzés");
    }


    public void OpenActivity(String Activity) {
        if (Activity == "Menu") intent = new Intent(this, Menu.class);
        if (Activity == "EdzesWebViewerActivity") intent = new Intent(this,EdzesWebviewerActivty.class);
        if (Activity == "SajatEdzesTervekActivity") intent = new Intent(this, SajatEdzesTervekActivity.class);
        startActivity(intent);

    }


}

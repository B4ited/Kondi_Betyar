package hu.nye.kondibetyar.edzes.edit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import hu.nye.kondibetyar.MenuActivity;
import hu.nye.kondibetyar.R;
import hu.nye.kondibetyar.edzes.EdzesActivity;
import hu.nye.kondibetyar.edzes.EdzesNapActivity;
import hu.nye.kondibetyar.edzes.EdzesTervekActivity;

public class EditEdzesActivity extends AppCompatActivity {
    private LinearLayout ll;
    private ImageButton add;
    private ImageButton menu;
    private EditText nev;
    private TextView title;
    private Intent intent;
    private String terv;
    public int db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edzes_edit_edzes);
        db=1;
        ll = this.findViewById(R.id.l_layout);
        menu = this.findViewById(R.id.ib_menu);
        add=this.findViewById(R.id.ib_add);
        nev=this.findViewById(R.id.et_nev);
        title=this.findViewById(R.id.t_title);
        intent=getIntent();
        title.setText(terv);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivity("MenuActivity");
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivity("EdzesActivity");
            }
        });

    }

    private void OpenActivity(String Activity) {

        if(Activity=="MenuActivity"){
            intent=new Intent(this, MenuActivity.class);
            startActivity(intent);
        }
        if(Activity=="EdzesActivity") {
            intent = new Intent(this, EdzesActivity.class);
            startActivity(intent);
        }
    }
}

package hu.nye.kondibetyar.edzes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import hu.nye.kondibetyar.R;
import hu.nye.kondibetyar.edzes.edit.EditEdzesActivity;
import hu.nye.kondibetyar.edzes.edit.EditNapActivity;
import hu.nye.kondibetyar.edzes.edit.EditTervekActivity;

public class EdzesActivity extends AppCompatActivity {
    private Button edzes;
    private ImageButton edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edzes);
        edzes = this.findViewById(R.id.b_mell);
        edit=this.findViewById(R.id.ib_add);
        edzes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivity("EdzesNapActivity");
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivity("EditEdzesActivity");
            }
        });

    }

    public void OpenActivity(String Activity) {
        if (Activity == "EdzesNapActivity") {
            Intent intent = new Intent(this, EdzesNapActivity.class);
            startActivity(intent);
        }

        if(Activity == "EditEdzesActivity"){
            Intent intent = new Intent(this, EditEdzesActivity.class);
            startActivity(intent);
        }

    }
}

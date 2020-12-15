package hu.nye.kondibetyar.edzes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import hu.nye.kondibetyar.R;

public class EdzesActivity extends AppCompatActivity {
    private Button edzes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edzes);
        edzes = this.findViewById(R.id.b_mell);
        edzes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivity("EdzesNapActivity");
            }
        });
    }

    public void OpenActivity(String Activity) {
        if (Activity == "EdzesNapActivity") {
            Intent intent = new Intent(this, EdzesNapActivity.class);
            startActivity(intent);
        }
    }
}

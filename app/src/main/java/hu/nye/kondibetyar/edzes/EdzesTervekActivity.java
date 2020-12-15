package hu.nye.kondibetyar.edzes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import hu.nye.kondibetyar.R;
import hu.nye.kondibetyar.etrend.EtrendActivity;
import hu.nye.kondibetyar.gyakorlatok.GyakorlatokActivity;
import hu.nye.kondibetyar.kapcsolat.KapcsolatActivity;

public class EdzesTervekActivity extends AppCompatActivity {
    private Button edzes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edzestervek);
        edzes = this.findViewById(R.id.b_hallado);
        edzes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivity("EdzesActivity");
            }
        });
    }

    public void OpenActivity(String Activity) {
        if (Activity == "EdzesActivity") {
            Intent intent = new Intent(this, EdzesActivity.class);
            startActivity(intent);
        }
    }
}
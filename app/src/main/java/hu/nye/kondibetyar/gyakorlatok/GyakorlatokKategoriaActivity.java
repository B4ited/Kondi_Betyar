package hu.nye.kondibetyar.gyakorlatok;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import hu.nye.kondibetyar.R;

public class GyakorlatokKategoriaActivity extends AppCompatActivity {

    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyakorlatok);

        title=this.findViewById(R.id.gyakorlatok_fotitle);
        title.setText("Mellizom csoport!");
    }
}

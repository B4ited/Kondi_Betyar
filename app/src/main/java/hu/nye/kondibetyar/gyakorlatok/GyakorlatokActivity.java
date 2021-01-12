package hu.nye.kondibetyar.gyakorlatok;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import hu.nye.kondibetyar.R;

public class GyakorlatokActivity extends AppCompatActivity {

    private TextView title;
    private Button mell;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyakorlatok);
        mell = this.findViewById(R.id.gyakorlatokKategoriaMell);
        intent = new Intent(this, GyakorlatokKategoriaActivity.class);
        mell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("kategoria","mell");
                startActivity(intent);
            }
        });
        title=this.findViewById(R.id.gyakorlatok_fotitle);
        title.setText("Válassz izomcsoportot!");
    }
}

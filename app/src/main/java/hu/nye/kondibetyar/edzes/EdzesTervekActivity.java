package hu.nye.kondibetyar.edzes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import hu.nye.kondibetyar.R;
import hu.nye.kondibetyar.edzes.edit.EditTervekActivity;


public class EdzesTervekActivity extends AppCompatActivity {
    private Button edzes;
    private ImageButton edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edzestervek);
        edzes = this.findViewById(R.id.b_hallado);
        edit=this.findViewById(R.id.ib_add);
        edzes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivity("EdzesActivity");
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivity("EditTervekActivity");
            }
        });
    }

    public void OpenActivity(String Activity) {
        if (Activity == "EdzesActivity") {
            Intent intent = new Intent(this, EdzesActivity.class);
            startActivity(intent);
        }
        if(Activity == "EditTervekActivity"){
            Intent intent = new Intent(this, EditTervekActivity.class);
            startActivity(intent);
        }
    }
}
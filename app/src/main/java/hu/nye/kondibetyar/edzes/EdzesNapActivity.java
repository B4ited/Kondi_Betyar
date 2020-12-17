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

public class EdzesNapActivity extends AppCompatActivity {
    private ImageButton edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edzesnap);
        edit=this.findViewById(R.id.ib_add);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivity("EditNapActivity");
            }
        });
    }
    public void OpenActivity(String Activity) {
        if(Activity == "EditNapActivity"){
            Intent intent = new Intent(this, EditNapActivity.class);
            startActivity(intent);
        }
    }

}

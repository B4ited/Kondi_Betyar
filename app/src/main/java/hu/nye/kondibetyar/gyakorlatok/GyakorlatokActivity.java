package hu.nye.kondibetyar.gyakorlatok;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;

import hu.nye.kondibetyar.R;

public class GyakorlatokActivity extends AppCompatActivity {
    public TextView tv_text;
    public  String text="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyakorlatok);

        tv_text=findViewById(R.id.tv_text);
        try {
            InputStream is=getAssets().open("test");
            int size=is.available();
            byte[] buffer=new byte[size];
            is.read(buffer);
            is.close();
            text=new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        tv_text.setText(text);


    }
}

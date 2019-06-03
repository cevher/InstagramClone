package android.galileo.instagramclone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{
    private Button btnSave;
    private EditText edtName, edtPunchSpeed, edtPunchPower, edtKickSpeed, edtKickPower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(MainActivity.this);

        edtName = findViewById(R.id.edtName);
        edtPunchSpeed = findViewById(R.id.edtPunchSpeed);
        edtPunchPower = findViewById(R.id.edtPunchPower);
        edtKickSpeed = findViewById(R.id.edtKickSpeed);
        edtKickPower = findViewById(R.id.edtKickPower);
    }


    @Override
    public void onClick(View v) {
        final ParseObject kickBoxer = new ParseObject("Kickboxer");
        kickBoxer.put("name", edtName.getText().toString());
        kickBoxer.put("punchSpeed", Integer.parseInt(edtPunchSpeed.getText().toString()));
        kickBoxer.put("punchPower", Integer.parseInt(edtPunchPower.getText().toString()));
        kickBoxer.put("kickSpeed", Integer.parseInt(edtKickSpeed.getText().toString()));
        kickBoxer.put("kickPower", Integer.parseInt(edtKickPower.getText().toString()));
        kickBoxer.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null) {
                    FancyToast.makeText(MainActivity.this,kickBoxer.get("name") + " saved to server",FancyToast.LENGTH_LONG,FancyToast.DEFAULT,true).show();
                } else{
                    FancyToast.makeText(MainActivity.this,e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                }


            }
        });

    }
}














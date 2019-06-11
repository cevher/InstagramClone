package android.galileo.instagramclone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.parse.*;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{
    private Button btnSave;
    private EditText edtName, edtPunchSpeed, edtPunchPower, edtKickSpeed, edtKickPower;
    private TextView txtGetData;
    private Button btnGetAll;
    private String allKickboxers;



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

        txtGetData = findViewById(R.id.txtGetData);
        btnGetAll = findViewById(R.id.btnGetAll);

        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("Kickboxer");
                parseQuery.getInBackground("N9Vrst7kQr", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if(object != null && e==null) {
                            txtGetData.setText(object.get("name") +" - " + "Punch Power: " + object.get("punchPower"));
                        }
                    }
                });
            }
        });

        btnGetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allKickboxers = "";
                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("Kickboxer");

                queryAll.whereGreaterThanOrEqualTo("punchPower", 5000);

                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if(e == null){
                            if(objects.size() > 0){
                                for (ParseObject kickBoxer : objects){
                                    allKickboxers = allKickboxers + kickBoxer.get("name") + "\n";
                                }

                                FancyToast.makeText(MainActivity.this, allKickboxers, FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                            } else {
                                FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.INFO, true).show();
                            }
                        }
                    }
                });
            }
        });
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














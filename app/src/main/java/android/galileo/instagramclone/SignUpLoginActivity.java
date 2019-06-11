package android.galileo.instagramclone;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLoginActivity extends AppCompatActivity {

    private EditText edtUsernameSignUp, edtUserNameLogin, edtPasswordSignUp, edtPasswordLogin;
    private Button btnSignUp, btnLogin;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.signup_activity);
        edtPasswordLogin = findViewById(R.id.edtLoginPassword);
        edtUserNameLogin = findViewById(R.id.edtLoginName);
        edtUsernameSignUp = findViewById(R.id.edtNameSignUp);
        edtPasswordSignUp = findViewById(R.id.edtPasswordSignUp);

        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogin = findViewById(R.id.btnLogin);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ParseUser appuser = new ParseUser();
                appuser.setUsername(edtUsernameSignUp.getText().toString());
                appuser.setPassword(edtPasswordSignUp.getText().toString());


                appuser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null){
                            FancyToast.makeText(SignUpLoginActivity.this, appuser.get("username") + " signedUp Succesfully", FancyToast.LENGTH_LONG, FancyToast.INFO, true).show();
                        } else {
                            FancyToast.makeText(SignUpLoginActivity.this, e.getMessage() , FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        }
                    }
                });
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logInInBackground(edtUserNameLogin.getText().toString(), edtPasswordLogin.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user != null && e == null){
                            FancyToast.makeText(SignUpLoginActivity.this, user.get("username") + " is logged in succesfully", FancyToast.LENGTH_LONG, FancyToast.INFO, true).show();
                        } else {
                            FancyToast.makeText(SignUpLoginActivity.this, e.getMessage() , FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        }
                    }
                });
            }
        });

    }
}

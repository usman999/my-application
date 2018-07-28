package sohaib.cardiacdiseaseprediction.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import sohaib.cardiacdiseaseprediction.R;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private TextView tvCreateAccount;
    private EditText etEnterPass,etEnterEmail;
    private String LoginEmail,LoginPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin=(Button) findViewById(R.id.btnLogin);
        tvCreateAccount=(TextView) findViewById(R.id.tvCreateAccount);
        etEnterPass=(EditText) findViewById(R.id.etLoginPassword);
        etEnterEmail=(EditText) findViewById(R.id.etLoginEmailAdress);

        etEnterEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if(!etEnterEmail.getText().toString().matches("[a-zA-Z0-9_-]+@[a-z]+.+[.a-z]") ){
                    etEnterEmail.setError("invalid email adress");
                }
                else {
                    LoginEmail=etEnterEmail.getText().toString();

                }
            }
        });

        etEnterPass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if(etEnterPass.getText().toString().equals("")){
                    etEnterPass.setError("Please Enter Value");
                }
                else {
                    LoginPass=etEnterPass.getText().toString();

                }
            }
        });





        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!etEnterPass.getText().toString().equals("") && !etEnterPass.getText().toString().equals("")){
                    Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(LoginActivity.this,"Please Enter Values First",Toast.LENGTH_LONG).show();
                }


            }
        });
        tvCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this, CreateAccountActivity.class);
                startActivity(intent);
            }
        });



    }
}

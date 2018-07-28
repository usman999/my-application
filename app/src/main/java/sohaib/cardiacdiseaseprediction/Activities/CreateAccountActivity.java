package sohaib.cardiacdiseaseprediction.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import sohaib.cardiacdiseaseprediction.R;

public class CreateAccountActivity extends AppCompatActivity {

    Button btn_Signup;
    EditText etFirstName,etLastName,etEmail,etPhone,etpassword,etConfirmPass;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        btn_Signup=(Button) findViewById(R.id.btn_signup);

        btn_Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CreateAccountActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });




    }
}

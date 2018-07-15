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

    Button btnLogin;
    TextView tvCreateAccount;
    EditText etEnterPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin=(Button) findViewById(R.id.btnLogin);
        tvCreateAccount=(TextView) findViewById(R.id.tvCreateAccount);
        etEnterPass=(EditText) findViewById(R.id.etLoginPassword);

        etEnterPass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getRawX() <= (etEnterPass.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width()))
                {
                    // your action here
                    Toast.makeText(LoginActivity.this,"helo",Toast.LENGTH_SHORT).show();
                    return true;
                }

        return false;
        }
    });





        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
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

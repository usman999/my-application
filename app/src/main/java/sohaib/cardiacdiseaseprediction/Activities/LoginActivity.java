package sohaib.cardiacdiseaseprediction.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import sohaib.cardiacdiseaseprediction.DataProviders.StaticData;
import sohaib.cardiacdiseaseprediction.DataProviders.User;
import sohaib.cardiacdiseaseprediction.Helper.SharedPrefManager;
import sohaib.cardiacdiseaseprediction.R;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private TextView tvCreateAccount;
    private EditText etEnterPass,etEnterEmail;
    private String LoginEmail,LoginPass;
    private String url= StaticData.BASE_URL+"/api/Login";
    private ProgressDialog dialog;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, Home.class));
        }


        btnLogin=(Button) findViewById(R.id.btnLogin);
        tvCreateAccount=(TextView) findViewById(R.id.tvCreateAccount);
        etEnterPass=(EditText) findViewById(R.id.etLoginPassword);
        etEnterEmail=(EditText) findViewById(R.id.etLoginEmailAdress);


        dialog=new ProgressDialog(this);


        etEnterEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {


                    if (!etEnterEmail.getText().toString().matches("[a-zA-Z0-9_-]+@[a-z]+.+[.a-z]")) {
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
            }
        });

        LoginPass=etEnterPass.getText().toString();




        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!etEnterPass.getText().toString().equals("") && !etEnterPass.getText().toString().equals("")){

                    LoginEmail=etEnterEmail.getText().toString();
                    LoginPass=etEnterPass.getText().toString();

                    if (etEnterEmail.getText().toString().matches("[a-zA-Z0-9_-]+@[a-z]+.+[.a-z]")){

                      //  Toast.makeText(LoginActivity.this,""+LoginEmail+" "+LoginPass,Toast.LENGTH_LONG).show();
                        Login();
                        dialog.setMessage("Please Wait");
                        dialog.setCanceledOnTouchOutside(false);
                        dialog.show();
                    }
                    else {
                        Toast.makeText(LoginActivity.this,"Invalid Email",Toast.LENGTH_LONG).show();

                    }
                }
                else {
                    Toast.makeText(LoginActivity.this,"Please Enter Values First",Toast.LENGTH_LONG).show();
                }



            }
        });
        tvCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
                Intent intent=new Intent(LoginActivity.this, CreateAccountActivity.class);
                startActivity(intent);
            }
        });



    }

    private void Login() {

        JSONObject object = new JSONObject();
        try{

            object.put("id",1);
            object.put("Email",LoginEmail);
            object.put("Password",LoginPass);

        }catch (Exception e){

        }

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                dialog.dismiss();
                /*Toast.makeText(LoginActivity.this," "+response.toString(),Toast.LENGTH_LONG).show();*/


                try {
                    user=new User(response.getInt("id"),
                            response.getString("Fisrt_Name"),
                            response.getString("Last_Name"),
                            response.getString("Email"),
                            response.getString("Phone_No"),
                            response.getString("Password"),
                            response.getString("User_Location"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                Intent intent=new Intent(LoginActivity.this, Home.class);
                startActivity(intent);
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                Toast.makeText(LoginActivity.this," "+error.toString(),Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}

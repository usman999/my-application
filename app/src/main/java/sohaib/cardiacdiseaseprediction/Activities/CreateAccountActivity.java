package sohaib.cardiacdiseaseprediction.Activities;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import sohaib.cardiacdiseaseprediction.DataProviders.StaticData;
import sohaib.cardiacdiseaseprediction.DataProviders.User;
import sohaib.cardiacdiseaseprediction.Helper.SharedPrefManager;
import sohaib.cardiacdiseaseprediction.R;

public class CreateAccountActivity extends AppCompatActivity {

    private Button btn_Signup;
    private EditText etFirstName,etLastName,etEmail,etPhone,etpassword,etConfirmPass;
    private Spinner spinnerCityName;
    private String firstNameValue,lastNameValue,emailValue,phoneValue,passValue,confirmPassValue,cityValue;
    private String url= StaticData.BASE_URL+"/api/User";
    private ProgressDialog dialog;
    private User user;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        btn_Signup=(Button) findViewById(R.id.btn_signup);
        etFirstName=(EditText) findViewById(R.id.etFirstName);
        etLastName=(EditText) findViewById(R.id.etLastName);
        etEmail=(EditText) findViewById(R.id.etEmail);
        etPhone=(EditText) findViewById(R.id.etPhoneNumber);
        etpassword=(EditText) findViewById(R.id.etPassword);
        etConfirmPass=(EditText) findViewById(R.id.etConfirmPassword);
        spinnerCityName=(Spinner) findViewById(R.id.spinnerSelectCity);


        etFirstName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(etFirstName.getText().toString().matches("[0-9]")){
                    etFirstName.setError("You Can't Use Number");
                    }

            }
        });

        etLastName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(etLastName.getText().toString().matches("[0-9]")){
                    etLastName.setError("You Can't Use Number");
                }

            }
        });



        etEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!etEmail.getText().toString().matches("[a-zA-Z0-9_-]+@[a-z]+.+[.a-z]")){
                    etEmail.setError("invalid email address");
                    }

            }
        });

        etpassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(etpassword.getText().toString().matches("")){
                    etpassword.setError("Enter Password");
                    }


            }
        });
        etConfirmPass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(etConfirmPass.getText().toString().equals(etpassword.getText().toString())){
                    confirmPassValue=etConfirmPass.getText().toString();
                    }
                    else {
                    etConfirmPass.setError("password does not match");
                }

            }
        });




        // Item Click Listener on Spinner
        spinnerCityName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @SuppressLint({"ResourceAsColor", "NewApi"})
          @Override
          public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

            if (position == 0) {
            /*Toast.makeText(getActivity(),"Not Selectable",Toast.LENGTH_SHORT).show();*/

            } else {
            spinnerCityName.setBackgroundColor(getColor(R.color.colorAccent));
            cityValue = String.valueOf(adapterView.getItemAtPosition(position));


             }
              }

              @Override
              public void onNothingSelected(AdapterView<?> adapterView) {

              }


    });

        btn_Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!etFirstName.getText().toString().equals("") && !etLastName.getText().toString().equals("") && !etEmail.getText().toString().equals("") && !etConfirmPass.getText().toString().equals("") && !cityValue.equals("")){
                    firstNameValue=etFirstName.getText().toString();
                    lastNameValue=etLastName.getText().toString();
                    emailValue=etEmail.getText().toString();
                    confirmPassValue=etConfirmPass.getText().toString();
                    phoneValue=etPhone.getText().toString();

                    if (!etEmail.getText().toString().matches("[a-zA-Z0-9_-]+@[a-z]+.+[.a-z]")){
                        Toast.makeText(CreateAccountActivity.this,"Incorrect Email",Toast.LENGTH_SHORT).show();

                    }else

                    if (!etpassword.getText().toString().equals(confirmPassValue)){
                        Toast.makeText(CreateAccountActivity.this,"Password Does not match",Toast.LENGTH_SHORT).show();
                    }
                    else {
                          signup();
                    dialog=new ProgressDialog(CreateAccountActivity.this);
                    dialog.setMessage("Please Wait");
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();
                    }








                }
                else {
                    Toast.makeText(CreateAccountActivity.this,"Please Enter Values First",Toast.LENGTH_SHORT).show();
                }




            }
        });








    }

    private void signup() {



        JSONObject object = new JSONObject();
        try{

            object.put("id",1);
            object.put("Fisrt_Name",firstNameValue);
            object.put("Last_Name",lastNameValue);
            object.put("Email",emailValue);
            object.put("Phone_No",phoneValue);
            object.put("Password",confirmPassValue);
            object.put("User_Location",cityValue);
        }catch (Exception e){

        }

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                dialog.dismiss();

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
                Intent intent=new Intent(CreateAccountActivity.this, Home.class);
                startActivity(intent);
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                Toast.makeText(CreateAccountActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}

package sohaib.cardiacdiseaseprediction.Activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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
import sohaib.cardiacdiseaseprediction.Fragments.HeartAnalysis;
import sohaib.cardiacdiseaseprediction.Fragments.Instructions;
import sohaib.cardiacdiseaseprediction.Fragments.Reports;
import sohaib.cardiacdiseaseprediction.Fragments.ViewDoctors;
import sohaib.cardiacdiseaseprediction.Helper.SharedPrefManager;
import sohaib.cardiacdiseaseprediction.R;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String url= StaticData.BASE_URL+"/api/Feedback";

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    TextView tvUserName,tvUserEmail;
    private User user;
    private static final String SHARED_PREF_NAME = "simplifiedcodingsharedpref";
    private SharedPreferences sharedPreferences;
    private static final String KEY_FIRSTNAME = "keyfirstname";
    private static final String KEY_EMAIL = "keyemail";
    private static final String KEY_ID = "keyid";
    private static Toolbar toolbar;
    private String feedBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());


        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView=navigationView.getHeaderView(0);

        tvUserName=headerView.findViewById(R.id.tvPersonName);
        tvUserEmail=headerView.findViewById(R.id.tvPersonEmail);

        sharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        if(!sharedPreferences.getString(KEY_FIRSTNAME,"").isEmpty()){
            tvUserName.setText(sharedPreferences.getString(KEY_FIRSTNAME,""));
            tvUserEmail.setText(sharedPreferences.getString(KEY_EMAIL,""));
        }




    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
/*

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getSupportFragmentManager();
        HeartAnalysis heartAnalysis=new HeartAnalysis();
        if (id == R.id.nav_Heart) {

            mViewPager.setCurrentItem(0);



        } else if (id == R.id.nav_ViewDoctor) {
            mViewPager.setCurrentItem(1);

        } else if (id == R.id.nav_Instructions) {
            mViewPager.setCurrentItem(2);

        } else if (id == R.id.nav_Reports) {
            mViewPager.setCurrentItem(3);

        } else if (id == R.id.nav_Logout) {

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
            finish();
            startActivity(new Intent(Home.this, LoginActivity.class));


        } else if (id == R.id.nav_Feedback) {

            AlertDialog.Builder alert=new AlertDialog.Builder(Home.this);
            final EditText editText=new EditText(Home.this);

            alert.setTitle("Enter Feedback");
            alert.setView(editText);
            alert.setPositiveButton("Send", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    feedBack=editText.getText().toString();
                    if(!feedBack.isEmpty()){
                        Feedback();
                        Toast.makeText(Home.this,"Feedback:"+" "+feedBack,Toast.LENGTH_LONG).show();

                    }
                    Toast.makeText(Home.this,"Please Enter Feedback",Toast.LENGTH_LONG).show();
                }
            });
            alert.show();




        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void Feedback() {


        JSONObject object = new JSONObject();
        try{

            object.put("Id",1);
            object.put("email",sharedPreferences.getString(KEY_EMAIL,""));
            object.put("feedback1",feedBack);

        }catch (Exception e){

        }

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                Toast.makeText(Home.this," "+response.toString(),Toast.LENGTH_LONG).show();


               /* try {
                    user=new User(response.getInt("id"),
                            response.getString("Fisrt_Name"),
                            response.getString("Last_Name"),
                            response.getString("Email"),
                            response.getString("Phone_No"),
                            response.getString("Password"),
                            response.getString("User_Location"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Home.this," "+error.toString(),Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    public static class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position){

                case 0:
                    HeartAnalysis heartAnalysis=new HeartAnalysis();

                    return heartAnalysis;
                case 1:
                    ViewDoctors viewDoctors=new ViewDoctors();
                    return viewDoctors;
                case 2:
                    Instructions instructions=new Instructions();
                    return instructions;
                case 3:
                    Reports reports=new Reports();
                    return reports;
                default:
                    return null;




            }




        }

        @Override
        public int getCount() {

            return 4;
        }
    }
}

package sohaib.cardiacdiseaseprediction.Activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Environment;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

import sohaib.cardiacdiseaseprediction.DataProviders.ReportsData;
import sohaib.cardiacdiseaseprediction.R;


public class HeartAnalysisResult extends AppCompatActivity {

    private String Name;
    private LinearLayout def;
    private CharSequence sysDate;
    private CharSequence sysTime;
    public static int REQUEST_PERMISSIONS = 1;
    boolean boolean_permission;

    private TextView tvName,tvGender,tvAge,tvRst_bloodPressure,tvSerumChlestrol, tvFst_bloodSugar,tvMax_heartRate,tvSt_depressionInduced,tvchestPain,tvrestingECC,tvindicedAmgina,tvslopeOfPeak,tvnumberOfMajorVessels,tvnumberOfDefects;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_analysis_result);

        def=(LinearLayout) findViewById(R.id.resultLayout);
        fn_permission();


        // TextView

        tvName=(TextView) findViewById(R.id.tvPatientName);
        tvAge=(TextView) findViewById(R.id.tvPatientAge);
        tvGender=(TextView) findViewById(R.id.tvPatientGender);
        tvFst_bloodSugar=(TextView) findViewById(R.id.tvPatientFastBP);
        tvRst_bloodPressure=(TextView) findViewById(R.id.tvPatientRstBP);
        tvSerumChlestrol=(TextView) findViewById(R.id.tvPatientCholestrol);
        tvMax_heartRate=(TextView) findViewById(R.id.tvPatientMaxHeartRate);
        tvSt_depressionInduced=(TextView) findViewById(R.id.tvPatientSTIndunced);
        tvchestPain=(TextView) findViewById(R.id.tvPatientChestPain);
        tvrestingECC=(TextView) findViewById(R.id.tvPatientRestingECC);
        tvindicedAmgina=(TextView) findViewById(R.id.tvPatientInducedAngina);
        tvslopeOfPeak=(TextView) findViewById(R.id.tvPatientSlopeOfPeak);
        tvnumberOfMajorVessels=(TextView) findViewById(R.id.tvPatientMajorVessels);
        tvnumberOfDefects=(TextView) findViewById(R.id.tvPatientNumberOFDefects);



        Intent intent=getIntent();
        Name=intent.getStringExtra("patientName");
        tvName.setText(Name);
        tvAge.setText(""+intent.getIntExtra("patientAge",0));
        tvGender.setText(intent.getStringExtra("patientGender"));
        tvFst_bloodSugar.setText(""+intent.getIntExtra("patientfstSugar",0)+" mg/dl");
        tvRst_bloodPressure.setText(""+intent.getIntExtra("patientRstBP",0)+" mm/hg");
        tvSerumChlestrol.setText(""+intent.getIntExtra("patientCholestrol",0)+" mg/dl");
        tvMax_heartRate.setText(""+intent.getIntExtra("patientMaxHeartRate",0)+" beats/min");
        tvSt_depressionInduced.setText(""+intent.getIntExtra("patientSTInduced",0)+" mm hg");
        tvchestPain.setText(intent.getStringExtra("chestPain"));
        tvrestingECC.setText(intent.getStringExtra("restingECC"));
        tvindicedAmgina.setText(intent.getStringExtra("inducedAngina"));
        tvslopeOfPeak.setText(intent.getStringExtra("slopeOfPeak"));
        tvnumberOfMajorVessels.setText(intent.getStringExtra("majorVessels"));
        tvnumberOfDefects.setText(intent.getStringExtra("defects"));




        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_result, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_save) {
                        Bitmap bitmap1 = loadBitmapFromView(def, def.getWidth(), def.getHeight());
                        saveBitmap(bitmap1);
               return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void saveBitmap(Bitmap bitmap) {

        Date d = new Date();
        sysDate = DateFormat.format("MMMM-d-yyyy", d.getTime());
        sysTime = DateFormat.format("hh:mm aa", d.getTime());


        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        //Toast.makeText(MainActivity.this,CustomerName,Toast.LENGTH_SHORT).show();


        String fname = ""+ sysDate +"-"+ sysTime + "png";

        // create foledr
        File folder = new File(Environment.getExternalStorageDirectory() + "/Cardic Result/"+ Name +"/");
        boolean success = true;
        if (!folder.exists()) {
            Toast.makeText(HeartAnalysisResult.this, "Directory Does Not Exist, Create It", Toast.LENGTH_SHORT).show();

            success=folder.mkdirs();

        }
        if (success) {
            Toast.makeText(HeartAnalysisResult.this, "Directory Created", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(HeartAnalysisResult.this, "Failed - Error", Toast.LENGTH_SHORT).show();
        }

        File imagePath = new File("/sdcard/Cardic Result/"+ Name +"/"+fname);
        FileOutputStream fos;
        try {

            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);

            fos.flush();
            fos.close();
            Toast.makeText(getApplicationContext(),imagePath.getAbsolutePath()+"",Toast.LENGTH_SHORT).show();




            Log.e("ImageSave", "Saveimage");
        } catch (FileNotFoundException e) {
            Log.e("GREC", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("GREC", e.getMessage(), e);
        }
    }


    public static Bitmap loadBitmapFromView(View v, int width, int height) {
        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.draw(c);

        return b;
    }

    private void fn_permission() {
        if ((ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)||
                (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {

            if ((ActivityCompat.shouldShowRequestPermissionRationale(HeartAnalysisResult.this, android.Manifest.permission.READ_EXTERNAL_STORAGE))) {
            } else {
                ActivityCompat.requestPermissions(HeartAnalysisResult.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_PERMISSIONS);

            }

            if ((ActivityCompat.shouldShowRequestPermissionRationale(HeartAnalysisResult.this, Manifest.permission.WRITE_EXTERNAL_STORAGE))) {
            } else {
                ActivityCompat.requestPermissions(HeartAnalysisResult.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_PERMISSIONS);

            }
        } else {
            boolean_permission = true;


        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSIONS) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                boolean_permission = true;
                Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_LONG).show();



            } else {
                Toast.makeText(getApplicationContext(), "Please allow the permission", Toast.LENGTH_LONG).show();

            }
        }
    }
}

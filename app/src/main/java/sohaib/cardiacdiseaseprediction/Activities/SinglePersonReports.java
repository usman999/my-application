package sohaib.cardiacdiseaseprediction.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toolbar;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import sohaib.cardiacdiseaseprediction.R;

public class SinglePersonReports extends AppCompatActivity {

    String personName;
    private List<String> filelist = new ArrayList<String>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_person_reports);

        Intent n=getIntent();
        personName=n.getStringExtra("ReportPersonName");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(personName);
        getSupportActionBar().setHomeButtonEnabled(true);
        listView=(ListView) findViewById(R.id.lv_singlePersonReports);

        File root = new File("/sdcard/Cardic Result/"+personName+"/");

        listDir(root);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parentView, View view, int position, long l) {

                File root = new File("/sdcard/Cardic Result/"+personName+"/"+parentView.getItemAtPosition(position)+"/");
                Intent intent = new Intent();
                intent.setAction(android.content.Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(new File(String.valueOf(root))),"image/png");
                startActivity(intent);
            }
        });




    }
    void listDir(File f) {
        File[] files = f.listFiles();

        if (filelist.isEmpty()) {
            for (File file : files) {
                filelist.add(file.getName());
            }
        }


        ArrayAdapter<String> diresctoryList = new ArrayAdapter<String>(SinglePersonReports.this, android.R.layout.simple_dropdown_item_1line, filelist);
        listView.setAdapter(diresctoryList);



    }


}

package sohaib.cardiacdiseaseprediction.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.renderscript.Int3;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import sohaib.cardiacdiseaseprediction.Activities.SinglePersonReports;
import sohaib.cardiacdiseaseprediction.R;


public class Reports extends Fragment {


    ListView filesListView;
    private List<String> filelist = new ArrayList<String>();


    public Reports() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_reports, container, false);

        filesListView = (ListView) view.findViewById(R.id.lv_reports);

        File root = new File("/sdcard/Cardic Result/");

        listDir(root);


        filesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent n=new Intent(getActivity(), SinglePersonReports.class);
                n.putExtra("ReportPersonName",""+adapterView.getItemAtPosition(position));
                startActivity(n);

            }
        });


        return view;
    }

    void listDir(File f) {
        File[] files = f.listFiles();

        if (filelist.isEmpty()) {
        for (File file : files) {
            filelist.add(file.getName());
        }
    }


            ArrayAdapter<String> diresctoryList = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, filelist);
            filesListView.setAdapter(diresctoryList);

        }



}

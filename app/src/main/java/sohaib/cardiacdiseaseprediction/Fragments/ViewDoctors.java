package sohaib.cardiacdiseaseprediction.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import sohaib.cardiacdiseaseprediction.Adapters.AdapterViewDoctor;
import sohaib.cardiacdiseaseprediction.DataProviders.ViewDoctorDataProvider;
import sohaib.cardiacdiseaseprediction.R;

public class ViewDoctors extends Fragment {


    public ViewDoctors() {

    }
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    String[] doctorName={"Dr. sohaib","Dr. Junaid","Dr. Bilal"};
    String[] doctorResidence={"sodhra","Sodhra","Sodhra"};
    String[] doctorSpeciality={"pump","Heart","Heart"};
    String[] doctorGender={"male","male","male"};
    String[] doctorphone={"0976554","0876544","09876544"};
    String[] doctorEmail={"sohaib@gmail.com","Junaid@gmail.com","Bilal@gmail.com"};

   ArrayList<ViewDoctorDataProvider> arrayLis=new ArrayList<ViewDoctorDataProvider>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_view_doctors, container, false);

        recyclerView=(RecyclerView) view.findViewById(R.id.rvViewDoctor);
        int i=0;
        for(String name:doctorName) {
            ViewDoctorDataProvider dataProvider = new ViewDoctorDataProvider(name,doctorSpeciality[i],doctorGender[i],doctorResidence[i],doctorphone[i],doctorEmail[i]);
            arrayLis.add(dataProvider);
            i++;
        }

        adapter = new AdapterViewDoctor(arrayLis,this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);




        return view;




    }

}

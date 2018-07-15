package sohaib.cardiacdiseaseprediction.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import sohaib.cardiacdiseaseprediction.Activities.HeartAnalysisResult;
import sohaib.cardiacdiseaseprediction.R;


public class HeartAnalysis extends Fragment {
    Button btnSubmit;

    public HeartAnalysis() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_heart_analysis, container, false);

        btnSubmit=(Button) view.findViewById(R.id.btn_SubmitHeartAnalysis);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getActivity(), HeartAnalysisResult.class);
                startActivity(intent);

            }
        });



        return view;



    }

}

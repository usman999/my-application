package sohaib.cardiacdiseaseprediction.Fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import sohaib.cardiacdiseaseprediction.DataProviders.StaticData;
import sohaib.cardiacdiseaseprediction.DataProviders.User;
import sohaib.cardiacdiseaseprediction.R;


public class HeartAnalysis extends Fragment {
    Button btnSubmit;
    Spinner spinnerChestPain,spinnerRestingECC,spinnerIndicedAmgina,spinnersLopeOfPeak,spinnerNumberOfMajorVessels,spinnerNumberOfDefects;
    private EditText etName,etAge,etRst_bloodPressure,etSerumChlestrol, etFst_bloodSugar,etMax_heartRate,etSt_depressionInduced;
    private String chestPainValue,restingECCValue,indicedAmginaValue,slopeOfPeakValue,numberOfMajorVesselsValue,numberOfDefectsValue;
    private int chestPainInt,restingECCInt,indicedAmginaInt,slopeOfPeakInt,numberOfMajorVesselsInt,numberOfDefectsInt;
    private int age,rst_bloodPressure,serumChlestrol, fst_bloodSugar,max_heartRate,st_depressionInduced;
    private RadioGroup radioGender;
    private String genderValue;
    private int genderValueInt;
    private String Name;
    private String Result;
    private String url= StaticData.BASE_URL+"/api/Heart";
    private User user;
    private int fst_bloodSugarInt;

    public HeartAnalysis() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_heart_analysis, container, false);

        btnSubmit=(Button) view.findViewById(R.id.btn_SubmitHeartAnalysis);

        // Spinners
        spinnerChestPain=(Spinner) view.findViewById(R.id.spinnerChestPain);
        spinnerRestingECC=(Spinner) view.findViewById(R.id.spinnerRestingECC);
        spinnerIndicedAmgina=(Spinner) view.findViewById(R.id.spinnerExerciseInduced);
        spinnersLopeOfPeak=(Spinner) view.findViewById(R.id.spinnerSlopPeak);
        spinnerNumberOfMajorVessels=(Spinner) view.findViewById(R.id.spinnerMajorVessels);
        spinnerNumberOfDefects=(Spinner) view.findViewById(R.id.spinnerNumber_Of_Defects);

        // Edit Texts
        etName=(EditText) view.findViewById(R.id.etName);
        etAge=(EditText) view.findViewById(R.id.etAge);
        etRst_bloodPressure=(EditText) view.findViewById(R.id.etRestingBloodPressure);
        etSerumChlestrol=(EditText) view.findViewById(R.id.etSerumCholestrol);
        etFst_bloodSugar =(EditText) view.findViewById(R.id.etFastingBloodSugar);
        etMax_heartRate=(EditText) view.findViewById(R.id.etMaximumHeartRateAchieved);
        etSt_depressionInduced=(EditText) view.findViewById(R.id.etSTDepressionInduced);

        // radio group
        radioGender =(RadioGroup) view.findViewById(R.id.radioGender);

         genderValue = ((RadioButton) view.findViewById(radioGender.getCheckedRadioButtonId())).getText().toString();

        radioGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                genderValue = ((RadioButton) view.findViewById(radioGender.getCheckedRadioButtonId())).getText().toString();
                /*Toast.makeText(getActivity(), genderValue, Toast.LENGTH_SHORT).show();*/
            }
        });



        etName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if(etName.getText().toString().isEmpty()) {
                    etName.setError("Can't be null");
                }
                else {
                    Name=etName.getText().toString();

                    }



            }
        });

        etAge.setOnFocusChangeListener(new View.OnFocusChangeListener() {
         @Override
         public void onFocusChange(View view, boolean b) {

              if(etAge.getText().toString().isEmpty()) {
                  etAge.setError("Can't be null");
              }
              else {
                  age = Integer.parseInt(etAge.getText()
                          .toString());
                  if (age > 120 || age < 1) {
                      etAge.setError("age must be between 1 - 120");
                  }
              }


         }
     });

        etRst_bloodPressure.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if(etRst_bloodPressure.getText().toString().isEmpty()) {
                    etRst_bloodPressure.setError("Can't be null");
                }
                else {
                    rst_bloodPressure = Integer.parseInt(etRst_bloodPressure.getText().toString());
                    if (rst_bloodPressure < 40 || rst_bloodPressure > 280) {
                        etRst_bloodPressure.setError("Blood Pressure Must be between 40 - 280");
                    }
                }
            }
        });

        etSerumChlestrol.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(etSerumChlestrol.getText().toString().isEmpty()) {
                    etSerumChlestrol.setError("Can't be null");
                }
                else {

                    serumChlestrol = Integer.parseInt(etSerumChlestrol.getText().toString());
                    if (serumChlestrol < 100 || serumChlestrol > 600) {
                        etSerumChlestrol.setError("Blood Pressure Must be between 100 - 600");
                    }
                }
            }
        });

        etFst_bloodSugar.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(etFst_bloodSugar.getText().toString().isEmpty()) {
                    etFst_bloodSugar.setError("Can't be null");
                }
                else {

                    fst_bloodSugar = Integer.parseInt(etFst_bloodSugar.getText().toString());
                    if (fst_bloodSugar < 40 || fst_bloodSugar > 280) {
                        etFst_bloodSugar.setError("Blood Pressure Must be between 40 - 280");
                    }
                }
            }
        });

        etMax_heartRate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if(etMax_heartRate.getText().toString().isEmpty()) {
                    etMax_heartRate.setError("Can't be null");
                }
                else {
                    max_heartRate = Integer.parseInt(etMax_heartRate.getText().toString());
                    if (max_heartRate < 80 || max_heartRate > 180) {
                        etMax_heartRate.setError("Blood Pressure Must be between 80 - 180");
                    }
                }
            }
        });

        etSt_depressionInduced.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {


                if(etSt_depressionInduced.getText().toString().isEmpty()) {
                    etSt_depressionInduced.setError("Can't be null");
                }
                else {
                    st_depressionInduced = Integer.parseInt(etSt_depressionInduced.getText().toString());
                    if (st_depressionInduced < 0 || st_depressionInduced > 7) {
                        etSt_depressionInduced.setError("Blood Pressure Must be between 80 - 180");
                    }
                }
            }
        });

        if(!etSt_depressionInduced.getText().toString().isEmpty()){
            st_depressionInduced = Integer.parseInt(etSt_depressionInduced.getText().toString());
        }









        // Item Click Listener on Spinner
        spinnerChestPain.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                if(position==0){
                    /*Toast.makeText(getActivity(),"Not Selectable",Toast.LENGTH_SHORT).show();*/
                    chestPainValue="";
                }
                else {

                    spinnerChestPain.setBackgroundResource(R.color.colorAccent);
                    chestPainValue= String.valueOf(adapterView.getItemAtPosition(position));
                    chestPainInt= (int) adapterView.getItemIdAtPosition(position);
                    /*Toast.makeText(getActivity(),""+chestPainValue,Toast.LENGTH_SHORT).show();*/

                }

            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerRestingECC.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                if(position==0){
                    /*Toast.makeText(getActivity(),"Not Selectable",Toast.LENGTH_SHORT).show();*/
                    restingECCValue="";
                }
                else {
                    spinnerRestingECC.setBackgroundResource(R.color.colorAccent);
                    restingECCValue= String.valueOf(adapterView.getItemAtPosition(position));
                    restingECCInt= (int) adapterView.getItemIdAtPosition(position);

                    /*Toast.makeText(getActivity(),""+restingECCValue,Toast.LENGTH_SHORT).show();*/

                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerIndicedAmgina.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                if(position==0){
                    indicedAmginaValue="";
                    /*Toast.makeText(getActivity(),"Not Selectable",Toast.LENGTH_SHORT).show();
*/
                }
                else {
                    spinnerIndicedAmgina.setBackgroundResource(R.color.colorAccent);
                    indicedAmginaValue= String.valueOf(adapterView.getItemAtPosition(position));
                    indicedAmginaInt= (int) adapterView.getItemIdAtPosition(position);
                    /*Toast.makeText(getActivity(),""+indicedAmginaValue,Toast.LENGTH_SHORT).show();*/

                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnersLopeOfPeak.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                if(position==0){
                    /*Toast.makeText(getActivity(),"Not Selectable",Toast.LENGTH_SHORT).show();*/
                    slopeOfPeakValue="";

                }
                else {
                    spinnersLopeOfPeak.setBackgroundResource(R.color.colorAccent);
                    slopeOfPeakValue= String.valueOf(adapterView.getItemAtPosition(position));
                    slopeOfPeakInt= (int) adapterView.getItemIdAtPosition(position);

                    /*Toast.makeText(getActivity(),""+slopeOfPeakValue,Toast.LENGTH_SHORT).show();*/

                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerNumberOfMajorVessels.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                if(position==0){
                    /*Toast.makeText(getActivity(),"Not Selectable",Toast.LENGTH_SHORT).show();*/
                    numberOfMajorVesselsValue="";

                }
                else {
                    spinnerNumberOfMajorVessels.setBackgroundResource(R.color.colorAccent);
                    numberOfMajorVesselsValue= String.valueOf(adapterView.getItemAtPosition(position));
                    numberOfMajorVesselsInt= (int) adapterView.getItemIdAtPosition(position);
                    /*Toast.makeText(getActivity(),""+numberOfMajorVesselsValue,Toast.LENGTH_SHORT).show();*/

                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spinnerNumberOfDefects.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                if(position==0){
                    /*Toast.makeText(getActivity(),"Not Selectable",Toast.LENGTH_SHORT).show();*/
                    numberOfDefectsValue="";
                }
                else {
                    spinnerNumberOfDefects.setBackgroundResource(R.color.colorAccent);
                    numberOfDefectsValue= String.valueOf(adapterView.getItemAtPosition(position));
                    numberOfMajorVesselsInt= (int) adapterView.getItemIdAtPosition(position);
                   /* Toast.makeText(getActivity(),""+numberOfDefectsValue,Toast.LENGTH_SHORT).show();*/

                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });





        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!etAge.getText().toString().isEmpty() && !etName.getText().toString().isEmpty() && !etRst_bloodPressure.getText().toString().isEmpty() && !etSerumChlestrol.getText().toString().isEmpty() && !etFst_bloodSugar.getText().toString().isEmpty() && !etMax_heartRate.getText().toString().isEmpty() && !etSt_depressionInduced.getText().toString().isEmpty() && !chestPainValue.equals("") && !restingECCValue.equals("") && !indicedAmginaValue.equals("")  && !slopeOfPeakValue.equals("") && !numberOfMajorVesselsValue.equals("") && !numberOfDefectsValue.equals("") ) {

                    if(genderValue.equals("male")){
                        genderValueInt=1;
                    }else if(genderValue.equals("female")){
                        genderValueInt=0;
                    }

                    if(fst_bloodSugar<120){
                        fst_bloodSugarInt=0;
                    }
                    else {
                        fst_bloodSugarInt=1;
                    }

                    diseasePrediction();



                    /*HeartResultSubmit();*/

                   /* Intent intent = new Intent(getActivity(), HeartAnalysisResult.class);
                    intent.putExtra("patientGender",genderValue);
                    intent.putExtra("patientName",Name);
                    intent.putExtra("patientAge",age);
                    intent.putExtra("patientRstBP",rst_bloodPressure);
                    intent.putExtra("patientCholestrol",serumChlestrol);
                    intent.putExtra("patientfstSugar", fst_bloodSugar);
                    intent.putExtra("patientMaxHeartRate",max_heartRate);
                    intent.putExtra("patientSTInduced",st_depressionInduced);
                    intent.putExtra("chestPain",chestPainValue);
                    intent.putExtra("restingECC",restingECCValue);
                    intent.putExtra("inducedAngina",indicedAmginaValue);
                    intent.putExtra("slopeOfPeak",slopeOfPeakValue);
                    intent.putExtra("majorVessels",numberOfMajorVesselsValue);
                    intent.putExtra("defects",numberOfDefectsValue);
                    startActivity(intent);*/

                }

                else {
                    Toast.makeText(getActivity(),"fill and select all Fileds",Toast.LENGTH_SHORT).show();
                }

            }
        });



        return view;



    }

    private void diseasePrediction() {


    }

    private void HeartResultSubmit() {


        JSONObject object = new JSONObject();
        try{
            object.put("id",1);
            object.put("User_Name",user.getFirstName());
            object.put("Email",user.getEmail());
            object.put("Location",user.getLocation());
            object.put("age",age);
            object.put("gender",genderValue);
            object.put("chest_pain",chestPainValue);
            object.put("sugar",fst_bloodSugar);
            object.put("rest_ecg",restingECCValue);
            object.put("exang",age);
            object.put("slope",slopeOfPeakValue);
            object.put("ca",numberOfMajorVesselsValue);
            object.put("thal",numberOfDefectsValue);
            object.put("bp",rst_bloodPressure);
            object.put("cholestrol",serumChlestrol);
            object.put("thalach",max_heartRate);
            object.put("old_peak",st_depressionInduced);
            object.put("Result","Heart");

        }catch (Exception e){

        }

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Toast.makeText(getActivity(),response.toString(),Toast.LENGTH_LONG).show();



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(request);
    }
}



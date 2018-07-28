package sohaib.cardiacdiseaseprediction.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.List;

import sohaib.cardiacdiseaseprediction.Adapters.AdapterViewDoctor;
import sohaib.cardiacdiseaseprediction.DataProviders.ViewDoctorDataProvider;
import sohaib.cardiacdiseaseprediction.R;

public class ViewDoctors extends Fragment {

    private String serverName="http://ad58d1e6.ngrok.io";
    private String JsonUrl=""+serverName+"/api/Doctor?type=json";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    private ProgressDialog dialog;


    ArrayList<ViewDoctorDataProvider> arrayLis=new ArrayList<ViewDoctorDataProvider>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_view_doctors, container, false);

        recyclerView=(RecyclerView) view.findViewById(R.id.rvViewDoctor);
        jsonRequest();

        dialog=new ProgressDialog(getActivity());
        dialog.setCanceledOnTouchOutside(false);
        dialog.setMessage("Please Wait");
        dialog.show();






        return view;




    }
    private void jsonRequest(){
        request=new JsonArrayRequest(JsonUrl, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                dialog.dismiss();

                JSONObject jsonObject=null;

                for (int i=0;i<response.length();i++){

                    try {
                        jsonObject=response.getJSONObject(i);
                        ViewDoctorDataProvider data=new ViewDoctorDataProvider();
                        data.setDoctorName(jsonObject.getString("dr_name"));
                        data.setDoctorEmail(jsonObject.getString("email"));
                        data.setDoctorPhone(jsonObject.getString("contact_no"));
                        data.setDoctorGender(jsonObject.getString("gender"));
                        data.setDoctorResidence(jsonObject.getString("city"));
                        data.setDoctorSpeciality(jsonObject.getString("speciality"));
                        arrayLis.add(data);



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                setupRecyclerView(arrayLis);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                Toast.makeText(getActivity(),""+error,Toast.LENGTH_LONG).show();

            }
        });


        requestQueue= Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(request);

    }

    private void setupRecyclerView(List<ViewDoctorDataProvider> datalist){
        adapter = new AdapterViewDoctor(arrayLis,this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }
}

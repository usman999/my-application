package sohaib.cardiacdiseaseprediction.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import org.w3c.dom.Text;

import java.util.ArrayList;

import sohaib.cardiacdiseaseprediction.DataProviders.ViewDoctorDataProvider;
import sohaib.cardiacdiseaseprediction.Fragments.ViewDoctors;
import sohaib.cardiacdiseaseprediction.R;


public class AdapterViewDoctor extends RecyclerView.Adapter<AdapterViewDoctor.RecyclerViewHolder>  {
    ViewDoctors ctx;
    ArrayList<ViewDoctorDataProvider> arrayList = new ArrayList<>();
    RecyclerViewHolder recyclerViewHolder;

    public AdapterViewDoctor(ArrayList<ViewDoctorDataProvider> arrayList, ViewDoctors ctx){

        this.arrayList=arrayList;
        this.ctx=ctx;

    }



    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewdoctor_single_rowdesign, parent, false);

        recyclerViewHolder = new RecyclerViewHolder(view,ctx);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        ViewDoctorDataProvider dataprovider = arrayList.get(position);

       /* holder.workoutName.setText(dataprovider.getWorkoutName());
        holder.workoutImage.setImageResource(dataprovider.getWorkoutImage());*/

        holder.doctorName.setText(dataprovider.getDoctorName());
        holder.doctorResidence.setText(dataprovider.getDoctorResidence());
        holder.doctorEmail.setText(dataprovider.getDoctorEmail());
        holder.doctorPhone.setText(dataprovider.getDoctorPhone());
        holder.doctorGender.setText(dataprovider.getDoctorGender());
        holder.doctorSpeciality.setText(dataprovider.getDoctorSpeciality());





    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }



    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView doctorName,doctorResidence,doctorGender,doctorPhone,doctorEmail,doctorSpeciality;

        ViewDoctors ctx;
        private RecyclerView recyclerViewHolder;

        @SuppressLint("NewApi")
        public RecyclerViewHolder(View view, ViewDoctors ctx) {
            super(view);
            view.setOnClickListener(this);
            this.ctx=ctx;

            doctorName=(TextView) view.findViewById(R.id.tvDoctorName);
            doctorResidence=(TextView) view.findViewById(R.id.tvDoctorResedenece);
            doctorEmail=(TextView) view.findViewById(R.id.tvDoctorEmail);
            doctorPhone=(TextView) view.findViewById(R.id.tvDoctorPhone);
            doctorGender=(TextView) view.findViewById(R.id.tvDoctorGender);
            doctorSpeciality=(TextView) view.findViewById(R.id.tvDoctorSpeciality);




        }

        @Override
        public void onClick(View view) {
            int position=getAdapterPosition();







        }
    }
}


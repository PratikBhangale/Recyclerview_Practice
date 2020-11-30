package com.example.finalapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ComplaintAdapter extends FirestoreRecyclerAdapter <Complaint, ComplaintAdapter.ComplaintHolder>{

    public ComplaintAdapter(@NonNull FirestoreRecyclerOptions<Complaint> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ComplaintHolder holder, int position, @NonNull Complaint model) {
        holder.textViewtitle.setText(model.getTitle());
        holder.textViewdescription.setText(model.getDescription());
        holder.textViewpriority.setText(String.valueOf(model.getPriority()));

    }

    @NonNull
    @Override
    public ComplaintHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.complaint_item,parent,false);
        return new ComplaintHolder(v);
    }

    static class ComplaintHolder extends RecyclerView.ViewHolder{
        TextView textViewtitle,textViewdescription,textViewpriority;

        public ComplaintHolder(@NonNull View itemView) {
            super(itemView);
            textViewtitle=itemView.findViewById(R.id.textView5);
            textViewdescription=itemView.findViewById(R.id.textView6);
            textViewpriority=itemView.findViewById(R.id.priority);
        }
    }

}

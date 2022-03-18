package com.example.house_rent_and_payment.apartment_details;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.house_rent_and_payment.R;

import java.util.List;

public class Apartment_Frag1_Pop_Up_Adapter extends RecyclerView.Adapter<Apartment_Frag1_Pop_Up_Adapter.ViewHolder>{

    public List<String> fileNameList;
    public List<String> fileDoneList;

    public Apartment_Frag1_Pop_Up_Adapter(List<String> fileNameList, List<String>fileDoneList){

        this.fileDoneList = fileDoneList;
        this.fileNameList = fileNameList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.g_a_my_apartment_pop_up_list_fragment1, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String fileName = fileNameList.get(position);
        holder.fileNameView.setText(fileName);

        String fileDone = fileDoneList.get(position);

        if(fileDone.equals("uploading")){
            holder.fileDoneView.setImageResource(R.drawable.loading);
        }
        else {
            holder.fileDoneView.setImageResource(R.drawable.checked);
        }

    }

    @Override
    public int getItemCount() {
        return fileNameList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public TextView fileNameView;
        public ImageView fileDoneView;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            fileNameView = (TextView) mView.findViewById(R.id.g_a_my_apartment_pop_up_list_frag1_tv_01);
            fileDoneView = (ImageView) mView.findViewById(R.id.g_a_my_apartment_pop_up_list_frag1_img_02);
        }
    }
}


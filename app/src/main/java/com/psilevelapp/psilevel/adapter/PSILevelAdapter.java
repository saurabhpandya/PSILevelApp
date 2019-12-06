package com.psilevelapp.psilevel.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.psilevelapp.R;
import com.psilevelapp.databinding.RowPsiReadingBinding;

import java.util.ArrayList;

public class PSILevelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<String> arylstPSIReading;

    public PSILevelAdapter(ArrayList<String> arylstPSIReading) {
        this.arylstPSIReading = arylstPSIReading;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowPsiReadingBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.row_psi_reading,
                parent,
                false);
        return new PSILevelViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String psiReading = arylstPSIReading.get(position);
        if (holder instanceof PSILevelViewHolder) {
            ((PSILevelViewHolder) holder).setData(psiReading);
        }
    }

    @Override
    public int getItemCount() {
        return arylstPSIReading.size();
    }

    public void updateReading(ArrayList<String> updateArylstPSIReading) {
        arylstPSIReading = updateArylstPSIReading;
    }

    class PSILevelViewHolder extends RecyclerView.ViewHolder {
        RowPsiReadingBinding binding;

        public PSILevelViewHolder(@NonNull RowPsiReadingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setData(String psiReading) {
            binding.txtvwPsilevel.setText(psiReading);
        }

    }

}

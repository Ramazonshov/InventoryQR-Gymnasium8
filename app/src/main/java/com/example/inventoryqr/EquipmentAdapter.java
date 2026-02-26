package com.example.inventoryqr;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class EquipmentAdapter extends RecyclerView.Adapter<EquipmentAdapter.ViewHolder> {

    private List<Equipment> equipmentList;
    private List<Equipment> filteredList;

    public EquipmentAdapter(List<Equipment> equipmentList) {
        this.equipmentList = equipmentList;
        this.filteredList = new ArrayList<>(equipmentList);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Equipment eq = filteredList.get(position);
        holder.text1.setText(eq.getName() + " (" + eq.getInvNumber() + ")");
        holder.text2.setText("Кабинет: " + eq.getCabinet() + " | Статус: " + eq.getStatus());
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public void filter(String query) {
        filteredList.clear();
        if (query.isEmpty()) {
            filteredList.addAll(equipmentList);
        } else {
            query = query.toLowerCase();
            for (Equipment eq : equipmentList) {
                if (eq.getName().toLowerCase().contains(query) ||
                        eq.getInvNumber().toLowerCase().contains(query) ||
                        eq.getCabinet().toLowerCase().contains(query)) {
                    filteredList.add(eq);
                }
            }
        }
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView text1, text2;

        ViewHolder(View itemView) {
            super(itemView);
            text1 = itemView.findViewById(android.R.id.text1);
            text2 = itemView.findViewById(android.R.id.text2);
        }
    }
}
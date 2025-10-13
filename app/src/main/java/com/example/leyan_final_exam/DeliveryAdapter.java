package com.example.leyan_final_exam;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class DeliveryAdapter extends RecyclerView.Adapter<DeliveryAdapter.DeliveryViewHolder> {

    private List<Delivery> deliveries;
    private List<Delivery> fullList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Delivery delivery);
    }

    public DeliveryAdapter(List<Delivery> deliveries, OnItemClickListener listener) {
        this.deliveries = deliveries;
        this.listener = listener;
        if (deliveries != null) {
            this.fullList = new ArrayList<>(deliveries);
        }
    }

    @NonNull
    @Override
    public DeliveryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_delivery, parent, false);
        return new DeliveryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeliveryViewHolder holder, int position) {
        Delivery delivery = deliveries.get(position);
        holder.textCustomerName.setText(delivery.customerName);
        holder.textAddress.setText(delivery.address);
        holder.textStatus.setText(delivery.status);

        holder.itemView.setOnClickListener(v -> listener.onItemClick(delivery));
    }

    @Override
    public int getItemCount() {
        return deliveries != null ? deliveries.size() : 0;
    }

    public static class DeliveryViewHolder extends RecyclerView.ViewHolder {
        TextView textCustomerName, textAddress, textStatus;

        public DeliveryViewHolder(@NonNull View itemView) {
            super(itemView);
            textCustomerName = itemView.findViewById(R.id.textCustomerName);
            textAddress = itemView.findViewById(R.id.textAddress);
            textStatus = itemView.findViewById(R.id.textStatus);
        }
    }

    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
        this.fullList = new ArrayList<>(deliveries);
        notifyDataSetChanged();
    }

    public void filterList(String text) {
        if (fullList == null) return;

        if (text == null || text.trim().isEmpty()) {
            deliveries = new ArrayList<>(fullList);
        } else {
            List<Delivery> filteredList = new ArrayList<>();
            String lowerText = text.toLowerCase();

            for (Delivery delivery : fullList) {
                if (delivery.customerName.toLowerCase().contains(lowerText) ||
                        delivery.address.toLowerCase().contains(lowerText)) {
                    filteredList.add(delivery);
                }
            }

            deliveries = filteredList;
        }

        notifyDataSetChanged();
    }
}

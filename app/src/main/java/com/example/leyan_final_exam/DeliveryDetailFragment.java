package com.example.leyan_final_exam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DeliveryDetailFragment extends Fragment {

    private TextView txtCustomerName, txtAddress;
    private Spinner spinnerStatus;
    private EditText editNote;
    private Button btnUpdate;
    private Delivery delivery;
    private DeliveryViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delivery_detail, container, false);

        txtCustomerName = view.findViewById(R.id.txtCustomerName);
        txtAddress = view.findViewById(R.id.txtAddress);
        spinnerStatus = view.findViewById(R.id.spinnerStatus);
        editNote = view.findViewById(R.id.editNote);
        btnUpdate = view.findViewById(R.id.btnUpdate);

        viewModel = new DeliveryViewModel(requireActivity().getApplication());

        if (getArguments() != null) {
            delivery = (Delivery) getArguments().getSerializable("delivery");
            if (delivery != null) {
                txtCustomerName.setText("Customer: " + delivery.customerName);
                txtAddress.setText("Address: " + delivery.address);
                editNote.setText(delivery.note);

                if (delivery.status.equals("Pending"))
                    spinnerStatus.setSelection(0);
                else if (delivery.status.equals("In Progress"))
                    spinnerStatus.setSelection(1);
                else
                    spinnerStatus.setSelection(2);
            }
        }

        btnUpdate.setOnClickListener(v -> {
            if (delivery != null) {
                String newStatus = spinnerStatus.getSelectedItem().toString();
                String newNote = editNote.getText().toString();

                delivery.status = newStatus;
                delivery.note = newNote;
                viewModel.updateDelivery(delivery);

                Toast.makeText(getContext(), "Updated successfully!", Toast.LENGTH_SHORT).show();
                requireActivity().onBackPressed();
            }
        });

        return view;
    }
}

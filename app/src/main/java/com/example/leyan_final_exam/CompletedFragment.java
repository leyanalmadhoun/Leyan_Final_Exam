package com.example.leyan_final_exam;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CompletedFragment extends Fragment {
    private RecyclerView recyclerView;
    private DeliveryAdapter adapter;
    private DeliveryViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_completed, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewCompleted);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel = new ViewModelProvider(requireActivity()).get(DeliveryViewModel.class);

        viewModel.getDeliveriesByStatus("Completed").observe(getViewLifecycleOwner(), deliveries -> {
            if (adapter == null) {
                adapter = new DeliveryAdapter(deliveries, delivery -> {
                    DeliveryDetailFragment fragment = new DeliveryDetailFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("delivery", delivery);
                    fragment.setArguments(bundle);

                    requireActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .addToBackStack(null)
                            .commit();
                });
                recyclerView.setAdapter(adapter);
            } else {
                adapter.setDeliveries(deliveries);
            }
        });

        return view;
    }

    public void filter(String text) {
        if (adapter != null) {
            adapter.filterList(text);
        }
    }
}

package com.example.leyan_final_exam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DashboardFragment extends Fragment {

    private TextView totalTxt, pendingTxt, inProgressTxt, completedTxt;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        totalTxt = view.findViewById(R.id.text_total);
        pendingTxt = view.findViewById(R.id.text_pending);
        inProgressTxt = view.findViewById(R.id.text_in_progress);
        completedTxt = view.findViewById(R.id.text_completed);

        AppDatabase db = AppDatabase.getDatabase(requireContext());
        DeliveryDao dao = db.deliveryDao();

        int total = dao.getTotalCount();
        int pending = dao.getPendingCount();
        int inProgress = dao.getInProgressCount();
        int completed = dao.getCompletedCount();

        totalTxt.setText("Total Deliveries: " + total);
        pendingTxt.setText("Pending: " + pending);
        inProgressTxt.setText("In Progress: " + inProgress);
        completedTxt.setText("Completed: " + completed);

        return view;
    }
}

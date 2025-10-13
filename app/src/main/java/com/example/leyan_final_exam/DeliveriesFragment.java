package com.example.leyan_final_exam;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import java.util.List;

public class DeliveriesFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_deliveries, container, false);

        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);
        EditText editSearch = view.findViewById(R.id.editSearch);

        viewPager.setAdapter(new ViewPagerAdapter(requireActivity()));

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            if (position == 0) tab.setText("Pending");
            else if (position == 1) tab.setText("In Progress");
            else tab.setText("Completed");
        }).attach();

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int currentTab = viewPager.getCurrentItem();
                List<Fragment> fragments = getChildFragmentManager().getFragments();


                if (fragments != null && fragments.size() > currentTab) {
                    Fragment currentFragment = fragments.get(currentTab);

                    if (currentFragment instanceof PendingFragment)
                        ((PendingFragment) currentFragment).filter(s.toString());
                    else if (currentFragment instanceof InProgressFragment)
                        ((InProgressFragment) currentFragment).filter(s.toString());
                    else if (currentFragment instanceof CompletedFragment)
                        ((CompletedFragment) currentFragment).filter(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        return view;
    }

    private class ViewPagerAdapter extends FragmentStateAdapter {
        public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            if (position == 0) return new PendingFragment();
            else if (position == 1) return new InProgressFragment();
            else return new CompletedFragment();
        }

        @Override
        public int getItemCount() {
            return 3;
        }
    }
}

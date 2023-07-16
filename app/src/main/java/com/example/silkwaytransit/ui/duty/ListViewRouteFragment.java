//package com.example.silkwaytransit.ui.duty;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.lifecycle.ViewModelProvider;
//
//import com.example.silkwaytransit.databinding.RouteListviewLayoutBinding;
//import com.example.silkwaytransit.ui.home.HomeViewModel;
//
//public class ListViewRouteFragment extends Fragment implements TimePickerFragment.TimePickerDialogFragmentEvents{
//
//    private @NonNull RouteListviewLayoutBinding binding;
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        HomeViewModel homeViewModel =
//                new ViewModelProvider(this).get(HomeViewModel.class);
//
//        binding = RouteListviewLayoutBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();
//
//        final TextView changeTimeSectionTextView = binding.changeTimeSection;
//        final TextView changeDepartureTimeTextView = binding.changeDepartureTimeSection;
////        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
//
//        changeTimeSectionTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                TimePickerFragment newFragment = new TimePickerFragment("timeSection");
//                newFragment.setEvents(ListViewRouteFragment.this);
//                newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
//            }
//        });
//
//        changeDepartureTimeTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                TimePickerFragment newFragment = new TimePickerFragment("departureTimeSection");
//                newFragment.setEvents(ListViewRouteFragment.this);
//                newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
//            }
//        });
//        return root;
//    }
//
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }
//
//    @Override
////    public void onTimeSelected(String timeType, String time) {
//        TextView textView = null;
//        switch (timeType){
//            case "timeSection":
//                textView = binding.changeTimeSection;
//                break;
//            case "departureTimeSection":
//                textView = binding.changeDepartureTimeSection;
//        }
//
//        textView.setText(time);
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//    }
//}

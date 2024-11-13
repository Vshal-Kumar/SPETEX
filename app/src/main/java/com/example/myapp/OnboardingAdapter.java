package com.example.myapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class OnboardingAdapter extends RecyclerView.Adapter<OnboardingAdapter.ViewHolder> {
    private final List<Integer> layouts;  // List of layout resource IDs for onboarding screens
    private final @NonNull Context context;

    // Constructor to initialize the list of layouts and the context
    public OnboardingAdapter(@NonNull List<Integer> layouts, @NonNull Context context) {
        this.layouts = layouts;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("OnboardingAdapter", "Inflating layout: " + viewType);
        // Inflate the layout for the onboarding screen using the viewType
        View view = LayoutInflater.from(context).inflate(viewType, parent, false);
        Log.d("OnboardingActivity", "Adapter set for ViewPager.");

        return new ViewHolder(view);  // Return the ViewHolder with the inflated view
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Binding logic, if needed
    }

    @Override
    public int getItemCount() {
        return layouts.size();  // Return the number of onboarding screens
    }

    @Override
    public int getItemViewType(int position) {
        return layouts.get(position);  // Return the layout resource ID for each screen
    }

    // ViewHolder class to hold references to views in each onboarding screen layout
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize views inside the ViewHolder if necessary
        }
    }
}

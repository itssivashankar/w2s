package com.example.w2s;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SelectedListAdapter extends RecyclerView.Adapter<SelectedListAdapter.HomeItemHolder> {

    private final Activity activity;

    private ListAdapterActivity listAdapter;

    private final List<Lists> selectedList = new ArrayList<>();

    public SelectedListAdapter(TMainActivity listActivity) {
        this.activity = listActivity;
    }


    @NonNull
    @Override
    public HomeItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HomeItemHolder homeItemHolder = new HomeItemHolder(
                LayoutInflater.from(
                        parent.getContext()
                ).inflate(
                        R.layout.selected_view,
                        parent,
                        false
                )
        );
        return homeItemHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull HomeItemHolder holder, int position) {

        Lists content = getItem(position);
        holder.selected_text.setText(content.getTitle());
        holder.selected_image.setOnClickListener(n -> {
            listAdapter.uncheckItem(content);
            removeItem(content);
        });

    }

    private Lists getItem(int position) {
        return selectedList.get(position);
    }

    @Override
    public int getItemCount() {
        return selectedList.size();
    }

    public void addItem(Lists item) {
        selectedList.add(item);
        notifyDataSetChanged();
    }

    public void removeItem(Lists item) {
        selectedList.remove(item);
        notifyDataSetChanged();
    }

    public void setListAdapter(ListAdapterActivity selectedListAdapter) {
        this.listAdapter = selectedListAdapter;
    }

    public class HomeItemHolder extends RecyclerView.ViewHolder {

        private TextView selected_text;

        private ImageView selected_image;

        public HomeItemHolder(@NonNull View itemView) {
            super(itemView);
            selected_text = itemView.findViewById(R.id.selected_title);
            selected_image = itemView.findViewById(R.id.selected_img);
        }
    }
}

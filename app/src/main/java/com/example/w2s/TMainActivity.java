package com.example.w2s;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.w2s.databinding.ActivityTmainBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TMainActivity extends AppCompatActivity {

    private ActivityTmainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTmainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Client.getUserList().enqueue(new Callback<List<Lists>>() {
            @Override
            public void onResponse(Call<List<Lists>> call, Response<List<Lists>> response) {
                          //  List<User> users = Arrays.asList(new Gson().fromJson(response.body().toString(), User[].class));
                List<Lists> datas = response.body();

                binding.selectedData.setLayoutManager(new LinearLayoutManager(TMainActivity.this, LinearLayoutManager.HORIZONTAL, false));

                SelectedListAdapter selectedListAdapter = new SelectedListAdapter(TMainActivity.this);
                binding.selectedData.setAdapter(selectedListAdapter);


                binding.recylerview.setLayoutManager(new LinearLayoutManager(TMainActivity.this));
                ListAdapterActivity listAdapter = new ListAdapterActivity(TMainActivity.this,datas, selectedListAdapter);
                binding.recylerview.setAdapter(listAdapter);
                selectedListAdapter.setListAdapter(listAdapter);

            }

            @Override
            public void onFailure(Call<List<Lists>> call, Throwable t) {

            }
        });
    }
}

class ListAdapterActivity extends RecyclerView.Adapter<ListAdapterActivity.HomeItemHolder>{

    private final Activity activity;

    private final List<Lists> listData;
    private final SelectedListAdapter adapter;

    public ListAdapterActivity(TMainActivity listActivity, List <Lists> data,SelectedListAdapter adapter) {

        this.listData = data;
        this.activity = listActivity;
        this.adapter = adapter;

    }


    @NonNull
    @Override
    public ListAdapterActivity.HomeItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HomeItemHolder homeItemHolder =  new ListAdapterActivity.HomeItemHolder(
                LayoutInflater.from(
                        parent.getContext()
                ).inflate(
                        R.layout.listview,
                        parent,
                        false
                )
        );
        return homeItemHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapterActivity.HomeItemHolder holder, int position) {

        Lists content = getItem(position);
        holder.text.setText(content.getTitle());
        holder.checkbox.setChecked(content.isChecked());
        holder.checkbox.setOnClickListener(n->{
           // holder.checkbox.setChecked(!holder.checkbox.isChecked());
            if(holder.checkbox.isChecked()) {
                System.out.println("listadapter checked " + content.getTitle());
                //removeItem(content.id);
                adapter.addItem(content);
            }else{
                adapter.removeItem(content);
                System.out.println("listadapter unchecd " + content.getTitle());
            }
        });

    }

    private Lists getItem(int position) {
        return listData.get(position);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public void uncheckItem(Lists item){
        int index = listData.indexOf(item);
        System.out.println("listData 0"+listData.size());
        System.out.println("listData 1"+index);
        listData.get(index).setChecked(false);
        notifyItemChanged(index);
    }

    public class HomeItemHolder extends RecyclerView.ViewHolder {
        private TextView text;
        private CheckBox checkbox;
        public HomeItemHolder(@NonNull View itemView) {
            super(itemView);
            text  = itemView.findViewById(R.id.title);
            checkbox  = itemView.findViewById(R.id.checkbox);
        }
    }
}



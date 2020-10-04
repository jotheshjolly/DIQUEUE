package com.jolly.creations.dequeue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class doc_name_adapter extends RecyclerView.Adapter<doc_name_adapter.ViewHolder> {
    Context context;
    List<doc_model> MainImageUploadInfoList;

    public doc_name_adapter(Context context, List<doc_model> TempList) {

        this.MainImageUploadInfoList = TempList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doc_list_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        doc_model userdetials = MainImageUploadInfoList.get(position);

        holder.user.setText(userdetials.getName());
        holder.des.setText(userdetials.getDes());

    }

    @Override
    public int getItemCount() {

        return MainImageUploadInfoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public Button user;
        public TextView des;


        public ViewHolder(View itemView) {

            super(itemView);

            user = (Button) itemView.findViewById(R.id.user_name);
            des = (TextView) itemView.findViewById(R.id.des);

        }
    }
}
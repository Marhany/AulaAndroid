package com.aulamobile.aulamobile_06_08_18;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.RecyclerViewHolder>{


    private Context mContext;
    private List<Tarefa> mList;
    private MainActivity activity;

    public ListAdapter(Context context, List<Tarefa> list){
        this.mContext = context;
        this.activity = (MainActivity) context;
        this.mList = list;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Tarefa tarefa = mList.get(position);
        holder.tvTarefa.setText(tarefa.getTarefa());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    protected class RecyclerViewHolder extends RecyclerView.ViewHolder{

        protected TextView tvTarefa;
        protected LinearLayout llRow;
        protected ImageView garb;
        protected ImageView edit;

        public RecyclerViewHolder(View itemView){
            super(itemView);
            tvTarefa = itemView.findViewById(R.id.tv_tarefa);
            llRow = itemView.findViewById(R.id.llRow);
            garb = itemView.findViewById(R.id.fabGarb);
            edit = itemView.findViewById(R.id.fabEdit);

            garb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Tarefa tarefa = mList.get(getAdapterPosition());
                    activity.onClickList(tarefa);
                }
            });

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Tarefa tarefa = mList.get(getAdapterPosition());
                    activity.onEditList(tarefa);
                }
            });
        }
    }
}

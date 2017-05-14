package com.maltsev.labyrinth;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private JSONArray mDataset;
    private MainFragment fragment;
    private Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public CardView cardView;
        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecyclerAdapter(JSONArray myDataset, MainFragment fragment) {
        mDataset = myDataset;
        this.fragment = fragment;
        this.context = fragment.getContext();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_main, parent, false);

        return new ViewHolder(v);
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, InfoViewActivity.class);

                intent.putExtra("title", ((TextView) view.findViewById(R.id.tv_card_main_title_1)).getText());
                intent.putExtra("subtitle", ((TextView) view.findViewById(R.id.tv_card_main_subtitle_1)).getText());
                intent.putExtra("description", ((TextView) view.findViewById(R.id.tv_card_main_description)).getText());

                context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation
                        ((Activity) context, view.findViewById(R.id.img_main_card_1), "shareView").toBundle());
            }
        });

        TextView textViewTitle = (TextView) holder.cardView.findViewById(R.id.tv_card_main_title_1);
        LevelInfo levelInfo = JSON.parseObject(mDataset.getString(position), LevelInfo.class);
        textViewTitle.setText(levelInfo.getTitle());

        TextView textViewSubtitle = (TextView) holder.cardView.findViewById(R.id.tv_card_main_subtitle_1);
        textViewSubtitle.setText(levelInfo.getSubTitle());

        TextView textViewDescription = (TextView) holder.cardView.findViewById(R.id.tv_card_main_description);
        textViewDescription.setText(levelInfo.getDescription());

        holder.cardView.setOnTouchListener(fragment);

        Button button = (Button) holder.cardView.findViewById(R.id.btn_card_main_1_action1);
        button.setOnClickListener(fragment);
        button.setTag(position);
        holder.cardView.findViewById(R.id.btn_card_main_1_action2).setOnClickListener(fragment);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
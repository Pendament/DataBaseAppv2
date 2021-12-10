package com.example.databasev2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class GameAdapter extends RecyclerView.Adapter {

    private ArrayList<Game> gameData;
    private View.OnClickListener onClickListener;

    public class GameViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewGame;
        public TextView textViewPrice;
        public TextView textViewDescription;

        public GameViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewGame = itemView.findViewById(R.id.textViewTitle);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            itemView.setTag(this);
            itemView.setOnClickListener(onClickListener);
        }

        public TextView getGameTextView() { return textViewGame; }
        public TextView getPriceTextView() { return textViewPrice; }

    }

    public void setOnClickListener(View.OnClickListener listener){ onClickListener = listener; }

    public GameAdapter(ArrayList<Game> arrayList) { gameData = arrayList; }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_item_view, parent, false);

        return new GameViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        GameViewHolder gvh = (GameViewHolder) holder;
        gvh.getGameTextView().setText(gameData.get(position).getTitle());
        gvh.getPriceTextView().setText(gameData.get(position).getPrice());
    }

    @Override
    public int getItemCount() { return gameData.size(); }

}

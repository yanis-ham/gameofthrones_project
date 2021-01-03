package com.example.game_of_thrones.presentation.display.favorite.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.game_of_thrones.R;

import java.util.ArrayList;
import java.util.List;

public class FavoriteGotCharacterAdapter extends RecyclerView.Adapter<FavoriteGotCharacterAdapter.FavoriteGotCharacterViewHolder>{


    private final List<FavoriteGotCharacterViewItem> favoriteGotCharacterViewItemsList;
    private FavoriteGotCharacterActionInterface favoriteGotCharacterActionInterface;

    public FavoriteGotCharacterAdapter(FavoriteGotCharacterActionInterface favoriteGotCharacterActionInterface){
        this.favoriteGotCharacterActionInterface = favoriteGotCharacterActionInterface;
        this.favoriteGotCharacterViewItemsList = new ArrayList<>();
    }

    public void bindViewModels(List<FavoriteGotCharacterViewItem> favoriteGotCharacterViewItemsList) {
        this.favoriteGotCharacterViewItemsList.clear();
        this.favoriteGotCharacterViewItemsList.addAll(favoriteGotCharacterViewItemsList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoriteGotCharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favorite_got_character_item, parent, false);
        return new FavoriteGotCharacterViewHolder(view, favoriteGotCharacterActionInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteGotCharacterViewHolder holder, int position) {
        holder.bind(favoriteGotCharacterViewItemsList.get(position));
    }

    @Override
    public int getItemCount() {
        return favoriteGotCharacterViewItemsList.size();
    }

    public static class FavoriteGotCharacterViewHolder extends RecyclerView.ViewHolder {

        private final TextView fullname_textView;
        private final ImageButton gotCharacter_image;
        private final ImageButton remove_button;
        private final View view;
        private final FavoriteGotCharacterActionInterface favoriteGotCharacterActionInterface;
        private FavoriteGotCharacterViewItem favoriteGotCharacterViewItem;

        public FavoriteGotCharacterViewHolder(@NonNull View itemView, final FavoriteGotCharacterActionInterface favoriteGotCharacterActionInterface) {
            super(itemView);
            this.view = itemView;
            fullname_textView =view.findViewById(R.id.favoriteGotCharacter_fullname);
            gotCharacter_image = view.findViewById(R.id.gotCharacter_imageview);
            remove_button = view.findViewById(R.id.remove_icon);
            this.favoriteGotCharacterActionInterface = favoriteGotCharacterActionInterface;
            setupListeners();
        }

        private void setupListeners(){
            remove_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    favoriteGotCharacterActionInterface.onRemove(favoriteGotCharacterViewItem.getId());
                }
            });
        }

        void bind(FavoriteGotCharacterViewItem favoriteGotCharacterViewItem){
            this.favoriteGotCharacterViewItem = favoriteGotCharacterViewItem;
            this.fullname_textView.setText(favoriteGotCharacterViewItem.getFullname());
            Glide.with(view)
                    .load(favoriteGotCharacterViewItem.getImageUrl())
                    .centerCrop()
                    .into(gotCharacter_image);
        }
    }
}

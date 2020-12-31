package com.example.game_of_thrones.presentation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.game_of_thrones.R;

import java.util.ArrayList;
import java.util.List;

public class GotCharacterAdapter extends RecyclerView.Adapter<GotCharacterAdapter.GotCharacterViewHolder> {

    private GotActionInterface gotActionInterface;
    private List<GotCharacterViewItem> gotCharacterViewItemList;

    public GotCharacterAdapter(GotActionInterface gotActionInterface){
        gotCharacterViewItemList = new ArrayList<>();
        this.gotActionInterface = gotActionInterface;
    }

    public void bindViewModels(List<GotCharacterViewItem> gotCharacterViewItemList){
        this.gotCharacterViewItemList.clear();
        this.gotCharacterViewItemList.addAll(gotCharacterViewItemList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GotCharacterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.got_character_img, viewGroup, false);
        GotCharacterViewHolder gotCharacterViewHolder = new GotCharacterViewHolder(view, gotActionInterface);
        return gotCharacterViewHolder;
    }

    @Override
    public void onBindViewHolder(GotCharacterViewHolder holder, int position) {
        holder.bind(gotCharacterViewItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return gotCharacterViewItemList.size();
    }

    public static class GotCharacterViewHolder extends RecyclerView.ViewHolder {

        private View gotView;
        private GotActionInterface gotActionInterface;
        private ImageButton gotCharacterImgBtn;
        private GotCharacterViewItem gotCharacterInformationViewItem;

        public GotCharacterViewHolder(View gotView, GotActionInterface gotActionInterface) {
            super(gotView);
            this.gotView = gotView;
            this.gotActionInterface = gotActionInterface;
            gotCharacterImgBtn = gotView.findViewById(R.id.gotCharacter_imgView);
            setupListener();
        }

        private void setupListener(){
            gotCharacterImgBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gotActionInterface.onClick(gotCharacterInformationViewItem.getId());
                }
            });
        }

        public void bind(GotCharacterViewItem gotCharacterInformationViewItem){
            this.gotCharacterInformationViewItem = gotCharacterInformationViewItem;
            Glide.with(gotView)
                    .load(gotCharacterInformationViewItem.getImage())
                    .into(gotCharacterImgBtn);
        }

        public ImageButton getImageButton() {
            return gotCharacterImgBtn;
        }

    }
}

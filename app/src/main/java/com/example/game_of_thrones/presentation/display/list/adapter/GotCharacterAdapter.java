package com.example.game_of_thrones.presentation.display.list.adapter;

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

    private GotCharacterActionInterface gotCharacterActionInterface;
    private List<GotCharacterViewItem> gotCharacterViewItemList;

    public GotCharacterAdapter(GotCharacterActionInterface gotCharacterActionInterface){
        gotCharacterViewItemList = new ArrayList<>();
        this.gotCharacterActionInterface = gotCharacterActionInterface;
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
        GotCharacterViewHolder gotCharacterViewHolder = new GotCharacterViewHolder(view, gotCharacterActionInterface);
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
        private GotCharacterActionInterface gotCharacterActionInterface;
        private ImageButton gotCharacterImgBtn;
        private GotCharacterViewItem gotCharacterInformationViewItem;

        public GotCharacterViewHolder(View gotView, GotCharacterActionInterface gotCharacterActionInterface) {
            super(gotView);
            this.gotView = gotView;
            this.gotCharacterActionInterface = gotCharacterActionInterface;
            gotCharacterImgBtn = gotView.findViewById(R.id.gotCharacter_imgView);
            setupListener();
        }

        private void setupListener(){
            gotCharacterImgBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gotCharacterActionInterface.onClick(gotCharacterInformationViewItem.getId());
                }
            });
        }

        public void bind(GotCharacterViewItem gotCharacterInformationViewItem){
            this.gotCharacterInformationViewItem = gotCharacterInformationViewItem;
            /*Glide.with(gotView)
                    .load(gotCharacterInformationViewItem.getImageUrl())
                    .placeholder(android.R.drawable.progress_indeterminate_horizontal)
                    .error(android.R.drawable.stat_notify_error)
                    .into(gotCharacterImgBtn);*/
            Glide.with(gotView)
                    .load(gotCharacterInformationViewItem.getImageUrl())
                    .centerCrop()
                    .into(gotCharacterImgBtn);
        }

        public ImageButton getImageButton() {
            return gotCharacterImgBtn;
        }

    }
}

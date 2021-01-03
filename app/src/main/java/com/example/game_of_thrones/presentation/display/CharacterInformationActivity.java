package com.example.game_of_thrones.presentation.display;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.game_of_thrones.R;
import com.example.game_of_thrones.data.di.FakeDependencyInjection;
import com.example.game_of_thrones.presentation.display.list.adapter.GotCharacterInformationViewItem;
import com.example.game_of_thrones.presentation.viewmodel.FavoriteGotCharacterViewModel;
import com.example.game_of_thrones.presentation.viewmodel.GotCharacterViewModel;

public class CharacterInformationActivity extends AppCompatActivity {
    private int id;
    private TextView fullname;
    private TextView title;
    private TextView family;
    private ImageView gotCharacterImg;
    private GotCharacterViewModel gotCharacterViewModel;
    private FavoriteGotCharacterViewModel favoriteGotCharacterViewModel;
    private Button favoriteGotCharacterButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.got_character_information);

        Intent i = getIntent();
        this.id = i.getIntExtra("Id", 1);

        registerViewModel();

        favoriteGotCharacterButton = findViewById(R.id.favorite_got_character_button);
        favoriteGotCharacterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favoriteGotCharacterViewModel.addCharacterToFavorites(id);
            }
        });
    }

    private void registerViewModel() {
        gotCharacterViewModel = new ViewModelProvider(this, FakeDependencyInjection.getViewModelFactory()).get(GotCharacterViewModel.class);
        favoriteGotCharacterViewModel = new ViewModelProvider(this, FakeDependencyInjection.getViewModelFactory()).get(FavoriteGotCharacterViewModel.class);
        gotCharacterViewModel.getCharactersInfo().observe(this, new Observer<GotCharacterInformationViewItem>() {
            @Override
            public void onChanged(GotCharacterInformationViewItem gotCharacterInformationViewItem) {
                setLayout(gotCharacterInformationViewItem);
            }
        });
        gotCharacterViewModel.getCharacterById(id);

    }

    private void setLayout(GotCharacterInformationViewItem character) {

        fullname = findViewById(R.id.gotCharacter_full_name);
        title = findViewById(R.id.gotCharacter_title);
        family = findViewById(R.id.gotCharacter_family);
        gotCharacterImg = findViewById(R.id.gotCharacter_img);

        fullname.append(" " + character.getFullName());
        title.append(" " + character.getTitle());
        family.append(" " + character.getFamily());

        Glide.with(this)
                .load(character.getImageUrl())
                .into(gotCharacterImg);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}

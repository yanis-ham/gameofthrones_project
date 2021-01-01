package com.example.game_of_thrones.presentation.display;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.game_of_thrones.R;
import com.example.game_of_thrones.data.di.FakeDependencyInjection;
import com.example.game_of_thrones.presentation.adapter.GotCharacterInformationViewItem;
import com.example.game_of_thrones.presentation.viewmodel.GotViewModel;

import java.util.List;

public class CharacterInformationActivity extends AppCompatActivity {
    private int id;
    private TextView fullname;
    private TextView firstname;
    private TextView lastname;
    private TextView title;
    private TextView family;
    private ImageView gotCharacterImg;
    private GotViewModel gotViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.got_character_information);

        Intent i = getIntent();
        this.id = i.getIntExtra("Id", 1);

        registerViewModel();
    }

    private void registerViewModel() {
        gotViewModel = new ViewModelProvider(this, FakeDependencyInjection.getViewModelFactory()).get(GotViewModel.class);

        gotViewModel.getCharactersInfo().observe(this, new Observer<GotCharacterInformationViewItem>() {
            @Override
            public void onChanged(GotCharacterInformationViewItem gotCharacterInformationViewItem) {
                setLayout(gotCharacterInformationViewItem);
            }
        });
        gotViewModel.getCharacterById(id);

    }

    private void setLayout(GotCharacterInformationViewItem character) {

        fullname = findViewById(R.id.gotCharacter_full_name);
        firstname = findViewById(R.id.gotCharacter_first_name);
        lastname = findViewById(R.id.gotCharacter_last_name);
        title = findViewById(R.id.gotCharacter_title);
        family = findViewById(R.id.gotCharacter_family);
        gotCharacterImg = findViewById(R.id.gotCharacter_img);

        fullname.append(" " + character.getFullName());
        firstname.append(" " + character.getFirstName());
        lastname.append(" " + character.getLastName());
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

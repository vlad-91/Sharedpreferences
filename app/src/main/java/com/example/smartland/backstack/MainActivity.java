 package com.example.smartland.backstack;


import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etText;
    Button btnSave, btnLoad;
    SharedPreferences sPref;

    final String SAVED_TEXT = "saved_text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etText = findViewById(R.id.etText);

        btnSave=findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        btnLoad=findViewById(R.id.btnLoad);
        btnLoad.setOnClickListener(this);

        loadText();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case  R.id.btnSave:
                saveText();
                break;
            case R.id.btnLoad:
                loadText();
                break;
        }
    }

    private void saveText() {

        sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        //sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(SAVED_TEXT, etText.getText().toString());
        ed.commit();
        Toast.makeText(MainActivity.this,"Text Saved", Toast.LENGTH_LONG).show();
    }

    private void loadText() {

        sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        //sPref = getPreferences(MODE_PRIVATE);
        String savedText = sPref.getString(SAVED_TEXT, "");
        etText.setText(savedText);
        Toast.makeText(MainActivity.this, "Text Loaded", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveText();
    }
}

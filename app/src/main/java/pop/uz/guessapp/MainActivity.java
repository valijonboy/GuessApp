package pop.uz.guessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_start;
    AppCompatEditText et_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_start = findViewById(R.id.btn_start);
        et_name = findViewById(R.id.et_name);

        showSystemUI();

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_name.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, getString(R.string.enter_name), Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent =  new Intent(MainActivity.this, GuessQuestionActivity.class);
                    intent.putExtra(GuessQuestionActivity.USER_NAME, et_name.getText().toString());
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
    }
}
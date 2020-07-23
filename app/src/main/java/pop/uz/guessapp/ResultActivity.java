package pop.uz.guessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView tv_name, tv_score;
    String userName;
    int totalQuestions, correctAnswers;
    Button button_finish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        showSystemUI();
        tv_name = findViewById(R.id.tv_name);
        tv_score = findViewById(R.id.tv_score);
        button_finish = findViewById(R.id.button_finish);

        userName = getIntent().getStringExtra(GuessQuestionActivity.USER_NAME);
        tv_name.setText(userName);

        totalQuestions = getIntent().getIntExtra(GuessQuestionActivity.TOTAL_QUESTIONS, 0);
        correctAnswers = getIntent().getIntExtra(GuessQuestionActivity.CORRECT_ANSWERS,0);

        tv_score.setText(String.format("Your score is %d out of %d", correctAnswers, totalQuestions));

        button_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultActivity.this, MainActivity.class));
                finish();
            }
        });
    }
    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
    }
}
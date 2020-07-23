package pop.uz.guessapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class GuessQuestionActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Question> questionsList = new ArrayList<>();
    private int mCurrentPosition = 1;
    private int mSelectedOptionPosition = 0;
    private int mCorrectAnswers = 0;
    private String mUserName = null;
    ProgressBar progressBar;
    TextView tv_progress, tv_question, tv_option_one, tv_option_two, tv_option_three, tv_option_four;
    ImageView iv_image;
    Button btn_submit;

    public static final String USER_NAME = "user_name";
    public static final String TOTAL_QUESTIONS =  "total_questions";
    public static final String CORRECT_ANSWERS = "correct_answers";

    private  static final String QUESTION_TEXT = "Well, guess what color it is?";
    private static final String RED = "RED";
    private static final String BLUE = "BLUE";
    private static final String BLACK = "BLACK";
    private static final String WHITE = "WHITE";
    private static final String GREEN = "GREEN";
    private static final String GREY = "GREY";
    private static final String YELLOW = "YELLOW";
    private static final String ORANGE = "ORANGE";
    private static final String PURPLE = "PURPLE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_question);

        progressBar = findViewById(R.id.progressBar);
        tv_progress = findViewById(R.id.tv_progress);
        tv_question = findViewById(R.id.tv_question);
        iv_image = findViewById(R.id.iv_image);
        tv_option_one = findViewById(R.id.tv_option_one);
        tv_option_two = findViewById(R.id.tv_option_two);
        tv_option_three = findViewById(R.id.tv_option_three);
        tv_option_four = findViewById(R.id.tv_option_four);
        btn_submit = findViewById(R.id.btn_submit);

        mUserName = getIntent().getStringExtra(USER_NAME);

        getQuestion();
        setQuestion();

        tv_option_one.setOnClickListener(this);
        tv_option_two.setOnClickListener(this);
        tv_option_three.setOnClickListener(this);
        tv_option_four.setOnClickListener(this);
        btn_submit.setOnClickListener(this);
    }

    private void getQuestion(){

        Question question1 = new Question(1, QUESTION_TEXT, R.drawable.red_color, RED, BLACK, GREEN, YELLOW, 1);
       Question question2 = new Question(2, QUESTION_TEXT, R.drawable.black_color, BLUE, BLACK, ORANGE, YELLOW, 2);
       Question question3 = new Question(3, QUESTION_TEXT, R.drawable.yellow_color, ORANGE, WHITE, BLACK, YELLOW, 4);
       Question question4 = new Question(4, QUESTION_TEXT, R.drawable.white_color, RED, YELLOW, WHITE, GREY, 3);
       Question question5 = new Question(5, QUESTION_TEXT, R.drawable.blue_color, WHITE, BLACK, PURPLE, BLUE, 4);
       Question question6 = new Question(6, QUESTION_TEXT, R.drawable.purple_color, WHITE, YELLOW, ORANGE, PURPLE, 4);
       Question question7 = new Question(7, QUESTION_TEXT, R.drawable.green_color, GREEN, BLUE, YELLOW, RED, 1);
       Question question8 = new Question(8, QUESTION_TEXT, R.drawable.grey_color, PURPLE, GREEN, GREY, YELLOW, 3);
       Question question9 = new Question(9, QUESTION_TEXT, R.drawable.orange_color, RED, WHITE, GREEN, ORANGE, 4);

       questionsList.add(question1);
       questionsList.add(question2);
       questionsList.add(question3);
       questionsList.add(question4);
       questionsList.add(question5);
       questionsList.add(question6);
       questionsList.add(question7);
       questionsList.add(question8);
       questionsList.add(question9);

    }

    @SuppressLint("DefaultLocale")
    private void setQuestion(){
        Question question = questionsList.get(mCurrentPosition - 1);

        defaultOptionsViews();
        progressBar.setMax(questionsList.size());

        if (mCurrentPosition == questionsList.size()){
            btn_submit.setText(R.string.finish);
        }else {
            btn_submit.setText(R.string.submit);
        }

        progressBar.setProgress(mCurrentPosition);
        tv_progress.setText(String.format(" %d/%d", mCurrentPosition, progressBar.getMax()));
        tv_question.setText(QUESTION_TEXT);
        iv_image.setImageResource(question.getImage());

        tv_option_one.setText(question.getOptionOne());
        tv_option_two.setText(question.getOptionTwo());
        tv_option_three.setText(question.getOptionThree());
        tv_option_four.setText(question.getOptionFour());
    }

    private void defaultOptionsViews(){
        ArrayList<TextView> options = new ArrayList<>();
        options.add(0, tv_option_one);
        options.add(1, tv_option_two);
        options.add(2, tv_option_three);
        options.add(3, tv_option_four);

        for (TextView option: options){
            option.setTextColor(Color.parseColor("#7A8089"));
            option.setTypeface(Typeface.DEFAULT);
            option.setBackground(ContextCompat.getDrawable(this,R.drawable.default_option_bg));
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.tv_option_one: selectedOptionView(tv_option_one, 1);
            break;
            case R.id.tv_option_two: selectedOptionView(tv_option_two, 2);
            break;
            case R.id.tv_option_three: selectedOptionView(tv_option_three, 3);
            break;
            case R.id.tv_option_four: selectedOptionView(tv_option_four, 4);
            break;
            case R.id.btn_submit:
                if (mSelectedOptionPosition == 0){
                    mCurrentPosition++;

                    if (mCurrentPosition <= questionsList.size()){
                        setQuestion();
                    }else {
                        Intent intent =new Intent(this, ResultActivity.class);
                        intent.putExtra(USER_NAME, mUserName);
                        intent.putExtra(CORRECT_ANSWERS,mCorrectAnswers);
                        intent.putExtra(TOTAL_QUESTIONS, questionsList.size());
                        startActivity(intent);
                        finish();
                    }
                }else {
                    Question questions = questionsList.get(mCurrentPosition - 1);
                    if (questions.getCorrectAnswer() != mSelectedOptionPosition){
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_bg);
                    }else {
                        mCorrectAnswers++;
                    }
                    answerView(questions.getCorrectAnswer(), R.drawable.correct_option_bg);

                    if (mCurrentPosition == questionsList.size()){
                        btn_submit.setText(R.string.finish);
                    }else {
                        btn_submit.setText(R.string.go_next);
                    }
                    mSelectedOptionPosition = 0;
                }
        }

    }

    private void answerView(int answer, int drawableView){
        switch (answer){
            case 1: tv_option_one.setBackground(ContextCompat.getDrawable(this, drawableView));
            break;
            case 2: tv_option_two.setBackground(ContextCompat.getDrawable(this, drawableView));
            break;
            case 3: tv_option_three.setBackground(ContextCompat.getDrawable(this, drawableView));
            break;
            case 4: tv_option_four.setBackground(ContextCompat.getDrawable(this, drawableView));
            break;
        }
    }

    private void selectedOptionView(TextView tv, int selectedOPtionNumber){
        defaultOptionsViews();
        mSelectedOptionPosition = selectedOPtionNumber;

        tv.setTextColor(Color.parseColor("#000000"));
        tv.setTypeface(tv.getTypeface(), Typeface.BOLD);
        tv.setBackground(ContextCompat.getDrawable(this,R.drawable.selected_option_bg));
    }
}
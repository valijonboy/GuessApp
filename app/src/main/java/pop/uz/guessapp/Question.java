package pop.uz.guessapp;

public class Question {

    private int id, image, correctAnswer;
    private String question, optionOne, optionTwo, optionThree, optionFour;


    public Question(int id,  String question, int image,
                    String optionOne, String optionTwo, String optionThree, String optionFour, int correctAnswer) {
        this.id = id;
        this.image = image;
        this.correctAnswer = correctAnswer;
        this.question = question;
        this.optionOne = optionOne;
        this.optionTwo = optionTwo;
        this.optionThree = optionThree;
        this.optionFour = optionFour;
    }

    public int getId(){ return id;}
    public String getQuestion(){return question;}
    public int getImage() {
        return image;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public String getOptionOne() {
        return optionOne;
    }

    public String getOptionTwo() {
        return optionTwo;
    }

    public String getOptionThree() {
        return optionThree;
    }

    public String getOptionFour() {
        return optionFour;
    }
}

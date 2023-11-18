package src.entity;

public class Question {
    /// A class represents ONE QUESTION in the trivia cash game

    private String content;
    private String category;
    private String difficultyLevel;
    private AnswerPackage answerPackage;


    public Question(String content, String category, String difficultyLevel, AnswerPackage answerPackage){
        this.content = content;
        this.category = category;
        this.difficultyLevel = difficultyLevel;
        this.answerPackage = answerPackage;
    }

    public String getContent(){
        return this.content;
    }

    public AnswerPackage getAnswerPackage() {
        return this.answerPackage;
    }
}

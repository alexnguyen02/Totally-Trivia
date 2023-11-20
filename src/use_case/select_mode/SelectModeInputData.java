package use_case.select_mode;

public class SelectModeInputData {
    private String category;

    private String difficultyLevel;

    private int numberOfQuestions;

    public SelectModeInputData(String category, String difficultyLevel, int numberOfQuestions){
        this.category = category;
        this.difficultyLevel = difficultyLevel;
        this.numberOfQuestions = numberOfQuestions;
    }

    public String getCategory(){
        return this.category;
    }

    public String getDifficultyLevel(){
        return this.difficultyLevel;
    }

    public int getNumberOfQuestions(){
        return this.numberOfQuestions;
    }

}

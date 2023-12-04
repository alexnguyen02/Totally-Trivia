package use_case.select_mode;

public class SelectModeInputData {
    private String category;

    private String difficultyLevel;

    private String numOfQuestions;

    public SelectModeInputData(String category, String difficultyLevel, String numOfQuestions){
        this.category = category;
        this.difficultyLevel = difficultyLevel;
        this.numOfQuestions = numOfQuestions;
    }

    public String getCategory(){
        return this.category;
    }

    public String getDifficultyLevel(){
        return this.difficultyLevel;
    }

    public String getNumberOfQuestionsString(){
        return this.numOfQuestions;
    }

}

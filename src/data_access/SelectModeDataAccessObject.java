package data_access;

import entity.AnswerPackage;
import entity.Question;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.select_mode.SelectModeDataObjectInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SelectModeDataAccessObject implements SelectModeDataObjectInterface {
    private String API_URL;

    private final String[] categoryList = new String[]{"Any category", "General knowledge", "Books", "Film",
            "Music", "Musicals & Theatres", "Television", "Video games", "Board games", "Science & Nature", "Computers",
            "Mathematics", "Mythology", "Sports", "Geography", "History", "Politics", "Arts", "Celebrities", "Animals",
            "Vehicles", "Comics", "Gadgets", "Japanese Anime & Manga", "Cartoon & Animations"};

    private final Map<String, Integer> categoryMap = new HashMap<>();

    private Integer numOfQuestions;

    private Integer category;

    private String difficultyLevel;

    public SelectModeDataAccessObject() {
        createCategoryMap();
    }

    private void createCategoryMap(){
        int categoryCode = 8;
        for (String s : categoryList){
            categoryMap.put(s, categoryCode);
            categoryCode += 1;
        }
    }

    private void buildAPIURL(String category, String difficultyLevel, int numOfQuestions){
        this.category = categoryMap.get(category);
        this.difficultyLevel = difficultyLevel.toLowerCase();
        this.numOfQuestions = numOfQuestions;

        if (category.equals("Any category") && difficultyLevel.equals("Any difficulty level")){
            API_URL = String.format("https://opentdb.com/api.php?amount=%d&type=multiple", this.numOfQuestions);
        } else if (category.equals("Any category")) {
            API_URL = String.format("https://opentdb.com/api.php?amount=%d&difficulty=%s&type=multiple", this.numOfQuestions, this.difficultyLevel);
        } else if (difficultyLevel.equals("Any difficulty level")) {
            API_URL = String.format("https://opentdb.com/api.php?amount=%d&category=%d&type=multiple", this.numOfQuestions, this.category);
        } else {
            API_URL = String.format("https://opentdb.com/api.php?amount=%d&category=%d&difficulty=%s&type=multiple", this.numOfQuestions, this.category, this.difficultyLevel);
        }
    }

    private String extractFromJSONString(String regex, String JSONString){
        String result = null;

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(JSONString);
        if (matcher.find()){
            result = matcher.group(1);
        }

        return result;
    }

    private AnswerPackage buildAnswerPackage(String JSONString){
        // Extract the correct answer from the JSONString
        String correctAnswerRegex = "\"correct_answer\":\"(.*?)\"";
        String correctAnswer = extractFromJSONString(correctAnswerRegex, JSONString);

        // Extract the incorrect answers from the JSONString
        JSONObject incorrectAnswersObject = new JSONObject(JSONString);
        JSONArray incorrectAnswersArray = incorrectAnswersObject.getJSONArray("incorrect_answers");

        // The array of incorrect answers only contains 3 answers, so the size of all possible answers
        // must be 3 + 1 (the correct answer) = 4
        int possibleAnswersSize = incorrectAnswersArray.length() + 1;

        // Build an array of possible answers from the array of incorrect answers
        ArrayList<String> possibleAnswersList = new ArrayList<>(possibleAnswersSize);
        for (int i = 0; i < incorrectAnswersArray.length(); i++) {
            possibleAnswersList.add(i, incorrectAnswersArray.getString(i));
        }

        // Generate a random index and
        // add the correct answer to the that index of the array of possible answers
        Random random = new Random();
        int randomIndex = random.nextInt(possibleAnswersSize);
        possibleAnswersList.add(randomIndex, correctAnswer);

        System.out.println(possibleAnswersList);

        return new AnswerPackage(possibleAnswersList, correctAnswer);
    }

    private Question buildQuestion(String JSONString){
        String content;
        String category;
        String difficultyLevel;
        AnswerPackage answerPackage;

        // Extract the content of the question
        String contentRegex = "\"question\":\"(.*?)\"";
        content = extractFromJSONString(contentRegex, JSONString);

        // Extract the category of the question
        String categoryRegex = "\"category\":\"(.*?)\"";
        category = extractFromJSONString(categoryRegex, JSONString);

        // Extract the difficulty level of the question
        String difficultyLevelRegex = "\"difficulty\":\"(.*?)\"";
        difficultyLevel = extractFromJSONString(difficultyLevelRegex, JSONString);

        // Build a new AnswerPackage
        answerPackage = buildAnswerPackage(JSONString);
        return new Question(content, category, difficultyLevel, answerPackage);
    }


    private ArrayList<Question> getQuestionsFromAPI(){
        ArrayList<Question> listOfQuestions= new ArrayList<>();
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(API_URL)
                .build();

        try {
            Response response = client.newCall(request).execute();

            assert response.body() != null;
            JSONObject responseBody = new JSONObject(response.body().string());

            if (response.isSuccessful()){
                JSONArray results = responseBody.getJSONArray("results");
                for (Object r : results){
                    String decodedResult = StringEscapeUtils.unescapeHtml4(r.toString());

                    Question question = buildQuestion(decodedResult);
                    listOfQuestions.add(question);

                    // For testing purpose
                    System.out.println(decodedResult);
                }
            } else {
                throw new RuntimeException("Error occurred when connecting with API");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return listOfQuestions;
    }

    @Override
    public ArrayList<Question> getQuestions(String category, String difficultyLevel, int numOfQuestions) {
        buildAPIURL(category, difficultyLevel, numOfQuestions);
        return getQuestionsFromAPI();
    }
}

package data_access;

import entity.AnswerPackage;
import entity.Question;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringEscapeUtils;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.select_mode.SelectModeDataObjectInterface;

import java.io.IOException;
import java.util.*;
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

    @Override
    public ArrayList<Question> getQuestions(String category, String difficultyLevel, int numOfQuestions) {
        buildAPIURL(category, difficultyLevel, numOfQuestions);
        return getQuestionsFromAPI();
    }

    private void buildAPIURL(String category, String difficultyLevel, int numOfQuestions){
        this.category = categoryMap.get(category);
        this.difficultyLevel = difficultyLevel.toLowerCase();
        this.numOfQuestions = numOfQuestions;

        if (category.equals("Any category") && difficultyLevel.equals("Any difficulty level")){
            API_URL = String.format("https://opentdb.com/api.php?amount=%d&type=multiple",
                    this.numOfQuestions);
        } else if (category.equals("Any category")) {
            API_URL = String.format("https://opentdb.com/api.php?amount=%d&difficulty=%s&type=multiple",
                    this.numOfQuestions,
                    this.difficultyLevel);
        } else if (difficultyLevel.equals("Any difficulty level")) {
            API_URL = String.format("https://opentdb.com/api.php?amount=%d&category=%d&type=multiple",
                    this.numOfQuestions,
                    this.category);
        } else {
            API_URL = String.format("https://opentdb.com/api.php?amount=%d&category=%d&difficulty=%s&type=multiple",
                    this.numOfQuestions,
                    this.category,
                    this.difficultyLevel);
        }
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
                JSONArray rawResults = responseBody.getJSONArray("results");
                for (Object r : rawResults){
                    String cleanResult = removeHTMLChar(r.toString());

                    Question question = buildQuestion(cleanResult);
                    listOfQuestions.add(question);
                }
            } else {
                throw new RuntimeException("Error occurred when connecting with API");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return listOfQuestions;
    }

    private Question buildQuestion(String JSONString){
        String content;
        String category;
        String difficultyLevel;
        AnswerPackage answerPackage;

        // Extract the content of the question
        String contentRegex = "\"question[^]]*correct_answer\"";
        content = cleanupContentString(extractRegex(contentRegex, JSONString, true));

        // For testing purpose
        System.out.println(content);

        // Extract the category of the question
        String categoryRegex = "\"category\":\"(.*?)\"";
        category = extractRegex(categoryRegex, JSONString, false);

        // Extract the difficulty level of the question
        String difficultyLevelRegex = "\"difficulty\":\"(.*?)\"";
        difficultyLevel = extractRegex(difficultyLevelRegex, JSONString, false);

        // Build a new AnswerPackage
        answerPackage = buildAnswerPackage(JSONString);
        return new Question(content, category, difficultyLevel, answerPackage);
    }

    private AnswerPackage buildAnswerPackage(String JSONString){
        // Extract the correct answer from the JSONString
        String correctAnswerRegex = "\"correct_answer\":\"(.*?)\"";
        String correctAnswer = extractRegex(correctAnswerRegex, JSONString, false);

        // Extract the incorrect answers from the JSONString
        String incorrectAnswersRegex = "\\[[^\\[]*]";
        String incorrectAnswers = extractRegex(incorrectAnswersRegex, JSONString, true);

        ArrayList<String> possibleAnswersList = getPossibleAnswersList(incorrectAnswers, correctAnswer);
        return new AnswerPackage(possibleAnswersList, correctAnswer);
    }

    @NotNull
    private static ArrayList<String> getPossibleAnswersList(String incorrectAnswers, String correctAnswer) {
        String cleanupString = incorrectAnswers.replace("[", "").replace("]", "");

        String[] incorrectAnswersArray = cleanupString.split(",");
        ArrayList<String> possibleAnswersList = new ArrayList<>();
        for (String answer : incorrectAnswersArray){
            possibleAnswersList.add(answer.replace("\"", ""));
        }
        return addCorrectAnswerToList(possibleAnswersList, correctAnswer, incorrectAnswersArray.length + 1);
    }

    private static ArrayList<String> addCorrectAnswerToList(ArrayList<String> possibleAnswersList, String correctAnswer, int listSize){
        Random random = new Random();
        int randomIndex = random.nextInt(listSize);

        possibleAnswersList.add(randomIndex, correctAnswer);

        return possibleAnswersList;
    }

    private String extractRegex(String regex, String JSONString, boolean noGroup){
        String result = null;

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(JSONString);
        if (matcher.find()){
            if (noGroup){
                result = matcher.group();
            } else {
                result = matcher.group(1);
            }
        }
        return result;
    }

    private String removeHTMLChar(String inputString){
        return StringEscapeUtils.unescapeHtml4(inputString);
    }

    private String cleanupContentString(String contentString) {
        String removedPrefix = contentString.replace("\"question\":\"", "");
        return removedPrefix.replace("\",\"correct_answer\"", "");
    }
}

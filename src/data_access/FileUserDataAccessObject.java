package data_access;

import entity.User;
import entity.UserFactory;
import use_case.delete.DeleteUserDataAccessInterface;
import use_case.game_over.GameOverUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.select_colour.SelectColourUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface, GameOverUserDataAccessInterface, DeleteUserDataAccessInterface, SelectColourUserDataAccessInterface {

    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, User> accounts = new HashMap<>();

    private UserFactory userFactory;

    public FileUserDataAccessObject(String csvPath, UserFactory userFactory) throws IOException {
        this.userFactory = userFactory;

        csvFile = new File(csvPath);
        headers.put("username", 0);
        headers.put("password", 1);
        headers.put("creation_time", 2);
        headers.put("points", 3);
        headers.put("colour_scheme", 4);

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                assert header.equals("username,password,creation_time,points,color_scheme");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String username = String.valueOf(col[headers.get("username")]);
                    String password = String.valueOf(col[headers.get("password")]);
                    String creationTimeText = String.valueOf(col[headers.get("creation_time")]);
                    String points = String.valueOf(col[headers.get("points")]);
                    String colourScheme = String.valueOf(col[headers.get("colour_scheme")]);
                    LocalDateTime ldt = LocalDateTime.parse(creationTimeText);
                    Integer int_points = Integer.valueOf(points);
                    User user = userFactory.create(username, password, ldt, int_points, colourScheme);
                    accounts.put(username, user);
                }
            }
        }
    }

    @Override
    public void save(User user) {
        accounts.put(user.getName(), user);
        this.save();
    }

    @Override
    public User get(String username) {
        return accounts.get(username);
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (User user : accounts.values()) {
                String line = String.format("%s,%s,%s,%s,%s",
                        user.getName(), user.getPassword(), user.getCreationTime(), user.getPoints(), user.getColourScheme());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Return whether a user exists with username identifier.
     * @param identifier the username to check.
     * @return whether a user exists with username identifier
     */
    @Override
    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }

    @Override
    public void changePoints(String userId, Integer points) {
        User user = accounts.get(userId);
        user.setPoints(user.getPoints() + points);
        save();
    }
  
   @Override
    public void changeColourScheme(String userId, String colour) {
        User user = accounts.get(userId);
        user.setColourScheme(colour);
        save();
    }
  
  @Override
    public String delete(String username) {
        if (accounts.containsKey(username)) {
            accounts.remove(username);
            this.save();
            return username;
        } else {
            return "User not found";
        }
    }
}

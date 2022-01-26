package day02;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Quiz {
    private String correctAnswers;
    private Map<String, String> contestantsAnswers = new HashMap<>();

    public Quiz(Path path) {
        readContestantAnswersFromFile(path);
    }

    private void readContestantAnswersFromFile(Path path) {
        try (BufferedReader bf = Files.newBufferedReader(path)) {
            correctAnswers = bf.readLine();
            while (bf.ready()) {
                parseLine(bf.readLine());
            }
        } catch (IOException ioException) {
            throw new IllegalStateException("Cannot read file!", ioException);
        }
    }

    private void parseLine(String line) {
        String[] fields = line.split(" ");
        String contestant = fields[0];
        if (contestantsAnswers.containsKey(contestant)) {
            contestantsAnswers.replace(contestant, contestantsAnswers.get(contestant).concat(fields[1]));
        } else {
            contestantsAnswers.put(contestant, fields[1]);
        }
    }

    public boolean isAnswerCorrect(String contestant, int number) {
        return contestantsAnswers.get(contestant).charAt(number - 1) == correctAnswers.charAt(number - 1);
    }

    private int getContestantTotalPoints(String code) {
        int points = 0;
        for (int i = 0; i < contestantsAnswers.get(code).length(); i++) {
            if (contestantsAnswers.get(code).charAt(i) == correctAnswers.charAt(i)) {
                points += i + 1;
            } else if (contestantsAnswers.get(code).charAt(i) != 'X') {
                points -= 2;
            }
        }
        return points;
    }

    public String getWinner() {
        String winner = null;
        int maxPoints = 0;
        for(String code : contestantsAnswers.keySet()){
            int actualPoints = getContestantTotalPoints(code);
            if(maxPoints < actualPoints){
                maxPoints = actualPoints;
                winner = code;

            }
        }
        if(winner == null){
            throw new IllegalArgumentException("No winner!");
        }
        return winner;
    }

    public Map<String, String> getContestantsAnswers() {
        return contestantsAnswers;
    }

}
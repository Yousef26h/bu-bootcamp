package module2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GradeAnalyzer {
    private static int invalidLines = 0; // counter for invalid lines
    public static void main(String[] args) {
        // Step 1: read scores from file
        ArrayList<Integer> scores = readScores("scores.txt");
        if (scores.isEmpty()) {
            System.out.println("No valid scores to process.");
            return;
        }
        // Step 2: calculate maximum and minimum scores
        int high = Integer.MIN_VALUE;
        int low = Integer.MAX_VALUE;
        for (int score : scores) {
            if (score > high) {
                high = score;
            }
            if (score < low) {
                low = score;
            }
        }
        // Step 3: calculate statistics
        double avg = calculateAverage(scores);
        // Step 3: write and print report
        writeReport(scores, avg, high, low, "report.txt");
    } 
 
    // Returns a list of valid scores read from the file
    public static ArrayList<Integer> readScores(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            ArrayList<Integer> scores = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String trimmedLine = line.trim();
                    if(trimmedLine.isEmpty()) {
                        continue; // skip empty lines
                    }
                    int score = Integer.parseInt(trimmedLine);
                    if (score >= 0 && score <= 100) {
                        scores.add(score);
                    } else {
                        System.err.println("Invalid score (out of range): " + score);
                    }
                } catch (NumberFormatException e) {
                    invalidLines++;
                    System.err.println("Invalid score (not an integer): " + line);
                }
            }
            return scores;
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return new ArrayList<>(); // return empty list on error
        }
    }
 
    // Returns the average of a list of scores, or 0.0 if the list is empty
    public static double calculateAverage(ArrayList<Integer> scores) {
        if (scores.isEmpty()) {
            return 0.0;
        }
        double sum = 0;
        for (double score : scores) {
            sum += score;
        }
        return sum / scores.size();
    }
 
    // Writes and prints the report
    public static void writeReport(ArrayList<Integer> scores,
                                   double avg, int high, int low,
                                   String outputFile) {
        // count grade bands
        int countA = 0, countB = 0, countC = 0, countD = 0, countF = 0;
        for (int score : scores) {
            if (score >= 90) {
                countA++;
            } else if (score >= 80) {
                countB++;
            } else if (score >= 70) {
                countC++;
            } else if (score >= 60) {
                countD++;
            } else {
                countF++;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write("=== Grade Analysis Report ===\n");
            System.out.println("=== Grade Analysis Report ===");
            writer.write(String.format("Total scores processed:: %d\n", scores.size()));
            System.out.println(String.format("Total scores processed: %d", scores.size()));
            writer.write(String.format("Invalid lines skipped: %d\n", invalidLines));
            System.out.println(String.format("Invalid lines skipped: %d", invalidLines));
            writer.write("\n");
            System.out.println("");
            System.out.println(String.format("Average score: %.2f", avg));
            writer.write(String.format("Average score: %.2f\n", avg));
            System.out.println(String.format("Highest score: %d", high));
            writer.write(String.format("Highest score: %d\n", high));
            System.out.println(String.format("Lowest score: %d", low));
            writer.write(String.format("Lowest score: %d\n", low));
            writer.write("\n");
            System.out.println("");
            System.out.println("Grade Distribution:");
            writer.write("\nGrade Distribution:\n");
            writer.write(String.format("A: %d\n", countA));
            System.out.println(String.format("A: %d", countA));
            writer.write(String.format("B: %d\n", countB));
            System.out.println(String.format("B: %d", countB));
            writer.write(String.format("C: %d\n", countC));
            System.out.println(String.format("C: %d", countC));
            writer.write(String.format("D: %d\n", countD));
            System.out.println(String.format("D: %d", countD));
            writer.write(String.format("F: %d\n", countF));
            System.out.println(String.format("F: %d", countF));
        } catch (IOException e) {
            System.err.println("Error writing report: " + e.getMessage());
        }

    }
}

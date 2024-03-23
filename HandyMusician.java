import java.util.Scanner;
import java.util.Arrays;

public class HandyMusician {

  private static final String[] ALL_NOTES = new String[] {
    "A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"
  };

  public static int indexCorrection(int index) {
    // Takes index int and allows the index to "wrap" around the key notes in ALL_NOTES array
    if(index >= ALL_NOTES.length) {
      int newIndex = index - 12;
      return newIndex;
    } else {
      return index;
    }
  }

  public static void determineChords(String key) {

    // List of chords
    String[] finalChords = new String[7];
    // List of indexes that make up the Major Triad
    int[] majorTriad = new int[]{0, 3, 4};
    // List of indexes that make up the pop chord progression
    int[] popProg = new int[]{0, 4, 5, 3};

    // Build list of chords
    int indexOfRoot = Arrays.asList(ALL_NOTES).indexOf(key);
    finalChords[0] = ALL_NOTES[indexOfRoot];
    finalChords[1] = ALL_NOTES[indexCorrection(indexOfRoot + 2)] + "m";
    finalChords[2] = ALL_NOTES[indexCorrection(indexOfRoot + 4)] + "m";
    finalChords[3] = ALL_NOTES[indexCorrection(indexOfRoot + 5)];
    finalChords[4] = ALL_NOTES[indexCorrection(indexOfRoot + 7)];
    finalChords[5] = ALL_NOTES[indexCorrection(indexOfRoot + 9)] + "m";
    finalChords[6] = ALL_NOTES[indexCorrection(indexOfRoot + 11)] + "mdim";

    // Print out all Major Scale chords
    System.out.println("\nAll Chords: \nI\tii\tiii\tIV\tV\tvi\tviio");
    for(String chord : finalChords) {
      System.out.print(chord + "\t");
    }
    System.out.println("");

    // Print Major Triad
    System.out.println("\nMajor Triad: \nI\tIV\tV");
    for(int i : majorTriad) {
      System.out.print(finalChords[i] + "\t");
    }
    System.out.println("");

    System.out.println("\nPop Chord Progression: \nI\tV\tvi\tIV");
    for(int i : popProg) {
      System.out.print(finalChords[i] + "\t");
    }
    System.out.println("");
  }

  public static void determineKeySignature(String key) {
    // Takes key and provides the key signature
  }

  public static void notesOnFretBoard(String key) {
    // Takes the key and shows which notes on the fretboard are included
  }

  public static String getKeyRoot() {
    Scanner scanner = new Scanner(System.in);
    String key;

    while(true) {
      System.out.print("Enter the musical key: ");
      key = scanner.nextLine().toUpperCase();
      if(Arrays.asList(ALL_NOTES).contains(key)) {
        scanner.close();
        return key;
      } else {
        System.out.println("No key of \"" + key + "\" was found");
      }
    }
  }

  public static void main(String[] args) {
    String musicalKey = getKeyRoot();

    determineChords(musicalKey);
    //determineKeySignature(musicalKey);
    //notesOnFretBoard(musicalKey);
  }

}

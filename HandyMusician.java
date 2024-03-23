import java.util.Scanner;
import java.util.Arrays;

public class HandyMusician {

  private static final String[] ALL_NOTES = new String[] {
    "a", "a#", "b", "c", "c#", "d", "d#", "e", "f", "f#", "g", "g#"
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
    String[] finalNotes = new String[]{key, null, null, null};
    int indexOfRoot = Arrays.asList(ALL_NOTES).indexOf(key);
    int indexOfIV = indexCorrection(indexOfRoot + 5);
    int indexOfV = indexCorrection(indexOfRoot + 7);
    int indexOfvi = indexCorrection(indexOfRoot + 9);
    
    finalNotes[1] = ALL_NOTES[indexOfIV];
    finalNotes[2] = ALL_NOTES[indexOfV];
    finalNotes[3] = ALL_NOTES[indexOfvi];

    System.out.println("\nMajor Triad: \nI\tIV\tV");
    for(int i = 0; i < (finalNotes.length - 1); i++) {
      System.out.print(finalNotes[i] + "\t");
    }

    System.out.println("\n\nPop Chords (play I-V-vi-IV): \nI\tIV\tV\tvi");
    int count = 0;
    for(String note : finalNotes) {
      String lineEnd = "";
      if(count < 3) {
        lineEnd = "\t";
        count++;
      } else {
        lineEnd = "m\n";
      }
      System.out.print(note + lineEnd);
    }
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
      key = scanner.nextLine();
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

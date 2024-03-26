import java.util.Scanner;
//import java.lang.reflect.Array;
import java.util.Arrays;

public class HandyMusician {


  private static final String[] ALL_NOTES = new String[] {
    "A", "A♯/B♭", "B", "C", "C♯/D♭", "D", "D♯/E♭", "E", "F", "F♯/G♭", "G", "G♯/A♭"
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

    String theSharp = key;
    String theFlat = key;
    String returnSharp = "no ♯ signature";
    String returnFlat = "no ♭ signature";

    String[] sharpSigs = new String[] {
      "C", "G", "D", "A", "E", "B", "F♯", "C♯"
    };
    String[] flatSigs = new String[] {
      "C", "F", "B♭", "E♭", "A♭", "D♭", "G♭", "C♭"
    };

    if(key.length() > 1) {
      theSharp = "" + key.charAt(0) + key.charAt(1);
      theFlat = "" + key.charAt(3) + key.charAt(4);
    }

    if(Arrays.asList(sharpSigs).contains(theSharp)) {
      returnSharp = Arrays.asList(sharpSigs).indexOf(theSharp) + " ♯'s";
    }

    if(Arrays.asList(flatSigs).contains(theFlat)) {
      returnFlat = Arrays.asList(flatSigs).indexOf(theFlat) + " ♭'s";
    }

    System.out.println("\nKey Signatures:");
    System.out.println(returnSharp + "\n" + returnFlat);

  }


  public static void notesOnFretBoard(String key) {
    // Takes the key and shows which notes on the fretboard are included
  }


  public static String sharpFlatCombine(String sharpFlat) {
    // Takes string of sharp or flat and returns the sharp/flat combo
    
    int sfIndex = 0;
    String[] sharps = new String[] {
      "A♯", "C♯", "D♯", "F♯", "G♯"
    };
    String[] flats = new String[] {
      "B♭", "D♭", "E♭", "G♭", "A♭"
    };
    String[] combos = new String[] {
      "A♯/B♭", "C♯/D♭", "D♯/E♭", "F♯/G♭", "G♯/A♭"
    };

    if(Arrays.asList(sharps).contains(sharpFlat)) {
      sfIndex = Arrays.asList(sharps).indexOf(sharpFlat);
    } else if(Arrays.asList(flats).contains(sharpFlat)) {
      sfIndex = Arrays.asList(flats).indexOf(sharpFlat);
    } else {
      return sharpFlat;
    }

    return combos[sfIndex];
  }


  public static String sharpFlatTranslate(String inputStr) {
    // Converts # to ♯ and b to ♭ 

    String translatedString = "";

    if(inputStr.length() > 1) {
      if(inputStr.charAt(1) == '#') {
        translatedString = inputStr.charAt(0) + "♯";
      } else if(inputStr.charAt(1) == 'B') {
        translatedString = inputStr.charAt(0) + "♭";
      } 
      translatedString = sharpFlatCombine(translatedString);
    } else {
      translatedString = inputStr;
    }

    return translatedString;
  }


  public static String getKeyRoot() {
    Scanner scanner = new Scanner(System.in);
    String key;

    while(true) {
      System.out.print("Enter the musical key: ");
      key = scanner.nextLine().toUpperCase();
      key = sharpFlatTranslate(key);
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
    determineKeySignature(musicalKey);
    //notesOnFretBoard(musicalKey);
  }

}

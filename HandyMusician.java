import java.util.Scanner;
//import java.lang.reflect.Array;
import java.util.Arrays;

public class HandyMusician {


  // List of all 12 keys
  private static final String[] ALL_NOTES = new String[] {
    "A", "A♯/B♭", "B", "C", "C♯/D♭", "D", "D♯/E♭", "E", "F", "F♯/G♭", "G", "G♯/A♭"
  };

  // Lists of sharp and flag signatures with index representing number of each
  private static final String[] SHARP_SIGS = new String[] {
    "C", "G", "D", "A", "E", "B", "F♯", "C♯"
  };
  private static final String[] FLAT_SIGS = new String[] {
    "C", "F", "B♭", "E♭", "A♭", "D♭", "G♭", "C♭"
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

    // both theSharp and theFlat initialized with key because "C" is in both lists
    String theSharp = key;
    String theFlat = key;
    String returnSharp = "no ♯ signature";
    String returnFlat = "no ♭ signature";

    // Turns sharp/flat pairs into single sharp and single flat
    if(key.length() > 1) {
      theSharp = "" + key.charAt(0) + key.charAt(1);
      theFlat = "" + key.charAt(3) + key.charAt(4);
    }

    // Completes final string for sharp and flats if any sig for each
    if(Arrays.asList(SHARP_SIGS).contains(theSharp)) {
      returnSharp = Arrays.asList(SHARP_SIGS).indexOf(theSharp) + " ♯'s";
    }
    if(Arrays.asList(FLAT_SIGS).contains(theFlat)) {
      returnFlat = Arrays.asList(FLAT_SIGS).indexOf(theFlat) + " ♭'s";
    }

    // Final printout
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
      System.out.println("\nKey: " + translatedString);
      translatedString = sharpFlatCombine(translatedString);
    } else {
      translatedString = inputStr;
    }

    return translatedString;
  }


  public static String getKeyRoot(Scanner scanner) {
    // User prompted for key root note
    // Returns the key root note

    while(true) {
      System.out.print("Enter the musical key: ");
      String key = scanner.nextLine().toUpperCase();
      key = sharpFlatTranslate(key);
      if(Arrays.asList(ALL_NOTES).contains(key)) {
        return key;
      } else {
        System.out.println("No key of \"" + key + "\" was found");
      }
    }
  }


  public static String getRootFromSig(Scanner scanner) {
    // Takes signature (number of sharps or flats) and returns a key

    String menu = "Enter number of sharps (n#) or number of flats (nb): ";
    char sharpFlat;
    String key;
    
    while(true) {
      System.out.print(menu);
      String sig = scanner.nextLine().toUpperCase();
      //JH sig = sharpFlatTranslate(sig);
      if(sig.length() == 2) {  // Make sure input is only 2 characters
        sharpFlat = sig.charAt(1);
        try {
          int num = Integer.parseInt(sig.charAt(0) + "");  // Make sure first character is an integer
          if(num >= 0 && num <= 7) {  // Make sure initial integer is 0-7
            if(sharpFlat == '#') {
              key = SHARP_SIGS[num];
              System.out.println("\nKey: " + key);
              key = sharpFlatCombine(key);
              return key;
              //JH return sharpFlatTranslate(key);
            } else if(sharpFlat == 'B') {
              key = FLAT_SIGS[num];
              System.out.println("\nKey: " + key);
              key = sharpFlatCombine(key);
              return key;
              //JH return sharpFlatTranslate(key);
            } else {  // If neither ♯ or ♭
              System.out.println("Didn't recognize \"" + sharpFlat + "\"");
            }
          } else {
            System.out.println("Initial Integer should be between 0 and 7");
          }
        } catch(NumberFormatException e) {
          System.out.println("The first character should be an integer");
        }
      } else {
        System.out.println("Improper input - expected 2 characters and no space");
      }
    }
  }


  public static int getOption(Scanner scanner) {
    // Presents an initial menu and takes an option to determine whether user has root note or signature

    int opt;
    String menu = "Do you have 1) the root note; or 2) the key signature (number of #'s or b's): ";

    while(true) {
      System.out.print(menu);
      String input = scanner.nextLine();

      try {
        opt = Integer.parseInt(input);
        if(opt == 1 || opt == 2) {
          return opt;
        } else {
          System.out.println("Valid options are 1 or 2");
        }
      } catch (NumberFormatException e) {
        System.out.println("Valid options are 1 or 2");
      }

    } 
  }


  public static void main(String[] args) {
    
    Scanner scanner = new Scanner(System.in);
    String musicalKey;
    int option = getOption(scanner);
  
//JH    if(option == 1) {
//JH      musicalKey = getKeyRoot(scanner);
//JH    } else if(option == 2) {
//JH      musicalKey = getRootFromSig(scanner);
//JH    } else {
//JH      musicalKey = "C";
//JH    }

    switch(option) {
      case 1:
        musicalKey = getKeyRoot(scanner);
        break;
      case 2:
        musicalKey = getRootFromSig(scanner);
        break;
      default:
        musicalKey = "C";
        break;
    }

    scanner.close();
    
    determineChords(musicalKey);
    determineKeySignature(musicalKey);
    //notesOnFretBoard(musicalKey);
  }

}

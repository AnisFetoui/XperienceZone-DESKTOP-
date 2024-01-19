/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;



import java.io.FileInputStream;
import java.io.IOException;
import com.swabunga.spell.engine.SpellDictionaryHashMap;
import com.swabunga.spell.engine.Word;
import com.swabunga.spell.event.SpellChecker;
import java.io.File;
import java.util.List;

public class Spellchecker {
    private SpellChecker spellChecker;

    public Spellchecker() {
   try {
            File dictionaryFile = new File("C:\\Users\\Med Aziz\\Desktop\\pi\\dictionnaire.txt");
            SpellDictionaryHashMap dictionaryHashMap = new SpellDictionaryHashMap(dictionaryFile);
            spellChecker = new SpellChecker(dictionaryHashMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String autoCorrectText(String text) {
        StringBuilder correctedText = new StringBuilder();
    String[] words = text.split("\\s+");

    for (String word : words) {
        String correctedWord = word;

        if (!spellChecker.isCorrect(word)) {
            // Si le mot est incorrect, obtenez des suggestions de correction.
            List<Word> suggestions = spellChecker.getSuggestions(word, 1); // Nombre de suggestions souhaitées.

            if (!suggestions.isEmpty()) {
                correctedWord = suggestions.get(0).getWord(); // Accédez au texte du mot.
            }
        }

        correctedText.append(correctedWord).append(" ");
    }

    return correctedText.toString().trim();
    }

    public static void main(String[] args) {
        Spellchecker spellCheckerWrapper = new Spellchecker();
        String textToCorrect = "ji veuxx fairr une recalamtion surr cett activitee";
        String correctedText = spellCheckerWrapper.autoCorrectText(textToCorrect);
        System.out.println("Corrected Text: " + correctedText);
    }
}
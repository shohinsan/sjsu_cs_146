package cs146.project4.shohin;

import org.junit.Test;

import java.io.IOException;

public class DictionaryJUnit {
    @Test
    public void testDictionary() throws IOException {
        Dictionary.spellChecker();
        //Number of words in poem: 254
        //Number of mismatched/nonexistent words: 96
    }
}

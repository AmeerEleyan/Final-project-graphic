/**
 * author: Ameer Eleyan, Mohammad AbuBader
 * ID: 1191076, 1190478
 * created: 1/29/2023    1:24 PM
 */
package Interface;

import java.util.ArrayList;
import java.util.HashMap;

public class BankOfQuestions {
    private final HashMap<String, HashMap<String, ArrayList<Question>>> bankQuestions;

    public BankOfQuestions() {
        this.bankQuestions = new HashMap<>();
        this.fillBankQuestions();
    }

    private void fillBankQuestions() {
        HashMap<String, Boolean> options = new HashMap<>();
        options.put("red", true);
        options.put("green", false);
        options.put("blue", false);
        HashMap<String, Boolean> options2 = new HashMap<>();
        options2.put("marwan", false);
        options2.put("implicit", true);
        options2.put("taha", false);
        HashMap<String, Boolean> options3 = new HashMap<>();
        options3.put("explicit", true);
        options3.put("marwan", false);
        options3.put("taha", false);
        ArrayList<Question> questions = new ArrayList<>();
        questions.add(new Question("What is the color of kfa marwan?", options));
        questions.add(new Question("What is explicit?", options2));
        questions.add(new Question("What is implicit?", options3));
        HashMap<String, ArrayList<Question>> innerMap = new HashMap<>();
        innerMap.put("Insert at first", questions);
        bankQuestions.put("Linked list", innerMap);
    }

    public HashMap<String, HashMap<String, ArrayList<Question>>> getBankQuestions() {
        return bankQuestions;
    }
}

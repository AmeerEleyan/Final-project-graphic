/**
 * author: Ameer Eleyan
 * ID: 1191076
 * created: 1/29/2023    1:24 PM
 */

import java.util.ArrayList;
import java.util.HashMap;

public class BankOfQuestions {
    private final HashMap<String, HashMap<String, ArrayList<Question>>> bankQuestions;

    public BankOfQuestions() {
        this.bankQuestions = new HashMap<>();
        this.fillBankQuestions();
    }

    // هان لازم تكتب كل الاسئلة والاجوبة
    private void fillBankQuestions() {

    }

    public HashMap<String, HashMap<String, ArrayList<Question>>> getBankQuestions() {
        return bankQuestions;
    }
}

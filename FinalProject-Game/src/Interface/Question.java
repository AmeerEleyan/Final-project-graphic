/**
 * author: Ameer Eleyan
 * ID: 1191076
 * created: 1/29/2023    1:20 PM
 */
package Interface;

import java.util.HashMap;


public class Question {
    private String question;
    private HashMap<String, Boolean> options;

    public Question(String question, HashMap<String, Boolean> options) {
        this.question = question;
        this.options = options;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public HashMap<String, Boolean> getOptions() {
        return options;
    }

    public void setOptions(HashMap<String, Boolean> options) {
        this.options = options;
    }
}

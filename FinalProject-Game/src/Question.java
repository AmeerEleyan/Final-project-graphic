import java.util.HashMap;

/**
 * author: Ameer Eleyan
 * ID: 1191076
 * created: 1/29/2023    1:20 PM
 */

public class Question {
    private String question;
    private HashMap<String,Boolean> answers;

    public Question(String question, HashMap<String, Boolean> answers) {
        this.question = question;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public HashMap<String, Boolean> getAnswers() {
        return answers;
    }

    public void setAnswers(HashMap<String, Boolean> answers) {
        this.answers = answers;
    }
}

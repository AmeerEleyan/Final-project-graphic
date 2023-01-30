import java.util.Vector;

/**
 * author: Ameer Eleyan
 * ID: 1191076
 * created: 1/29/2023    1:24 PM
 */

public class BankOfQuestions {
    private Vector<Question> linkedListQuestions;
    private Vector<Question> stackQuestions;
    private Vector<Question> queueQuestions;

    public BankOfQuestions() {
        this.linkListQuestionsBank();
        this.stackQuestionsBank();
        this.queueQuestionsBank();
    }

    private void linkListQuestionsBank() {
        this.linkedListQuestions = new Vector<>();
    }

    private void stackQuestionsBank() {
        this.stackQuestions = new Vector<>();
    }

    private void queueQuestionsBank() {
        this.queueQuestions = new Vector<>();
    }

    public Vector<Question> getLinkedListQuestions() {
        return linkedListQuestions;
    }

    public Vector<Question> getStackQuestions() {
        return stackQuestions;
    }

    public Vector<Question> getQueueQuestions() {
        return queueQuestions;
    }
}

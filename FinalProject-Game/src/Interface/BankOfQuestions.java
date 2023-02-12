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
        this.fillQueueQuestions();
        this.fillStackQuestions();
    }

    private void fillStackQuestions() {
        HashMap<String, Boolean> questionOneOptions = new HashMap<>();
        questionOneOptions.put("A- 1", true);
        questionOneOptions.put("B- 2", false);
        questionOneOptions.put("C- 3", false);

        HashMap<String, Boolean> questionTwoOptions = new HashMap<>();
        questionTwoOptions.put("A- O(log n)", false);
        questionTwoOptions.put("B- O(n)", false);
        questionTwoOptions.put("C- O(1)", true);

        HashMap<String, Boolean> questionThreeOptions = new HashMap<>();
        questionThreeOptions.put("A- Yes,but we need another temporary pointer", false);
        questionThreeOptions.put("B- This is not a property of stack", false);
        questionThreeOptions.put("C- A+B", true);

        ArrayList<Question> questions = new ArrayList<>();
        questions.add(new Question("The minimum number of pointer needed in push action?", questionOneOptions));
        questions.add(new Question("Depending on the simulation process,what do you think the time complexity in push operation?", questionTwoOptions));
        questions.add(new Question("Can we push a new node in the bottom of the stack?", questionThreeOptions));

        HashMap<String, ArrayList<Question>> innerMap = new HashMap<>();
        innerMap.put("Push", questions);

        questionOneOptions = new HashMap<>();
        questionOneOptions.put("A- FIFO", false);
        questionOneOptions.put("B- LIFO", true);
        questionOneOptions.put("C- Linear tree", false);

        questionTwoOptions = new HashMap<>();
        questionTwoOptions.put("A- Because we need just access the next node by 'next pointer of the top node'", true);
        questionTwoOptions.put("B- No,it's O(n)", false);
        questionTwoOptions.put("C- Because we need the access for all nodes", false);

        questionThreeOptions = new HashMap<>();
        questionThreeOptions.put("A- Yes", false);
        questionThreeOptions.put("B- No", true);
        questionThreeOptions.put("C- none", false);

        questions = new ArrayList<>();
        questions.add(new Question("What best describes the stack paradigm?", questionOneOptions));
        questions.add(new Question("Why is pop O(1)?", questionTwoOptions));
        questions.add(new Question("Does pop remove the oldest element?", questionThreeOptions));

        innerMap.put("Pop", questions);
        bankQuestions.put("Stack", innerMap);

    }

    private void fillQueueQuestions() {
        HashMap<String, Boolean> questionOneOptions = new HashMap<>();
        questionOneOptions.put("A- FIFO", true);
        questionOneOptions.put("B- LIFO", false);
        questionOneOptions.put("C- Ordered array", false);

        HashMap<String, Boolean> questionTwoOptions = new HashMap<>();
        questionTwoOptions.put("A- Head", false);
        questionTwoOptions.put("B- Tail", true);
        questionTwoOptions.put("C- Both of them", false);

        HashMap<String, Boolean> questionThreeOptions = new HashMap<>();
        questionThreeOptions.put("A- O(nlogn)", false);
        questionThreeOptions.put("B- O(n)", false);
        questionThreeOptions.put("C- O(1)", true);

        ArrayList<Question> questions = new ArrayList<>();
        questions.add(new Question("What best describes the queue paradigm?", questionOneOptions));
        questions.add(new Question("Which pointer moves in the enqueue operation?", questionTwoOptions));
        questions.add(new Question("What is the time complexity of enqueue operation?", questionThreeOptions));

        HashMap<String, ArrayList<Question>> innerMap = new HashMap<>();
        innerMap.put("Enqueue", questions);
        bankQuestions.put("Queue", innerMap);

        questionOneOptions = new HashMap<>();
        questionOneOptions.put("A- Yes", false);
        questionOneOptions.put("B- No", true);
        questionOneOptions.put("C- None of the above", false);

        questionTwoOptions = new HashMap<>();
        questionTwoOptions.put("A- 1", false);
        questionTwoOptions.put("B- 3", false);
        questionTwoOptions.put("C- 2", true);

        questionThreeOptions = new HashMap<>();
        questionThreeOptions.put("A- 1", false);
        questionThreeOptions.put("B- 4", true);
        questionThreeOptions.put("C- 3", false);

        questions = new ArrayList<>();
        questions.add(new Question("Can the dequeue operation be performed on an empty queue?", questionOneOptions));
        questions.add(new Question("The minimum number needed in queue?", questionTwoOptions));
        questions.add(new Question("You have this queue,(1<-2<-3<-4) which node will be removed?", questionThreeOptions));
        innerMap = new HashMap<>();
        innerMap.put("Dequeue", questions);
        bankQuestions.put("Queue", innerMap);

    }

    public HashMap<String, HashMap<String, ArrayList<Question>>> getBankQuestions() {
        return bankQuestions;
    }
}

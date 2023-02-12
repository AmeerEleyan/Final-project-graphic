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

    private final String START_TAG = "<html><p>";
    private final String END_TAG = "</p></html>";


    public BankOfQuestions() {
        this.bankQuestions = new HashMap<>();
        this.fillBankQuestions();
    }

    private void fillBankQuestions() {
        this.fillLinkedListQuestions();
        this.fillQueueQuestions();
        this.fillStackQuestions();
    }

    private void fillLinkedListQuestions() {
        HashMap<String, Boolean> questionOneOptions = new HashMap<>();
        questionOneOptions.put("A- g->t->e->r", false);
        questionOneOptions.put("B- r->g->t->e", true);
        questionOneOptions.put("C- g->r->t->e", false);

        HashMap<String, Boolean> questionTwoOptions = new HashMap<>();
        questionTwoOptions.put("A- O(log n)", false);
        questionTwoOptions.put("B- O(n)", false);
        questionTwoOptions.put("C- O(1)", true);

        HashMap<String, Boolean> questionThreeOptions = new HashMap<>();
        questionThreeOptions.put("A- Yes,its' sequential traversal.", true);
        questionThreeOptions.put("B- Yes,it's random traversal", false);
        questionThreeOptions.put("C- A+B", false);

        ArrayList<Question> questions = new ArrayList<>();
        questions.add(new Question(START_TAG + "You have linked list of char's: g->t->e.<br>If we apply \"Insert at first\" to add 'r'.<br>The linked list will be?<br>" + END_TAG, questionOneOptions));
        questions.add(new Question(START_TAG + "What is the time complexity of the<br>\"Insert at first\" operation in a linked list?<br>" + END_TAG, questionTwoOptions));
        questions.add(new Question(START_TAG + "Is linked list sequential or random?<br>" + END_TAG, questionThreeOptions));

        HashMap<String, ArrayList<Question>> innerMap = new HashMap<>();
        innerMap.put("Insert at first", questions);

        //Insert at last
        //**********************************************************************************

        HashMap<String, Boolean> questionOneOptionsP = new HashMap<>();
        questionOneOptionsP.put("A- O(log n)", false);
        questionOneOptionsP.put("B- O(n)", true);
        questionOneOptionsP.put("C- O(1)", false);

        HashMap<String, Boolean> questionTwoOptionsP = new HashMap<>();
        questionTwoOptionsP.put("A- Yes", true);
        questionTwoOptionsP.put("B- No", false);
        questionTwoOptionsP.put("C- none", false);

        HashMap<String, Boolean> questionThreeOptionsP = new HashMap<>();
        questionThreeOptionsP.put("A- An element is added to the beginning of the linked list", false);
        questionThreeOptionsP.put("B- An element is added to the end of the linked list", true);
        questionThreeOptionsP.put("C- An element is removed from the end of the linked list", false);

        ArrayList<Question> questionsP = new ArrayList<>();
        questionsP.add(new Question(START_TAG + "What is the time complexity of the<br>\"Insert at last\" operation in a linked list?<br>" + END_TAG, questionOneOptionsP));
        questionsP.add(new Question(START_TAG + "Can the \"Insert at last\" operation be used<br>to add elements to an empty linked list?<br>" + END_TAG, questionTwoOptionsP));
        questionsP.add(new Question(START_TAG + "What is the result of the \"Insert at last\"<br>operation in a linked list?<br>" + END_TAG, questionThreeOptionsP));
        innerMap.put("Insert at last", questionsP);

        //Insert at middle
        //******************************************************************

        HashMap<String, Boolean> questionOneOptionsL = new HashMap<>();
        questionOneOptionsL.put("A- O(nlog n)", false);
        questionOneOptionsL.put("B- O(log n)", false);
        questionOneOptionsL.put("C- O(n)", true);

        HashMap<String, Boolean> questionTwoOptionsL = new HashMap<>();
        questionTwoOptionsL.put("A- Will be added at first", false);
        questionTwoOptionsL.put("B- Will be added at last", false);
        questionTwoOptionsL.put("C- A+B", true);

        HashMap<String, Boolean> questionThreeOptionsL = new HashMap<>();
        questionThreeOptionsL.put("A- 0", true);
        questionThreeOptionsL.put("B- 1", false);
        questionThreeOptionsL.put("C- 2", false);

        ArrayList<Question> questionsL = new ArrayList<>();
        questionsL.add(new Question(START_TAG + "If we apply the \"Insert at middle\"<br>operation to an empty linked list?<br>" + END_TAG, questionTwoOptionsL));
        questionsL.add(new Question(START_TAG + "What is the minimum number of temporarily<br>pointer need in \"Insert at middle\" operation in a linked list?<br>" + END_TAG, questionThreeOptionsL));
        questionsL.add(new Question(START_TAG + "What is the time complexity of the<br>\"Insert at middle\" operation in a linked list?<br>" + END_TAG, questionOneOptionsL));
        innerMap.put("Insert at middle", questionsL);

        //remove at first
        //***************************************************
        HashMap<String, Boolean> questionOneOptionsR = new HashMap<>();
        questionOneOptionsR.put("A- O(nlog n)", false);
        questionOneOptionsR.put("B- O(1)", true);
        questionOneOptionsR.put("C- O(n)", false);

        HashMap<String, Boolean> questionTwoOptionsR = new HashMap<>();
        questionTwoOptionsR.put("A- g->t", false);
        questionTwoOptionsR.put("B- t->e", true);
        questionTwoOptionsR.put("C- t->g->g", false);

        HashMap<String, Boolean> questionThreeOptionsR = new HashMap<>();
        questionThreeOptionsR.put("A- Yes", true);
        questionThreeOptionsR.put("B- No", false);
        questionThreeOptionsR.put("C- None", false);

        ArrayList<Question> questionsR = new ArrayList<>();
        questionsR.add(new Question(START_TAG + "You have linked list of char's: g->t->e.<br>If we apply \"Remove First\".The linked list will be?<br>" + END_TAG, questionTwoOptionsR));
        questionsR.add(new Question(START_TAG + "Can the \"Remove first\" operation be used<br>to remove the only element in one time?<br>" + END_TAG, questionThreeOptionsR));
        questionsR.add(new Question(START_TAG + "What is the time complexity of the<br>\"Remove first\" operation in a linked list?<br>" + END_TAG, questionOneOptionsR));

        innerMap.put("Remove first", questionsR);

        //Remove at last
        //************************************************************************


        HashMap<String, Boolean> questionOneOptionsRL = new HashMap<>();
        questionOneOptionsRL.put("A- O(n)", true);
        questionOneOptionsRL.put("B- O(1)", false);
        questionOneOptionsRL.put("C- O(nlog n)", false);

        HashMap<String, Boolean> questionTwoOptionsRL = new HashMap<>();
        questionTwoOptionsRL.put("A- An element is removed from the end of the linked list", false);
        questionTwoOptionsRL.put("B- An element is removed from the beginning of the linked list", false);
        questionTwoOptionsRL.put("C- An element is removed from the end of the linked list", true);

        HashMap<String, Boolean> questionThreeOptionsRL = new HashMap<>();
        questionThreeOptionsRL.put("A- No", true);
        questionThreeOptionsRL.put("B- Yes", false);
        questionThreeOptionsRL.put("C- none", false);

        ArrayList<Question> questionsRL = new ArrayList<>();
        questionsRL.add(new Question(START_TAG + "What is the result of the \"Remove last\"<br>operation in a linked list?<br>" + END_TAG, questionTwoOptionsRL));
        questionsRL.add(new Question(START_TAG + "What is the time complexity of the <br>\"Remove last\" operation in a linked list?<br>" + END_TAG, questionOneOptionsRL));
        questionsRL.add(new Question(START_TAG + "Can the \"Remove last\" operation be used to<br>remove the many element in one time?<br>" + END_TAG, questionThreeOptionsRL));

        innerMap.put("Remove last", questionsRL);
        bankQuestions.put("Linked list", innerMap);
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
        questions.add(new Question(START_TAG + "The minimum number of pointer needed in push action?<br>" + END_TAG, questionOneOptions));
        questions.add(new Question(START_TAG + "Depending on the simulation process,what do<br>you think the time complexity in push operation?<br>" + END_TAG, questionTwoOptions));
        questions.add(new Question(START_TAG + "Can we push a new node in the bottom of the stack?<br>" + END_TAG, questionThreeOptions));

        HashMap<String, ArrayList<Question>> innerMap = new HashMap<>();
        innerMap.put("Push", questions);

        questionOneOptions = new HashMap<>();
        questionOneOptions.put("A- FIFO", false);
        questionOneOptions.put("B- LIFO", true);
        questionOneOptions.put("C- Linear tree", false);

        questionTwoOptions = new HashMap<>();
        questionTwoOptions.put("A- Because we need just access the next node by'next pointer of the top node'", true);
        questionTwoOptions.put("B- No,it's O(n)", false);
        questionTwoOptions.put("C- Because we need the access for all nodes", false);

        questionThreeOptions = new HashMap<>();
        questionThreeOptions.put("A- Yes", false);
        questionThreeOptions.put("B- No", true);
        questionThreeOptions.put("C- none", false);

        questions = new ArrayList<>();
        questions.add(new Question(START_TAG + "What best describes the stack paradigm?<br>" + END_TAG, questionOneOptions));
        questions.add(new Question(START_TAG + "Why is pop O(1)?<br>" + END_TAG, questionTwoOptions));
        questions.add(new Question(START_TAG + "Does pop remove the oldest element?<br>" + END_TAG, questionThreeOptions));

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
        questions.add(new Question(START_TAG + "What best describes the queue paradigm?<br>" + END_TAG, questionOneOptions));
        questions.add(new Question(START_TAG + "Which pointer moves in the enqueue operation?<br>" + END_TAG, questionTwoOptions));
        questions.add(new Question(START_TAG + "What is the time complexity of enqueue operation?<br>" + END_TAG, questionThreeOptions));

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
        questions.add(new Question(START_TAG + "Can the dequeue operation be performed<br>on an empty queue?<br>" + END_TAG, questionOneOptions));
        questions.add(new Question(START_TAG + "The minimum number needed in queue?<br>" + END_TAG, questionTwoOptions));
        questions.add(new Question(START_TAG + "You have this queue,(1<-2<-3<-4) which<br>node will be removed?<br>" + END_TAG, questionThreeOptions));

        innerMap.put("Dequeue", questions);
        bankQuestions.put("Queue", innerMap);

    }


    public HashMap<String, HashMap<String, ArrayList<Question>>> getBankQuestions() {
        return bankQuestions;
    }
}

import DataStructure.ACTION;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


public class UiLoader {


    private JFrame frame;
    private JLabel questionLabel;
    private JRadioButton answer1;
    private JRadioButton answer2;
    private JRadioButton answer3;
    private JButton nextButton;

    private final String[] items = {"Linked list", "Stack", "Queue"};

    private final DefaultComboBoxModel<String> actionModel = new DefaultComboBoxModel<>();
    private final JComboBox<String> dataStructureBox = new JComboBox<>(items);
    private final JComboBox<String> actionBox = new JComboBox<>(actionModel);
    private final JButton runButton = createButton("Run");
    private final JButton takeExamButton = createButton("Take exam");

    private int currentQuestionIndex = 0;
    private int correctAnswerNumber = 0;
    private String actionType;
    private String dataStructureType;
    private int counterOfLinkedList = 1;
    private int counterOfStack = 0;
    private int counterOfQueue = 0;
    private FPSAnimator animator;
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private final BankOfQuestions bankOfQuestions = new BankOfQuestions();
    private final Font font = new Font("Serif", Font.BOLD, 16);
    Drawer drawer;

    public static void main(String[] args) {
        Runnable runnable = () -> new UiLoader().initialization();
        EventQueue.invokeLater(runnable);
    }

    private void initialization() {

        JPanel comboPanel = new JPanel(new GridBagLayout());
        comboPanel.setBackground(Color.WHITE);
        comboPanel.setBorder(BorderFactory.createTitledBorder("Data structure"));

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.ipady = 20;
        comboPanel.add(createLabel("Data structure"), gridBagConstraints);


        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.ipady = 20;
        dataStructureBox.setPrototypeDisplayValue("Insert at first  ");
        comboPanel.add(dataStructureBox, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = -1;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.ipady = 20;
        comboPanel.add(createLabel("Action"), gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = -1;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.ipady = 20;
        actionBox.setPrototypeDisplayValue("Insert at first  ");
        fillComboBoxAction(Objects.requireNonNull(this.dataStructureBox.getSelectedItem()).toString(), this.actionBox);

        dataStructureBox.addActionListener(ae -> fillComboBoxAction(Objects.requireNonNull(this.dataStructureBox.getSelectedItem()).toString(), this.actionBox));
        comboPanel.add(actionBox, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = -1;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.ipady = 20;
        comboPanel.add(this.runButton, gridBagConstraints);
        runButton.addActionListener(a -> {
            this.run();
            if (this.animator.isPaused()) {
                this.animator.resume();
            } else {
                this.animator.start();
            }
        });

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = -1;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.ipady = 20;
        comboPanel.add(this.takeExamButton, gridBagConstraints);
        takeExamButton.addActionListener(ab -> takeExamWindow());

        this.drawer = new Drawer();

        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        GLCanvas glcanvas = new GLCanvas(capabilities);
        glcanvas.addGLEventListener(drawer);
        glcanvas.setSize(1000, (int) (this.screenSize.getHeight() - 90));

        comboPanel.setSize(new Dimension(100, 50));

        final JPanel container = new JPanel();
        container.setLayout(new BorderLayout());
        container.add(comboPanel, BorderLayout.EAST);
        container.add(glcanvas, BorderLayout.WEST);


        JFrame frame = new JFrame("Data structure");
        frame.getContentPane().add(container);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(1300, 800));
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        int fbsSpeed = 20;
        this.animator = new FPSAnimator(glcanvas, fbsSpeed, true);
        drawer.setAnimator(animator);
    }

    private void run() {
        if (Objects.requireNonNull(this.dataStructureBox.getSelectedItem()).equals("Linked list")) {
            String action = Objects.requireNonNull(this.actionBox.getSelectedItem()).toString();
            switch (action) {
                case "Insert at first" -> this.drawer.setActionType(ACTION.INSERT_AT_FITST);
                case "Insert at last" -> this.drawer.setActionType(ACTION.INSERT_AT_LAST);
                case "Insert at middle" -> this.drawer.setActionType(ACTION.INSERT_AT_MIDDLE);
                case "Remove first" -> this.drawer.setActionType(ACTION.REMOVE_AT_FITST);
                default -> this.drawer.setActionType(ACTION.REMOVE_AT_LAST);
            }
        } else if (Objects.requireNonNull(this.dataStructureBox.getSelectedItem()).equals("Stack")) {
            String action = Objects.requireNonNull(this.actionBox.getSelectedItem()).toString();
            if (action.equals("Push")) {
                this.drawer.setActionType(ACTION.PUSH);
            } else {
                this.drawer.setActionType(ACTION.POP);
            }
        } else {
            String action = Objects.requireNonNull(this.actionBox.getSelectedItem()).toString();
            if (action.equals("Enqueue")) {
                this.drawer.setActionType(ACTION.ENQUEUE);
            } else {
                this.drawer.setActionType(ACTION.DEQUEUE);
            }
        }
    }


    private void takeExamWindow() {

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        questionLabel = this.createLabel("");
        questionLabel.setBorder(new EmptyBorder(0, 4, 0, 0));
        panel.add(questionLabel, constraints);

        answer1 = new JRadioButton();
        answer1.setFont(font);
        answer1.setForeground(Color.BLACK);
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(answer1, constraints);

        answer2 = new JRadioButton();
        answer2.setFont(font);
        answer2.setForeground(Color.BLACK);
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(answer2, constraints);

        answer3 = new JRadioButton();
        answer3.setFont(font);
        answer3.setForeground(Color.BLACK);
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(answer3, constraints);

        ButtonGroup answerGroup = new ButtonGroup();
        answerGroup.add(answer1);
        answerGroup.add(answer2);
        answerGroup.add(answer3);
        nextButton = new JButton("Next");

        dataStructureType = Objects.requireNonNull(dataStructureBox.getSelectedItem()).toString();
        actionType = Objects.requireNonNull(actionBox.getSelectedItem()).toString();
        ArrayList<Question> questions2 = bankOfQuestions.getBankQuestions().get(dataStructureType).get(actionType);
        ArrayList<Question> questions = bankOfQuestions.getBankQuestions().get(dataStructureType).get(actionType);
        fillQuestionAnswers(questions);
        nextButton.setEnabled(false);
        nextButton.addActionListener(e -> {
            if (currentQuestionIndex != questions.size()) {
                fillQuestionAnswers(questions);
            } else {
                nextButton.setText("Finish");
                JOptionPane.showMessageDialog(null, "Your Score is " + correctAnswerNumber);
                currentQuestionIndex = 0;
                if (correctAnswerNumber == 3) {
                    if (dataStructureType.equals("Linked list") && counterOfLinkedList < 5)
                        counterOfLinkedList++;
                    else if (dataStructureType.equals("Stack") && counterOfStack < 1)
                        counterOfStack++;
                    else if (dataStructureType.equals("Queue") && counterOfQueue < 1)
                        counterOfQueue++;

                    fillComboBoxAction(dataStructureType, actionBox);
                    correctAnswerNumber = 0;
                    frame.dispose();
                }
            }
        });

        answer1.addActionListener(e -> {
            String selectedAnswer = answer1.getActionCommand();
            checkAnswer(selectedAnswer);
            nextButton.setEnabled(true);
        });

        answer2.addActionListener(e -> {
            String selectedAnswer = answer2.getActionCommand();
            checkAnswer(selectedAnswer);
            nextButton.setEnabled(true);
        });

        answer3.addActionListener(e -> {
            String selectedAnswer = answer3.getActionCommand();
            checkAnswer(selectedAnswer);
            nextButton.setEnabled(true);
        });


        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout());
        southPanel.add(nextButton, BorderLayout.EAST);

        frame = new JFrame("Exam Questions");
        frame.setSize(new Dimension(400, 250));
        frame.add(panel, BorderLayout.CENTER);
        frame.add(southPanel, BorderLayout.SOUTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private void fillQuestionAnswers(ArrayList<Question> questions) {
        Question question = questions.get(currentQuestionIndex);
        questionLabel.setText(question.getQuestion());
        answer1.setText(question.getOptions().keySet().toArray()[0].toString());
        answer2.setText(question.getOptions().keySet().toArray()[1].toString());
        answer3.setText(question.getOptions().keySet().toArray()[2].toString());
        currentQuestionIndex++;
    }


    private void checkAnswer(String selectedAnswer) {
        HashMap<String, Boolean> options = bankOfQuestions.getBankQuestions().get(dataStructureType).get(actionType).get(currentQuestionIndex - 1).getOptions();
        for (HashMap.Entry<String, Boolean> option : options.entrySet()) {
            if (option.getKey().equals(selectedAnswer)) {
                if (option.getValue().equals(true)) {
                    this.correctAnswerNumber++;
                }
                break;
            }
        }
    }

    private void fillComboBoxAction(String str, JComboBox<String> comboBox) {

        if (str.equals("Linked list")) {
            comboBox.removeAllItems();
            comboBox.addItem("Insert at first");
            if (this.counterOfLinkedList == 2)
                comboBox.addItem("Insert at last");
            else if (this.counterOfLinkedList == 3 && Objects.requireNonNull(comboBox.getSelectedItem()).equals("Insert last"))
                comboBox.addItem("Insert at middle");
            else if (this.counterOfLinkedList == 4 && Objects.requireNonNull(comboBox.getSelectedItem()).equals("Insert middle"))
                comboBox.addItem("Remove first");
            else if (this.counterOfLinkedList == 5 && Objects.requireNonNull(comboBox.getSelectedItem()).equals("Remove first"))
                comboBox.addItem("Remove last");
            if (comboBox.getItemCount() < counterOfLinkedList)
                counterOfLinkedList--;
        } else if (str.equals("Stack")) {
            comboBox.removeAllItems();
            comboBox.addItem("Push");
            if (this.counterOfStack == 1)
                comboBox.addItem("Pop");
        } else {
            comboBox.removeAllItems();
            comboBox.addItem("Enqueue");
            if (this.counterOfQueue == 1)
                comboBox.addItem("Dequeue");
        }

    }

    private JLabel createLabel(String lbl) {
        JLabel label = new JLabel(lbl);
        label.setHorizontalAlignment(JLabel.LEFT);
        label.setPreferredSize(new Dimension(120, 20));
        label.setFont(font);
        label.setForeground(Color.BLACK);
        return label;
    }

    private JButton createButton(String name) {
        JButton button = new JButton(name);
        button.setFont(new Font("Serif", Font.BOLD, 14));
        button.setForeground(Color.BLACK);
        button.setPreferredSize(new Dimension(100, 20));
        button.setOpaque(true);
        button.setBackground(Color.WHITE);

        return button;
    }

}

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;
import java.awt.*;


public class UiLoader extends JFrame {
    private Drawer drawer;
    private GLCanvas glcanvas;

    private FPSAnimator animator;

    private JSlider animatorSpeed;

    private int fbsSpeed = 20;

    private final JTextField simulationSpeed = createTextField("40");
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private final JTextField noOfDisks = createTextField("3");

    private final JTextField stepUpperBound = createTextField("");

    public static JTextField stepNumber = createTextField("-");
    private JButton startButton;
    public static JButton pauseButton, stopButton, resetButton;


    public void initialization() {
        this.drawer = new Drawer();

        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        this.glcanvas = new GLCanvas(capabilities);
        this.glcanvas.addGLEventListener(this.drawer);
        this.glcanvas.setSize((int) 1100, (int) (this.screenSize.getHeight() - 100));

        final JFrame frame = new JFrame("");
        frame.getContentPane().add(glcanvas);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);
        this.animator = new FPSAnimator(glcanvas, fbsSpeed, true);
        this.drawer.setAnimator(animator);
        this.animator.start();

        /*final GLProfile profile = GLProfile.get(GLProfile.GL2);
        final GLCapabilities capabilities = new GLCapabilities(profile);
        this.glcanvas = new GLCanvas(capabilities);
        this.glcanvas.setSize((int) this.screenSize.getWidth(), (int) (this.screenSize.getHeight() - 100));
        this.glcanvas.addGLEventListener(drawer);
       // this.simulationSpeed.setEditable(false);

        final JPanel container = new JPanel();
        container.setLayout(new BorderLayout());
       // container.add(this.getMainPanel(), BorderLayout.EAST);
        container.add(glcanvas, BorderLayout.WEST);

        final JFrame frame = new JFrame("Project Two(Tower of Hanoi)");
        frame.getContentPane().add(container);
        frame.setSize(this.screenSize);
        frame.setVisible(true);
        frame.setResizable(false);*/
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JPanel getMainPanel() {
        JPanel inputsPanel = new JPanel(new GridBagLayout());
        inputsPanel.setBackground(Color.WHITE);
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 20;
        inputsPanel.add(this.createLabel("Number of Disks"), gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 40;
        gridBagConstraints.ipady = 20;
        inputsPanel.add(this.noOfDisks, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 20;
        inputsPanel.add(this.createLabel("Step upper bound"), gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 40;
        gridBagConstraints.ipady = 20;
        inputsPanel.add(this.stepUpperBound, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 20;
        inputsPanel.add(this.createLabel("Step number"), gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 40;
        gridBagConstraints.ipady = 20;
        inputsPanel.add(stepNumber, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 20;
        inputsPanel.add(this.createLabel("Simulation speed"), gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 40;
        gridBagConstraints.ipady = 20;
        inputsPanel.add(this.simulationSpeed, gridBagConstraints);

        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(280, 500));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.add(Box.createRigidArea(new Dimension(10, 300)));
        mainPanel.add(inputsPanel);

        //mainPanel.add(this.animatorSpeed);
        // mainPanel.add(this.startButton);
        //mainPanel.add(pauseButton);
        ///mainPanel.add(stopButton);
        ///mainPanel.add(resetButton);
        //mainPanel.add(resetButton);
        return mainPanel;
    }

    private static JTextField createTextField(String text) {
        JTextField textField = new JTextField(1);
        textField.setPreferredSize(new Dimension(30, 20));
        textField.setText(text);
        Font font = new Font("Serif", Font.PLAIN, 17);
        textField.setFont(font);
        textField.setHorizontalAlignment(JLabel.CENTER);
        return textField;
    }

    private JLabel createLabel(String lbl) {
        JLabel label = new JLabel(lbl + ": ");
        label.setHorizontalAlignment(JLabel.LEFT);
        label.setPreferredSize(new Dimension(120, 20));
        Font font = new Font("Serif", Font.BOLD, 16);
        label.setFont(font);
        if (Color.BLACK != null)
            label.setForeground(Color.BLACK);
        return label;
    }

    private JButton createButton(String name, Color color) {
        JButton button = new JButton(name);
        button.setFont(new Font("Serif", Font.BOLD, 16));
        button.setForeground(color);
        button.setPreferredSize(new Dimension(200, 40));
        button.setOpaque(true);
        button.setBackground(Color.WHITE);

        return button;
    }

    public static void main(String[] args) {
        Runnable runnable = () -> new UiLoader().initialization();
        EventQueue.invokeLater(runnable);
    }
}
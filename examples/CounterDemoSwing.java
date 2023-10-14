package eu.thomaskuenneth.declarative_uis;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class CounterDemoSwing extends JFrame {

    private Font font1;
    private Font font2;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CounterDemoSwing().setVisible(true));
    }

    private CounterDemoSwing() {
        super(CounterDemoSwing.class.getSimpleName());
        setContentPane(createUI());
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private JComponent createUI() {
        int[] counter = new int[] { 0 };
        var box = Box.createVerticalBox();
        box.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        var label = new JLabel();
        font1 = label.getFont().deriveFont(Font.ITALIC, 14f);
        font2 = label.getFont().deriveFont(Font.BOLD, 72f);
        var panel = new JPanel();
        panel.setAlignmentX(0.5f);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalGlue());
        panel.add(label);
        panel.add(Box.createVerticalGlue());
        box.add(panel);
        var button = new JButton("Klick");
        button.addActionListener(e -> updateUI(label, ++counter[0]));
        button.setAlignmentX(0.5f);
        box.add(button);
        updateUI(label, counter[0]);
        return box;
    }

    private void updateUI(JLabel label, int counter) {
        if (counter == 0) {
            label.setFont(font1);
            label.setText("Noch nicht geklickt");
        } else {
            label.setFont(font2);
            label.setText(Integer.toString(counter));
        }
    }
}

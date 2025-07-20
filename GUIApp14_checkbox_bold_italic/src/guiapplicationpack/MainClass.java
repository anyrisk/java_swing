package guiapplicationpack;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

class MainPanel extends JPanel
{
    private JTextArea txtText;
    private JScrollPane spnText;
    private JCheckBox chkboxBold, chkboxItalic;
    
    private JCheckBox makeCheckBox(String cap, int x, int y, int w, int h)
    {
        JCheckBox temp = new JCheckBox(cap);
        temp.setFont(new Font("Verdana", 1, 14));
        temp.setOpaque(false);
        temp.setBounds(x, y, w, h);
        temp.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                int style = 0;
                if (chkboxBold.isSelected()) style += 1;
                if (chkboxItalic.isSelected()) style += 2;
                txtText.setFont(new Font("Verdana", style, 14));
            }
        });
        add(temp);
        return(temp);
    }
    
    public MainPanel()
    {
        txtText = new JTextArea();
        txtText.setFont(new Font("Verdana", 0, 14));
        spnText = new JScrollPane(txtText);
        spnText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        spnText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        spnText.setBounds(10, 10, 460, 300);
        add(spnText);
        
        chkboxBold = makeCheckBox("Bold", 100, 330, 100, 30);
        chkboxItalic = makeCheckBox("Italic", 300, 330, 100, 30);
    }
}

class MainFrame extends JFrame
{
    private MainPanel panel;
    public MainFrame()
    {
        panel = new MainPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);
        add(panel);
    }
}
public class MainClass
{
    public static void main(String[] args)
    {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
        frame.setSize(500,420);
        frame.setTitle("Experimenting Check Boxes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }
}
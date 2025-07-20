package guiapplicationpack;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class MainPanel extends JPanel implements ActionListener
{
    
    private JButton btnRed,btnGreen,btnBlue;
    
    private JButton makeButton(String caption,int x,int y,int w,int h)
    {
        
        JButton temp = new JButton(caption);
        temp.setBounds(x,y,w,h);
        temp.setFont(new Font("Verdana", 1, 12));
        temp.setBackground(Color.BLACK);
        temp.setForeground(Color.WHITE);
        temp.addActionListener(this);
        super.add(temp);
        return temp;
        
    }
    public MainPanel()
    {
        btnRed = makeButton("Red",150,20,100,30);
        btnGreen = makeButton("Green",260,20,100,30);
        btnBlue = makeButton("Blue",370,20,100,30);
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object ob = e.getSource();
        if(ob == btnRed)
            setBackground(Color.red);
        else if(ob == btnGreen)
            setBackground(Color.green);
        else if(ob == btnBlue)
            setBackground(Color.BLUE);
    }
}

class MainFrame extends JFrame
{
    private MainPanel panel = null;
    public MainFrame()
    {
        panel = new MainPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);
        super.add(panel);
    }
}

public class MainClass
{
    public static void main(String[] args)
    {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,400);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Experiencing Buttons and Click Events");
        frame.setResizable(false);
    }
}
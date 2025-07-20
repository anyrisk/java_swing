package guiapplicationpack;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class PanelSize extends JPanel
{
    private ButtonGroup btngrpSize; 
    private JRadioButton rdbtnSmall, rdbtnMedium, rdbtnLarge;
    private String dataSize = "";
    
    private JRadioButton makeRadioButton(String cap,int x,int y,int w,int h)
    {
        JRadioButton temp = new JRadioButton(cap);
        temp.setBounds(x,y,w,h);
        temp.setFont(new Font("Courier New", 1, 16));
        temp.setOpaque(false);
        btngrpSize.add(temp);
        temp.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                dataSize = ((JRadioButton)e.getSource()).getText();        
            }
        });
        add(temp);
        return temp;
    }
    public PanelSize()
    {
        btngrpSize = new ButtonGroup();
        rdbtnSmall = makeRadioButton("Small", 10, 20, 100, 30);
        rdbtnMedium = makeRadioButton("Medium", 10, 40, 100, 30);
        rdbtnMedium.setSelected(true);
        dataSize = "Medium";
        rdbtnLarge = makeRadioButton("Large", 10, 60, 100, 30);
    }
    public String getDataSize()
    {
        return dataSize;
    }
}

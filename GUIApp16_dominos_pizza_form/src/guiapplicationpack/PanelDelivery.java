package guiapplicationpack;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class PanelDelivery extends JPanel
{
    private ButtonGroup btngrpDelivery; 
    private JRadioButton rdbtnEatin, rdbtnTakeout;
    private String dataDelivery;
    
    private JRadioButton makeRadioButton(String cap,int x,int y,int w,int h)
    {
        JRadioButton temp = new JRadioButton(cap);
        temp.setBounds(x,y,w,h);
        temp.setFont(new Font("Courier New", 1, 16));
        temp.setOpaque(false);
        btngrpDelivery.add(temp);
        temp.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                dataDelivery = ((JRadioButton)e.getSource()).getText();   
            }
        });
        add(temp);
        return temp;
    }
    public PanelDelivery()
    {
        btngrpDelivery = new ButtonGroup();
        rdbtnEatin = makeRadioButton("Eat in", 10, 20, 100, 30);
        rdbtnTakeout = makeRadioButton("Take out", 10, 40, 150, 30);
        dataDelivery = "Eat in";
        rdbtnEatin.setSelected(true);   
    }
    public String getDataDelivery()
    {
        return dataDelivery;
    }
}


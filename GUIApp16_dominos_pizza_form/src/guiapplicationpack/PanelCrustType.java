package guiapplicationpack;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class PanelCrustType extends JPanel
{
    private ButtonGroup  btngrpType;
    private JRadioButton rdbtnThin,rdbtnThick;
    private String dataType = "";
    
    private JRadioButton makeRadioButton(String cap,int x,int y,int w,int h)
    {
        JRadioButton temp = new JRadioButton(cap);
        temp.setBounds(x,y,w,h);
        temp.setFont(new Font("Courier New", 1, 16));
        temp.setOpaque(false);
        btngrpType.add(temp);
        temp.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                dataType = ((JRadioButton)e.getSource()).getText();
            }
        });
        add(temp);
        return temp;
    }
    public PanelCrustType()
    {
        btngrpType  = new ButtonGroup();
        rdbtnThin   = makeRadioButton("Thin Crust",10,20,150,30);
        rdbtnThin.setSelected(true);
        dataType = "Thin Crust";
        rdbtnThick  = makeRadioButton("Thick Crust",10,40,150,30);
    }
    
    public String getDataType()
    {
        return dataType;
    }
}

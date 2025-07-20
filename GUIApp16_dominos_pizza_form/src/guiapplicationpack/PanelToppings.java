package guiapplicationpack;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class PanelToppings extends JPanel
{
    private final int LENGTH = 6;
    private JCheckBox[] chkboxTop = new JCheckBox[LENGTH];
    private String[] topName = {"Extra Cheese","Black Olives","Green Pepper","Mushroom","Onion","Tomato"};
    private boolean[] topping = new boolean[LENGTH];
    private JCheckBox makeCheckBox(String cap, int x, int y, int w, int h)
    {
        JCheckBox temp = new JCheckBox(cap);
        temp.setFont(new Font("Courier New", 1, 16));
        temp.setOpaque(false);
        temp.setBounds(x, y, w, h);
        temp.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                JCheckBox ob = (JCheckBox)e.getSource();
                int index = (int)ob.getClientProperty("index");
                topping[index] = true;
            }
        });
        add(temp);
        return(temp);
    }
    public PanelToppings()
    {
//        chkboxTop[0] = makeCheckBox("Extra Cheese", 10, 20, 150, 30);
//        chkboxTop[1] = makeCheckBox("Black Olives", 10, 40, 150, 30);
//        chkboxTop[2] = makeCheckBox("Green Pepper", 10, 60, 150, 30);
//        chkboxTop[3] = makeCheckBox("Mushroom",     160, 20, 150, 30);
//        chkboxTop[4] = makeCheckBox("Onion",        160, 40, 150, 30);
//        chkboxTop[5] = makeCheckBox("Tomato",       160, 60, 150, 30);
        

        int idx, xPos, yPos;
        xPos = 10;
        yPos = 20;
        
        for(idx = 0; idx < LENGTH; idx++, yPos += 20)
        {
            chkboxTop[idx] = makeCheckBox(topName[idx], xPos, yPos, 150, 30);
            chkboxTop[idx].putClientProperty("index", idx);
            topping[idx] = false;
            if(idx == 2)
            {
                xPos = 180;
                yPos = 0;
            }
        }
    }
    public boolean[] getTopping()
    {
        return topping;
    }
}

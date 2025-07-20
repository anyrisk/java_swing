package guiapplicationpack;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;


class MainFrame extends JFrame
{
    private JTextField      txtName,txtPhone;
    private PanelSize       pnlSize;
    private PanelCrustType  pnlType;
    private PanelToppings   pnlTop;
    private PanelDelivery   pnlDeli;
    private JButton         btnSubmit, btnCommit, btnShow, btnExit, btnShowLess;
    private JTextArea       txtReport;
    private JScrollPane     spnReport;
    private ArrayList<Customer> custRec=new ArrayList<>();
    
    private JLabel makeLabel(String cap,int x,int y,int w,int h,int mode)
    {
        JLabel temp = new JLabel(cap);
        if(mode == 1)
        {
            temp.setFont(new Font("Courier New", 1, 30));
            temp.setOpaque(true);
            temp.setBackground(Color.RED);
            temp.setForeground(Color.WHITE);
            temp.setHorizontalAlignment(JLabel.CENTER);
            temp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));            
        }
        else
            temp.setFont(new Font("Courier New", 1, 16));
        temp.setBounds(x,y,w,h);
        super.add(temp);
        return temp;
    }
    private JTextField makeTextField(int x,int y,int w,int h)
    {
        JTextField temp = null;
        temp = new JTextField();
        temp.setFont(new Font("Courier New",1,16));
        temp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        temp.setBounds(x,y,w,h);
        temp.setHorizontalAlignment(JTextField.CENTER);
        add(temp);
        return temp;
    }
    
    private JPanel makePanel(String cap, int x, int y, int w, int h, int mode)
    {
        JPanel temp = null;
        if (mode == 1)
            temp = new PanelSize();
        else if (mode == 2)
            temp = new PanelCrustType();
        else if (mode == 3)
            temp = new PanelToppings();
        else if (mode == 4)                
            temp = new PanelDelivery();
        
        temp.setLayout(new BorderLayout());
        temp.setBounds(x, y, w, h);
        Border bdr1 = BorderFactory.createLineBorder(Color.BLUE, 2);
        Border bdr2 = BorderFactory.createTitledBorder(bdr1, cap, TitledBorder.LEFT,TitledBorder.TOP, new Font("Verdana", 1, 16));
        temp.setBorder(bdr2);
        temp.setBackground(Color.ORANGE);
        add(temp);
        return temp;
    }
    
    private JButton makeButton(String caption,int x,int y,int w,int h)
    {
        JButton temp = new JButton(caption);
        temp.setBounds(x,y,w,h);
        temp.setFont(new Font("Courier New", 1, 12));
        temp.setMargin(new Insets(0,0,0,0));
        temp.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                Object ob = e.getSource();
                if(ob == btnSubmit)
                {
                    String name = txtName.getText();
                    String phone = txtPhone.getText();
                    String size = pnlSize.getDataSize();
                    String type = pnlType.getDataType();
                    String deli = pnlDeli.getDataDelivery();
                    boolean[] topping = pnlTop.getTopping();
                    
                    Customer cust = new Customer(name, phone, size, type, deli, topping);
                    custRec.add(cust);
                }
                else if(ob == btnCommit)
                {
                    try
                     {
                         File path = new File("Order.csv");
                         FileWriter writer = new FileWriter(path,false);
                         for (Customer temp:custRec)
                         {
                             String rec = temp.getName()+","+temp.getPhone()+","+temp.getCrust()+","+temp.getSize()+","+temp.getDelivery()+","+temp.getToppings()[0]+","+temp.getToppings()[1]+","+temp.getToppings()[2]+","+temp.getToppings()[3]+","+temp.getToppings()[4]+","+temp.getToppings()[5]+"\n";
                             writer.write(rec);
                         }
                         writer.close();
                         JOptionPane.showMessageDialog(null, "Record committed successfully.");
                     }
                     catch(IOException ex){}
                     catch(Exception ex){}
                }
                else if(ob == btnShow)
                {
                    setSize(650, 550);
                    setLocationRelativeTo(null);
                    
                    StringBuilder sb = new StringBuilder();
                    sb.append(String.format("%-24s|%-10s|%-6s|%-11s|%-8s|%-12s|%-12s|%-12s|%-8s|%-6s|%-6s\n","Customer Name", "Phone No.", "Size", "Crust Type", "Delivery","Extra Cheese", "Black Olives", "Green Pepper", "Mushroom", "Onion", "Tomato"));
                    sb.append("------------------------+----------+------+-----------+--------+------------+------------+------------+--------+------+------\n");
                    for (Customer cust : custRec) {
                    boolean[] tops = cust.getToppings();
                    sb.append(String.format("%-24s|%-10s|%-6s|%-11s|%-8s|%-12s|%-12s|%-12s|%-8s|%-6s|%-6s\n",
                    cust.getName(), cust.getPhone(), cust.getSize(), cust.getCrust(), cust.getDelivery(),tops[0], tops[1], tops[2], tops[3], tops[4], tops[5]));
                    }
                    txtReport.setText(sb.toString());
                }
                
                else if(ob == btnExit)
                {
                    System.exit(0);
                }
                else if(ob == btnShowLess)
                {
                    setSize(650, 390);
                    setLocationRelativeTo(null);
                }
            }
        });
        super.add(temp);
        return temp;
    }
    private void init()
    {
        try
        {
            File path = new File("Order.csv");
            Scanner sc = new Scanner(path);
            while(sc.hasNext())
            {
                String rec = sc.nextLine();
                String[] recarr = rec.split(",");
                boolean[] tops=new boolean[6];
                for(int i=0,j=5;i<=5;i++,j++){
                    tops[i]=Boolean.valueOf(recarr[j]);
                   
                }
                Customer temp = new Customer(recarr[0],recarr[1],recarr[2],recarr[3],recarr[4],tops);
                custRec.add(temp);
            }
            sc.close();
        }
        catch(Exception ex){}
    }

    
    public MainFrame()
    {
        makeLabel("Domino's Pizza",10,10,610,50,1);
        makeLabel("Customer Name", 10,70,150,30,2);
        txtName = makeTextField(150,70,200,30);
        makeLabel("Phone", 360,70,80,30,2);
        txtPhone = makeTextField(420,70,200,30);
        
        pnlSize = (PanelSize)makePanel("Size", 10, 110, 250, 100, 1);
        pnlType = (PanelCrustType)makePanel("Crust Type", 10, 220, 250, 80, 2);
        pnlTop = (PanelToppings)makePanel("Toppings", 270, 110, 350, 100, 3);
        pnlDeli = (PanelDelivery)makePanel("Mode of Delivery", 270, 220, 350, 80, 4);
        
        btnSubmit = makeButton("Submit", 50, 310, 100, 30);
        btnCommit = makeButton("Commit", 200, 310, 100, 30);
        btnShow = makeButton("Show>>", 350, 310, 100, 30);
        btnExit = makeButton("Exit", 500, 310, 100, 30);
        btnShowLess = makeButton("Show Less", 250, 470, 150, 30);
        
        txtReport = new JTextArea();
        txtReport.setEditable(false);
        txtReport.setFont(new Font("Courier New", 1, 12));
        spnReport = new JScrollPane(txtReport);
        spnReport.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        spnReport.setBounds(10, 360, 610, 100);
        add(spnReport);
                
        
        
        makeLabel("",0,0,0,0,2);
    }
}
public class MainClass
{
    public static void main(String[] args)
    {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());
        frame.setSize(650,390); //390 & 550
        frame.setTitle("Pizza Order Collection Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.YELLOW);
        frame.setLocationRelativeTo(null);
    }
}

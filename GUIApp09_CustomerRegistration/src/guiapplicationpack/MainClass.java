package guiapplicationpack;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class Customer
{
    
    private String name;
    private String address;
    private String password;
    
    public Customer()
    {
        name = "";
        address = "";
        password = "";
    }
    
    public Customer(String n,String a,String p)
    {
        name = n;
        address = a;
        password = p;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getAddress()
    {
        return address;
    }
    
    public String getPassword()
    {
        return password;
    }
}

class MainPanel extends JPanel
{
    private JLabel          lblName,lblAddress,lblPWD;
    private JTextField      txtName;
    private JTextArea       txtAddress,txtReport;
    private JScrollPane     spnAddress,spnReport;
    private JPasswordField  txtPWD;
    private JButton         btnUnmask,btnSubmit,btnCommit,btnShow,btnExit,btnBack;
    private Vector<Customer> custRec = new Vector<Customer>();
    
    private JLabel makeLabel(String cap,int x,int y,int w,int h)
    {
        JLabel temp = new JLabel(cap);
        temp.setBounds(x,y,w,h);
        temp.setFont(new Font("Courier New", 1, 18));
        super.add(temp);
        return temp;
    }
    
    private JScrollPane makeScrollPane(int x,int y,int w,int h,JTextArea txtArea)
    {
        JScrollPane temp = new JScrollPane(txtArea);
        temp.setBounds(x,y,w,h);
        temp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        temp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        temp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(temp);
        return temp;
    }
    private JComponent makeTextBox(int x,int y,int w,int h,int mode)
    {
        JComponent temp = null;
        if(mode == 1)
            temp = new JTextField();
        else if(mode == 2)
            temp = new JPasswordField();
        temp.setFont(new Font("Courier New",1,16));
        temp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        temp.setBounds(x,y,w,h);
        add(temp);
        return temp;
    }
    private JButton makeButton(String caption,int x,int y,int w,int h)
    {
        JButton temp = new JButton(caption);
        temp.setBounds(x,y,w,h);
        temp.setFont(new Font("Verdana", 1, 12));
        temp.setMargin(new Insets(0,0,0,0));
        temp.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                Object ob = e.getSource();
                if(ob == btnSubmit)
                {
                    Customer temp = new Customer(txtName.getText(),txtAddress.getText().replaceAll("\n"," "),txtPWD.getText());
                    custRec.add(temp);
                    txtName.setText("");
                    txtAddress.setText("");
                    txtPWD.setText("");
                    txtName.grabFocus();
                }
                else if(ob == btnCommit)
                {
                    try
                    {
                        File dataFile = new File("Customer.csv");
                        FileWriter fWrite = new FileWriter(dataFile,false);
                        for (Customer cust:custRec)
                        {
                            String rec = cust.getName()+","+cust.getAddress()+","+cust.getPassword()+"\n";
                            fWrite.write(rec);
                        }
                        JOptionPane.showMessageDialog(null, "Record committed successfully.");
                        fWrite.close();
                    }
                    catch(IOException ex){}
                    catch(Exception ex){}
                }
                else if(ob == btnShow)
                {
                    txtReport.setText("");
                    int slno = 1;
                    for(Customer temp:custRec)
                    {
                        String name = temp.getName();
                        String address = temp.getAddress();
                        String pwd = temp.getPassword();
                        String rec = String.format("%03d) %-20s|%-50s|%s\n",slno,name,address,pwd);
                        txtReport.append(rec);
                        slno++;
                    }
                    setVisibleInvisible();
                }
                else if(ob == btnBack)
                {
                    setVisibleInvisible();
                }
                else if(ob == btnExit)
                {
                    System.exit(0);
                }
            }
        });
        super.add(temp);
        return temp;
    }
    private void setVisibleInvisible()
    {
        lblName.setVisible(!lblName.isVisible());
        lblAddress.setVisible(!lblAddress.isVisible());
        lblPWD.setVisible(!lblPWD.isVisible());
        txtName.setVisible(!txtName.isVisible());
        spnAddress.setVisible(!spnAddress.isVisible());
        spnReport.setVisible(!spnReport.isVisible());
        txtPWD.setVisible(!txtPWD.isVisible());
        btnUnmask.setVisible(!btnUnmask.isVisible());
        btnSubmit.setVisible(!btnSubmit.isVisible());
        btnCommit.setVisible(!btnCommit.isVisible());
        btnShow.setVisible(!btnShow.isVisible());
        btnBack.setVisible(!btnBack.isVisible());
        btnExit.setVisible(!btnExit.isVisible());
    }
    private  void init()
    {
        try
        {
            File dataFile = new File("Customer.csv");
            Scanner sc = new Scanner(dataFile);
            while(sc.hasNext())
            {
                String recstr = sc.nextLine();
                String[] recarr = recstr.split(",");
                Customer temp = new Customer(recarr[0], recarr[1], recarr[2]);
                custRec.add(temp);
            }
            sc.close();
        }
        catch(IOException ex){}
        catch(Exception ex){}
    }
    public MainPanel()
    {
        txtReport = new JTextArea();
        txtReport.setFont(new Font("Courier New",1,16));
        txtReport.setEditable(false);
        spnReport = makeScrollPane(10,10,470,200,txtReport);
        spnReport.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        spnReport.setVisible(false);
        
        btnBack = makeButton("Back",200,220,100,30);
        btnBack.setVisible(false);
        
        lblName = makeLabel("Customer Name",10,10,250,30);
        txtName = (JTextField)makeTextBox(220,10,250,30,1);
        txtName.setHorizontalAlignment(JTextField.CENTER);
        
        
        lblAddress = makeLabel("Enter Address",10,50,250,30);
        txtAddress = new JTextArea();
        txtAddress.setFont(new Font("Courier New",1,16));
        txtAddress.setLineWrap(true);
        spnAddress = makeScrollPane(220,50,250,100,txtAddress);
        
        lblPWD = makeLabel("Enter Password",10,160,250,30);
        txtPWD = (JPasswordField)makeTextBox(220,160,200,30,2);
        txtPWD.setHorizontalAlignment(JPasswordField.CENTER);
        txtPWD.setEchoChar('*');
        
        btnUnmask = makeButton("",425,160,45,30);
        btnUnmask.setIcon(new ImageIcon("eye.png"));
        btnUnmask.addMouseListener(new MouseListener() 
        {
            @Override
            public void mouseClicked(MouseEvent e){}
            @Override
            public void mousePressed(MouseEvent e)
            {
                txtPWD.setEchoChar('\0');
            }
            @Override
            public void mouseReleased(MouseEvent e)
            {
                txtPWD.setEchoChar('*');
            }
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        btnSubmit = makeButton("Submit",36,220,80,30);
        btnCommit = makeButton("Commit",152,220,80,30);
        btnShow = makeButton("Show",268,220,80,30);
        btnExit = makeButton("Exit",384,220,80,30);
        init();
    }
}
class MainFrame extends JFrame
{
    private MainPanel panel = null;
    public MainFrame()
    {
        panel = new MainPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.PINK);
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
        frame.setSize(500,300);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Customer Entry Form");
        frame.setResizable(false);
    }
}

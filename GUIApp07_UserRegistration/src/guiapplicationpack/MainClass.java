package guiapplicationpack;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

class User
{
    private int     uid;
    private String  uname;
    private User    next;
    
    public User()
    {
        uid = 0;
        uname = "";
        next = null;
    }
    
    public User(int uid,String uname)
    {
        this.uid = uid;
        this.uname = uname;
        next = null;
    }
    
    public User(User obj)
    {
        this.uid = obj.uid;
        this.uname = obj.uname;
        next = null;
    }
    
    public int getUID()
    {
        return uid;
    }
    
    public String getUName()
    {
        return uname;
    }
    
    public User getNext()
    {
        return next;
    }
    
    public void setUID(int uid)
    {
        this.uid = uid;
    }
    
    public void setUName(String uname)
    {
        this.uname = uname;
    }
    
    public void setNext(User next)
    {
        this.next = next;
    }
}

class MainPanel extends JPanel implements ActionListener
{
    private JTextField  txtUID,txtUName;
    private JButton     btnSubmit,btnCommit,btnShow,btnExit;
    private User        start,end;
    
    private JLabel makeLabel(String cap,int x,int y,int w,int h)
    {
        JLabel temp = new JLabel(cap);
        temp.setBounds(x,y,w,h);
        temp.setFont(new Font("Courier New", 1, 18));
        super.add(temp);
        return temp;
    }
    private JTextField makeTextField(int x,int y,int w,int h)
    {
        JTextField temp = new JTextField();
        temp.setFont(new Font("Courier New",1,16));
        temp.setHorizontalAlignment(JTextField.LEFT);
        temp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        temp.setBounds(x, y, w, h);
        super.add(temp);
        return temp;
    }
    private JButton makeButton(String caption,int x,int y,int w,int h)
    {
        JButton temp = new JButton(caption);
        temp.setBounds(x,y,w,h);
        temp.setFont(new Font("Verdana", 1, 12));
        temp.addActionListener(this);
        super.add(temp);
        return temp;
    }
    private void init()
    {
        try
        {
            File path = new File("user.csv");
            Scanner sc = new Scanner(path);
            while(sc.hasNext())
            {
                String recstr = sc.nextLine();
                String[] recarr = recstr.split(",");
                User temp = new User(Integer.parseInt(recarr[0]),recarr[1]);
                if(start == null)
                    start = end = temp;
                else
                {
                    end.setNext(temp);
                    end = temp;
                }
            }
        }
        catch(Exception ex){}
    }
    public MainPanel()
    {
        makeLabel("Enter User ID",10,10,200,30);
        txtUID = makeTextField(210,10,270,30);
        makeLabel("Enter User Name",10,60,200,30);
        txtUName = makeTextField(210,60,270,30);
        
        btnSubmit = makeButton("Submit",20,110,100,30);
        btnCommit = makeButton("Commit",140,110,100,30);
        btnShow = makeButton("Show",260,110,100,30);
        btnExit = makeButton("Exit",380,110,100,30);
        
        start = end = null;
        
        init();
        
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object ob = e.getSource();
        if(ob == btnSubmit)
        {
            User temp = new User(Integer.parseInt(txtUID.getText()),txtUName.getText());
            if(start == null)
                start = end = temp;
            else
            {
                end.setNext(temp);
                end = temp;
            }
            JOptionPane.showMessageDialog(null, "User Registered Successfully");
            txtUID.setText("");
            txtUName.setText("");
            txtUID.grabFocus();
        }
        else if(ob == btnCommit)
        {
            try
            {
                User ptr = null;
                File path = new File("user.csv");
                FileWriter fwriter = new FileWriter(path,false);
                for(ptr = start;ptr != null;ptr = ptr.getNext())
                {
                    String rec = ptr.getUID()+","+ptr.getUName()+"\n";
                    fwriter.write(rec);
                }
                fwriter.close();
                JOptionPane.showMessageDialog(null, "File Committed Successfully");
                
            }
            catch(IOException ex){}
        }
        else if(ob == btnShow)
        {
            int     sno = 1;
            User    ptr;
            if(start == null)
                JOptionPane.showMessageDialog(null, "No User Registered Yet");
            else
            {
                System.out.println("---+------+--------------------");
                System.out.println("SNO|USERID|USER NAME           ");
                System.out.println("---+------+--------------------");
                for(ptr = start; ptr != null; ptr = ptr.getNext(),sno++) 
                    System.out.printf("%03d|%6d|%-20s\n",sno,ptr.getUID(),ptr.getUName());
                System.out.println("---+------+--------------------");
            }
        }
        else if(ob == btnExit)
        {
            System.exit(0);
        }
    }
}

class MainFrame extends JFrame
{
    private MainPanel panel = null;
    public MainFrame()
    {
        panel = new MainPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.LIGHT_GRAY);
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
        frame.setSize(520,200);
        frame.setLocationRelativeTo(null);
        frame.setTitle("New User Registration");
        frame.setResizable(false);
    }
}



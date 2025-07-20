package guiapplicationpack;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

class MainPanel extends JPanel
{
    private JTextField      txtStudentName;
    private ButtonGroup     btngrpCourse;
    private JRadioButton    rdobtnBCA,rdobtnBTech,rdobtnMCA,rdobtnMTech;
    private String[] bca = {"CLanguage","Data Structure","DBMS","J2SE"};
    private String[] btech = {"Object Design using UML","Numerical","Artificial Intelligence","J2EE"};
    private String[] mca = {"Data Structure","Object Design Technique","DBMS","Artificial Intelligence"};
    private String[] mtech = {"VLSI","Advance DBMS","Advance Networking","TCP/IP Programming"};
    private JComboBox       combxSubject;
    private Vector<String>  studentList = new Vector<String>();
    private JList           lstStudentList;
    private JScrollPane     spnStudentList;
    private JButton         btnRegistration,btnDelete,btnClear,btnExit;
    private String course = "BCA";
    
    private JLabel makeLabel(String cap,int x,int y,int w,int h)
    {
        JLabel temp = new JLabel(cap);
        temp.setFont(new Font("Courier New", 1, 18));
        temp.setBounds(x,y,w,h);
        super.add(temp);
        return temp;
    }
    private JTextField makeTextField(int x,int y,int w,int h)
    {
        JTextField temp = new JTextField();
        temp.setFont(new Font("Courier New",1,16));
        temp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        temp.setBounds(x,y,w,h);
        temp.setHorizontalAlignment(JTextField.CENTER);
        temp.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(txtStudentName.getText().length() == 20) e.setKeyChar('\0');
            }

            @Override
            public void keyPressed(KeyEvent e) {
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }
        });
        add(temp);
        return temp;
    }
    private JComboBox makeComboBox(int x,int y,int w,int h,String[] items)
    {
        JComboBox temp = new JComboBox(items);
        temp.setFont(new Font("Verdana", 1, 12));
        temp.setBounds(x,y,w,h);
        add(temp);
        return temp;
    }
    private JScrollPane makeScrollPane(int x,int y,int w,int h,JList list)
    {
        JScrollPane temp = new JScrollPane(list);
        temp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        temp.setBounds(x,y,w,h);
        add(temp);
        return temp;
    }
    private JRadioButton makeRadioButton(String cap,int x,int y,int w,int h)
    {
        JRadioButton temp = new JRadioButton(cap);
        temp.setBounds(x,y,w,h);
        temp.setFont(new Font("Courier New", 1, 16));
        temp.setOpaque(false);
        btngrpCourse.add(temp);
        temp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JRadioButton ob = (JRadioButton)e.getSource();
                course = ob.getText();
                combxSubject.removeAllItems();
                if (ob.equals(rdobtnBCA))
                    for(String tmp: bca)    combxSubject.addItem(tmp);
                else if(ob.equals(rdobtnBTech))
                    for(String tmp: btech)    combxSubject.addItem(tmp);
                else if(ob.equals(rdobtnMCA))
                    for(String tmp: mca)    combxSubject.addItem(tmp);
                else if(ob.equals(rdobtnMTech))
                    for(String tmp: mtech)    combxSubject.addItem(tmp);
            }
        });
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
                if(ob == btnRegistration)
                {
                    String rec = String.format("%-20s|%-5s|%s", txtStudentName.getText(), course, combxSubject.getSelectedItem());
                    studentList.add(rec);
                    lstStudentList.setListData(studentList);
                    rdobtnBCA.setSelected(true);
                    combxSubject.removeAllItems();
                    for(String tmp: bca)    combxSubject.addItem(tmp);
                    txtStudentName.selectAll();
                    txtStudentName.grabFocus();
                    
                }
                else if(ob == btnDelete)
                {
                    studentList.remove(lstStudentList.getSelectedIndex());
                    lstStudentList.setListData(studentList);
                }
                else if(ob == btnClear)
                {
                    int confirm = JOptionPane.showConfirmDialog(null, "Want to delete all?", "Confirm", JOptionPane.YES_NO_OPTION);
                    if(confirm == JOptionPane.YES_OPTION)
                    {
                        studentList.clear();
                        lstStudentList.setListData(studentList);
                    }
                }
                else if(ob == btnExit)   System.exit(0);
            }
        });
        super.add(temp);
        return temp;
    }
    public MainPanel()
    {
        makeLabel("Student Name", 10, 10, 150, 30);
        txtStudentName = makeTextField(200, 10, 320, 30);
        makeLabel("Course", 10, 50, 100, 30);
        btngrpCourse = new ButtonGroup();
        rdobtnBCA = makeRadioButton("BCA", 150, 50, 100, 30);
        rdobtnBCA.setSelected(true);
        rdobtnBTech = makeRadioButton("BTech", 250, 50, 100, 30);
        rdobtnMCA = makeRadioButton("MCA", 350, 50, 100, 30);
        rdobtnMTech = makeRadioButton("MTech", 450, 50, 100, 30);
        makeLabel("Subject", 10, 90, 100, 30);
        combxSubject = makeComboBox(200, 90, 320, 30, bca);
        makeLabel("Status", 10, 130, 100, 30);
        lstStudentList = new JList();
        lstStudentList.setFont(new Font("Courier New", 1, 16));
        spnStudentList = makeScrollPane(10, 170, 510, 180, lstStudentList);
        btnRegistration = makeButton("Register", 20, 360, 100, 30);
        btnDelete = makeButton("Delete", 152, 360, 100, 30);
        btnClear = makeButton("Clear", 284, 360, 100, 30);
        btnExit = makeButton("Exit", 416, 360, 100, 30);
        
    }
}

class MainFrame extends JFrame
{
    private MainPanel panel = null;
    public MainFrame()
    {
        panel = new MainPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(215,240,160));
        super.add(panel);
    }
}
public class MainClass
{
    public static void main(String[] args)
    {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
        frame.setSize(560,450);
        frame.setTitle("Student Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }
}
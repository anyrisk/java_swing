package guiapplicationpack;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

class Employee
{
    private int     code;
    private String  name;
    private int     sal;
    private String  dept;
    
    public Employee()
    {
        code = 0;
        name = "";
        sal  = 0;
        dept = "";
    }
    
    public Employee(int c,String n,int s,String d)
    {
        code = c;
        name = n;
        sal  = s;
        dept = d;
    }
    
    public int getCode()
    {
        return code;
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getSal()
    {
        return sal;
    }
    
    public String getDept()
    {
        return dept;
    }
}

class MainPanel extends JPanel
{
    private JLabel          lblCode,lblName,lblSal,lblDept;
    private JTextField      txtCode,txtName,txtSal,txtDept;
    private JTextArea       txtReport;
    private JScrollPane     spnReport;
    private JButton         btnSubmit,btnCommit,btnShow,btnExit,btnBack;
    private ArrayList<Employee> empList = new ArrayList<Employee>();
    private File            file = new File("Employee.xml");
    
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
    private JTextField makeTextBox(int x,int y,int w,int h)
    {
        JTextField temp = null;
        temp = new JTextField();
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
                    Employee temp = new Employee(Integer.parseInt(txtCode.getText()),txtName.getText(),Integer.parseInt(txtSal.getText()),txtDept.getText());
                    empList.add(temp);
                    txtCode.setText("");
                    txtName.setText("");
                    txtSal.setText("");
                    txtDept.setText("");
                    txtCode.grabFocus();
                }
                else if(ob == btnCommit)
                {
                   try
                   {
                       DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                       DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                       Document doc = dBuilder.newDocument();
                       Element root = doc.createElement("employee");
                       doc.appendChild(root);
                       for(Employee temp: empList)
                       {
                           Element emp = doc.createElement("emp");
                           root.appendChild(emp);
                           
                           Element code = doc.createElement("code");
                           code.appendChild(doc.createTextNode(String.valueOf(temp.getCode())));
                           emp.appendChild(code);
                           
                           Element name = doc.createElement("name");
                           name.appendChild(doc.createTextNode(temp.getName()));
                           emp.appendChild(name);
                           
                           Element sal = doc.createElement("sal");
                           sal.appendChild(doc.createTextNode(String.valueOf(temp.getSal())));
                           emp.appendChild(sal);
                           
                           Element dept = doc.createElement("dept");
                           dept.appendChild(doc.createTextNode(temp.getDept()));
                           emp.appendChild(dept);
                       }
                       
                       TransformerFactory transformerFactory = TransformerFactory.newInstance();
                       Transformer transformer = transformerFactory.newTransformer();
                       DOMSource source = new DOMSource(doc);
                       StreamResult result = new StreamResult(file);
                       transformer.transform(source, result);
                       JOptionPane.showMessageDialog(null, "Employee Records created successfully");
                   }
                   catch(Exception ex)
                   {
                       JOptionPane.showMessageDialog(null, ex);
                   }
                }
                else if(ob == btnShow)
                {
                    txtReport.setText("");
                    for(Employee temp:empList)
                    {
                        int     code = temp.getCode();
                        String  name = temp.getName();
                        int     sal  = temp.getSal();
                        String  dept = temp.getDept();
                        String  rec = String.format("%4d|%-20s|%7d|%s\n",code,name,sal,dept);
                        txtReport.append(rec);
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
        lblCode.setVisible(!lblCode.isVisible());
        lblName.setVisible(!lblName.isVisible());
        lblSal.setVisible(!lblSal.isVisible());
        lblDept.setVisible(!lblDept.isVisible());
        txtCode.setVisible(!txtCode.isVisible());
        txtName.setVisible(!txtName.isVisible());
        txtSal.setVisible(!txtSal.isVisible());
        txtDept.setVisible(!txtDept.isVisible());
        spnReport.setVisible(!spnReport.isVisible());
        btnSubmit.setVisible(!btnSubmit.isVisible());
        btnCommit.setVisible(!btnCommit.isVisible());
        btnShow.setVisible(!btnShow.isVisible());
        btnExit.setVisible(!btnExit.isVisible());
        btnBack.setVisible(!btnBack.isVisible());
    }
    private void init()
    {
    }
    public MainPanel()
    {
        txtReport = new JTextArea();
        txtReport.setFont(new Font("Courier New",1,16));
        txtReport.setEditable(false);
        spnReport = makeScrollPane(10,10,520,170,txtReport);
        spnReport.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        spnReport.setVisible(false);
        
        btnBack = makeButton("Back",200,185,100,30);
        btnBack.setVisible(false);
        
        lblCode = makeLabel("Enter Employee Code",10,10,250,30);
        txtCode = makeTextBox(260,10,265,30);
        txtCode.setHorizontalAlignment(JTextField.CENTER);
        
        lblName = makeLabel("Enter Employee Name",10,50,250,30);
        txtName = makeTextBox(260,50,265,30);
        txtName.setHorizontalAlignment(JTextField.CENTER);
        
        lblSal = makeLabel("Enter Basic Salary",10,90,250,30);
        txtSal = makeTextBox(260,90,265,30);
        txtSal.setHorizontalAlignment(JTextField.CENTER);
        
        lblDept = makeLabel("Enter Department Name",10,130,250,30);
        txtDept = makeTextBox(260,130,265,30);
        txtDept.setHorizontalAlignment(JTextField.CENTER);
      
        btnSubmit = makeButton("Submit",10,185,100,30);
        btnCommit = makeButton("Commit",148,185,100,30);
        btnShow = makeButton("Show",287,185,100,30);
        btnExit = makeButton("Exit",427,185,100,30);
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
        frame.setSize(550, 270);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Employee Registration Form");
        frame.setResizable(false);
    }
}
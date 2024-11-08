import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame {

    Login(){
        setTitle("Automated Tailor Machine");
        setLayout(null);

        ImageIcon image = new ImageIcon("E:\\Advanced Java Programs\\ATM Simulator\\src\\icons\\logo.jpg");
        Image resize = image.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
        ImageIcon resizedimage = new ImageIcon(resize);

        JLabel label = new JLabel(resizedimage);
        label.setBounds(70,10,100,100);
        add(label);

        JLabel title = new JLabel("Welcome To ATM");
        title.setFont(new Font("Osward", Font.BOLD,38));
        title.setBounds(200,40,400,40);
        add(title);

        JLabel cardno = new JLabel("Card No.:");
        cardno.setFont(new Font("Raleway", Font.BOLD,28));
        cardno.setBounds(120,150,200,30);
        add(cardno);

        JTextField cardnoText = new JTextField();
        cardnoText.setFont(new Font("Arial",Font.PLAIN,16));
        cardnoText.setBounds(350,150,220,30);
        add(cardnoText);

        JLabel pin = new JLabel("Pin:");
        pin.setFont(new Font("Raleway", Font.BOLD,28));
        pin.setBounds(120,200,200,30);
        add(pin);

        JPasswordField pinText = new JPasswordField();
        pinText.setFont(new Font("Arial", Font.PLAIN, 14));
        pinText.setBounds(350,200,220,30);
        add(pinText);

        JButton signIn = new JButton("SIGN IN");
        signIn.setBackground(Color.BLACK);
        signIn.setForeground(Color.WHITE);
        signIn.setBounds(350,250,100,30);
        add(signIn);
        signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Conn con = new Conn();
                String cardnumber = cardnoText.getText();
                String pinnumber = pinText.getText();

                String query = "Select * from login where card_number = '" + cardnumber + "' and pin_number = '" + pinnumber + "'";

                try{
                   ResultSet res = con.s.executeQuery(query);

                   if(res.next()){
                       setVisible(false);
                       new Transactions(pinnumber).setVisible(true);
                   }
                   else{
                       JOptionPane.showMessageDialog(null,"Incorrect Card Number or PIN");
                   }
                }
                catch (Exception ex){
                    System.out.println(ex);
                }
            }
        });

        JButton clear = new JButton("CLEAR");
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);
        clear.setBounds(470,250,100,30);
        add(clear);
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardnoText.setText("");
                pinText.setText("");
            }
        });

        JButton signUpp = new JButton("SIGN UP");
        signUpp.setBackground(Color.BLACK);
        signUpp.setForeground(Color.WHITE);
        signUpp.setBounds(350,300,220,30);
        add(signUpp);
        signUpp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new SignUpOne().setVisible(true);
            }
        });


        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(350,200);
        setSize(800,480);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Login();
    }
}

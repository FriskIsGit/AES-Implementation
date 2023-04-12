
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class App{
    static JFrame frame = new JFrame("AES sample");

    static JTextField passField = new JTextField("password");
    static JTextField dataField = new JTextField("message");
    static JTextField outputField = new JTextField("0<length<=16");
    static JButton encryptBut = new JButton("Encrypt");
    static JButton decryptBut = new JButton("Decrypt");
    static JLabel passLabel = new JLabel("Password:");
    static JLabel stringLabel = new JLabel("String:");
    static JLabel outPutLabel = new JLabel("Output:");

    public static void main(String[] args){
        setupFrame();
    }

    private static void setupFrame(){
        frame.setLayout(new GridLayout(3, 3));
        frame.setSize(new Dimension(960, 200));
        frame.setVisible(true);

        frame.add(passLabel);
        frame.add(stringLabel);
        frame.add(outPutLabel);

        frame.add(passField);
        frame.add(dataField);
        frame.add(outputField);
        frame.add(encryptBut);
        frame.add(decryptBut);

        frame.getContentPane().setBackground(Color.gray);

        encryptBut.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                encrypt(passField.getText(), dataField.getText());
            }
        });
        decryptBut.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                decrypt(passField.getText(), dataField.getText());
            }
        });

        passField.setCaretColor(Color.white);
        passField.setBackground(Color.black);
        passField.setForeground(Color.white);
        passField.setFont(new Font("Dialog", Font.PLAIN, 25));

        dataField.setCaretColor(Color.white);
        dataField.setBackground(Color.black);
        dataField.setForeground(Color.white);
        dataField.setFont(new Font("Dialog", Font.PLAIN, 25));

        outputField.setCaretColor(Color.white);
        outputField.setBackground(Color.black);
        outputField.setForeground(Color.white);
        outputField.setEditable(false);
        System.out.println(outputField.getFont());
        outputField.setFont(new Font("Dialog", Font.PLAIN, 25));
    }

    //String: mate,..
    private static void decrypt(String pass, String data){
        if (!(pass.length() <= 16 && data.length() <= 16)) return;
        byte[] state = data.getBytes();
        System.out.println("State b4 decryption");
        Printer.formatPrintArr(state, 4, 4);
        byte[] roundKey = pass.getBytes();
        byte[] result = Decryption.decryptData(state, Decryption.keyScheduleList(roundKey));
        System.out.println("Decryption result");
        Printer.formatPrintArr(result, 4, 4);
        outputField.setText(Convert.arrToString(result));
    }

    private static void encrypt(String pass, String data){
        if (!(pass.length() <= 16 && data.length() <= 16)) return;
        byte[] state = data.getBytes();
        System.out.println("State b4 encryption");
        Printer.formatPrintArr(state, 4, 4);
        byte[] result = Encryption.encryptData(state, pass.getBytes());
        System.out.println("Encryption result");
        Printer.formatPrintArr(result, 4, 4);
        outputField.setText(Convert.arrToString(result));
    }

}

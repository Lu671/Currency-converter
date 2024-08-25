package com.mycompany.project.currConverter;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class currConverter extends JFrame {
    private JButton convert, cancel;
    private JComboBox<String> box1, box2;
    private JTextField t1;
    private JLabel l1, l2;
    private JTextArea t2;
    
    public currConverter(String t){
        super(t);
        this.setLocation(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        convert = new JButton("Convert");
        cancel = new JButton("Cancel");

        String[] currency = {"EUR", "SAR", "GBP", "JPY", "CHF", "CAD"};
        box1 = new JComboBox<>(currency);
        t1 = new JTextField(10);
        t2 = new JTextArea();

        l1 = new JLabel("US");
        l2 = new JLabel("  To:");

        JPanel p = (JPanel)this.getContentPane();
        JPanel j1 = new JPanel();
        JPanel j2 = new JPanel();
        j1.add(t1);
        j1.add(l1);
        j1.add(l2);
        j1.add(box1);
        j1.add(t2);
        p.add(j1, BorderLayout.NORTH);
        j2.add(convert);
        j2.add(cancel);
        p.add(j2, BorderLayout.CENTER);

        convert.addActionListener(new ConvertListener());
        cancel.addActionListener(new CancelListener());

        this.pack();
        this.setVisible(true);
    }

    public double calculate(double a) {
        if (box1.getSelectedItem().equals("SAR"))
            return a * 3.76;
        if (box1.getSelectedItem().equals("EUR"))
            return a * 0.95;
        if (box1.getSelectedItem().equals("GBP"))
            return a * 0.82;
        if (box1.getSelectedItem().equals("CHF"))
            return a * 0.94;
        if (box1.getSelectedItem().equals("CAD"))
            return a * 1.37;
        else // JPY
            return a * 137.72;
    }

    public class ConvertListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (t1.getText().equals(""))
                JOptionPane.showMessageDialog(null, "MISSING VALUE", "Warning", JOptionPane.WARNING_MESSAGE);
            else {
                String us = t1.getText();
                double usd = Double.parseDouble(us);
                double result = calculate(usd);
                String showResult = result + " " + box1.getSelectedItem().toString();                
                t2.setText(showResult);
            }
        }
    }

    public class CancelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int n = JOptionPane.showConfirmDialog(null, "Do You Want To Cancel?");
            if (n == 0)
                System.exit(0);
            if (n == 1) {
                t1.setText("");
                t2.setText("");
            }
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        currConverter c = new currConverter("USD Converter");
        c.setVisible(true);
    }
}

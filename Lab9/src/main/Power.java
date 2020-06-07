package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Power extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
        new Power();
	}
	
	JPanel panel;
	
	JTextField number;
	JLabel label;
	JButton button;
	
	public Power() {
		super("Kalkulator potęg");
		this.setSize(350, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();    
        this.add(panel);
        init();
        this.setVisible(true);
	}
	
	private void init() {
        number = new JTextField(20);
        number.setBounds(100,20,165,25);
        panel.add(number);
        

        button = new JButton("Oblicz");
        button.setBounds(10, 80, 80, 25);
        panel.add(button);
        
        label = new JLabel();
        label.setBounds(100,20,165,25);
        panel.add(label);
        
        button.addActionListener(this);
        
        
    }

	@Override
	public void actionPerformed(ActionEvent e) {

		try {
			int x = Integer.parseInt(number.getText());
			label.setText(new Integer(x * x).toString());
		} catch (Exception ex) {
			label.setText("Wrong number");
		}
	}

}

package calc;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class CalculatorGUI extends JFrame implements ActionListener {
	
	public static void main(String[] args) {
		CalculatorGUI frame = new CalculatorGUI();
		frame.setVisible(true);
	}				
	
	private static final long serialVersionUID = 1L;
	String equation = "";
	private JPanel contentPane;
	private JPanel buttons;
	private JLabel eqLabel;
	private JPanel labelPanel;
	
	public CalculatorGUI() {		
		setTitle("Calculator v1.0");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());

		labelPanel = new JPanel();
		labelPanel.setPreferredSize(new Dimension(400, 100));	
		
		eqLabel = new JLabel(equation);
		eqLabel.setBorder(new TitledBorder(null, "Equation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		eqLabel.setPreferredSize(new Dimension(400, 80));
		labelPanel.add(eqLabel);
		contentPane.add(labelPanel, BorderLayout.PAGE_START);
		
		buttons = new JPanel();
		buttons.setLayout(new GridLayout(6,3, 10, 10));
		contentPane.add(buttons);		
				
		JButton btn1 = new JButton("1");
		btn1.addActionListener(this);
		btn1.setPreferredSize(new Dimension(40, 40));
		buttons.add(btn1);
		
		JButton btn2 = new JButton("2");
		btn2.addActionListener(this);
		btn2.setPreferredSize(new Dimension(40, 40));
		buttons.add(btn2);
		
		JButton btn3 = new JButton("3");
		btn3.addActionListener(this);
		buttons.add(btn3);
		
		JButton btn4 = new JButton("4");
		btn4.addActionListener(this);
		buttons.add(btn4);
		
		JButton btn5 = new JButton("5");
		btn5.addActionListener(this);
		buttons.add(btn5);
		
		JButton btn6 = new JButton("6");
		btn6.addActionListener(this);
		buttons.add(btn6);
		
		JButton btn7 = new JButton("7");
		btn7.addActionListener(this);
		buttons.add(btn7);
		
		JButton btn8 = new JButton("8");
		btn8.addActionListener(this);
		buttons.add(btn8);
		
		JButton btn9 = new JButton("9");
		btn9.addActionListener(this);
		buttons.add(btn9);		
		
		JButton btnClearLast = new JButton("Back");
		btnClearLast.addActionListener(this);
		buttons.add(btnClearLast);		
		
		JButton btn0 = new JButton("0");
		btn0.addActionListener(this);
		buttons.add(btn0);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(this);
		buttons.add(btnClear);
		
		JButton btnPlus = new JButton("+");
		btnPlus.addActionListener(this);
		buttons.add(btnPlus);
		
		JButton btnMinus = new JButton("-");
		btnMinus.addActionListener(this);
		buttons.add(btnMinus);
		
		JButton btnDot = new JButton(".");
		btnDot.addActionListener(this);
		buttons.add(btnDot);
		
		JButton btnMutliply = new JButton("*");
		btnMutliply.addActionListener(this);
		buttons.add(btnMutliply);		
		
		JButton btnDivide = new JButton("/");
		btnDivide.addActionListener(this);
		buttons.add(btnDivide);		
		
		JButton btnEquals = new JButton("=");
		btnEquals.addActionListener(this);
		buttons.add(btnEquals);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String command = arg0.getActionCommand();
		if (command == "=") {
			equation = String.valueOf(eval(equation));
		}else if (command == "Clear"){
			equation = "";
		}else if (command == "Back"){
			equation = equation.substring(0, equation.length()-1);
		}else {
			equation += command;
		}		
		eqLabel.setText(equation);
	}	
		
	public static double eval(final String str) {
		return  new Object() {
	        int pos = -1, ch;

	        void nextChar() {
	            ch = (++pos < str.length()) ? str.charAt(pos) : -1;
	        }

	        boolean eat(int charToEat) {
	            while (ch == ' ') nextChar();
	            if (ch == charToEat) {
	                nextChar();
	                return true;
	            }
	            return false;
	        }

	        double parse() {
	            nextChar();
	            double x = parseExpression();
	            if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
	            return x;
	        }

	        // Grammar:
	        // expression = term | expression `+` term | expression `-` term
	        // term = factor | term `*` factor | term `/` factor
	        // factor = `+` factor | `-` factor | `(` expression `)`
	        //        | number | functionName factor | factor `^` factor

	        double parseExpression() {
	            double x = parseTerm();
	            for (;;) {
	                if      (eat('+')) x += parseTerm(); // addition
	                else if (eat('-')) x -= parseTerm(); // subtraction
	                else return x;
	            }
	        }

	        double parseTerm() {
	            double x = parseFactor();
	            for (;;) {
	                if      (eat('*')) x *= parseFactor(); // multiplication
	                else if (eat('/')) x /= parseFactor(); // division
	                else return x;
	            }
	        }

	        double parseFactor() {
	            if (eat('+')) return parseFactor(); // unary plus
	            if (eat('-')) return -parseFactor(); // unary minus

	            double x;
	            int startPos = this.pos;
	            if (eat('(')) { // parentheses
	                x = parseExpression();
	                eat(')');
	            } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
	                while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
	                x = Double.parseDouble(str.substring(startPos, this.pos));
	            } else if (ch >= 'a' && ch <= 'z') { // functions
	                while (ch >= 'a' && ch <= 'z') nextChar();
	                String func = str.substring(startPos, this.pos);
	                x = parseFactor();
	                if (func.equals("sqrt")) x = Math.sqrt(x);
	                else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
	                else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
	                else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
	                else throw new RuntimeException("Unknown function: " + func);
	            } else {
	                throw new RuntimeException("Unexpected: " + (char)ch);
	            }

	            if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

	            return x;
	        }
	    }.parse();
 	
	}
}

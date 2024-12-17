package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class CalculadoraFR extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField textFieldDisplay;
	private Color corBotao;
	private Color corFundo;
	private Color corTexto;
	private Color corLinha;
	private int x;
	private int y;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalculadoraFR frame = new CalculadoraFR();
					frame.setLocationRelativeTo(frame);
					frame.setUndecorated(true);
					frame.setVisible(true);
					frame.setOpacity((float)0.99);
					frame.setTitle("Calculadora");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CalculadoraFR() {
		
		corBotao = new Color(70,71,74);
		corFundo = new Color(53,53,53);
		corLinha = new Color(53,53,53);
		corTexto = Color.white;
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 320, 490);
		contentPane = new JPanel();
		contentPane.setBackground(corFundo);
		contentPane.setBorder(null);
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		movimentarTela();
		
		JButton btnFechar = criarBotao("x", 280, 0, 40, 30);
		btnFechar.addActionListener(this);
		contentPane.add(btnFechar);
		
		textFieldDisplay = new JTextField();
		textFieldDisplay.setBorder(null);
		textFieldDisplay.setEditable(false);
		textFieldDisplay.setFont(new Font("Tahoma", Font.PLAIN, 48));
		textFieldDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldDisplay.setText("0");
		textFieldDisplay.setForeground(Color.white);
		textFieldDisplay.setBounds(0, 32, 320, 60);
		textFieldDisplay.setBackground(corFundo);
		contentPane.add(textFieldDisplay);
		textFieldDisplay.setColumns(10);
		
		JButton btnApagar = criarBotao("C", 0, 90, 160, 80);
		btnApagar.addActionListener(this);
		contentPane.add(btnApagar);
		
		JButton btnMultiplicacao = criarBotao("*", 160, 90, 80, 80);
		btnMultiplicacao.addActionListener(this);
		contentPane.add(btnMultiplicacao);
		
		JButton btnDivisao = criarBotao("/", 240, 90, 80, 80);
		btnDivisao.addActionListener(this);
		contentPane.add(btnDivisao);
		
		JButton btnSubtracao = criarBotao("-", 240, 170, 80, 80);
		btnSubtracao.addActionListener(this);
		contentPane.add(btnSubtracao);
		
		JButton btnAdicao = criarBotao("+", 240, 250, 80, 80);
		btnAdicao.addActionListener(this);
		contentPane.add(btnAdicao);
		
		JButton btnIgual = criarBotao("=", 240, 330, 80, 160);
		btnIgual.addActionListener(this);
		contentPane.add(btnIgual);
		
		JButton btnZero = criarBotao("0", 0, 410, 160, 80);
		btnZero.addActionListener(this);
		contentPane.add(btnZero);
		
		JButton btnPonto = criarBotao(".", 160, 410, 80, 80);
		btnPonto.addActionListener(this);
		contentPane.add(btnPonto);
		
		JPanel panelBotoesNumericos = new JPanel();
		panelBotoesNumericos.setBounds(0, 170, 240, 320);
		panelBotoesNumericos.setLayout(new GridLayout(4, 3));
		panelBotoesNumericos.setBorder(null);
		panelBotoesNumericos.setBackground(corFundo);
		panelBotoesNumericos.setOpaque(true);
		
		String[] botoesNumericos = {"7", "8", "9", "4", "5", "6", "1", "2", "3"};
		
		for (String botao: botoesNumericos) {
			JButton btn = new JButton(botao);
			btn.addActionListener(this);
			btn.setHorizontalAlignment(SwingConstants.CENTER);
			btn.setFont(new Font("Tahoma", Font.PLAIN, 36));
			btn.setForeground(corTexto);
			btn.setFocusPainted(false);
			btn.setBackground(corBotao);
			btn.setBorder(new LineBorder(corLinha, 2));	
			panelBotoesNumericos.add(btn);
		}
		contentPane.add(panelBotoesNumericos);
		
}
			
	private JButton criarBotao(String textoBotao, int x, int y, int width, int height) {
		JButton JButton = new JButton(textoBotao);
		JButton.setHorizontalAlignment(SwingConstants.CENTER);
		JButton.setFont(new Font("Tahoma", Font.PLAIN, textoBotao.equalsIgnoreCase("x") ? 20 : 36));
		JButton.setForeground(corTexto);
		JButton.setFocusPainted(false);
		JButton.setBackground(corBotao);
		JButton.setBorder(new LineBorder(corLinha, 2));
		JButton.setBounds(x, y, width, height);
		
		return JButton;
	}

	private void movimentarTela() {
		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
		@Override
		public void mouseDragged(MouseEvent e) {
			setLocation(e.getXOnScreen() - x, e.getYOnScreen() - y);
		    }
		});
		contentPane.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				x = e.getX();
				y = e.getY();
			} 
		});
	}	

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if("=".equals(comando)) {
			
			String expressao = textFieldDisplay.getText();
			double resultado = verificarExpressao(expressao);
			textFieldDisplay.setText(Double.toString(resultado));
		}else if("C".equalsIgnoreCase(comando)) {
			//limpar o display
			textFieldDisplay.setText("0");
		}else if("x".equalsIgnoreCase(comando)) {
			dispose();
		}else {
			
			String textoDisplay = textFieldDisplay.getText();
			char lastChar = textoDisplay.charAt(textoDisplay.length()-1);
			
			if(isOperador(lastChar) && isOperador(comando.charAt(comando.length()-1))) {
				//trocar operador 
				textoDisplay = textoDisplay.substring(0, textoDisplay.length()-1);		
				textFieldDisplay.setText(textoDisplay+comando);
			}else {
				
				if(textFieldDisplay.getText().equalsIgnoreCase("0")) {
					textFieldDisplay.setText("");
				}
				
				textFieldDisplay.setText(textFieldDisplay.getText()+comando);
			}
		}
	}
	
	private boolean isOperador(char operador) {
		return operador == '+' || operador == '-' || operador == '*' || operador == '/';
	}
	private double verificarExpressao(String expressao) {
		try {
			//remove os espacos em branco e caracteres invalidos
			expressao = expressao.replaceAll("\\s+", "");
			
			if(expressao.isEmpty()) {
				return 0;
			}
			
			//divide a expressao em partes separadas por operadores
			Pattern pattern = Pattern.compile("[+\\-*/]");
			Matcher matcher = pattern.matcher(expressao);
			String[] partesExpressao = pattern.split(expressao);
			
			//converter as partes em numeros e operadores
			double resultado = Double.parseDouble(partesExpressao[0]);
			int i=1;
			
			//realiza operacoes sequencialmente 
			while(matcher.find()) {
				String operador = matcher.group();
				double valor = Double.parseDouble(partesExpressao[i++]);
				
				switch(operador) {
				case "+":
					resultado += valor;
					break;
				case "-":
					resultado -= valor;
					break;
				case "*":
					resultado *= valor;
					break;
				case "/":
					resultado /= valor;
					break;	
				}
			}
			
			return resultado;
			
		}catch(NumberFormatException | ArithmeticException e) {
			return 0;
		}
	}
}

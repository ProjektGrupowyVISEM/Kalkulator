	import java.awt.Color;
	import java.awt.Dimension;
	import java.awt.Font;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.awt.event.KeyEvent;
	import java.awt.event.KeyListener;
	
	import javax.swing.JButton;
	import javax.swing.JFrame;
	import javax.swing.JPanel;
	import javax.swing.JTextField;
	
	/**
	 * @author
 * 
 */
public class Calc extends JPanel implements ActionListener, KeyListener{
	private int bounds = 3;
	private Color displayBackground;
	
	public Color getDisplayBackground() {
		return displayBackground;
	}

	public void setDisplayBackground(Color displayBackground) {
		this.displayBackground = displayBackground;
		display.setBackground(displayBackground);
		this.initComponent();
	}

	public Color getDisplayForeground() {
		return displayForeground;
	}

	public void setDisplayForeground(Color displayForeground) {
		this.displayForeground = displayForeground;
		display.setForeground(displayForeground);
		this.initComponent();
	}
	private Color displayForeground;
	public int getCalcBounds() {
		return bounds;
	}

	public void setCalcBounds(int bounds) {
		this.bounds = bounds;
		initComponent ();
	}
	private int buttonSize = 60;
	private int textFieldSize = 40;

	public int getDisplaySize() {
		return textFieldSize;
	}

	public void setDisplaySize(int _textFieldSize) {
		textFieldSize = _textFieldSize;
		initComponent();
	}

	public int getButtonSize() {
		return buttonSize;
	}

	public void setButtonSize(int _buttonSize) {
		buttonSize = _buttonSize;
		for (int i=0; i<10; i++) numbers[i].setButtonSize(buttonSize);
		mul.setButtonSize(buttonSize);
		add.setButtonSize(buttonSize);
		sub.setButtonSize(buttonSize);
		div.setButtonSize(buttonSize);
		can.setButtonSize(buttonSize);
		exe.setButtonSize(buttonSize);
		initComponent();
	}
	private Font font;
	private Font displayFont = new Font("Courier", Font.BOLD,16);
	public Font getDisplayFont() {
		return displayFont;
	}

	public void setDisplayFont(Font displayFont) {
		this.displayFont = displayFont;
	}

	public Font getButtonFont() {
		return font;
	}

	public void setButtonFont(Font font) {
		this.font = font;
		for (int i=0; i<10; i++) numbers[i].setFont (font);
		mul.setFont(font);
		add.setFont(font);
		sub.setFont(font);
		div.setFont(font);
		can.setFont(font);
		exe.setFont(font);
	}

	public Color getButtonBackground() {
		return background;
	}

	public void setButtonBackground(Color background) {
		this.background = background;
		for (int i=0; i<10; i++) numbers[i].setBackground (background);
		mul.setBackground(background);
		add.setBackground(background);
		sub.setBackground(background);
		div.setBackground(background);
		can.setBackground(background);
		exe.setBackground(background);
	}

	public Color getButtonForeground() {
		return foreground;
	}

	public void setButtonForeground(Color foreground) {
		this.foreground = foreground;
		for (int i=0; i<10; i++) numbers[i].setForeground (foreground);
		mul.setForeground(foreground);
		add.setForeground(foreground);
		sub.setForeground(foreground);
		div.setForeground(foreground);
		can.setForeground(foreground);
		exe.setForeground(foreground);

	}
	private Color background;
	private Color foreground;

	final CalcButton[] numbers = new CalcButton[10];
	final CalcButton mul = new CalcButton("x");
	final CalcButton add = new CalcButton("+");
	final CalcButton sub = new CalcButton("-");
	final CalcButton div = new CalcButton("/");
	final CalcButton can = new CalcButton("C");
	final CalcButton exe = new CalcButton("=");
	JTextField display = new JTextField();

	/**
	 * Model of calc can be set from outside - this one is default
	 */
	public Calc () {
		for (int i = 0; i < 10; i++) 
			numbers[i] = new CalcButton("" + ((i + 1) % 10));
		initComponent ();
	}
	private void initComponent () {
		removeKeyListener(this);
		addKeyListener(this);
		model.removeActionListener (this);
		model.addActionListener(this);
		this.removeAll();
		setPreferredSize (new Dimension (buttonSize*4+5*bounds,buttonSize*4+textFieldSize+7*bounds));
		setLayout(null);
		int buttonSizeB = buttonSize + bounds;
		for (int i = 0; i < 10; i++) {
			numbers[i].removeActionListener(this);
			numbers[i].setBounds(bounds + i % 3 * buttonSizeB,bounds*2+textFieldSize + i / 3 * buttonSizeB, buttonSize, buttonSize);
			add(numbers[i]);
			numbers[i].addActionListener(this);
			(numbers[i]).setActionCommand(numbers[i].getText());
		}
		can.removeActionListener(this);
		exe.removeActionListener(this);
		div.removeActionListener(this);
		sub.removeActionListener(this);
		add.removeActionListener(this);
		mul.removeActionListener(this);
		can.setBounds(bounds+1*buttonSizeB,bounds*2+3*buttonSizeB + textFieldSize,buttonSize,buttonSize);
		exe.setBounds(bounds+2*buttonSizeB,bounds*2+3*buttonSizeB + textFieldSize,buttonSize,buttonSize);
		div.setBounds(bounds+3*buttonSizeB,bounds*2+3*buttonSizeB + textFieldSize,buttonSize,buttonSize);
		add.setBounds(bounds+3*buttonSizeB,bounds*2+0*buttonSizeB + textFieldSize,buttonSize,buttonSize);
		sub.setBounds(bounds+3*buttonSizeB,bounds*2+1*buttonSizeB + textFieldSize,buttonSize,buttonSize);
		mul.setBounds(bounds+3*buttonSizeB,bounds*2+2*buttonSizeB + textFieldSize,buttonSize,buttonSize);
		can.setActionCommand("C");
		exe.setActionCommand("=");
		div.setActionCommand("/");
		add.setActionCommand("+");
		sub.setActionCommand("-");
		mul.setActionCommand("*");
		can.addActionListener(this);
		exe.addActionListener(this);
		div.addActionListener(this);
		add.addActionListener(this);
		sub.addActionListener(this);
		mul.addActionListener(this);
		add (can);
		add (exe);
		add (div);
		add (add);
		add (sub);
		add (mul);
		display.setBackground(background);
		display.setBounds(bounds,bounds,buttonSizeB*4-bounds,textFieldSize);
		display.setHorizontalAlignment(JTextField.RIGHT);	
		display.setFont (displayFont);
		display.setBackground(displayBackground);
		display.setForeground(displayForeground);
		display.setEditable(false);
		add(display);
		this.setFocusable(true);
		this.revalidate();
	}

	private DefaultCalcModel model = new DefaultCalcModel();

	public DefaultCalcModel getModel() {
		return model;
	}

	public void setModel(DefaultCalcModel model) {
		this.model = model;
		model.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
	//    System.out.println("Count of listeners: " + ((JButton) arg0.getSource()).getActionListeners().length);  
		 if (arg0.getActionCommand().equals(DefaultCalcModel.UPDATE)) {
			 System.out.println ("update?");
			 display.setText(model.getDisplay());
		 }
		 else model.pressButton (arg0.getActionCommand().charAt(0));
		 this.requestFocus();
		 
	}

	static public void main(String[] args) {
		JFrame frame = new JFrame();
		Calc calc = new Calc ();
		calc.setButtonForeground(Color.YELLOW);
		calc.setDisplayFont(new Font("Terminal", Font.BOLD,22));
		calc.setButtonSize(70);
		calc.setDisplaySize(50);
		calc.setCalcBounds(4);
		calc.setButtonBackground(Color.BLACK);
		calc.setDisplayForeground (Color.YELLOW);
		calc.setDisplayBackground (Color.BLACK);
		frame.add(calc);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		System.out.println(arg0.getKeyChar());
		// TODO Auto-generated method stub
		if (arg0.getKeyChar()>='0' && arg0.getKeyChar()<='9')
			actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ""+arg0.getKeyChar()));
		if (arg0.getKeyChar()=='*')
			actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ""+arg0.getKeyChar()));
		if (arg0.getKeyChar()=='/')
			actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ""+arg0.getKeyChar()));
		if (arg0.getKeyChar()=='+')
			actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ""+arg0.getKeyChar()));
		if (arg0.getKeyChar()=='-')
			actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ""+arg0.getKeyChar()));
		if (arg0.getKeyChar()=='C')
			actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ""+arg0.getKeyChar()));
		if (arg0.getKeyChar()=='=')
			actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ""+arg0.getKeyChar()));
		
	}

}

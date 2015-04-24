import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;


public class CalcButton extends JButton {
	private Font font = new Font("Tahoma", Font.BOLD,12);
	public Color getBackground() {
		return background;
	}

	public void setBackground(Color background) {
		this.background = background;
		super.setBackground(background);
		    
	}

	private Color foreground = Color.WHITE;
	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
		super.setFont(font);
	}

	public Color getForeground() {
		return foreground;
	}

	public void setForeground(Color foreground) {
		this.foreground = foreground;
		super.setForeground(foreground);
		    
	}

	private Color background = Color.BLUE;
	
	private int buttonSize = 50;
	
	public int getButtonSize() {
		return buttonSize;
	}

	public void setButtonSize(int buttonSize) {
		this.buttonSize = buttonSize;
	      setPreferredSize(new Dimension(buttonSize,buttonSize));
	}

	public CalcButton (String text) {
		  setBackground(background);
	      setForeground(foreground);
	      setText(text);
	      setPreferredSize(new Dimension(buttonSize,buttonSize));
	      setFocusPainted(false);
	      setFont(font);
	}
}

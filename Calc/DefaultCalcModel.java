import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class DefaultCalcModel {
	private double a,b,result;
	public static final String UPDATE = "UPDATE";
	private boolean abset=false;
	private boolean aindicator=false;
	private boolean aset =false;
	private boolean res = false;
	private boolean err = false;
	private String display="";
	private Action savedCommand;
	private ArrayList<ActionListener>al = new ArrayList<ActionListener> ();
	public  void addActionListener (ActionListener alist) {
		al.add(alist);
	}
	private void performAction (String action) {
		for (int i=0; i<al.size(); i++) 
			al.get(i).actionPerformed(new ActionEvent (this, ActionEvent.ACTION_PERFORMED, action));
	}
	public String getDisplay() {
		// TODO Auto-generated method stub
		return display;
	}
	
	public void pressButton(char command) {
		if (command >= '0' && command <= '9') pressedDigit(command - '0');
		if (command == '+') resolve (new Action(){

			@Override
			public void evaluate(double a, double b) {
				result = a+b;
				System.out.println(result);
				
			}

			@Override
			public boolean twoArguments() {
				
				return true;
			}
			
		});
		if (command == '-') resolve (new Action(){

			@Override
			public void evaluate(double a, double b) {
				result = a-b;
				
			}

			@Override
			public boolean twoArguments() {
				// TODO Auto-generated method stub
				return true;
			}
			
		});
		if (command == '/') resolve (new Action(){

			@Override
			public void evaluate(double a, double b) {
				if (b==0) {
					display = "ERR DIV BY 0";
					err=true;
				}
				else result = a/b;
				
			}

			@Override
			public boolean twoArguments() {
				// TODO Auto-generated method stub
				return true;
			}
			
		});
		if (command == '*') resolve (new Action(){

			@Override
			public void evaluate(double a, double b) {
				result = a*b;
			}

			@Override
			public boolean twoArguments() {
				// TODO Auto-generated method stub
				return true;
			}
			
		});
		if (command == '=') resolve (new Action(){

			@Override
			public void evaluate(double a, double b) {
				if (savedCommand!=null)savedCommand.evaluate(a, b);
				savedCommand = null;
				res = true;
			}

			@Override
			public boolean twoArguments() {
				// TODO Auto-generated method stub
				return false;
			}
		});
		if (command == 'C') resolve (new Action(){

			@Override
			public void evaluate(double a, double b) {
				if (!display.equals("")) {
					display="";
				}
				else if (aset) {
					display = ""+a;
					aset = false;
					}
				else {
					display = "";
				}
			}

			@Override
			public boolean twoArguments() {
				// TODO Auto-generated method stub
				return false;
			}
			
		});
		

	}

	private void resolve(Action action) {
		if (display.equals("")) return;
		if (aset) {
			
			if (!action.twoArguments()) {
				b = Double.parseDouble(display);
				aindicator = true;
				action.evaluate(a, b);
				if (!err) display = ""+result;
				else err=false;
				abset=true;
				aset=false;
			}
			
		} 
		if (!aset) {
			if (!abset) {
				a = Double.parseDouble(display);
				aindicator = true;
				aset = true;
				if (action.twoArguments()) savedCommand = action;
				else action.evaluate(a, b);
			}
			else abset = false;
		}	

		performAction(UPDATE);
		System.out.println ("display: |" + display + "| a: " + a + " b: " + b + 
				" ASET: " + aset + " savedAction: " +savedCommand);
	}

	


	private void pressedDigit(int i) {
		if (res) {
			display = "";
			res = false;
		}
		if (aindicator) {
			display = "";
			aindicator = false;
		}
		if (display.equals("0.0)")) display = "";
		display+=i;
		System.out.println ("display: |" + display + "| a: " + a + " b: " + b + 
				" ASET: " + aset + " savedAction: " +savedCommand);
		performAction(UPDATE);
	}
	public void removeActionListener(ActionListener a) {
		al.remove(a);
	}
	
}

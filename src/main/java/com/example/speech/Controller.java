package com.example.speech;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Controller extends JPanel implements ActionListener{

	private JTextArea display; 
	private JTextArea lineNum;
	//processing unit
	public Process process;
	String latest;
	RecordThread recorder;
	JButton enter;
	JButton proceed;
	JPanel buttons;

	/**
	 * Constructor for this class
	 */
	public Controller(){
		process = new Process();
		recorder = new RecordThread();
		panel();
	}

	/**
	 * This function sets up the structure and components
	 * of the GUI.
	 */
	public void panel(){
		this.setLayout(new FlowLayout());
		
		buttons = new JPanel();
		buttons.setLayout(new GridLayout(2,1));
		
		display = new JTextArea(40, 40);
		add(display);
		display.setLineWrap(true);
		display.setEditable(true);
		display.setTabSize(2);
		display.setMargin(new Insets(0, 20, 0, 0));
		
		enter = new JButton("rec ●");
		enter.setActionCommand("enter");
		enter.addActionListener(this);
		buttons.add(enter);
		
		proceed = new JButton("proceed >>");
		proceed.setActionCommand("proceed");
		proceed.addActionListener(this);
		buttons.add(proceed);
		add(buttons);

	}
	
	
	/**
	 * This function captures the action input events from the GUI
	 * and performs the specified actions.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("proceed")) {
			latest = display.getText().toLowerCase();
			latest  = Process.process(latest);
			//so far so good 
			display.setText(latest);
		
		}
		if (e.getActionCommand().equals("enter")) {
			
			/* calls the processing unit, passing the current file and returns
			   an array of strings which sets TextArea to array of Strings */
			recorder.record();
			try {
				latest = QuickstartSample.recognize();
				display.setText("\n You recorded: \n" + latest);

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
	/**
	 * This function merges two arrays from 
	 * currentFile and latest recognition result.
	 * @param a1
	 * @param a2
	 * @return final array
	 */
	public static String[] concatArr(String[] a1, String[] a2) {
		String[] newArr = new String[a1.length + a2.length];
		for(int i = 0; i < a1.length; i++) {
			newArr[i] = a1[i];
		}
		for(int i = a1.length; i < newArr.length; i++) {
			newArr[i] = a2[i-a1.length];
		}
		return newArr;
	}
}

package com.example.speech;

public class Process{
	static int mode = 0; 
	/**
	 * This function processes the raw array of text received from GCP.
	 * @param currentFile
	 * @param newStr
	 * @return an array of strings for processed java commands
	 */
	public static String process(String input) {
		try {
			switch(mode) {
			case 0:
				input = AIMLChatBot.inputAIML(input);
				break;
				
			case 1:

				break;
				
			case 2:

				break;
				
			case 3:

				break;
				
			case 4:
				break;
			
			case 5:
				break;
				
			}
			TextToSpeech.talk(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return input;
	}


}

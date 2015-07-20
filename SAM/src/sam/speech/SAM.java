package sam.speech;

import voce.SpeechSynthesizer;


public class SAM {
	
	public static SpeechSynthesizer synth = null;
	public static void main(String[] argv)
	{
		voce.SpeechInterface.init("voce/voce/lib", false, true, "./grammar", "vocab");
		synth = new SpeechSynthesizer("Sam");

		boolean quit = false;
		while(!quit)
		{
			try
			{
				Thread.sleep(200);
			}
			catch (InterruptedException e)
			{
				System.out.println("Exception: " + e);
			}
			while(voce.SpeechInterface.getRecognizerQueueSize() > 0)
			{
				String s = voce.SpeechInterface.popRecognizedString();
				
				if (-1 != s.indexOf("quit"))
				{
					quit = true;
				}
				System.out.println("You said: "+ s);
				if(s.equals("sam get the weather"))
				{
					Weather w = new Weather();
					w.getWeather();
				}
				if(s.equals("hello sam"))
				{
					synth.synthesize("hello");
				}
				if(s.equals("thank you sam"))
				{
					synth.synthesize("you're welcome");
				}
				if(s.equals("quit"))
				{
					synth.synthesize("goodbye");
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
				}
			}			
		}
	
		voce.SpeechInterface.destroy();
		synth.destroy();
		System.exit(0);
	}
}

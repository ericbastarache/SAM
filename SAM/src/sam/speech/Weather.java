package sam.speech;

import java.net.URL; 

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.CharacterData; 
import org.w3c.dom.Document; 
import org.w3c.dom.Element; 
import org.w3c.dom.Node; 
import org.w3c.dom.NodeList;  

public class Weather extends SAM {
	String url = "http://rss.theweathernetwork.com/weather/caon0696";

	private static Weather instance = null; 
	public Weather() { 
	} 
	public static Weather getInstance() { 
		if(instance == null) 
			instance = new Weather(); 
			return instance; 
	} 
	void getWeather(){
		try { 
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder(); 
			URL u = new URL("http://rss.theweathernetwork.com/weather/caon0696"); // your feed url 
			Document doc = builder.parse(u.openStream()); 
			NodeList nodes = doc.getElementsByTagName("item"); 
			for(int i=0;i<nodes.getLength()-3;i++) { 
				Element element = (Element)nodes.item(i); 
				String w = getElementValue(element, "description").toString();
				String weather = w.replaceAll("[^a-zA-Z0-9]", " ");
				
				String temp = weather.substring(0, 12);
				temp += weather.substring(13, 14);
				System.out.println(temp);
				System.out.println(weather);
				System.out.println(temp.length());
				//synth.synthesize(weather);
				
				
			}//for 
		}//try 
		catch(Exception ex) { 
			ex.printStackTrace(); 
		} 
		
	}
	private  String getCharacterDataFromElement(Element e) { 
		try { 
			Node child = e.getFirstChild(); 
			if(child instanceof CharacterData) { 
				CharacterData cd = (CharacterData) child; 
				return cd.getData(); 
			} 
		}catch(Exception ex) { 

		} 
		return ""; 
	} 
	protected float getFloat(String value) { 
		if(value != null && !value.equals("")) 
			return Float.parseFloat(value); 
		else 
			return 0; 
	} 
	protected String getElementValue(Element parent,String label) { 
		return getCharacterDataFromElement((Element)parent.getElementsByTagName(label).item(0));
	} 
	
		
}

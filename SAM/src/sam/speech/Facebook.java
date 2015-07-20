package sam.speech;

import java.net.URI;
import java.net.URISyntaxException;

public class Facebook extends SAM {

	void postFB(){
		
		try {
			URI fbUri = new URI("https://www.facebook.com/dialog/oauth?client_id={app-id}&redirect_uri={https://www.facebook.com/connect/login_success.html}");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}

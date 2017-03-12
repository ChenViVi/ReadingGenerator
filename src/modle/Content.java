package modle;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;




public class Content {
    
    JSONObject content = new JSONObject();
	
    public Content(String title,String cover,String review,String score,String link){
        try {
            content.put("title", title);
            content.put("cover", cover);
            content.put("review", review);
            content.put("score", score);
            content.put("link", link);
        } catch (JSONException ex) {
            Logger.getLogger(Content.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	
    public String getContentString(){
        return content.toString();
    }
}

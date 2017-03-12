package modle;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;


public class Define {
	
    JSONObject define = new JSONObject();
	
    public void addDefine(String key,String value){
        try {
            define.put(key, value);
        } catch (JSONException ex) {
            Logger.getLogger(Define.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public JSONObject getDefineJSON(){
        return define;
    }
}

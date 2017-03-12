package modle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import util.FileUtil;



public class Reading {
    
    private String path = "D:\\1_Develop\\6_Html\\3_Blog\\hexo2\\source\\_data\\reading.json";
    Define define = new Define();
    HashMap<String,ArrayList<Content>> contentsMap = new HashMap<String,ArrayList<Content>>();
    
    public void setPath(String path){
        this.path = path;
    }
    
    public void generateFromFile(){
        try {
            JSONObject readingJson = new JSONObject(new JSONTokener(FileUtil.readFile(path)));
            JSONObject defineJson = readingJson.getJSONObject("define");
            JSONObject contentsJson = readingJson.getJSONObject("contents");
            Iterator iterator = defineJson.keys();
            while(iterator.hasNext()){
                String key = (String) iterator.next();
                String value = defineJson.getString(key);
                addDefine(key,value);
                JSONArray contentsArray = contentsJson.getJSONArray(key);
                for(int i = 0; i < contentsArray.length(); i++){
                    JSONObject contentsObject = contentsArray.getJSONObject(i);
                    addContent(key,new Content(
                            contentsObject.getString("title"),
                            contentsObject.getString("cover"),
                            contentsObject.getString("review"),
                            contentsObject.getString("score"),
                            contentsObject.getString("link")
                    ));
                }
            }
        } catch (JSONException ex) {
            Logger.getLogger(Reading.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	
    public void addDefine(String key,String value){
        define.addDefine(key, value);
        contentsMap.put(key,new ArrayList<>());
    }
    
    public JSONObject getDefineJSON(){
        return define.getDefineJSON();
    }
	
    public void addContent(String defineKey,Content content){
        ArrayList<Content> contents = contentsMap.get(defineKey);
        if(contents != null){
            contents.add(content);
	}
    }
	
    public JSONObject getContentsJSON(){
        JSONObject contents = new JSONObject();
        for (Object object : contentsMap.entrySet()) {
            Map.Entry entry = (Map.Entry) object;
            String defineKey = (String) entry.getKey();
            ArrayList<Content> contentsArrayList = (ArrayList<Content>) entry.getValue();
            JSONArray contentsJson = new JSONArray();
            try {
                for(Content content : contentsArrayList){
                    contentsJson.put(new JSONObject(new JSONTokener(content.getContentString())));
                }
                contents.put(defineKey, contentsJson);
            } catch (JSONException ex) {
                Logger.getLogger(Reading.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return contents;
    }
    
    public JSONObject getReadingJSON(){
        JSONObject readingJson = new JSONObject();
        try {
            readingJson.put("define", getDefineJSON());
            readingJson.put("contents", getContentsJSON());
        } catch (JSONException ex) {
            Logger.getLogger(Reading.class.getName()).log(Level.SEVERE, null, ex);
        }
        return readingJson;
    }

    public void saveToFile(){
        try {
            FileUtil.writeTxtFile(getReadingJSON().toString(),path);
        } catch (IOException ex) {
            Logger.getLogger(Reading.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

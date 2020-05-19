/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.gui.Userbp;
import com.mycompany.myapp.entities.Feat;
import com.mycompany.myapp.entities.Userstorybp;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asus
 */
public class Serviceuserbp {
     public ArrayList<Userstorybp> ubp;
        public ArrayList<Integer> todo;
         public ArrayList<Integer> getid;
         public ArrayList<Integer> doing;
                public ArrayList<Integer> done;
                 public ArrayList<Integer> num;
          
           public ArrayList<String> nom;
    String r;
    int result;
    public static Servicefeat instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public Serviceuserbp() {
         req = new ConnectionRequest();
    }

    public static Servicefeat getInstance() {
        if (instance == null) {
            instance = new Servicefeat();
        }
        return instance;
    }
    
    public ArrayList<Userstorybp> parseuserbp(String jsonText){
        try {//affichage sous forme de json
            ubp=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Userstorybp u = new Userstorybp();
                float id = Float.parseFloat(obj.get("priorityBp").toString());
                u.setPrio((int)id);
               u.setUsersetory(obj.get("userStoryBp").toString());
               u.setFeat(obj.get("feature").toString());
               float idf = Float.parseFloat(obj.get("idUserStoryBacklogProduit").toString());
                u.setId((int)idf);

                
                      
                ubp.add(u);
            }
            
            
        } catch (IOException ex) {
            
        }
        return ubp;
    }
  public ArrayList<Userstorybp> getAlluserbp(int id){
        //methode afficher dans symfony
        String url = Statics.BASE_URL+"/bp/bpusermobile/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ubp = parseuserbp(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return ubp;
    }

   public boolean updateuserbp (int p,String userst,int idf,int id) {
     
        String url = Statics.BASE_URL + "/bp/modifieruserbpm/" + p+"/"+userst +"/"+idf+"/"+id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println("la modif de feature est effectuer");
        return resultOK;

    }
    public boolean ajouteruserbp (int idf,String userstory,int p) {
     
        String url = Statics.BASE_URL + "/bp/ajoutuserbpm/" + idf+"/"+userstory+"/"+p ;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println("l'ajout de userstory est effectuer");
        return resultOK;

    }
    public void Supprimeruserbp(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(Statics.BASE_URL + "/bp/deleteuserbpm/" + id);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        System.out.println("la supprision de feature est effectuer");

    }
     public ArrayList<Integer> getAlltodo(int id){
        //methode afficher dans symfony
        String url = Statics.BASE_URL+"/bp/progressmobiletodo/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                todo = parsebptodo(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return todo;
    }

  public ArrayList<Integer> parsebptodo(String jsonText){
        try {//affichage sous forme de json
            todo=new ArrayList<Integer>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                
                float id = Float.parseFloat(obj.get("counter").toString());
                
               int k=(int)id;
              
              
                     
                      
                      
                todo.add(k);
            }
            
            
        } catch (IOException ex) {
            
        }
        return todo;
    }
    
  public ArrayList<Integer> getAlldoing(int id){
        //methode afficher dans symfony
        String url = Statics.BASE_URL+"/bp/progressmobiledoing/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                doing = parsebptodo(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return todo;
    }

  public ArrayList<Integer> parsebpdoing(String jsonText){
        try {//affichage sous forme de json
            todo=new ArrayList<Integer>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                
                float id = Float.parseFloat(obj.get("counter").toString());
               int k=(int)id;
              
              
                     
                      
                      
                doing.add(k);
            }
            
            
        } catch (IOException ex) {
            
        }
        return doing;
    }
    
   public ArrayList<Integer> getAlldone(int id){
        //methode afficher dans symfony
        String url = Statics.BASE_URL+"/bp/progressmobiledone/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                done = parsebpdone(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return done;
    }

  public ArrayList<Integer> parsebpdone(String jsonText){
        try {//affichage sous forme de json
            done=new ArrayList<Integer>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                
                float id = Float.parseFloat(obj.get("counter").toString());
               int k=(int)id;
              
              
                     
                      
                      
                done.add(k);
            }
            
            
        } catch (IOException ex) {
            
        }
        return done;
    }
    public ArrayList<Integer> parsenumfeat(String jsonText){
        try {//affichage sous forme de json
            num=new ArrayList<Integer>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Userstorybp f = new Userstorybp();
                
                 float id = Float.parseFloat(obj.get("idBacklogFeature").toString());
               
                f.setIdf((int)id);
                int k=f.getIdf();
              System.out.println(k);
                      
                num.add(k);
            }
            
            
        } catch (IOException ex) {
            
        }
        return num;
    }
  public ArrayList<Integer> getnumfeat(String titre){
        //methode afficher dans symfony
        String url = Statics.BASE_URL+"/bp/findnumfeature/"+titre;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                num = parsenumfeat(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return num;
    }
  
  public ArrayList<String> parsenomfeat(String jsonText){
        try {//affichage sous forme de json
            nom=new ArrayList<String>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Userstorybp f = new Userstorybp();
                String k="";
                
          
                f.setFeat(obj.get("feature").toString());
               k=f.getFeat();
                
                      
                nom.add(k);
            }
            
            
        } catch (IOException ex) {
            
        }
        return nom;
    }
  public ArrayList<String> getnomfeat(){
        //methode afficher dans symfony
        String url = Statics.BASE_URL+"/bp/nomfeature";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                nom = parsenomfeat(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return nom;
    }
  
}

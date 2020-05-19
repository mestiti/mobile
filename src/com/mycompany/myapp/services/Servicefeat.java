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
import com.mycompany.myapp.entities.Feat;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asus
 */
public class Servicefeat {
     public ArrayList<Feat> fe;
        public ArrayList<Integer> todo;
          public ArrayList<Integer> num;
          
           public ArrayList<String> nom;
         public ArrayList<Integer> getid;
         public ArrayList<Integer> doing;
                public ArrayList<Integer> done;
    String r;
    int result;
    public static Servicefeat instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public Servicefeat() {
         req = new ConnectionRequest();
    }

    public static Servicefeat getInstance() {
        if (instance == null) {
            instance = new Servicefeat();
        }
        return instance;
    }
    
    public ArrayList<Feat> parsefeat(String jsonText){
        try {//affichage sous forme de json
            fe=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Feat f = new Feat();
                float id = Float.parseFloat(obj.get("idBacklogFeature").toString());
                f.setIdf((int)id);
               f.setFeature(obj.get("feature").toString());
               
                
                      
                fe.add(f);
            }
            
            
        } catch (IOException ex) {
            
        }
        return fe;
    }
  public ArrayList<Feat> getAllfeat(int id){
        //methode afficher dans symfony
        String url = Statics.BASE_URL+"/bp/bpfeatmobile/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                fe = parsefeat(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return fe;
    }
    
  
   public void Supprimerfeat(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(Statics.BASE_URL + "/bp/deletefeatm/" + id);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        System.out.println("la supprision de feature est effectuer");

    }
    public boolean update (int idp,String feat,int idf) {
     
        String url = Statics.BASE_URL + "/bp/modifierbpm/" + idp+"/"+feat +"/"+idf;
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
    
     public boolean ajouterfeat (int idp,String feat) {
     
        String url = Statics.BASE_URL + "/bp/ajoutbpm/" + idp+"/"+feat ;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println("l'ajout de feature est effectuer");
        return resultOK;

    }
    
    
       public ArrayList<Integer> parsenum(String jsonText){
        try {//affichage sous forme de json
            num=new ArrayList<Integer>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Feat f = new Feat();
                
                 float id = Float.parseFloat(obj.get("idProjet").toString());
               
                f.setId((int)id);
                int k=f.getId();
              System.out.println(k);
                      
                num.add(k);
            }
            
            
        } catch (IOException ex) {
            
        }
        return num;
    }
  public ArrayList<Integer> getnum(String titre){
        //methode afficher dans symfony
        String url = Statics.BASE_URL+"/bp/findnumprojet/"+titre;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                num = parsenum(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return num;
    }
  
  public ArrayList<String> parsenomprojet(String jsonText){
        try {//affichage sous forme de json
            nom=new ArrayList<String>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Feat f = new Feat();
                String k="";
                
          
                f.setNomp(obj.get("nomProjet").toString());
               k=f.getNomp();
                
                      
                nom.add(k);
            }
            
            
        } catch (IOException ex) {
            
        }
        return nom;
    }
  public ArrayList<String> getnom(){
        //methode afficher dans symfony
        String url = Statics.BASE_URL+"/bp/nomprojet";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                nom = parsenomprojet(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return nom;
    }
  
  
  
}

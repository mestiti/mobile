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
import com.mycompany.gui.Sprint_form;
import com.mycompany.myapp.entities.Sprint;
import com.mycompany.myapp.entities.User;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceBS {

    public ArrayList<User> users;
    public ArrayList<Sprint> sprints;
    
    public static ServiceBS instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceBS() {
         req = new ConnectionRequest();
    }

    public static ServiceBS getInstance() {
        if (instance == null) {
            instance = new ServiceBS();
        }
        return instance;
    }


    public ArrayList<User> parseuser(String jsonText){
        try {
            users=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
             User u = new User();
               float id = Float.parseFloat(obj.get("id").toString());
                u.setId((int)id);
                u.setUsername(obj.get("username").toString());
                u.setRoles(obj.get("roles").toString());
                u.setImg(obj.get("image_user").toString());
                users.add(u);
            }
        } catch (IOException ex) {
            
        }
        return users;
    }
    
    
    
    public ArrayList<User> getuser(String name){
        String url = Statics.BASE_URL+"/bs/user/"+name+"";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseuser(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return users;
    }
    
   public ArrayList<Sprint> getSprint(String name){
    String url = Statics.BASE_URL+"/bs/sprints/"+name+"";
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
    @Override
    public void actionPerformed(NetworkEvent evt) {
    sprints = parsesprint(new String(req.getResponseData()));
    req.removeResponseListener(this);
    }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
       //System.out.println(sprints);
    return sprints;
    }
   
   public ArrayList<Sprint> parsesprint(String jsonText){
        try {
            sprints=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
             Sprint u = new Sprint();
               float id = Float.parseFloat(obj.get("id_sprint").toString());
               float nbr = Float.parseFloat(obj.get("liste_user_sroty_bs").toString());
               float id_bs = Float.parseFloat(obj.get("id_bs").toString());

                u.setId((int)id);
                u.setDesc(obj.get("description").toString());
                u.setDate1(obj.get("date_debut_sprint").toString());
                u.setDate2(obj.get("date_fin_sprint").toString());
                u.setNbr((int)nbr);
                u.setId_bs((int)id_bs);
                u.setEquipe(obj.get("nom_equipe").toString());
                u.setProjet(obj.get("nom_projet").toString());
                
                sprints.add(u);
            }
        } catch (IOException ex) {
            
        }
        return sprints;
    }
   
   public boolean upimg(String t,int id) {
        String url = Statics.BASE_URL + "/bs/updateimg/img?img=" + t + "&id=" + id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return true;
    }
    
     
}
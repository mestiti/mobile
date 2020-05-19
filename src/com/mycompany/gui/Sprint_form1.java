/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;


import com.codename1.components.FloatingActionButton;
import com.codename1.components.SpanLabel;
import com.codename1.components.WebBrowser;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Sprint;
import com.mycompany.myapp.services.ServiceBS;
import java.util.ArrayList;


/**
 *
 * @author Asus
 */
public class Sprint_form1 extends Form {
      

    Form current;
    Resources res1;
    private static final int[] COLORS = {0xf8e478, 0x60e6ce, 0x878aee};
    private static final String[] LABELS = {"Title", "Coding", "Learning"};
    ServiceBS srv = new ServiceBS();

    public Sprint_form1(Resources res, int x,Form f,String username,int idu ,int ids,String img) {
      
     
            ArrayList<Sprint> sprints = srv.getSprint(username);

       // System.err.println(sprints);

    this.setTitle(sprints.get(x).getDesc());
   
    this.getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK_IOS,e -> new Sprint_form(res,username,idu,img).show());

     SpanLabel nom =new SpanLabel("Nom Sprint : "+sprints.get(x).getDesc());
     SpanLabel d1 =new SpanLabel("Début de Sprint : "+sprints.get(x).getDate1());
     SpanLabel d2 =new SpanLabel("Date Limite : "+sprints.get(x).getDate2());
     SpanLabel nbr =new SpanLabel("nombre de user story : "+sprints.get(x).getNbr());
     
     Button del = new Button("Delete");
     Button modif = new Button("Update");
     
    Container c1 = new Container(BoxLayout.y());
     Container c2 = new Container(BoxLayout.x());
     c1.add(nom);
     c1.add(d1);
     c1.add(d2);
     c1.add(nbr);
     c2.add(del);
     c2.add(modif);
     
     add(c1);
     add(c2);

    }    
    }


    
  
    

   

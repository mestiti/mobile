/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.animations.FlipTransition;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Feat;
import com.mycompany.myapp.entities.Userstorybp;
import com.mycompany.myapp.services.Servicefeat;
import com.mycompany.myapp.services.Serviceuserbp;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class Userbp extends SideMenuBaseFormPO{
    private static final int[] COLORS = {0xf8e478, 0x60e6ce, 0x878aee};
    private static final String[] LABELS = {"Design", "Coding", "Learning"};
    String username1;
    int id1;
    String img1;
    
    Form current;
    public Userbp(Resources res,String img,String username, int id){
                super(BoxLayout.y());

        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Inbox", "Title"),
                        new Label("19", "InboxNumber")
                )
        );
              //System.out.println(id);
       img1=img;
         username1=username;
         id1=id;
 Feat fe = null;
                SpanLabel sp = new SpanLabel();
        Serviceuserbp sb = new Serviceuserbp();
         ArrayList<Userstorybp> bp = sb.getAlluserbp(id);
          
      
      

        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        TextField duration = new TextField("2000", "Duration", 6, TextArea.NUMERIC);
       
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        
        Container remainingTasks = BoxLayout.encloseY(
                        new Label("numero ", "CenterTitle")
                       
                );
        remainingTasks.setUIID("RemainingTasks");
        
        Container completedTasks = BoxLayout.encloseY(
                        new Label("Feature", "CenterTitle")
                        
        );
        completedTasks.setUIID("CompletedTasks");
 Container remainingTasks2 = BoxLayout.encloseY(
                        new Label("userstory ", "CenterTitle")
                       
                );
        remainingTasks2.setUIID("RemainingTasks");
      
        Container remainingTasks1 = BoxLayout.encloseY(
                        new Label("prio ", "CenterTitle")
                       
                );
        remainingTasks1.setUIID("RemainingTasks");
      
        
      FloatingActionButton fab1  = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        RoundBorder rb = (RoundBorder)fab1.getUnselectedStyle().getBorder();
         
        rb.uiid(true);
        fab1.bindFabToContainer(getContentPane());
        fab1.addActionListener(e -> {
            fab1.setUIID("FloatingActionButton");
          
            
            Style bg = getContentPane().getUnselectedStyle();
           

setTransitionOutAnimator(new FlipTransition(-1, duration.getAsInt(2000)));
              new Userbp_formulaireajout(res,img,username,id).show();
              
           
      
        });
        Container titleCmp = BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                        BorderLayout.west(
                                BoxLayout.encloseY(
                                    new Label("features on this project", "Title"),
                                    new Label("connected as "+username, "SubTitle")
                                )
                            ),
                        GridLayout.encloseIn(3, completedTasks,remainingTasks2,remainingTasks1)
                );
              tb.setTitleComponent(titleCmp);
        add(new Label("Today", "TodayTitle"));  
        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
         for (Userstorybp obj : bp){
        addButtonBottom(res,arrowDown, obj.getFeat()+"   "+obj.getUsersetory()+"         "+obj.getPrio(), 0x5ae29d, true,obj,username,id,1,img);
        }
       
      
         
        setupSideMenu(res,img);
        
        
    }



    
   private void addButtonBottom(Resources res,Image arrowDown, String text, int color, boolean first,Userstorybp u,String username
    ,int idu,int ids,String img) {
       TextField duration = new TextField("2000", "Duration", 6, TextArea.NUMERIC);
       
        MultiButton finishLandingPage = new MultiButton(text);
        Label l = new Label(text);
        finishLandingPage.setEmblem(arrowDown);
        finishLandingPage.setUIID("Container");
        finishLandingPage.setUIIDLine1("TodayEntry");
        finishLandingPage.setIcon(createCircleLine(color, finishLandingPage.getPreferredH(),  first));
        finishLandingPage.setIconUIID("Container");
        finishLandingPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Style bg = getContentPane().getUnselectedStyle();
           

setTransitionOutAnimator(new FlipTransition(-1, duration.getAsInt(2000)));

                new Userbp_formulairemodif(res,img,username,idu,u).show();           
            }
        });
        add(FlowLayout.encloseIn(finishLandingPage));

    }
    
   private Image createCircleLine(int color, int height, boolean first) {
        Image img = Image.createImage(height, height, 0);
        Graphics g = img.getGraphics();
        g.setAntiAliased(true);
        g.setColor(0xcccccc);
        int y = 0;
        if(first) {
            y = height / 6 + 1;
        }
        g.drawLine(height / 2, y, height / 2, height);
        g.drawLine(height / 2 - 1, y, height / 2 - 1, height);
        g.setColor(color);
        g.fillArc(height / 2 - height / 4, height / 6, height / 2, height / 2, 0, 360);
        return img;
    }
        @Override
    protected void showOtherForm(Resources res) {
       new ProfileFormPO(res,username1,id1,img1).show();
    }

    @Override
    protected void editprofile(Resources res) {
        new EditPro_form1(res,username1,id1,img1).show();}

    @Override
    protected void showfeat(Resources res) {
        new Feature(res,img1,username1,id1).show();

    }
    @Override
    protected void Chartshow(Resources res) {
        new Chart(res,img1,username1,id1).show();
    }

    @Override
    protected void showuserbp(Resources res) {
         new Feature(res,img1,username1,id1).show();
    }

 
}
    


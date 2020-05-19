/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ToastBar;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Userstorybp;
import com.mycompany.myapp.services.Serviceuserbp;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Asus
 */
public class Userbp_formulaireajout extends SideMenuBaseFormPO{
    private static final int[] COLORS = {0xf8e478, 0x60e6ce, 0x878aee};
    private static final String[] LABELS = {"Design", "Coding", "Learning"};
    String username1;
    int id1;
    String img1;
   
    Form current;
    Userstorybp f1;
    
    public Userbp_formulaireajout(Resources res,String img,String username, int id){
 super("Newsfeed", BoxLayout.y());        
          
       
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Inbox", "Title"),
                        new Label("19", "InboxNumber")
                )
        );
         createDemo(res);
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        
        Container titleCmp = BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                        BorderLayout.west(
                                BoxLayout.encloseY(
                                    new Label("formulaire", "Title"),
                                    new Label("connected as "+username, "SubTitle")
                                )
                            )
                        
                );
        
                tb.setTitleComponent(titleCmp);
                

        setupSideMenu(res,img);
         img1=img;
         username1=username;
         id1=id;
        
    }
  
    public void createDemo(Resources res) {
       Serviceuserbp s=new Serviceuserbp();
        ArrayList<String> bp = s.getnomfeat();
            
        ComboBox c=new ComboBox();
        for(int i=0;i<bp.size();i++)
        {c.addItem(bp.get(i));
        System.out.println(bp.get(i));
        }
add(c);
         TextField userstory = new TextField("  ");
      
        userstory.setUIID("TextFieldBlack");
        addStringValue("userstory", userstory);
        
        TextField prio = new TextField("   ");
     
        prio.setUIID("TextFieldBlack");
        addStringValue("prio", prio);

        

          
         // int p=Integer.parseInt(prio.getText());
        
                  
Button btadd=new Button("Add");
         btadd.setUIID("LoginButton");
         Random r = new Random();
          ToastBar.Status status = ToastBar.getInstance().createStatus();
          
          
         btadd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
          double d = Double.parseDouble(prio.getText());
int i = (int)d; 

        s.ajouteruserbp(s.getnumfeat((String) c.getSelectedItem()).get(0), userstory.getText(), i);
           status.setMessage("Working on Task with Progress");
            status.setProgress(0);
            status.setIcon(createIcon(FontImage.MATERIAL_BACKUP));
            status.show();
            
            for (int progress=0; progress <= 100; progress += 20) {
                Display.getInstance().invokeAndBlock(()->{Util.sleep(500);});
                status.setProgress(progress);
                status.show();
                if (progress == 100) {
                   status.setIcon(createIcon(FontImage.MATERIAL_DONE));
                    status.setProgress(-1);
                    status.setExpires(2000);
                    status.setMessage("The task is now complete");
                    status.show();
                }
            }
            }
        });
        
        
         
       Container by = BoxLayout.encloseY(
                
                
               btadd
                  
               
        );
        add( by);
    
        
          

        
        
    }
    
     private Image createIcon(char charcode) {
        int iconWidth = Display.getInstance().convertToPixels(8, true);
        Style iconStyle = new Style();
        Font iconFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        if (Font.isNativeFontSchemeSupported()) {
            iconFont = Font.createTrueTypeFont("native:MainBold", null).derive((int)(iconWidth * 0.5), Font.STYLE_BOLD);
        }
        iconStyle.setFont(iconFont);
        iconStyle.setFgColor(0xffffff);
        iconStyle.setBgTransparency(0);

        FontImage completeIcon = FontImage.createMaterial(charcode, iconStyle);
        return completeIcon;
    }
    
      private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
      public Component createLineSeparator(int color) {
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(color);
        separator.getUnselectedStyle().setBgTransparency(100);
        separator.setShowEvenIfBlank(true);
        return separator;
    }
    @Override
    protected void showOtherForm(Resources res) {
        new StatsFormPO(res,username1,id1,img1).show();
    }

    

    @Override
    protected void editprofile(Resources res) {
         new EditPro_form1(res,username1,id1,img1).show();
    }

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
         new Userbp(res,img1,username1,id1).show();
    }}

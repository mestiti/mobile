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
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.animations.FlipTransition;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Feat;
import com.mycompany.myapp.services.ServiceBS;
import com.mycompany.myapp.services.Servicefeat;
import java.util.ArrayList;

/**Servicebp sb = new Servicebp();
 *
 * @author Asus
 */
public class Feature_formulaire extends SideMenuBaseFormPO{
    private static final int[] COLORS = {0xf8e478, 0x60e6ce, 0x878aee};
    private static final String[] LABELS = {"Design", "Coding", "Learning"};
    String username1;
    int id1;
    String img1;
   
    Form current;
    Feat f1;
    
    public Feature_formulaire(Resources res,String img,String username, int id,Feat f){
 super("Newsfeed", BoxLayout.y());        
        
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Inbox", "Title"),
                        new Label("19", "InboxNumber")
                )
        );
         createDemo(res,f);
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
        f1=f;
    }
  
    public void createDemo(Resources res,Feat f) {
        TextField duration = new TextField("2000", "Duration", 6, TextArea.NUMERIC);
         ToastBar.Status status = ToastBar.getInstance().createStatus();
       Servicefeat s=new Servicefeat();
        ArrayList<String> bp = s.getnom();
            
        ComboBox c=new ComboBox();
        for(int i=0;i<bp.size();i++)
        {c.addItem(bp.get(i));
        System.out.println(bp.get(i));
        }
add(c);

//System.out.println((String) c.getSelectedItem());
 
        getContentPane().setScrollVisible(false);
         TextField username = new TextField("feature");
         username.setText(f.getFeature());
        username.setUIID("TextFieldBlack");
        addStringValue("Feature", username);
        
Button btup=new Button("update");
         btup.setUIID("LoginButton");
         
               
                btup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
          Servicefeat sb = new Servicefeat();
         
       
          System.out.println(f.getIdf()+username.getText()+s.getnum((String) c.getSelectedItem()).get(0));
       
         // System.out.println(bp1.get(1));
         if( sb.update(s.getnum((String) c.getSelectedItem()).get(0),username.getText(), f.getIdf()))
         { Dialog.show("succes ", "modification faite avec succes" + "", "OK", null);
            }}
        });
                
                
            
        
         
        
         Button btsup=new Button("REMOVE");
         btsup.setUIID("LoginButton");
         btsup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
          Servicefeat sb = new Servicefeat();
          sb.Supprimerfeat(f.getIdf());
         Dialog.show("succes ", "suppression faite avec succes" + "", "OK", null);
       
            }
        });
         
         
       Container by = BoxLayout.encloseY(
                
                
                 btsup,
               btup
                  
               
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
    }

    
        
}

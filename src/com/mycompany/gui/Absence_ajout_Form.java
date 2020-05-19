/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.capture.Capture;
import com.codename1.components.ToastBar;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.isTablet;
import static com.codename1.ui.CN.openGallery;
import static com.codename1.ui.CN1Constants.GALLERY_IMAGE;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.PickerComponent;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.layouts.BorderLayout;
import static com.codename1.ui.layouts.BorderLayout.SOUTH;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.TextModeLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.myapp.services.ServiceBS;
import java.io.IOException;

/**
 *
 * @author Hajer
 */
public class Absence_ajout_Form extends SideMenuBaseFormSM{
    private static final int[] COLORS = {0xf8e478, 0x60e6ce, 0x878aee};
    private static final String[] LABELS = {"Design", "Coding", "Learning"};
    String username1;
    int id1;
    String img1;
    ServiceBS srv = new ServiceBS();
    Form current;
    
    
    public Absence_ajout_Form(Resources res,String img,String username, int id){
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
                                    new Label("Absneces", "Title"),
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
        getContentPane().setScrollVisible(false);
         TextField username = new TextField("sandeep");
        username.setUIID("TextFieldBlack");
        addStringValue("Username", username);

        TextField email = new TextField("sandeep@gmail.com", "E-Mail", 20, TextField.EMAILADDR);
        email.setUIID("TextFieldBlack");
        addStringValue("E-Mail", email);
        
        TextField password = new TextField("sandeep", "Password", 20, TextField.PASSWORD);
        password.setUIID("TextFieldBlack");
        addStringValue("Password", password);
        
        
        
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
    }

    @Override
    protected void showOtherForm1(Resources res) {
    }

    @Override
    protected void editprofile(Resources res) {
    }

    @Override
    protected void showForm2(Resources res) {
    }
    
    
}

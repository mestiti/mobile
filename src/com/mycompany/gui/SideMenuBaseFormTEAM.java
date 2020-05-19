/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import static com.codename1.ui.Image.createImage;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 *
 * @author Asus
 */
public abstract class SideMenuBaseFormTEAM extends Form {

    public SideMenuBaseFormTEAM(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public SideMenuBaseFormTEAM(String title) {
        super(title);
    }

    public SideMenuBaseFormTEAM() {
    }

    public SideMenuBaseFormTEAM(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }
    
    public void setupSideMenu(Resources res , String img) {
 Image i=null;
        try {
           i = createImage(img);
          
        } catch (IOException ex) {
        }
        //Image profilePic = res.getImage("log.png");
        Image profilePic = i;      
        Image mask = res.getImage("round-mask.png");
        mask = mask.scaledHeight(mask.getHeight() / 4 * 3);
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label("  Jennifer", profilePic, "SideMenuTitle");
        profilePicLabel.setMask(mask.createMask());

        Container sidemenuTop = BorderLayout.center(profilePicLabel);
        sidemenuTop.setUIID("SidemenuTop");
        
        getToolbar().addComponentToSideMenu(sidemenuTop);
        getToolbar().addMaterialCommandToSideMenu("  home", FontImage.MATERIAL_DASHBOARD,  e -> showOtherForm(res));
        getToolbar().addMaterialCommandToSideMenu("  TABLEAU BLANC", FontImage.MATERIAL_TRENDING_UP,  e -> showOtherForm(res));
        getToolbar().addMaterialCommandToSideMenu("  ", FontImage.MATERIAL_ACCESS_TIME,  e -> showOtherForm(res));
        getToolbar().addMaterialCommandToSideMenu("update profile", FontImage.MATERIAL_SETTINGS,  e -> editprofile(res));
        getToolbar().addMaterialCommandToSideMenu("  logout", FontImage.MATERIAL_EXIT_TO_APP,  e -> new LoginForm(res).show());
    }
    
    protected abstract void showOtherForm(Resources res);
    protected abstract void editprofile(Resources res);
    
}

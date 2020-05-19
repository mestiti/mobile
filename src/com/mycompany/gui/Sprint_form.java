/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.mycompany.gui.SideMenuBaseFormSM;
import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.XYMultipleSeriesDataset;
import com.codename1.charts.models.XYSeries;
import com.codename1.charts.renderers.XYMultipleSeriesRenderer;
import com.codename1.charts.renderers.XYSeriesRenderer;
import com.codename1.charts.views.CubicLineChart;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.io.File;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Sprint;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceBS;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author Asus
 */
public class Sprint_form extends SideMenuBaseFormSM {
    private static final int[] COLORS = {0xf8e478, 0x60e6ce, 0x878aee};
    private static final String[] LABELS = {"Design", "Coding", "Learning"};
    String username1;
    int id1;
    String img1;
    ServiceBS srv = new ServiceBS();
    Form current;
  

    public Sprint_form(Resources res,String username, int id,String img)  {
        super(BoxLayout.y());
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        
       ArrayList<Sprint> sprints = srv.getSprint(username);
       // System.err.println(sprints);
       int k = sprints.size();
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        
        Container remainingTasks = BoxLayout.encloseY(
                        new Label("Team", "CenterTitle"),
                        new Label(sprints.get(0).getEquipe(), "CenterSubTitle")
                );
        remainingTasks.setUIID("RemainingTasks");
        Container completedTasks = BoxLayout.encloseY(
                        new Label("Project", "CenterTitle"),
                        new Label(sprints.get(0).getProjet(), "CenterSubTitle")
        );
        completedTasks.setUIID("CompletedTasks");

        Container titleCmp = BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                        BorderLayout.west(
                                BoxLayout.encloseY(
                                    new Label("Sprints on this project", "Title"),
                                    new Label("connected as "+username, "SubTitle")
                                )
                            ),
                        GridLayout.encloseIn(2, remainingTasks, completedTasks)
                );
        
        FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
        fab.getAllStyles().setMargin(BOTTOM, completedTasks.getPreferredH() - fab.getPreferredH() / 2);
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));
                        
        add(new Label("Today", "TodayTitle"));
        
        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
        for (int i=0;i<k;i++){
        addButtonBottom(res,arrowDown, sprints.get(i).getDesc(), 0x5ae29d, true,i,username,id,sprints.get(i).getId(),img);
        }
        setupSideMenu(res,img);
        username1=username;
        id1=id;
        img1=img;
    }


    
    private void addButtonBottom(Resources res,Image arrowDown, String text, int color, boolean first,int x,String username
    ,int idu,int ids,String img) {
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
                new Sprint_form1(res,x,current,username,idu,ids,img).show();           }
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
        new ProfileFormSM(res,username1,id1,img1).show();
    }
    protected void showOtherForm1(Resources res) {
        new Sprint_form(res,username1,id1,img1).show();
    }
    
    protected void showsprint(Resources res,int id) {
        
    }
    @Override
    protected void editprofile(Resources res) {
        new EditPro_form1(res,username1,id1,img1).show();
    }

    @Override
    protected void showForm2(Resources res) {
                         new Absence_Form(res,img1,username1,id1).show();

    }
    

    

    

}

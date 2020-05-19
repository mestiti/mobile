/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import static com.codename1.charts.renderers.XYSeriesRenderer.FillOutsideLine.Type.values;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
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
import com.mycompany.myapp.services.Servicefeat;
import com.mycompany.myapp.services.Serviceuserbp;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class Chart extends SideMenuBaseFormPO{
   private static final int[] COLORS = {0xf8e478, 0x60e6ce, 0x878aee};
    private static final String[] LABELS = {"Design", "Coding", "Learning"};
    String username1;
    int id1;
    String img1;
    
    Form current;
    public Chart(Resources res,String img,String username, int id){
        
                super(BoxLayout.y());
                Font largeFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.SIZE_LARGE, Font.STYLE_PLAIN);
     Font smallFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.SIZE_SMALL, Font.STYLE_PLAIN);

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
       
       Serviceuserbp bp=new Serviceuserbp();
     
      

        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        TextField duration = new TextField("2000", "Duration", 6, TextArea.NUMERIC);
       
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        
        Container remainingTasks = BoxLayout.encloseY(
                        new Label("avancement ", "CenterTitle")
                       
                );
        remainingTasks.setUIID("RemainingTasks");
        Container completedTasks = BoxLayout.encloseY(
                        new Label("project", "CenterTitle")
                        
        );
        completedTasks.setUIID("CompletedTasks");

      // Container c = new Container();
      
 
        Container titleCmp = BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                        BorderLayout.west(
                                BoxLayout.encloseY(
                                    new Label("features on this project", "Title"),
                                    new Label("connected as "+username, "SubTitle")
                                )
                            ),
                        GridLayout.encloseIn(2, remainingTasks, completedTasks)
                );
              tb.setTitleComponent(titleCmp);
       add(new Label("", ""));  
      
       //Label l=new Label("rrr");
      Button btup=new Button("Contacter");
         btup.setUIID("LoginButton");
         btup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               Message m = new Message("en tant que "+username);
        String textAttachmentUri = null;
m.getAttachments().put(textAttachmentUri, "text/plain");
Display.getInstance().sendMessage(new String[] {"wemanage02@gmail.com"}, "Prise de rensez-vous", m);       

            }
        });
   
        
       
        add( btup);
    
        setupSideMenu(res,img);

      
        double[] values = new double[]{bp.getAlltodo(id).get(0), bp.getAlldoing(id).get(0),  bp.getAlldone(id).get(0)};

        // Set up the renderer
    int[] colors = new int[] { ColorUtil.BLACK, ColorUtil.GRAY, ColorUtil.WHITE};
    final DefaultRenderer renderer = buildCategoryRenderer(colors);
    renderer.setZoomButtonsVisible(true);
    renderer.setZoomEnabled(true);
    renderer.setBackgroundColor(0xE7ECF0);
    renderer.setChartTitleTextFont(largeFont);
    renderer.setDisplayValues(true);
    renderer.setShowLabels(true);
    
    renderer.setApplyBackgroundColor(true);
    renderer.setLabelsColor(0xDE122E);
//renderer.setAxisTitleTextSize(smallFont.getHeight()/2);
    renderer.setChartTitleTextFont(smallFont);
    renderer.setLabelsTextSize(smallFont.getHeight()/2);
    renderer.setLegendTextSize(smallFont.getHeight()/2);
        // Create the chart ... pass the values and renderer to the chart object.
        PieChart chart = new PieChart(buildCategoryDataset("Project budget", values), renderer);

        // Wrap the chart in a Component so we can add it to a form
        ChartComponent c = new ChartComponent(chart);

      
        add(c);
        // Set up the renderer
      
    }
       
        
       
     protected CategorySeries buildCategoryDataset(String title, double[] values) {
        CategorySeries series = new CategorySeries(title);
       String[] monTableau = new String[] {"todo", "doing", "done"};
String p ="";
           
            
                  /*for (double value : values) {
           
                  for(int i=0;i<monTableau.length;i++)
                  {  p=monTableau[i];}
            series.add("  "+p , value);}*/
       
 series.add("  todo" , values[0]);
 series.add("  doing" , values[1]);
 series.add("  done" , values[2]);
        return series;
    }
 private DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(5);
        renderer.setLegendTextSize(5);
        
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
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
        
         new Userbp(res,img1,username1,id1).show();
        
    }
}


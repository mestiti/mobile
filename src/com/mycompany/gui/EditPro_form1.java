/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;


import com.codename1.capture.Capture;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import static com.codename1.ui.Image.createImage;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.services.ServiceBS;
import java.io.IOException;
import java.io.OutputStream;


/**
 *
 * @author Asus
 */
public class EditPro_form1 extends Form {
      

    Form current;
    Resources res1;
    private static final int[] COLORS = {0xf8e478, 0x60e6ce, 0x878aee};
    private static final String[] LABELS = {"Title", "Coding", "Learning"};
    String pa="";

    public EditPro_form1(Resources res,String username,int id,String img) {
          this.setTitle("Edit Profile img");
   
          this.getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK_IOS,e -> new ProfileFormSM(res,username,id,img).show());
        Label l = new Label("Current image");
        Label l2 = new Label("Selected image");
        setLayout(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
Container center = new Container(new BoxLayout(BoxLayout.Y_AXIS));
Image i = null;

try {
           i = createImage(img);
           i = i.scaled(350, 350);
          
        } catch (IOException ex) {
        }
Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
Label picture = new Label("", "Container");
Image camera = FontImage.createMaterial(FontImage.MATERIAL_CAMERA, s);
this.getToolbar().addCommandToRightBar("", camera, ev -> {
             String filePath = Capture.capturePhoto();
        if (filePath != null) {
            try {
                String pathToBeStored = "file://Users/Asus/Desktop/"+ System.currentTimeMillis() +  ".jpg";
                
                Image img1 = Image.createImage(filePath);
                picture.setIcon(img1);
                revalidate();
                OutputStream os = FileSystemStorage.getInstance().openOutputStream(pathToBeStored );
                ImageIO.getImageIO().save(img1, os, ImageIO.FORMAT_JPEG, 0.9f);
                pa="C:"+pathToBeStored.substring(5);
                os.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            }
        });


        ServiceBS srv = new ServiceBS();
        Button update = new Button("update");
        update.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent evt) {
                  srv.upimg(pa, id);
                  Dialog.show("Done ", "Try to Logout and Relog To Complete " + "", "OK", null);

              }
          });

Label line1 = new Label("current image");
Label line2 = new Label("Selected image");

center.add(line1);
center.add(i);
center.add(line2);
center.add(picture);
center.add(update);

addComponent(BorderLayout.CENTER, center);
      
    }


      

    }
    


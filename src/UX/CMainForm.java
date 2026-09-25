package UX;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import data.*;
import icons.CIconLoader;
import java.awt.event.KeyEvent;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.swing.JOptionPane;

/**
 *
 * @author georgiosaslanidis
 */
public class CMainForm extends javax.swing.JFrame {

    private String JSONStringResponse;
    private WeatherData oData;
    private CWindDirection oDirection;
    
    private CIconLoader oLoader;
    
    public CMainForm() {
        initComponents();
        
        JSONStringResponse = new String();
        oDirection = new CWindDirection();
    }
    
    private void getInfo(String p_sCityName){
    
        // Replace "YOUR_API_KEY" with your actual OpenWeatherMap API key
        String apiKey = "YOUR_API_KEY";

        // The city name that the user searched for
        String sCityName = p_sCityName;

        // OpenWeatherMap API endpoint for current weather data
        String apiUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + sCityName + "&appid=" + apiKey;

        try {
            // Create a URL object with the API endpoint
            URL url = new URL(apiUrl);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method to GET
            connection.setRequestMethod("GET");

            // Get the response code
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read the response from the API
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                String response = new String();

                while ((line = reader.readLine()) != null) {
                    response += line;
                }

                reader.close();

                // Print the JSON response
                System.out.println(response.toString());
                
                //save response to translate JSON
                JSONStringResponse =  response;
            }
            else {
                
                JOptionPane.showMessageDialog(null, "Typen in a valid City!");
                System.out.println("Error: Unable to retrieve weather data. Response code: " + responseCode);
            }

            // Close the connection
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void GetWeatherImage(String p_sImageCode){
        
         try {

            String iconCode = p_sImageCode; // The weather icon

            // Construct the URL for the weather icon
            URL iconUrl = new URL("http://openweathermap.org/img/wn/" + iconCode + ".png");

            // Open connection
            HttpURLConnection connection = (HttpURLConnection) iconUrl.openConnection();

            // Set up the request method
            connection.setRequestMethod("GET");

            // Get the input stream from the connection
            try (InputStream inputStream = connection.getInputStream()) {
                // Replace "weather_icon.png" with the desired file name
                try (FileOutputStream outputStream = new FileOutputStream("weather_icon.png")) {
                    // Read from input stream and write to file
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }
            }

            // Close the connection
            connection.disconnect();
            
            //Load the icon to the UI
            oLoader = new CIconLoader("weather_icon.png", 100, 100);
        } 
         catch (Exception e) {
            e.printStackTrace();
        }
    
    }

    private void DisplayInfo(){
        
        lblCity1.setText((String) oData.GetEntry("name").Value + ", " + (String) oData.GetEntry("sys.country").Value);
        
        String Celcius = String.format("%.1f", (double) oData.GetEntry("main.temp").Value - 273.15);
        lblDegrees1.setText(Celcius + " \u2103");
        
        String Feel = String.format("%.1f", (double) oData.GetEntry("main.feels_like").Value - 273.15);
        lblFeel1.setText(Feel + " \u2103");
        
        lblOverall1.setText((String) oData.GetEntry("weather.description").Value);
        
        lblClouds1.setText(oData.GetEntry("clouds.all").Value + "%");
        
        //If visibilty is less than 1 kilometer show it with m
        //else show it as kilometers
        Double dVis = ((Number)oData.GetEntry("visibility").Value).doubleValue();
        if(dVis < 1000)
            lblVisibility.setText(Double.toString(dVis) + " m");
        else{
            
            Double dKM = dVis/1000;
            lblVisibility.setText(Double.toString(dKM) + " km");
        }
            
        
        String Speed = String.format("%.2f", (double)oData.GetEntry("wind.speed").Value * 3.6);
        lblWind1.setText(Speed + " km/h");
        
        //Get the direction of the wind
        Long lDegrees = (long)oData.GetEntry("wind.deg").Value;
        String sWindDirection = oDirection.getDirection(lDegrees);
        lblWDegrees1.setText(sWindDirection);    
        
        //Display Weather Icon
        this.GetWeatherImage((String) oData.GetEntry("weather.icon").Value);
        this.lblImage.setIcon(oLoader.getIcon());
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlRIGHT = new javax.swing.JPanel();
        lblClouds = new javax.swing.JLabel();
        lblClouds1 = new javax.swing.JLabel();
        lblWind = new javax.swing.JLabel();
        lblWind1 = new javax.swing.JLabel();
        lblWDegrees = new javax.swing.JLabel();
        lblWDegrees1 = new javax.swing.JLabel();
        lblVisibility = new javax.swing.JLabel();
        lblVisibility1 = new javax.swing.JLabel();
        pnlLEFT = new javax.swing.JPanel();
        lblDegrees1 = new javax.swing.JLabel();
        lblOverall1 = new javax.swing.JLabel();
        lblFeel = new javax.swing.JLabel();
        lblOverall = new javax.swing.JLabel();
        lblFeel1 = new javax.swing.JLabel();
        lblCity = new javax.swing.JLabel();
        lblDegrees = new javax.swing.JLabel();
        lblCity1 = new javax.swing.JLabel();
        pnlCENTER = new javax.swing.JPanel();
        lblImage = new javax.swing.JLabel();
        pnlBOTTOM = new javax.swing.JPanel();
        pnlTOP = new javax.swing.JPanel();
        txtCity = new javax.swing.JTextField();
        lblSearchCity = new javax.swing.JLabel();
        txtGo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlRIGHT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblClouds.setText("Clouds:");

        lblWind.setText("Wind Speed:");

        lblWDegrees.setText("Wind Degrees:");

        lblVisibility1.setText("Visibility:");

        javax.swing.GroupLayout pnlRIGHTLayout = new javax.swing.GroupLayout(pnlRIGHT);
        pnlRIGHT.setLayout(pnlRIGHTLayout);
        pnlRIGHTLayout.setHorizontalGroup(
            pnlRIGHTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRIGHTLayout.createSequentialGroup()
                .addGroup(pnlRIGHTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlRIGHTLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(lblClouds)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblClouds1))
                    .addGroup(pnlRIGHTLayout.createSequentialGroup()
                        .addGroup(pnlRIGHTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlRIGHTLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(pnlRIGHTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblWind)
                                    .addComponent(lblVisibility1)))
                            .addGroup(pnlRIGHTLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblWDegrees)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlRIGHTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblWind1)
                            .addComponent(lblWDegrees1)
                            .addComponent(lblVisibility))))
                .addContainerGap(160, Short.MAX_VALUE))
        );
        pnlRIGHTLayout.setVerticalGroup(
            pnlRIGHTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRIGHTLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pnlRIGHTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblClouds, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblClouds1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlRIGHTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblVisibility, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVisibility1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(pnlRIGHTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblWind, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblWind1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlRIGHTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblWDegrees, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblWDegrees1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlLEFT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblFeel.setText("Real Feel:");

        lblOverall.setText("Overall:");

        lblCity.setText("City:");

        lblDegrees.setText("Degrees:");

        javax.swing.GroupLayout pnlLEFTLayout = new javax.swing.GroupLayout(pnlLEFT);
        pnlLEFT.setLayout(pnlLEFTLayout);
        pnlLEFTLayout.setHorizontalGroup(
            pnlLEFTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLEFTLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlLEFTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlLEFTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlLEFTLayout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(lblDegrees))
                        .addComponent(lblFeel)
                        .addComponent(lblCity, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblOverall, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlLEFTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblDegrees1)
                    .addComponent(lblFeel1)
                    .addComponent(lblOverall1, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                    .addComponent(lblCity1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        pnlLEFTLayout.setVerticalGroup(
            pnlLEFTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLEFTLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pnlLEFTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCity, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCity1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(pnlLEFTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDegrees)
                    .addComponent(lblDegrees1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlLEFTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFeel, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFeel1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlLEFTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOverall1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblOverall, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pnlCENTER.setBackground(new java.awt.Color(0, 153, 255));
        pnlCENTER.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout pnlCENTERLayout = new javax.swing.GroupLayout(pnlCENTER);
        pnlCENTER.setLayout(pnlCENTERLayout);
        pnlCENTERLayout.setHorizontalGroup(
            pnlCENTERLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 310, Short.MAX_VALUE)
            .addGroup(pnlCENTERLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlCENTERLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lblImage)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        pnlCENTERLayout.setVerticalGroup(
            pnlCENTERLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 157, Short.MAX_VALUE)
            .addGroup(pnlCENTERLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlCENTERLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lblImage)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout pnlBOTTOMLayout = new javax.swing.GroupLayout(pnlBOTTOM);
        pnlBOTTOM.setLayout(pnlBOTTOMLayout);
        pnlBOTTOMLayout.setHorizontalGroup(
            pnlBOTTOMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 805, Short.MAX_VALUE)
        );
        pnlBOTTOMLayout.setVerticalGroup(
            pnlBOTTOMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 159, Short.MAX_VALUE)
        );

        pnlTOP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtCity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCityKeyPressed(evt);
            }
        });

        lblSearchCity.setText("City:");

        txtGo.setText("Go");
        txtGo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTOPLayout = new javax.swing.GroupLayout(pnlTOP);
        pnlTOP.setLayout(pnlTOPLayout);
        pnlTOPLayout.setHorizontalGroup(
            pnlTOPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTOPLayout.createSequentialGroup()
                .addGap(192, 192, 192)
                .addComponent(lblSearchCity, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCity, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(txtGo, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTOPLayout.setVerticalGroup(
            pnlTOPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTOPLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(pnlTOPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSearchCity)
                    .addComponent(txtCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pnlLEFT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCENTER, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlRIGHT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(pnlTOP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlBOTTOM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pnlTOP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlRIGHT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlCENTER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlLEFT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBOTTOM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void GO(){
        
        this.getInfo(txtCity.getText());
        oData = new WeatherData(JSONStringResponse);
        
       this.DisplayInfo();
    }
    
    private void txtGoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGoActionPerformed

        this.GO();
    }//GEN-LAST:event_txtGoActionPerformed

    private void txtCityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCityKeyPressed
       
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            
            this.GO();
        }
    }//GEN-LAST:event_txtCityKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CMainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CMainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CMainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CMainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CMainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblCity;
    private javax.swing.JLabel lblCity1;
    private javax.swing.JLabel lblClouds;
    private javax.swing.JLabel lblClouds1;
    private javax.swing.JLabel lblDegrees;
    private javax.swing.JLabel lblDegrees1;
    private javax.swing.JLabel lblFeel;
    private javax.swing.JLabel lblFeel1;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblOverall;
    private javax.swing.JLabel lblOverall1;
    private javax.swing.JLabel lblSearchCity;
    private javax.swing.JLabel lblVisibility;
    private javax.swing.JLabel lblVisibility1;
    private javax.swing.JLabel lblWDegrees;
    private javax.swing.JLabel lblWDegrees1;
    private javax.swing.JLabel lblWind;
    private javax.swing.JLabel lblWind1;
    private javax.swing.JPanel pnlBOTTOM;
    private javax.swing.JPanel pnlCENTER;
    private javax.swing.JPanel pnlLEFT;
    private javax.swing.JPanel pnlRIGHT;
    private javax.swing.JPanel pnlTOP;
    private javax.swing.JTextField txtCity;
    private javax.swing.JButton txtGo;
    // End of variables declaration//GEN-END:variables
}

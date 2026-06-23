
package icons;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;



public class CIconLoader 
{
    
    // ...................................................
    // Read-Only Property: FileName \\
    private String fileName;

    public String getFileName() {
        return fileName;
    }
    // ...................................................
    
    // ...................................................
    // Read-Only Property: Width \\
    private int width;

    public int getWidth() {
        return width;
    }
    // ...................................................

    // ...................................................
    // Read-Only Property: Height \\
    private int height;

    public int getHeight() {
        return height;
    }    
    // ...................................................
    
    
    // ...................................................
    // Read-Only Property: Icon \\
    private BufferedImage loadedImage;
    
    private ImageIcon icon;

    public ImageIcon getIcon() {
        return icon;
    }
    // ...................................................
    
    
    // -------------------------------------------------------------------------
    public CIconLoader(String p_sFileName)
    {
        this.fileName = p_sFileName;
        this.width = 32;
        this.height = 32;
        this.Load();
    }
    // -------------------------------------------------------------------------
    public CIconLoader(String p_sFileName, int p_nWidth, int p_nHeight)
    {
        
        this.fileName = p_sFileName;
        this.width = p_nWidth;
        this.height = p_nHeight;
        this.Load();
    }
    // -------------------------------------------------------------------------
    public void Load()
    {
        BufferedImage oImage = null;
        try 
        {
            File oFile = new File(this.fileName);
            this.loadedImage = ImageIO.read(oFile);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
        
        BufferedImage oResizedImage = this.resizeImage(this.loadedImage
                , this.width, this.height);
        this.icon = new ImageIcon(oResizedImage);
    }
    // -------------------------------------------------------------------------
    private BufferedImage resizeImage(BufferedImage p_oImage, int p_nNewWidth, int p_nNewHeight)
    {
        BufferedImage oNewImage = new BufferedImage(p_nNewWidth, p_nNewHeight
                                , BufferedImage.TYPE_INT_ARGB);
        Graphics2D oGfx = oNewImage.createGraphics();

        oGfx.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        oGfx.drawImage(p_oImage, 0, 0, p_nNewWidth, p_nNewHeight, null);
        oGfx.dispose();

        return oNewImage;
    }      
    // -------------------------------------------------------------------------
}

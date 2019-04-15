import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;
import java.awt.Color;

/**
 * @author Washington O. da Silva
 */

public class Menu 
{
    private GL2 m_GL2;
    private GLUT m_Glut;
    
    
    public Menu(GL2 _openGL2)
    {
        m_GL2 = _openGL2;
        m_Glut = new GLUT();
    }
    
    public void CreateText(int _x, int _y, String _text)
    {
        m_GL2.glRasterPos2f(_x, _y);        
        m_Glut.glutBitmapString(GLUT.BITMAP_8_BY_13, _text);
    }
    
    public void CreateText(int _x, int _y, String _text, Color _color)
    {
        m_GL2.glColor3f(_color.getRed(), _color.getGreen(), _color.getBlue());
        m_GL2.glRasterPos2f(_x, _y);        
        m_Glut.glutBitmapString(GLUT.BITMAP_8_BY_13, _text);
    }
    
    public void CreateText(int _x, int _y, String _text, int _font)
    {
        m_GL2.glRasterPos2f(_x, _y);   
        
        switch(_font)
        {
            case 2:
                m_Glut.glutBitmapString(GLUT.BITMAP_9_BY_15, _text);
                break;
            case 3:
                m_Glut.glutBitmapString(GLUT.BITMAP_8_BY_13, _text);
                break;
            case 4:
                m_Glut.glutBitmapString(GLUT.BITMAP_TIMES_ROMAN_10, _text);
                break;
            case 5:
                m_Glut.glutBitmapString(GLUT.BITMAP_TIMES_ROMAN_24, _text);
                break;
            case 6:
                m_Glut.glutBitmapString(GLUT.BITMAP_HELVETICA_10, _text);
                break;
            case 7:
                m_Glut.glutBitmapString(GLUT.BITMAP_HELVETICA_12, _text);
                break;
            case 8:
                m_Glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, _text);
                break;
            default:
                m_Glut.glutBitmapString(GLUT.BITMAP_8_BY_13, _text);
        }
    }
    
    public void CreateText(int _x, int _y, String _text, Color _color, int _font)
    {
        m_GL2.glColor3f(_color.getRed(), _color.getGreen(), _color.getBlue());
        m_GL2.glRasterPos2f(_x, _y);    
        
        switch(_font)
        {
            case 2:
                m_Glut.glutBitmapString(GLUT.BITMAP_9_BY_15, _text);
                break;
            case 3:
                m_Glut.glutBitmapString(GLUT.BITMAP_8_BY_13, _text);
                break;
            case 4:
                m_Glut.glutBitmapString(GLUT.BITMAP_TIMES_ROMAN_10, _text);
                break;
            case 5:
                m_Glut.glutBitmapString(GLUT.BITMAP_TIMES_ROMAN_24, _text);
                break;
            case 6:
                m_Glut.glutBitmapString(GLUT.BITMAP_HELVETICA_10, _text);
                break;
            case 7:
                m_Glut.glutBitmapString(GLUT.BITMAP_HELVETICA_12, _text);
                break;
            case 8:
                m_Glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, _text);
                break;
            default:
                m_Glut.glutBitmapString(GLUT.BITMAP_8_BY_13, _text);
        }
    }
    
    public void CreateLogo()
    {
        DrawT();
        DrawE();
        DrawI();
        DrawR();
        DrawS();
    }
    
    private void DrawT()
    {
        int posX = -60;
        int posY = 50;
        
        for(int i = 0; i < 3; i++)
        {
             m_GL2.glPushMatrix();
             m_GL2.glTranslatef(posX, posY, 0);
             m_GL2.glColor3f(1, 0, 0);
             m_Glut.glutSolidCube(5);
             m_GL2.glPopMatrix();
             posX += 5;
        }
        
        posX = -55;
        posY = 45;
        
        for(int i = 0; i < 4; i++)
        {
             m_GL2.glPushMatrix();
             m_GL2.glTranslatef(posX, posY, 0);
             m_GL2.glColor3f(1, 0, 0);
             m_Glut.glutSolidCube(5);
             m_GL2.glPopMatrix();
             posY -= 5;
        }
        
        posX = -25;
        posY = 50;
        
        for(int i = 0; i < 3; i++)
        {
             m_GL2.glPushMatrix();
             m_GL2.glTranslatef(posX, posY, 0);
             m_GL2.glColor3f(1, 0, 0);
             m_Glut.glutSolidCube(5);
             m_GL2.glPopMatrix();
             posX += 5;
        }
        
        posX = -20;
        posY = 45;
        
        for(int i = 0; i < 4; i++)
        {
             m_GL2.glPushMatrix();
             m_GL2.glTranslatef(posX, posY, 0);
             m_GL2.glColor3f(1, 0, 0);
             m_Glut.glutSolidCube(5);
             m_GL2.glPopMatrix();
             posY -= 5;
        }
    }
    
    private void DrawI()
    {
        int posX = 15;
        int posY = 50;
        
        for(int i = 0; i < 5; i++)
        {
             m_GL2.glPushMatrix();
             m_GL2.glTranslatef(posX, posY, 0);
             m_GL2.glColor3f(0, 0, 1);
             m_Glut.glutSolidCube(5);
             m_GL2.glPopMatrix();
             posY -= 5;
        }
    }
    
    private void DrawS()
    {
        int posX = 30;
        int posY = 50;
        
        for(int z = 0; z < 3; z++)
        {
            for(int i = 0; i < 2; i++)
            {
                 m_GL2.glPushMatrix();
                 m_GL2.glTranslatef(posX, posY, 0);
                 m_GL2.glColor3f(0, 1, 1);
                 m_Glut.glutSolidCube(5);
                 m_GL2.glPopMatrix();
                 posX -= 5;
            }
            
            posX = 30;
            posY -= 10;
        }
        
        posX = 25;
        posY = 50;
        
        for(int z= 0; z < 2; z++)
        {
            for(int i = 0; i < 3; i++)
            {
                 m_GL2.glPushMatrix();
                 m_GL2.glTranslatef(posX, posY, 0);
                 m_GL2.glColor3f(0, 1, 1);
                 m_Glut.glutSolidCube(5);
                 m_GL2.glPopMatrix();
                 posY -= 5;
            }
            
            posX = 30;
            posY = 40;
        }
    }
    
    private void DrawE()
    {
        int posX = -40;
        int posY = 50;
        
        for(int i = 0; i < 5; i++)
        {
             m_GL2.glPushMatrix();
             m_GL2.glTranslatef(posX, posY, 0);
             m_GL2.glColor3f(0, 1, 0);
             m_Glut.glutSolidCube(5);
             m_GL2.glPopMatrix();
             posY -= 5;
        }
        
        posX = -35;
        posY = 50;
        
        for(int z = 0; z < 3; z++)
        {
            for(int i = 0; i < 2; i++)
            {
                 m_GL2.glPushMatrix();
                 m_GL2.glTranslatef(posX, posY, 0);
                 m_GL2.glColor3f(0, 1, 0);
                 m_Glut.glutSolidCube(5);
                 m_GL2.glPopMatrix();
                 posX -= 5;
            }
            
            posX = -35;
            posY -= 10;
        }
    }
    
    private void DrawR()
    {
        int posX = -5;
        int posY = 50;
        
        for(int i = 0; i < 5; i++)
        {
             m_GL2.glPushMatrix();
             m_GL2.glTranslatef(posX, posY, 0);
             m_GL2.glColor3f(1, 1, 0);
             m_Glut.glutSolidCube(5);
             m_GL2.glPopMatrix();
             posY -= 5;
        }
        
        posX = 0;
        posY = 50;
        
        for(int z = 0; z < 2; z++)
        {
            for(int i = 0; i < 1; i++)
            {
                 m_GL2.glPushMatrix();
                 m_GL2.glTranslatef(posX, posY, 0);
                 m_GL2.glColor3f(1, 1, 0);
                 m_Glut.glutSolidCube(5);
                 m_GL2.glPopMatrix();
                 posX += 5;
            }
            
            posX = 0;
            posY -= 10;
        }
        
        posX = 5;
        posY = 50;
        
        for(int i = 0; i < 3; i++)
        {
             m_GL2.glPushMatrix();
             m_GL2.glTranslatef(posX, posY, 0);
             m_GL2.glColor3f(1, 1, 0);
             m_Glut.glutSolidCube(5);
             m_GL2.glPopMatrix();
             posY -= 5;
        }
        
        posX = 0;
        posY = 35;
        
        for(int i = 0; i < 2; i++)
        {
             m_GL2.glPushMatrix();
             m_GL2.glTranslatef(posX, posY, 0);
             m_GL2.glColor3f(1, 1, 0);
             m_Glut.glutSolidCube(5);
             m_GL2.glPopMatrix();
             posY -= 5;
             posX += 5;
        }
    }
    
    public void SelectPoint(int _pos)
    {
        switch(_pos)
        {
            case 1:
                m_GL2.glTranslatef(-10, -50, 0);
                break;
            case 2:
                m_GL2.glTranslatef(-10, -60, 0);
                break;
            case 3:
                m_GL2.glTranslatef(-10, -70, 0);
                break;
        }
        m_GL2.glColor3f(1, 1, 1);
        m_Glut.glutSolidCube(2);
    }
}
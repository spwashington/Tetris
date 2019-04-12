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
}
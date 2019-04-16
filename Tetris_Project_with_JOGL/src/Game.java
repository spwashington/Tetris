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

public class Game implements GLEventListener, KeyListener
{
    private boolean m_Ingame;
    private GL2 m_GL2;
    private GLUT m_GLUT;
    private Menu m_Menu;
    private Ingame m_Game;
    private int m_Selection;
    
    @Override
    public void init(GLAutoDrawable _drawable) 
    {
        m_GL2 = _drawable.getGL().getGL2();
        m_GL2.glEnable(GL2.GL_DEPTH_TEST);
        m_GLUT = new GLUT();
        m_Menu = new Menu(m_GL2);
        m_Game = new Ingame(m_GL2);
        m_Ingame = false;
        m_Selection = 1;
    }

    @Override
    public void dispose(GLAutoDrawable _drawable) 
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void display(GLAutoDrawable _drawable) 
    {
        m_GL2 = _drawable.getGL().getGL2();  
        m_GLUT = new GLUT();
        m_GL2.glClearColor(0, 0, 0, 1);        
        m_GL2.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);       
        m_GL2.glLoadIdentity();
        
        
        //Game Logic
        if(!m_Ingame)
            MenuScreen();
        else
            GameScreen();
        
        
        //End Game
        
        m_GL2.glFlush();
    }
    
    public void MenuScreen()
    {
        m_Menu.CreateLogo();
        m_Menu.CreateText(-5, 95, "HIGH SCORE", Color.white, 7);
        m_Menu.CreateText(-5, 90, Integer.toString(m_Game.GetHighScore()), Color.white, 1);
        m_Menu.CreateText(-5, -50, "Start Game", Color.white, 8);
        m_Menu.CreateText(-5, -60, "How to Play", Color.white, 8);
        m_Menu.CreateText(-5, -70, "Exit", Color.white, 8);
        m_Menu.SelectPoint(m_Selection);
    }
    
    public void GameScreen()
    {
        
    }

    @Override
    public void reshape(GLAutoDrawable _drawable, int _x, int _y, int _width, int _height) 
    {
        GL2 m_GL2 = _drawable.getGL().getGL2();        
        m_GL2.glMatrixMode(GL2.GL_PROJECTION);      
        m_GL2.glLoadIdentity();
        m_GL2.glOrtho(-100,100,-100,100,-100,100);
        m_GL2.glMatrixMode(GL2.GL_MODELVIEW);
    }

    @Override
    public void keyPressed(KeyEvent _event) 
    {
        switch(_event.getKeyCode())
        {
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
            case KeyEvent.VK_UP:
                if(m_Selection > 1)
                    m_Selection--;
                break;
            case KeyEvent.VK_DOWN:
                if(m_Selection < 3)
                    m_Selection++;
                break;
            case KeyEvent.VK_ENTER:
                if(!m_Ingame)
                {
                    switch(m_Selection)
                    {
                        case 1:
                            m_Ingame = true;
                            break;
                        case 3:
                            System.exit(0);
                            break;
                    }
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent _event) 
    {
    }
}
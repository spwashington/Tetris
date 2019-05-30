import com.jogamp.newt.Screen;
import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;
import java.awt.Color;
import java.awt.Window;

/**
 * @author Washington O. da Silva
 */

public class Game implements GLEventListener, KeyListener
{
    private boolean m_Ingame;
    private boolean m_HowToPlay;
    private GL2 m_GL2;
    private GLUT m_GLUT;
    private Menu m_Menu;
    private Ingame m_Game;
    private int m_Selection;
   
    private float[] m_AmbientLight;
    private float m_DiffuseLight[];
    private float m_SpecularLight[];
    private float m_LightPosition[];
    private float m_Specular[];
    private int m_SpecularMaterial;
    

    
    @Override
    public void init(GLAutoDrawable _drawable) 
    {
        m_GL2 = _drawable.getGL().getGL2();
        m_GL2.glEnable(GL2.GL_DEPTH_TEST);
        m_GLUT = new GLUT();
        m_Menu = new Menu(m_GL2);
        m_Game = new Ingame(m_GL2);
        m_Ingame = false;
        m_HowToPlay = false;
        m_Selection = 1;
        
        m_AmbientLight = new float[]{0.1f, 0.1f, 0.1f, 1.0f};
        m_DiffuseLight = new float[]{1.0f, 1.0f, 1.0f, 1.0f};
        m_SpecularLight = new float[]{1.0f, 1.0f, 1.0f, 1.0f};
        m_LightPosition = new float[]{0.0f, 70.0f, 100.0f, 1.0f};
        m_Specular = new float[]{1.0f, 1.0f, 1.0f, 1.0f};
        m_SpecularMaterial = 60;

    }

    @Override
    public void dispose(GLAutoDrawable _drawable) 
    {
    }

    @Override
    public void display(GLAutoDrawable _drawable) 
    {
        m_GL2 = _drawable.getGL().getGL2();  
        m_GLUT = new GLUT();
        m_GL2.glClearColor(0, 0, 0, 1);        
        m_GL2.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);       
        m_GL2.glLoadIdentity();
        Brightest();
        
        //Game Logic
        if(!m_HowToPlay)
        {
            if(!m_Ingame)
                MenuScreen();
            else
            {
                m_AmbientLight = new float[]{0.5f, 0.5f, 0.5f, 1.0f};
                m_LightPosition = new float[]{0.0f, 0.0f, 0.0f, 0.0f};
                GameScreen();
            }
        }
        else
            HowToPlay();
        
        m_GL2.glFlush();
    }
    
    public void MenuScreen()
    {
        ActiveLight(true);
        m_Menu.CreateLogo();
        ActiveLight(false);
        m_Menu.CreateText(-5, 95, "HIGH SCORE", Color.white, 8);
        m_Menu.CreateText(2, 90, Integer.toString(m_Game.GetHighScore()), Color.white, 1);
        m_Menu.CreateText(-5, -50, "Start Game", Color.white, 8);
        m_Menu.CreateText(-5, -60, "How to Play", Color.white, 8);
        m_Menu.CreateText(-5, -70, "Exit", Color.white, 8);
        m_Menu.SelectPoint(m_Selection);
    }
    
    public void GameScreen()
    {
        ActiveLight(true);
        m_Game.CreateScene();
        m_Game.Execute();
        ActiveLight(false);
        m_Menu.CreateText(35, 45, "Points", Color.white, 8);
        m_Menu.CreateText(35, 40, Integer.toString(m_Game.GetPoints()), Color.white, 8);
        m_Menu.CreateText(40, -5, "Next Piece", Color.white, 7);
        
        if(m_Game.IsGameOver())
        {
            m_Menu.CreateText(35, -20, "GAME OVER", Color.red, 8);
            m_Menu.CreateText(35, -25, "Press ENTER to return to Main Menu", Color.white, 7);
        }
    }
    
    public void HowToPlay()
    {
        m_Selection = 2;
        ActiveLight(true);
        m_Menu.CreateLogo();
        ActiveLight(false);
        m_Menu.CreateText(-10, -20, "HOW TO PLAY", Color.yellow, 8);
        m_Menu.CreateText(-20, -30, "1. Use arrows left and right to move a piece", Color.white, 7);
        m_Menu.CreateText(-20, -35, "2. Complete a line with piece to give 100 points", Color.white, 7);
        m_Menu.CreateText(-20, -40, "3. After complete a line with pieces, this line will be removed", Color.white, 7);
        m_Menu.CreateText(-20, -45, "4. If you place a piece in the out top, you lose", Color.white, 7);
        m_Menu.CreateText(-5, -60, "Back", Color.white, 8);
        m_Menu.SelectPoint(m_Selection);
    }
    
    public void Brightest() 
    {
        m_GL2.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, m_Specular, 0);
        m_GL2.glMateriali(GL2.GL_FRONT, GL2.GL_SHININESS, m_SpecularMaterial);
        m_GL2.glLightModelfv(GL2.GL_LIGHT_MODEL_AMBIENT, m_AmbientLight, 0);
        m_GL2.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, m_AmbientLight, 0);
        m_GL2.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, m_DiffuseLight, 0);
        m_GL2.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPECULAR, m_SpecularLight, 0);
        m_GL2.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, m_LightPosition, 0);
    }

    public void ActiveLight(boolean _enable) 
    {
        if(_enable)
        {
            m_GL2.glEnable(GL2.GL_COLOR_MATERIAL);
            m_GL2.glEnable(GL2.GL_LIGHTING);
            m_GL2.glEnable(GL2.GL_LIGHT0);
            m_GL2.glShadeModel(GL2.GL_FLAT);
        }
        else
        {
            m_GL2.glDisable(GL2.GL_COLOR_MATERIAL);
            m_GL2.glDisable(GL2.GL_LIGHTING);
            m_GL2.glDisable(GL2.GL_LIGHT0);
            m_GL2.glShadeModel(GL2.GL_FLAT);
        }
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
                KeyUp();
                break;
                
            case KeyEvent.VK_DOWN:
                KeyDown();
                break;
                
            case KeyEvent.VK_ENTER:
                KeyEnter();
                break;
                
            case KeyEvent.VK_LEFT:
                KeyLeft();
                break;
                
            case KeyEvent.VK_RIGHT:
                KeyRight();
                break;
        }
    }
    
    private void KeyEnter()
    {
        if(!m_Ingame)
        {
            switch(m_Selection)
            {
                case 1:
                    m_Ingame = true;
                    m_Game.NewGame();
                    break;
                case 2:
                    m_HowToPlay = !m_HowToPlay;
                    break;
                case 3:
                    System.exit(0);
                    break;
            }
        }
        
        if(m_Game.IsGameOver())
        {
            m_Ingame = false;
            ActiveLight(true);
        }
    }
    
    private void KeyLeft()
    {
        if(m_Ingame && !m_Game.IsGameOver())
            m_Game.MoveLeft();
    }
    
    private void KeyRight()
    {
        if(m_Ingame && !m_Game.IsGameOver())
            m_Game.MoveRight();
    }
    
    private void KeyDown() 
    {
        if(m_Selection < 3 && !m_Ingame)
            m_Selection++;
        
        if(m_Ingame && !m_Game.IsGameOver())
            m_Game.FastDropPiece();
    }
    
    private void KeyUp()
    {
        if(m_Selection > 1 && !m_Ingame)
            m_Selection--;
        
        if(m_Ingame && !m_Game.IsGameOver())
            m_Game.RotatePiece();
    }
    
    @Override
    public void keyReleased(KeyEvent _event) 
    {        
        switch(_event.getKeyCode())
        {
            case KeyEvent.VK_DOWN:
                m_Game.NormalDropPiece();
                break;
        }
    }
}
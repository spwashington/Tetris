import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;

/**
 * @author Washington O. da Silva
 */

public class Game implements GLEventListener, KeyListener
{
    @Override
    public void init(GLAutoDrawable drawable) 
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void dispose(GLAutoDrawable drawable) 
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void display(GLAutoDrawable drawable) 
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) 
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyPressed(KeyEvent _event) 
    {
        switch(_event.getKeyCode())
        {
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;
import java.awt.Color;
import java.util.Random;

/**
 * @author Washington O. da Silva
 */

public class Piece 
{
    private GL2 m_GL2;
    private GLUT m_Glut;
    private int m_PosX;
    private int m_PosY;
    
    public Piece(GL2 _openGL2, String _pieceType)
    {
        m_GL2 = _openGL2;
        m_Glut = new GLUT();
        m_PosX = -20;
        m_PosY = 45;
    }
    
    public void DropPiece(int _posX, int _posY, String _pieceType)
    {
        m_GL2.glPushMatrix();
        m_GL2.glTranslatef(_posX, _posY, 0);
        m_GL2.glColor3f(1, 1, 1);
        m_Glut.glutSolidCube(5);
        m_GL2.glPopMatrix();
    }
    
    public void DrawPiece(int[][] _board)
    {
        for(int x = 0; x < _board.length; x++)
        {
            for(int y = 0; y < _board[x].length; y++)
            {
                if(_board[x][y] == 1)
                    AddPieceInBoard(GetBoardPos(x, y)[0], GetBoardPos(x, y)[1]);
            }
        }
    }
    
    private void AddPieceInBoard(int _posX, int _posY)
    {
        m_GL2.glPushMatrix();
        m_GL2.glTranslatef(_posX, _posY, 0);
        m_GL2.glColor3f(0, 1, 0);
        m_Glut.glutSolidCube(5);
        m_GL2.glPopMatrix();
    }
    
    private int[] GetBoardPos(int _posX, int _posY)
    {
        int pos [] = new int[2];
        
        pos[0] = -20;
        pos[1] = 45;
        
        for(int i = 0; i != _posX; i++)
        {
            pos[0] += 5;
        }
        
        for(int i = 0; i != _posY; i++)
        {
            pos[1] -= 5;
        }
        
        return pos;
    }
}
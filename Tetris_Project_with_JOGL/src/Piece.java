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
    private int [] m_PieceLimit;
    
    public Piece(GL2 _openGL2, String _pieceType)
    {
        m_GL2 = _openGL2;
        m_Glut = new GLUT();
        m_PosX = -20;
        m_PosY = 45;
        m_PieceLimit = new int[2];
    }
    
    public void DropPiece(int _posX, int _posY, String _pieceType)
    {
        DefinePiece(_pieceType, _posX, _posY);
    }
    
    private void DefinePiece(String _pieceType, int _posX, int _posY)
    {
        switch(_pieceType)
        {
            case "Block":
                DrawBlock(_posX, _posY);
                break;
            case "Quad":
                DrawQuad(_posX, _posY);
                break;
            case "Zl":
                DrawZL(_posX, _posY);
                break;
            case "Zr":
                DrawZR(_posX, _posY);
                break;
            case "Tower":
                DrawTower(_posX, _posY);
                break;
            case "T":
                DrawT(_posX, _posY);
                break;
            case "Pl":
                DrawPL(_posX, _posY);
                break;
            case "Pr":
                DrawPR(_posX, _posY);
                break;
        }
    }
    
    private void DrawBlock(int _posX, int _posY)
    {
        m_GL2.glPushMatrix();
        m_GL2.glTranslatef(_posX, _posY, 0);
        m_GL2.glColor3f(1, 1, 1);
        m_Glut.glutSolidCube(5);
        m_GL2.glPopMatrix();
    }
    
    private void DrawPL(int _posX, int _posY)
    {
        m_GL2.glPushMatrix();
        m_GL2.glTranslatef(_posX, _posY, 0);
        m_GL2.glColor3f(1, 1, 1);
        m_Glut.glutSolidCube(5);
        m_GL2.glPopMatrix();
        
        m_GL2.glPushMatrix();
        m_GL2.glTranslatef(_posX, _posY + 5, 0);
        m_GL2.glColor3f(1, 1, 1);
        m_Glut.glutSolidCube(5);
        m_GL2.glPopMatrix();
        
        m_GL2.glPushMatrix();
        m_GL2.glTranslatef(_posX, _posY + 10, 0);
        m_GL2.glColor3f(1, 1, 1);
        m_Glut.glutSolidCube(5);
        m_GL2.glPopMatrix();
        
        m_GL2.glPushMatrix();
        m_GL2.glTranslatef(_posX - 5, _posY + 10, 0);
        m_GL2.glColor3f(1, 1, 1);
        m_Glut.glutSolidCube(5);
        m_GL2.glPopMatrix();
    }
    
    private void DrawPR(int _posX, int _posY)
    {
        m_GL2.glPushMatrix();
        m_GL2.glTranslatef(_posX, _posY, 0);
        m_GL2.glColor3f(1, 1, 1);
        m_Glut.glutSolidCube(5);
        m_GL2.glPopMatrix();
        
        m_GL2.glPushMatrix();
        m_GL2.glTranslatef(_posX, _posY + 5, 0);
        m_GL2.glColor3f(1, 1, 1);
        m_Glut.glutSolidCube(5);
        m_GL2.glPopMatrix();
        
        m_GL2.glPushMatrix();
        m_GL2.glTranslatef(_posX, _posY + 10, 0);
        m_GL2.glColor3f(1, 1, 1);
        m_Glut.glutSolidCube(5);
        m_GL2.glPopMatrix();
        
        m_GL2.glPushMatrix();
        m_GL2.glTranslatef(_posX + 5, _posY + 10, 0);
        m_GL2.glColor3f(1, 1, 1);
        m_Glut.glutSolidCube(5);
        m_GL2.glPopMatrix();
    }
    
    private void DrawT(int _posX, int _posY)
    {
        m_GL2.glPushMatrix();
        m_GL2.glTranslatef(_posX, _posY, 0);
        m_GL2.glColor3f(1, 1, 1);
        m_Glut.glutSolidCube(5);
        m_GL2.glPopMatrix();
        
        m_GL2.glPushMatrix();
        m_GL2.glTranslatef(_posX, _posY + 5, 0);
        m_GL2.glColor3f(1, 1, 1);
        m_Glut.glutSolidCube(5);
        m_GL2.glPopMatrix();
        
        m_GL2.glPushMatrix();
        m_GL2.glTranslatef(_posX + 5, _posY + 5, 0);
        m_GL2.glColor3f(1, 1, 1);
        m_Glut.glutSolidCube(5);
        m_GL2.glPopMatrix();
        
        m_GL2.glPushMatrix();
        m_GL2.glTranslatef(_posX - 5, _posY + 5, 0);
        m_GL2.glColor3f(1, 1, 1);
        m_Glut.glutSolidCube(5);
        m_GL2.glPopMatrix();
    }
    
    private void DrawQuad(int _posX, int _posY)
    {
        m_GL2.glPushMatrix();
        m_GL2.glTranslatef(_posX, _posY, 0);
        m_GL2.glColor3f(1, 1, 1);
        m_Glut.glutSolidCube(5);
        m_GL2.glPopMatrix();
        
        m_GL2.glPushMatrix();
        m_GL2.glTranslatef(_posX + 5, _posY, 0);
        m_GL2.glColor3f(1, 1, 1);
        m_Glut.glutSolidCube(5);
        m_GL2.glPopMatrix();
        
        m_GL2.glPushMatrix();
        m_GL2.glTranslatef(_posX, _posY + 5, 0);
        m_GL2.glColor3f(1, 1, 1);
        m_Glut.glutSolidCube(5);
        m_GL2.glPopMatrix();
        
        m_GL2.glPushMatrix();
        m_GL2.glTranslatef(_posX + 5, _posY + 5, 0);
        m_GL2.glColor3f(1, 1, 1);
        m_Glut.glutSolidCube(5);
        m_GL2.glPopMatrix();
    }
    
    private void DrawZL(int _posX, int _posY)
    {
        m_GL2.glPushMatrix();
        m_GL2.glTranslatef(_posX, _posY, 0);
        m_GL2.glColor3f(1, 1, 1);
        m_Glut.glutSolidCube(5);
        m_GL2.glPopMatrix();
        
        m_GL2.glPushMatrix();
        m_GL2.glTranslatef(_posX + 5, _posY, 0);
        m_GL2.glColor3f(1, 1, 1);
        m_Glut.glutSolidCube(5);
        m_GL2.glPopMatrix();
        
        m_GL2.glPushMatrix();
        m_GL2.glTranslatef(_posX, _posY + 5, 0);
        m_GL2.glColor3f(1, 1, 1);
        m_Glut.glutSolidCube(5);
        m_GL2.glPopMatrix();
        
        m_GL2.glPushMatrix();
        m_GL2.glTranslatef(_posX - 5, _posY + 5, 0);
        m_GL2.glColor3f(1, 1, 1);
        m_Glut.glutSolidCube(5);
        m_GL2.glPopMatrix();
    }
    
    private void DrawZR(int _posX, int _posY)
    {
        m_GL2.glPushMatrix();
        m_GL2.glTranslatef(_posX, _posY, 0);
        m_GL2.glColor3f(1, 1, 1);
        m_Glut.glutSolidCube(5);
        m_GL2.glPopMatrix();
        
        m_GL2.glPushMatrix();
        m_GL2.glTranslatef(_posX + 5, _posY, 0);
        m_GL2.glColor3f(1, 1, 1);
        m_Glut.glutSolidCube(5);
        m_GL2.glPopMatrix();
        
        m_GL2.glPushMatrix();
        m_GL2.glTranslatef(_posX + 5, _posY + 5, 0);
        m_GL2.glColor3f(1, 1, 1);
        m_Glut.glutSolidCube(5);
        m_GL2.glPopMatrix();
        
        m_GL2.glPushMatrix();
        m_GL2.glTranslatef(_posX + 10, _posY + 5, 0);
        m_GL2.glColor3f(1, 1, 1);
        m_Glut.glutSolidCube(5);
        m_GL2.glPopMatrix();
    }
    
    private void DrawTower(int _posX, int _posY)
    {
        m_GL2.glPushMatrix();
        m_GL2.glTranslatef(_posX, _posY, 0);
        m_GL2.glColor3f(1, 1, 1);
        m_Glut.glutSolidCube(5);
        m_GL2.glPopMatrix();
        
        m_GL2.glPushMatrix();
        m_GL2.glTranslatef(_posX, _posY + 5, 0);
        m_GL2.glColor3f(1, 1, 1);
        m_Glut.glutSolidCube(5);
        m_GL2.glPopMatrix();
        
        m_GL2.glPushMatrix();
        m_GL2.glTranslatef(_posX, _posY + 10, 0);
        m_GL2.glColor3f(1, 1, 1);
        m_Glut.glutSolidCube(5);
        m_GL2.glPopMatrix();
        
        m_GL2.glPushMatrix();
        m_GL2.glTranslatef(_posX, _posY + 15, 0);
        m_GL2.glColor3f(1, 1, 1);
        m_Glut.glutSolidCube(5);
        m_GL2.glPopMatrix();
    }
    
    public void DrawPieceInBoard(int[][] _board)
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
    
    public int[] GetPieceLimit(String _CurrentPiece)
    {
        switch(_CurrentPiece)
        {
            case "Block":
                m_PieceLimit[0] = 1; //Right
                m_PieceLimit[1] = 1; //Left
                return m_PieceLimit;
            case "Quad":
                m_PieceLimit[0] = 2;
                m_PieceLimit[1] = 1;
                return m_PieceLimit;
            case "Zl":
                m_PieceLimit[0] = 2;
                m_PieceLimit[1] = 2;
                return m_PieceLimit;
            case "Zr":
                m_PieceLimit[0] = 3;
                m_PieceLimit[1] = 1;
                return m_PieceLimit;
            case "Tower":
                m_PieceLimit[0] = 1;
                m_PieceLimit[1] = 1;
                return m_PieceLimit;
            case "T":
                m_PieceLimit[0] = 2;
                m_PieceLimit[1] = 2;
                return m_PieceLimit;
            case "Pl":
                m_PieceLimit[0] = 1;
                m_PieceLimit[1] = 2;
                return m_PieceLimit;
            case "Pr":
                m_PieceLimit[0] = 2;
                m_PieceLimit[1] = 1;
                return m_PieceLimit;
            default:
                m_PieceLimit[0] = 0;
                m_PieceLimit[1] = 0;
                return m_PieceLimit;
        }
    }
}

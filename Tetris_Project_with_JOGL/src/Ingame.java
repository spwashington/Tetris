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

public class Ingame
{
    private GL2 m_GL2;
    private GLUT m_Glut;
    private int m_Points;
    private int m_HighScore;
    private int m_Grid [][];
    private int m_MatrixPosX;
    private int m_MatrixPosY;
    private int m_PosX;
    private int m_PosY;
    private String m_NextPiece;
    private Piece m_Piece;
    private float m_UpdateTimer;
    
    public Ingame(GL2 _openGL2)
    {
        m_GL2 = _openGL2;
        m_Glut = new GLUT();
        m_Points = 0;
        m_HighScore = 0;
        m_PosX = -20;
        m_PosY = 50; //Lugar a cima, o valido é 45
        m_Grid = new int[10][20];
        m_MatrixPosX = 0;
        m_MatrixPosY = 0;
        m_NextPiece = NextPiece();
        m_Piece = new Piece(_openGL2, m_NextPiece);
        m_UpdateTimer = 0;
        
        CreateBoard();
    }
    
    public void NewGame()
    {
        m_Points = 0;
        m_PosX = -20;
        m_PosY = 50; //Lugar a cima, o valido é 45
        m_MatrixPosX = 0;
        m_MatrixPosY = 0;
        m_NextPiece = NextPiece();
        m_UpdateTimer = 0;
        
        CreateBoard();
    }
    
    public void CreateScene()
    {
        m_GL2.glLineWidth(10.0f);
        m_GL2.glBegin(GL2.GL_LINES);
        m_GL2.glColor3f(1,0,0); 
        
        m_GL2.glVertex2f(-23.5f, 45f);
        m_GL2.glVertex2f(-23.5f, -55f);
        
        m_GL2.glVertex2f(28.5f, 45f);
        m_GL2.glVertex2f(28.5f, -55f);
        
        m_GL2.glVertex2f(-23.5f, -55f);
        m_GL2.glVertex2f(28.5f, -55f);
        m_GL2.glEnd();
        
        m_GL2.glLineWidth(1.0f);
        m_GL2.glBegin(GL2.GL_LINES);
        m_GL2.glColor3f(1,0,1); 
        m_GL2.glVertex2f(-23.5f, 45f);
        m_GL2.glVertex2f(28.5f, 45f);
        
        m_GL2.glColor3f(0,0,1); 
        m_GL2.glVertex2f(35f, 30f);
        m_GL2.glVertex2f(35f, 0f);
        
        m_GL2.glVertex2f(35f, 30f);
        m_GL2.glVertex2f(55f, 30f);
        
        m_GL2.glVertex2f(55f, 30f);
        m_GL2.glVertex2f(55f, 0f);
        
        m_GL2.glVertex2f(55f, 0f);
        m_GL2.glVertex2f(35f, 0f);
        m_GL2.glEnd();  
    }
    
    public int GetHighScore()
    {
        return m_HighScore;
    }
    
    public int GetPoints()
    {
        return m_Points;
    }
    
    public void MoveLeft()
    {
        if(m_PosX > -20 && LeftCanMove())
        {
            m_PosX -= 5;
            m_MatrixPosX--;
        }
    }
    
    public void MoveRight()
    {
        if(m_PosX < 25 && RightCanMove())
        {
            m_PosX += 5;
            m_MatrixPosX++;
        }
    }
    
    private boolean LeftCanMove()
    {
        if( m_MatrixPosX > 0)
        {
            if(m_Grid[m_MatrixPosX-1][m_MatrixPosY] == 0)
                return true;
            else
                return false;
        }
        else
            return false;
    }
    
    private boolean RightCanMove()
    {
        if( m_MatrixPosX < 19)
        {
            if(m_Grid[m_MatrixPosX+1][m_MatrixPosY] == 0)
                return true;
            else
                return false;
        }
        else
            return false;
    }
    
    public void FastDropPiece()
    {
        if(m_PosY > -45)
        {
            m_PosY -= 5;
            m_MatrixPosY++;
        }
    }
    
    public void Execute()
    {
        m_Piece.DrawPiece(m_Grid);
        
        if(!IsGameOver())
            DropPiece();
        
        //Sorteia peça
        //Desenha peça na tela next
        
        //Ver peça que ta na fila
        //Desenhar na tela
        //Sortear outra peça
        //desenha nova peça
        
    }
    
    private void DropPiece()
    {
        MakePoints();
        UpdatePiece();
        CheckPieceBellow();
    }
    
    private void UpdatePiece()
    {
        m_Piece.DropPiece(m_PosX, m_PosY, m_NextPiece);
        m_UpdateTimer += 0.1f;
        
        if(m_UpdateTimer >= 5f)
        {
            if( m_PosY < 50)
                m_MatrixPosY++;
            
            m_PosY -= 5;
            m_UpdateTimer = 0;
        }
    }
    
    private void CheckPieceBellow()
    {
        if(m_MatrixPosY < 19)
        {
            if(m_Grid[m_MatrixPosX][m_MatrixPosY+1] == 1)
            {
               m_Grid[m_MatrixPosX][m_MatrixPosY] = 1;
               ResetValues();
            }
        }
        if(m_MatrixPosY == (m_Grid[m_MatrixPosX].length - 1))
        {
            m_Grid[m_MatrixPosX][m_MatrixPosY] = 1;
            ResetValues();  
        }  
    }
    
    private void MakePoints()
    {
        boolean lineFull = false;
        
        for(int y = 0; y < m_Grid[0].length; y++)
        {
            for(int x = 0; x < m_Grid.length; x++)
            {
                if(m_Grid[x][y] == 1)
                    lineFull = true;
                else
                {
                    lineFull = false;
                    break;
                }
            }
            
            if(lineFull)
            {
                m_Points += 100;
                
                for(int i = 0; i < m_Grid.length; i++)
                {
                    m_Grid[i][y] = 0;
                }
                
                for(int i = y; i > 0; i--)
                {
                    for(int z = 0; z < m_Grid.length; z++)
                    {
                        m_Grid[z][i] = m_Grid[z][i-1];
                        m_Grid[z][i-1] = 0;
                    }
                }
            }
        }
    }
    
    private void ResetValues()
    {
        m_PosX = -20;
        m_PosY = 50; //Lugar a cima, o valido é 45
        m_MatrixPosX = 0;
        m_MatrixPosY = 0;
    }
    
    public boolean IsGameOver()
    {
        if(m_MatrixPosY == 0)
        {
            if(m_Grid[m_MatrixPosX][m_MatrixPosY] == 1)
            {
                if(m_HighScore < m_Points)
                    m_HighScore = m_Points;
                
                return true;
            }
            else
                return false;
        }
        else
            return false;
    }
    
    private String NextPiece()
    {
        Random piece = new Random();
        
        switch(piece.nextInt(8)+1)
        {
            case 1:
                return "Quad";
            case 2:
                return "Zl";
            case 3:
                return "Zr";
            case 4:
                return "Block";
            case 5:
                return "T";
            case 6:
                return "Tower";
            case 7:
                return "Pl";
            case 8:
                return "Pr";
            default:
                return null;      
        }
    }
    
    private void CreateBoard()
    {
        /*
        m_Grid[9][19] = 1;
        m_Grid[8][19] = 1;
        m_Grid[7][19] = 1;
        m_Grid[6][19] = 1;
        m_Grid[5][19] = 1;
        m_Grid[4][19] = 1;
        m_Grid[3][19] = 1;
        m_Grid[2][19] = 1;
        m_Grid[1][19] = 1;
        
        /*m_Grid[0][18] = 1;
        m_Grid[8][18] = 1;
        m_Grid[7][18] = 1;
        m_Grid[6][18] = 1;
        m_Grid[5][18] = 1;
        m_Grid[4][18] = 1;
        m_Grid[3][18] = 1;
        m_Grid[2][18] = 1;
        m_Grid[1][18] = 1;
        
         m_Grid[9][17] = 1;
        m_Grid[8][17] = 1;
        m_Grid[7][17] = 1;
        m_Grid[6][17] = 1;
        m_Grid[5][17] = 1;
        m_Grid[4][17] = 1;
        m_Grid[3][17] = 1;
        m_Grid[2][17] = 1;
        m_Grid[1][17] = 1;
*/
        
        for(int x = 0; x < m_Grid.length; x++)
        {
            for(int y = 0; y < m_Grid[x].length; y++)
            {
                m_Grid[x][y] = 0;
            }
        } 
    }
}

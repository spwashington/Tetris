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
    private String m_CurrentPiece;
    private Piece m_Piece;
    private float m_UpdateTimer;
    private boolean m_FastDropPiece;
    private float m_DropSpeed;
    
    public Ingame(GL2 _openGL2)
    {
        m_GL2 = _openGL2;
        m_Glut = new GLUT();
        m_Points = 0;
        m_HighScore = 0;
        m_PosX = 5;
        m_PosY = 50; //Lugar a cima, o valido é 45
        m_Grid = new int[10][20];
        m_MatrixPosX = 5;
        m_MatrixPosY = 0;
        m_CurrentPiece = NextPiece();
        m_NextPiece = NextPiece();
        m_Piece = new Piece(_openGL2, m_NextPiece);
        m_UpdateTimer = 0;
        m_FastDropPiece = false;
        m_DropSpeed = 5f;
        
        CreateBoard();
    }
    
    public void NewGame()
    {
        m_Points = 0;
        m_PosX = 5;
        m_PosY = 50; //Lugar a cima, o valido é 45
        m_MatrixPosX = 5;
        m_MatrixPosY = 0;
        m_CurrentPiece = NextPiece();
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
            m_MatrixPosX--;
            m_PosX -= 5;
            CheckBoard();
        }
    }
    
    public void MoveRight()
    {
        if(m_PosX < 25 && RightCanMove())
        {
            m_MatrixPosX++;
            m_PosX += 5;
            CheckBoard();
        }
    }
    
    private boolean LeftCanMove()
    {
        if( m_MatrixPosX > (m_Piece.GetPieceLimit(m_CurrentPiece)[1] - 1))
        {
            if(m_Grid[m_MatrixPosX - m_Piece.GetPieceLimit(m_CurrentPiece)[1]][m_MatrixPosY] == 0)
                return true;
            else
                return false;
        }
        else
            return false;
    }
    
    private boolean RightCanMove()
    {
        if( m_MatrixPosX < (m_Grid.length - m_Piece.GetPieceLimit(m_CurrentPiece)[0])) //tava 19
        {
            if(m_Grid[m_MatrixPosX + m_Piece.GetPieceLimit(m_CurrentPiece)[0]][m_MatrixPosY] == 0)
                return true;
            else
                return false;
        }
        else
            return false;
    }
    
    public void NormalDropPiece()
    {
        m_FastDropPiece = false;
    }
    
    public void FastDropPiece()
    {
        m_FastDropPiece = true;
    }
    
    public void Execute()
    {
        //m_CurrentPiece = "Pl"; //TEST
        
        m_Piece.DrawPieceInBoard(m_Grid);
        
        if(!IsGameOver())
            DropPiece(); 
    }
    
    public String tttttt()
    {
        return "cu: " + m_CurrentPiece + "  next: " + m_NextPiece + 
                "   vecRt: " + (m_Grid.length - m_Piece.GetPieceLimit(m_CurrentPiece)[0]) + 
                "   vecLt: " + (m_Grid.length - m_Piece.GetPieceLimit(m_CurrentPiece)[1]) +
                "   XPOSMAT: " + m_MatrixPosX + "Press: " + m_FastDropPiece;
    }
    
    private void DropPiece()
    {
        MakePoints();
        UpdatePiece();
        UpdateNextPiece();
        UpdateSpeed();
    }
    
    private void UpdateSpeed()
    {
        if(m_Points > 5000)
            m_DropSpeed = 0.5f;
    }
    
    private void UpdatePiece()
    {
        m_Piece.DropPiece(m_PosX, m_PosY, m_CurrentPiece);
        m_UpdateTimer += 0.1f;
        
        if(!m_FastDropPiece)
        {
            if(m_UpdateTimer >= 5f)
            {
                if( m_PosY < 50)
                    m_MatrixPosY++;

                m_PosY -= 5;
                CheckBoard();
                m_UpdateTimer = 0;
            }
        }
        else
        {
            if(m_UpdateTimer >= 0.5f)
            {
                if( m_PosY < 50)
                    m_MatrixPosY++;

                m_PosY -= 5;
                CheckBoard();
                m_UpdateTimer = 0;
            }
        }
    }
    
    private void UpdateNextPiece()
    {
        m_Piece.DropPiece(40, 10, m_NextPiece);
    }
    
    private void CheckBoard()
    {
        switch(m_CurrentPiece)
        {
            case "Quad":
                QuadPlace();
                break;
            case "Block":
                BlockPlace();
                break;
            case "Tower":
                TowerPlace();
                break;
            case "T":
                TPlace();
                break;
            case "Zl":
                ZlPlace();
                break;
            case "Zr":
                ZrPlace();
                break;
            case "Pl":
                PlPlace();
                break;
            case "Pr":
                PrPlace();
                break;
        }
    }
    
    private boolean HavePieceBellowTop(int _blockWidth, int _blockHeight, int _blockTopStartInPos)
    {
         boolean have = false;
         int top = _blockHeight - 1;
         
        if(m_MatrixPosY > 1)
        {
            for(int i = 0; i < _blockWidth; i++)
            {
                if(m_Grid[(m_MatrixPosX + _blockTopStartInPos) + i][(m_MatrixPosY - top) + 1] == 1)
                {
                    have = true;
                    break;
                }
            }
        }
         
         return have;
    }
    
    private boolean HavePieceBellowTop(int _blockWidth, int _blockHeight)
    {
         boolean have = false;
         int top = _blockHeight - 1;
         
         if(m_MatrixPosY > 1)
         for(int i = 0; i < _blockWidth; i++)
        {
            if(m_Grid[m_MatrixPosX + i][(m_MatrixPosY - top) + 1] == 1)
            {
                have = true;
                break;
            }
        }
         
         return have;
    }
    
    private boolean HavePieceBellowBase(int _blockWidth)
    {
        boolean have = false;
        
        for(int i = 0; i < _blockWidth; i++)
        {
            if(m_Grid[m_MatrixPosX + i][m_MatrixPosY + 1] == 1)
            {
                have = true;
                break;
            }
        }
        
        return have;
    }
    
    private void QuadPlace()
    {
        if(m_MatrixPosY == (m_Grid[m_MatrixPosX].length - 1))
        {
            m_Grid[m_MatrixPosX][m_MatrixPosY] = 1;
            m_Grid[m_MatrixPosX+1][m_MatrixPosY] = 1;
            m_Grid[m_MatrixPosX][m_MatrixPosY-1] = 1;
            m_Grid[m_MatrixPosX+1][m_MatrixPosY-1] = 1;
            ResetValues();  
        }
        else if(HavePieceBellowBase(2) && m_MatrixPosY < 19 && m_MatrixPosY > 0)
        {
            m_Grid[m_MatrixPosX][m_MatrixPosY] = 1;
            m_Grid[m_MatrixPosX+1][m_MatrixPosY] = 1;
            m_Grid[m_MatrixPosX][m_MatrixPosY-1] = 1;
            m_Grid[m_MatrixPosX+1][m_MatrixPosY-1] = 1;
            ResetValues();
        }
    }
    
    private void TPlace()
    {
        if(m_MatrixPosY == (m_Grid[m_MatrixPosX].length - 1))
        {
            m_Grid[m_MatrixPosX][m_MatrixPosY] = 1;
            m_Grid[m_MatrixPosX][m_MatrixPosY-1] = 1;
            m_Grid[m_MatrixPosX+1][m_MatrixPosY-1] = 1;
            m_Grid[m_MatrixPosX-1][m_MatrixPosY-1] = 1;
            ResetValues();  
        }
        else if(HavePieceBellowTop(3, 2, -1) && m_MatrixPosY > 0)
        {
            m_Grid[m_MatrixPosX][m_MatrixPosY] = 1;
            m_Grid[m_MatrixPosX][m_MatrixPosY-1] = 1;
            m_Grid[m_MatrixPosX+1][m_MatrixPosY-1] = 1;
            m_Grid[m_MatrixPosX-1][m_MatrixPosY-1] = 1;
            ResetValues();
        }
        else if(HavePieceBellowBase(1) && m_MatrixPosY < 19 && m_MatrixPosY > 0)
        {
            m_Grid[m_MatrixPosX][m_MatrixPosY] = 1;
            m_Grid[m_MatrixPosX][m_MatrixPosY-1] = 1;
            m_Grid[m_MatrixPosX+1][m_MatrixPosY-1] = 1;
            m_Grid[m_MatrixPosX-1][m_MatrixPosY-1] = 1;
            ResetValues();
        }
    }
    
    private void ZlPlace()
    {
        if(m_MatrixPosY == (m_Grid[m_MatrixPosX].length - 1))
        {
            m_Grid[m_MatrixPosX][m_MatrixPosY] = 1;
            m_Grid[m_MatrixPosX][m_MatrixPosY-1] = 1;
            m_Grid[m_MatrixPosX+1][m_MatrixPosY] = 1;
            m_Grid[m_MatrixPosX-1][m_MatrixPosY-1] = 1;
            ResetValues();  
        }
        else if(HavePieceBellowTop(2, 2, -1) && m_MatrixPosY > 0)
        {
            m_Grid[m_MatrixPosX][m_MatrixPosY] = 1;
            m_Grid[m_MatrixPosX][m_MatrixPosY-1] = 1;
            m_Grid[m_MatrixPosX+1][m_MatrixPosY] = 1;
            m_Grid[m_MatrixPosX-1][m_MatrixPosY-1] = 1;
            ResetValues();
        }
        else if(HavePieceBellowBase(2) && m_MatrixPosY < 19 && m_MatrixPosY > 0)
        {
            m_Grid[m_MatrixPosX][m_MatrixPosY] = 1;
            m_Grid[m_MatrixPosX][m_MatrixPosY-1] = 1;
            m_Grid[m_MatrixPosX+1][m_MatrixPosY] = 1;
            m_Grid[m_MatrixPosX-1][m_MatrixPosY-1] = 1;
            ResetValues();
        }
    }
    
    private void ZrPlace()
    {
        if(m_MatrixPosY == (m_Grid[m_MatrixPosX].length - 1))
        {
            m_Grid[m_MatrixPosX][m_MatrixPosY] = 1;
            m_Grid[m_MatrixPosX+1][m_MatrixPosY] = 1;
            m_Grid[m_MatrixPosX+1][m_MatrixPosY-1] = 1;
            m_Grid[m_MatrixPosX+2][m_MatrixPosY-1] = 1;
            ResetValues();  
        }
        else if(HavePieceBellowTop(2, 3) && m_MatrixPosY > 0)
        {
            m_Grid[m_MatrixPosX][m_MatrixPosY] = 1;
            m_Grid[m_MatrixPosX+1][m_MatrixPosY] = 1;
            m_Grid[m_MatrixPosX+1][m_MatrixPosY-1] = 1;
            m_Grid[m_MatrixPosX+2][m_MatrixPosY-1] = 1;
            ResetValues();
        }
        else if(HavePieceBellowBase(2) && m_MatrixPosY < 19 && m_MatrixPosY > 0)
        {
            m_Grid[m_MatrixPosX][m_MatrixPosY] = 1;
            m_Grid[m_MatrixPosX+1][m_MatrixPosY] = 1;
            m_Grid[m_MatrixPosX+1][m_MatrixPosY-1] = 1;
            m_Grid[m_MatrixPosX+2][m_MatrixPosY-1] = 1;
            ResetValues();
        }
    }
    
    private void PlPlace()
    {
        if(m_MatrixPosY == (m_Grid[m_MatrixPosX].length - 1))
        {
            m_Grid[m_MatrixPosX][m_MatrixPosY] = 1;
            m_Grid[m_MatrixPosX][m_MatrixPosY-1] = 1;
            m_Grid[m_MatrixPosX][m_MatrixPosY-2] = 1;
            m_Grid[m_MatrixPosX-1][m_MatrixPosY-2] = 1;
            ResetValues();  
        }
        else if(HavePieceBellowTop(2, 3, -1) && m_MatrixPosY > 2)
        {
            m_Grid[m_MatrixPosX][m_MatrixPosY] = 1;
            m_Grid[m_MatrixPosX][m_MatrixPosY-1] = 1;
            m_Grid[m_MatrixPosX][m_MatrixPosY-2] = 1;
            m_Grid[m_MatrixPosX-1][m_MatrixPosY-2] = 1;
            ResetValues();
        }
        else if(HavePieceBellowBase(1) && m_MatrixPosY < 19 && m_MatrixPosY > 0)
        {
            m_Grid[m_MatrixPosX][m_MatrixPosY] = 1;
            m_Grid[m_MatrixPosX][m_MatrixPosY-1] = 1;
            m_Grid[m_MatrixPosX][m_MatrixPosY-2] = 1;
            m_Grid[m_MatrixPosX-1][m_MatrixPosY-2] = 1;
            ResetValues();
        }
    }
    
    private void PrPlace()
    {
        if(m_MatrixPosY == (m_Grid[m_MatrixPosX].length - 1))
        {
            m_Grid[m_MatrixPosX][m_MatrixPosY] = 1;
            m_Grid[m_MatrixPosX][m_MatrixPosY-1] = 1;
            m_Grid[m_MatrixPosX][m_MatrixPosY-2] = 1;
            m_Grid[m_MatrixPosX+1][m_MatrixPosY-2] = 1;
            ResetValues();  
        }
        else if(HavePieceBellowTop(2, 3) && m_MatrixPosY > 2)
        {
            m_Grid[m_MatrixPosX][m_MatrixPosY] = 1;
            m_Grid[m_MatrixPosX][m_MatrixPosY-1] = 1;
            m_Grid[m_MatrixPosX][m_MatrixPosY-2] = 1;
            m_Grid[m_MatrixPosX+1][m_MatrixPosY-2] = 1;
            ResetValues();
        }
        else if(HavePieceBellowBase(1) && m_MatrixPosY < 19 && m_MatrixPosY > 0)
        {
            m_Grid[m_MatrixPosX][m_MatrixPosY] = 1;
            m_Grid[m_MatrixPosX][m_MatrixPosY-1] = 1;
            m_Grid[m_MatrixPosX][m_MatrixPosY-2] = 1;
            m_Grid[m_MatrixPosX+1][m_MatrixPosY-2] = 1;
            ResetValues();
        }
    }
    
    private void TowerPlace()
    {
        if(m_MatrixPosY == (m_Grid[m_MatrixPosX].length - 1))
        {
            m_Grid[m_MatrixPosX][m_MatrixPosY] = 1;
            m_Grid[m_MatrixPosX][m_MatrixPosY - 1] = 1;
            m_Grid[m_MatrixPosX][m_MatrixPosY - 2] = 1;
            m_Grid[m_MatrixPosX][m_MatrixPosY - 3] = 1;
            ResetValues();  
        }
        else if(HavePieceBellowBase(1) && m_MatrixPosY < 19 && m_MatrixPosY > 0)
        {
            m_Grid[m_MatrixPosX][m_MatrixPosY] = 1;
            m_Grid[m_MatrixPosX][m_MatrixPosY - 1] = 1;
            m_Grid[m_MatrixPosX][m_MatrixPosY - 2] = 1;
            m_Grid[m_MatrixPosX][m_MatrixPosY - 3] = 1;
            ResetValues();
        }
    }
    
    private void BlockPlace()
    {
        if(m_MatrixPosY == (m_Grid[m_MatrixPosX].length - 1))
        {
            m_Grid[m_MatrixPosX][m_MatrixPosY] = 1;
            ResetValues();  
        }
        else if(HavePieceBellowBase(1) && m_MatrixPosY < 19 && m_MatrixPosY > 0)
        {
            m_Grid[m_MatrixPosX][m_MatrixPosY] = 1;
            ResetValues();
        }
    }
    
    /*
    private boolean CanMove(int _blockWidth, int _blockHeight)
    {
        boolean move = true;
        
        for(int i = 0; i < _blockWidth; i++)
        {
            if(m_Grid[m_MatrixPosX + i][m_MatrixPosY + 1] == 1)
            {
                move = false;
                break;
            }
        }
        
        return move;
    }*/
    
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
                m_DropSpeed -= 0.13f;
                
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
        m_PosX = 5;
        m_PosY = 50;
        m_MatrixPosX = 5;
        m_MatrixPosY = 0;
        m_CurrentPiece = m_NextPiece;
        m_NextPiece = NextPiece();
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
        for(int x = 0; x < m_Grid.length; x++)
        {
            for(int y = 0; y < m_Grid[x].length; y++)
            {
                m_Grid[x][y] = 0;
            }
        } 
    }
}

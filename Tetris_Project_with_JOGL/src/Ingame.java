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
    private boolean m_MoveRight;
    private boolean m_MoveLeft;
    
    private int[] t  = new int[1];
    private String teste = "";
    
    public Ingame(GL2 _openGL2)
    {
        m_GL2 = _openGL2;
        m_Glut = new GLUT();
        m_Points = 0;
        m_HighScore = 0;
        m_PosX = 5;
        m_PosY = 30; //Lugar a cima, o valido é 45
        m_Grid = new int[10][20];
        m_MatrixPosX = 5;
        m_MatrixPosY = 4;
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
        m_PosY = 30; //Lugar a cima, o valido é 45
        m_MatrixPosX = 5;
        m_MatrixPosY = 4;
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
        if(m_MoveLeft)
        {
            m_MatrixPosX--;
            m_PosX -= 5;
            CheckBoard();
        }
        /*
        if(CanMove("Left", GetCurrentPieceLimit("Left")))
        {
            
        }*/
    }
    
    public void MoveRight()
    {
        if(m_MoveRight)
        {
            m_MatrixPosX++;
            m_PosX += 5;
            CheckBoard();
        }
        /*
        if(CanMove("Right", GetCurrentPieceLimit("Right")))
        {
            m_MatrixPosX++;
            m_PosX += 5;
        }*/
    }
    
    /*
    private boolean IsStageLimit(String _direction)
    {
        if(_direction == "Left")
        {
            if(m_CurrentPiece == "Quad")
            {
                if(m_MatrixPosX == 0)
                    return true;
            }
            else if(m_CurrentPiece == "Zl")
            {
                switch(m_Piece.GetRotateValue())
                {
                    case 90:
                    case 270:
                        if(m_MatrixPosX == 0)
                            return true;
                            break;
                    default:
                        if(m_MatrixPosX == 1)
                            return true;
                            break;
                }
            }
            else if(m_CurrentPiece == "Zr")
            {
                switch(m_Piece.GetRotateValue())
                {
                    case 90:
                    case 270:
                        if(m_MatrixPosX == 1)
                            return true;
                            break;
                    default:
                        if(m_MatrixPosX == 0)
                            return true;
                            break;
                }
            }
            else if(m_CurrentPiece == "Tower")
            {
                switch(m_Piece.GetRotateValue())
                {
                    case 90:
                    case 270:
                        if(m_MatrixPosX == 0)
                            return true;
                            break;
                    default:
                        if(m_MatrixPosX == 0)
                            return true;
                            break;
                }
            }
            else if(m_CurrentPiece == "T")
            {
                switch(m_Piece.GetRotateValue())
                {
                    case 0:
                        if(m_MatrixPosX == 1)
                            return true;
                            break;
                    case 90:
                        if(m_MatrixPosX == 1)
                            return true;
                            break;
                    case 180:
                        if(m_MatrixPosX == 0)
                            return true;
                            break;
                    case 270:
                        if(m_MatrixPosX == 0)
                            return true;
                            break;
                }
            }
            else if(m_CurrentPiece == "Pl")
            {
                switch(m_Piece.GetRotateValue())
                {
                    case 0:
                        if(m_MatrixPosX == 1)
                            return true;
                            break;
                    case 90:
                        if(m_MatrixPosX == 0)
                            return true;
                            break;
                    case 180:
                        if(m_MatrixPosX == 0)
                            return true;
                            break;
                    case 270:
                        if(m_MatrixPosX == 0)
                            return true;
                            break;
                }
            }
            else if(m_CurrentPiece == "Pr")
            {
                switch(m_Piece.GetRotateValue())
                {
                    case 0:
                        if(m_MatrixPosX == 0)
                            return true;                            
                            break;
                    case 90:
                        if(m_MatrixPosX == 2)
                            return true;                            
                            break;
                    case 180:
                        if(m_MatrixPosX == 0)
                            return true;                                
                            break;
                    case 270:
                        if(m_MatrixPosX == 0)
                            return true;                            
                            break;
                }
            }
            else if(m_CurrentPiece == "Block")
            {
                if(m_MatrixPosX == 0)
                    return true;
            }   
        }
        else
        {
            if(m_CurrentPiece == "Quad")
            {
                if(m_MatrixPosX == 8)
                    return true;
            }
            else if(m_CurrentPiece == "Zl")
            {
                switch(m_Piece.GetRotateValue())
                {
                    case 90:
                    case 270:
                        if(m_MatrixPosX == 8)
                            return true;         
                            break;
                    default:
                        if(m_MatrixPosX == 8)
                            return true;         
                            break;
                }
            }
            else if(m_CurrentPiece == "Zr")
            {
                switch(m_Piece.GetRotateValue())
                {
                    case 90:
                    case 270:
                        if(m_MatrixPosX == 9)
                            return true;         
                            break;
                    default:
                        if(m_MatrixPosX == 7)
                            return true;         
                            break;
                }
            }
            else if(m_CurrentPiece == "Tower")
            {
                switch(m_Piece.GetRotateValue())
                {
                    case 90:
                    case 270:
                        if(m_MatrixPosX == 6)
                            return true;         
                            break;
                    default:
                        if(m_MatrixPosX == 9)
                            return true;         
                            break;
                }
            }
            else if(m_CurrentPiece == "T")
            {
                switch(m_Piece.GetRotateValue())
                {
                    case 0:
                        if(m_MatrixPosX == 8)
                            return true;         
                            break;
                    case 90:
                        if(m_MatrixPosX == 9)
                            return true;         
                            break;
                    case 180:
                        if(m_MatrixPosX == 7)
                            return true;         
                            break;
                    case 270:
                        if(m_MatrixPosX == 8)
                            return true;         
                            break;
                }
            }
            else if(m_CurrentPiece == "Pl")
            {
                switch(m_Piece.GetRotateValue())
                {
                    case 0:
                        if(m_MatrixPosX == 9)
                            return true;       
                            break;
                    case 90:
                        if(m_MatrixPosX == 7)
                            return true;
                            break;
                    case 180:
                        if(m_MatrixPosX == 8)
                            return true;
                            break;
                    case 270:
                        if(m_MatrixPosX == 7)
                            return true;
                            break;
                }
            }
            else if(m_CurrentPiece == "Pr")
            {
                switch(m_Piece.GetRotateValue())
                {
                    case 0:
                        if(m_MatrixPosX == 8)
                            return true;         
                            break;
                    case 90:
                        if(m_MatrixPosX == 9)
                            return true;         
                            break;
                    case 180:
                        if(m_MatrixPosX == 8)
                            return true;         
                            break;
                    case 270:
                        if(m_MatrixPosX == 7)
                            return true;         
                            break;
                }
            }
            else if(m_CurrentPiece == "Block")
            {
                if(m_MatrixPosX == 9)
                    return true;
            }
        }
        return false;
    }*/
    
    private int[] GetCurrentPieceLimit(String _direction)
    {
        int [] tempPos = new int[1];
        
        if(_direction == "Left")
        {
            if(m_CurrentPiece == "Quad")
            {
                int[] temp = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX, m_MatrixPosY-1};
                t = temp;
                return temp;
            }
            else if(m_CurrentPiece == "Zl")
            {
                switch(m_Piece.GetRotateValue())
                {
                    case 90:
                    case 270:
                        int[] temp = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX, m_MatrixPosY-1, m_MatrixPosX+1, m_MatrixPosY-2};
                        tempPos = temp;
                        break;
                    default:
                        int[] temp1 = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX-1, m_MatrixPosY-1};
                        tempPos = temp1;
                        break;
                }
                return tempPos;
            }
            else if(m_CurrentPiece == "Zr")
            {
                switch(m_Piece.GetRotateValue())
                {
                    case 90:
                    case 270:
                        int[] temp = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX-1, m_MatrixPosY-1, m_MatrixPosX-1, m_MatrixPosY-2};
                        tempPos = temp;
                        break;
                    default:
                        int[] temp1 = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX+1, m_MatrixPosY-1};
                        tempPos = temp1;
                        break;
                }
                return tempPos;
            }
            else if(m_CurrentPiece == "Pr")
            {
                switch(m_Piece.GetRotateValue())
                {
                    case 0:
                        int[] temp = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX, m_MatrixPosY-1, m_MatrixPosX, m_MatrixPosY-2};
                        tempPos = temp;
                        break;
                    case 90:
                        int[] temp1 = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX-2, m_MatrixPosY-1};
                        tempPos = temp1;
                        break;
                    case 180:
                        int[] temp2 = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX+1, m_MatrixPosY-1, m_MatrixPosX+1, m_MatrixPosY-2};
                        tempPos = temp2;
                        break;
                    case 270:
                        int[] temp3 = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX, m_MatrixPosY-1};
                        tempPos = temp3;
                        break;
                }
                return tempPos;
            }
            else if(m_CurrentPiece == "Pl")
            {
                switch(m_Piece.GetRotateValue())
                {
                    case 0:
                        int[] temp = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX, m_MatrixPosY-1, m_MatrixPosX-1, m_MatrixPosY-2};
                        tempPos = temp;
                        break;
                    case 90:
                        int[] temp1 = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX+2, m_MatrixPosY-1};
                        tempPos = temp1;
                        break;
                    case 180:
                        int[] temp2 = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX, m_MatrixPosY-1, m_MatrixPosX, m_MatrixPosY-2};
                        tempPos = temp2;
                        break;
                    case 270:
                        int[] temp3 = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX, m_MatrixPosY-1};
                        tempPos = temp3;
                        break;
                }
                return tempPos;
            }
            else if(m_CurrentPiece == "T")
            {
                switch(m_Piece.GetRotateValue())
                {
                    case 0:
                        int[] temp = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX-1, m_MatrixPosY-1};
                        tempPos = temp;
                        break;
                    case 90:
                        int[] temp1 = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX-1, m_MatrixPosY-1, m_MatrixPosX, m_MatrixPosY-2};
                        tempPos = temp1;
                        break;
                    case 180:
                        int[] temp2 = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX+1, m_MatrixPosY-1};
                        tempPos = temp2;
                        break;
                    case 270:
                        int[] temp3 = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX, m_MatrixPosY-1, m_MatrixPosX, m_MatrixPosY-2};
                        tempPos = temp3;
                        break;
                }
                return tempPos;
            }
            else if(m_CurrentPiece == "Tower")
            {
                switch(m_Piece.GetRotateValue())
                {
                    case 90:
                    case 270:
                        int[] temp = {m_MatrixPosX, m_MatrixPosY};
                        tempPos = temp;
                        break;
                    default:
                        int[] temp1 = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX, m_MatrixPosY-1, m_MatrixPosX, m_MatrixPosY-2, m_MatrixPosX, m_MatrixPosY-3};
                        tempPos = temp1;
                        break;
                }
                return tempPos;
            }
            else if(m_CurrentPiece == "Block")
            {
                int[] temp = {m_MatrixPosX, m_MatrixPosY};
                return temp;
            }
        }
        else
        {
            if(m_CurrentPiece == "Quad")
            {
                int[] temp = {m_MatrixPosX+1, m_MatrixPosY, m_MatrixPosX+1, m_MatrixPosY-1};
                return temp;
            }
            else if(m_CurrentPiece == "Zl")
            {
                switch(m_Piece.GetRotateValue())
                {
                    case 90:
                    case 270:
                        int[] temp = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX+1, m_MatrixPosY-1, m_MatrixPosX+1, m_MatrixPosY-2};
                        tempPos = temp;
                        break;
                    default:
                        int[] temp1 = {m_MatrixPosX+1, m_MatrixPosY, m_MatrixPosX, m_MatrixPosY-1};
                        tempPos = temp1;
                        break;
                }
                return tempPos;
            }
            else if(m_CurrentPiece == "Zr")
            {
                switch(m_Piece.GetRotateValue())
                {
                    case 90:
                    case 270:
                        int[] temp = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX, m_MatrixPosY-1, m_MatrixPosX-1, m_MatrixPosY-2};
                        tempPos = temp;
                        break;
                    default:
                        int[] temp1 = {m_MatrixPosX+1, m_MatrixPosY, m_MatrixPosX+2, m_MatrixPosY-1};
                        tempPos = temp1;
                        break;
                }
                return tempPos;
            }
            else if(m_CurrentPiece == "Pr")
            {
                switch(m_Piece.GetRotateValue())
                {
                    case 0:
                        int[] temp = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX, m_MatrixPosY-1, m_MatrixPosX+1, m_MatrixPosY-2};
                        tempPos = temp;
                        break;
                    case 90:
                        int[] temp1 = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX, m_MatrixPosY-1};
                        tempPos = temp1;
                        break;
                    case 180:
                        int[] temp2 = {m_MatrixPosX+1, m_MatrixPosY, m_MatrixPosX+1, m_MatrixPosY-1, m_MatrixPosX+1, m_MatrixPosY-2};
                        tempPos = temp2;
                        break;
                    case 270:
                        int[] temp3 = {m_MatrixPosX+2, m_MatrixPosY, m_MatrixPosX, m_MatrixPosY-1};
                        tempPos = temp3;
                        break;
                }
                return tempPos;
            }
            else if(m_CurrentPiece == "Pl")
            {
                switch(m_Piece.GetRotateValue())
                {
                    case 0:
                        int[] temp = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX, m_MatrixPosY-1, m_MatrixPosX, m_MatrixPosY-2};
                        tempPos = temp;
                        break;
                    case 90:
                        int[] temp1 = {m_MatrixPosX+2, m_MatrixPosY, m_MatrixPosX+2, m_MatrixPosY-1};
                        tempPos = temp1;
                        break;
                    case 180:
                        int[] temp2 = {m_MatrixPosX+1, m_MatrixPosY, m_MatrixPosX, m_MatrixPosY-1, m_MatrixPosX, m_MatrixPosY-2};
                        tempPos = temp2;
                        break;
                    case 270:
                        int[] temp3 = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX+2, m_MatrixPosY-1};
                        tempPos = temp3;
                        break;
                }
                return tempPos;
            }
            else if(m_CurrentPiece == "T")
            {
                switch(m_Piece.GetRotateValue())
                {
                    case 0:
                        int[] temp = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX+1, m_MatrixPosY-1};
                        tempPos = temp;
                        break;
                    case 90:
                        int[] temp1 = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX, m_MatrixPosY-1, m_MatrixPosX, m_MatrixPosY-2};
                        tempPos = temp1;
                        break;
                    case 180:
                        int[] temp2 = {m_MatrixPosX+2, m_MatrixPosY, m_MatrixPosX+1, m_MatrixPosY-1};
                        tempPos = temp2;
                        break;
                    case 270:
                        int[] temp3 = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX+1, m_MatrixPosY-1, m_MatrixPosX, m_MatrixPosY-2};
                        tempPos = temp3;
                        break;
                }
                return tempPos;
            }
            else if(m_CurrentPiece == "Tower")
            {
                switch(m_Piece.GetRotateValue())
                {
                    case 90:
                    case 270:
                        int[] temp = {m_MatrixPosX+3, m_MatrixPosY};
                        tempPos = temp;
                        break;
                    default:
                        int[] temp1 = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX, m_MatrixPosY-1, m_MatrixPosX, m_MatrixPosY-2, m_MatrixPosX, m_MatrixPosY-3};
                        tempPos = temp1;
                        break;
                }
                return tempPos;
            }
            else if(m_CurrentPiece == "Block")
            {
                int[] temp = {m_MatrixPosX, m_MatrixPosY};
                return temp;
            }
        }
        return null;
    }
    
    private boolean CanMove(String _direction, int[] _positions)
    {
        int x = 0;
        int y = 1;
        int amountPieceToCheck = (_positions.length / 2);
        
        if(_direction == "Left" && !IsStageLimit("Left"))
        {
            for(int i = 0; i < amountPieceToCheck; i++)
            {
                if(m_Grid[_positions[x] - 1][_positions[y]] > 0)
                    return false;
                else
                {
                    x += 2;
                    y += 2;
                }
            }
            return true;
        }
        else if(_direction == "Right" && !IsStageLimit("Right"))
        {
            for(int i = 0; i < amountPieceToCheck; i++)
            {
                if(m_Grid[_positions[x] + 1][_positions[y]] > 0)
                    return false;
                else
                {
                    x += 2;
                    y += 2;
                }
            }
            return true;
        }
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
    
    public boolean ttt()
    {
        return m_MoveLeft;
    }
    
    public boolean tttr()
    {
        return m_MoveRight;
    }
    
    public String rrrrrrr()
    {
        //return "X-1:" + m_Grid[m_MatrixPosX-1][m_MatrixPosY];
        return "X_MPos = " + m_MatrixPosX;
    }
    
    public void Execute()
    {
        m_CurrentPiece = "T"; //TEST
        
        m_Piece.DrawPieceInBoard(m_Grid);
        
        if(!IsGameOver())
        {
            DropPiece(); 
            //CheckMoveLeft();
            Move();
        }
    }
    
    private void Move()
    {
        m_MoveLeft = true;
        m_MoveRight = true;
        
        switch(m_CurrentPiece)
        {
            case "T":
                if(!IsStageLimit("Left"))
                {
                    if(m_Piece.GetRotateValue() == 0)
                    {
                        if(m_MatrixPosX > 1)
                        {
                            if(m_Grid[m_MatrixPosX-2][m_MatrixPosY-1] > 0 || m_Grid[m_MatrixPosX-1][m_MatrixPosY] > 0)
                                m_MoveLeft = false;
                        }
                        else
                            m_MoveLeft = false;
                    }
                    else if(m_Piece.GetRotateValue() == 90)
                    {
                        if(m_MatrixPosX > 1)
                        {
                            if(m_Grid[m_MatrixPosX-1][m_MatrixPosY] > 0 || m_Grid[m_MatrixPosX-2][m_MatrixPosY-1] > 0 || m_Grid[m_MatrixPosX-1][m_MatrixPosY-2] > 0)
                                m_MoveLeft = false;
                        }
                        else
                            m_MoveLeft = false;
                    }
                    else if(m_Piece.GetRotateValue() == 180)
                    {
                        if(m_MatrixPosX > 0)
                        {
                            if(m_Grid[m_MatrixPosX-1][m_MatrixPosY] > 0 || m_Grid[m_MatrixPosX][m_MatrixPosY-1] > 0)
                                m_MoveLeft = false;
                        }
                        else
                            m_MoveLeft = false;
                    }
                    else if(m_Piece.GetRotateValue() == 270)
                    {
                        if(m_MatrixPosX > 0)
                        {
                            if(m_Grid[m_MatrixPosX-1][m_MatrixPosY] > 0 || m_Grid[m_MatrixPosX-1][m_MatrixPosY-1] > 0 || m_Grid[m_MatrixPosX-1][m_MatrixPosY-2] > 0)
                                m_MoveLeft = false;
                        }
                        else
                            m_MoveLeft = false;
                    }
                }
                else
                    m_MoveLeft = false;
                
                if(!IsStageLimit("Right"))
                {
                    if(m_Piece.GetRotateValue() == 0)
                    {
                        if(m_MatrixPosX < 8)
                        {
                            if(m_Grid[m_MatrixPosX+1][m_MatrixPosY] > 0 || m_Grid[m_MatrixPosX+2][m_MatrixPosY-1] > 0)
                                m_MoveRight = false;
                        }
                        else
                            m_MoveRight = false;
                    }
                    else if(m_Piece.GetRotateValue() == 90)
                    {
                        if(m_MatrixPosX < 9)
                        {
                            if(m_Grid[m_MatrixPosX+1][m_MatrixPosY] > 0 || m_Grid[m_MatrixPosX+1][m_MatrixPosY-1] > 0 || m_Grid[m_MatrixPosX+1][m_MatrixPosY-2] > 0)
                                m_MoveRight = false;
                        }
                        else
                            m_MoveRight = false;
                    }
                    else if(m_Piece.GetRotateValue() == 180)
                    {
                        if(m_MatrixPosX < 7)
                        {
                            if(m_Grid[m_MatrixPosX+3][m_MatrixPosY] > 0 || m_Grid[m_MatrixPosX+2][m_MatrixPosY-1] > 0)
                                m_MoveRight = false;
                        }
                        else
                            m_MoveRight = false;
                    }
                    else if(m_Piece.GetRotateValue() == 270)
                    {
                        if(m_MatrixPosX < 8)
                        {
                            if(m_Grid[m_MatrixPosX+1][m_MatrixPosY] > 0 || m_Grid[m_MatrixPosX+2][m_MatrixPosY-1] > 0 || m_Grid[m_MatrixPosX+1][m_MatrixPosY-2] > 0)
                                m_MoveRight = false;
                        }
                        else
                            m_MoveRight = false;
                    }
                } 
                else
                    m_MoveRight = false;
            break;
            
            case "Zr":
                if(!IsStageLimit("Left"))
                {
                    if(m_Piece.GetRotateValue() == 0 || m_Piece.GetRotateValue() == 180)
                    {
                        if(m_MatrixPosX > 0)
                        {
                            if(m_Grid[m_MatrixPosX-1][m_MatrixPosY] > 0 || m_Grid[m_MatrixPosX][m_MatrixPosY-1] > 0)
                                m_MoveLeft = false;
                        }
                        else
                            m_MoveLeft = false;
                    }
                    else if(m_Piece.GetRotateValue() == 90 || m_Piece.GetRotateValue() == 270)
                    {
                        if(m_MatrixPosX > 1)
                        {
                            if(m_Grid[m_MatrixPosX-1][m_MatrixPosY] > 0 || m_Grid[m_MatrixPosX-2][m_MatrixPosY-1] > 0 || m_Grid[m_MatrixPosX-2][m_MatrixPosY-2] > 0)
                                m_MoveLeft = false;
                        }
                        else
                            m_MoveLeft = false;
                    }
                }
                else
                    m_MoveLeft = false;
                
                if(!IsStageLimit("Right"))
                {
                    if(m_Piece.GetRotateValue() == 0 || m_Piece.GetRotateValue() == 180)
                    {
                        if(m_MatrixPosX < 7)
                        {
                            if(m_Grid[m_MatrixPosX+2][m_MatrixPosY] > 0 || m_Grid[m_MatrixPosX+3][m_MatrixPosY-1] > 0)
                                m_MoveRight = false;
                        }
                        else
                            m_MoveRight = false;
                    }
                    else if(m_Piece.GetRotateValue() == 90 || m_Piece.GetRotateValue() == 270)
                    {
                        if(m_MatrixPosX < 9)
                        {
                            if(m_Grid[m_MatrixPosX+1][m_MatrixPosY] > 0 || m_Grid[m_MatrixPosX+1][m_MatrixPosY-1] > 0 || m_Grid[m_MatrixPosX][m_MatrixPosY-2] > 0)
                                m_MoveRight = false;
                        }
                        else
                            m_MoveRight = false;
                    }
                } 
                else
                    m_MoveRight = false;
            break;
            
            case "Zl":
                if(!IsStageLimit("Left"))
                {
                    if(m_Piece.GetRotateValue() == 0 || m_Piece.GetRotateValue() == 180)
                    {
                        if(m_MatrixPosX > 1)
                        {
                            if(m_Grid[m_MatrixPosX-1][m_MatrixPosY] > 0 || m_Grid[m_MatrixPosX-2][m_MatrixPosY-1] > 0)
                                m_MoveLeft = false;
                        }
                        else
                            m_MoveLeft = false;
                    }
                    else if(m_Piece.GetRotateValue() == 90 || m_Piece.GetRotateValue() == 270)
                    {
                        if(m_MatrixPosX > 0)
                        {
                            if(m_Grid[m_MatrixPosX-1][m_MatrixPosY] > 0 || m_Grid[m_MatrixPosX-1][m_MatrixPosY-1] > 0 || m_Grid[m_MatrixPosX][m_MatrixPosY-2] > 0)
                                m_MoveLeft = false;
                        }
                        else
                            m_MoveLeft = false;
                    }
                }
                else
                    m_MoveLeft = false;
                
                if(!IsStageLimit("Right"))
                {
                    if(m_Piece.GetRotateValue() == 0 || m_Piece.GetRotateValue() == 180)
                    {
                        if(m_MatrixPosX < 9)
                        {
                            if(m_Grid[m_MatrixPosX+1][m_MatrixPosY] > 0 || m_Grid[m_MatrixPosX][m_MatrixPosY-1] > 0)
                                m_MoveRight = false;
                        }
                        else
                            m_MoveRight = false;
                    }
                    else if(m_Piece.GetRotateValue() == 90 || m_Piece.GetRotateValue() == 270)
                    {
                        if(m_MatrixPosX < 8)
                        {
                            if(m_Grid[m_MatrixPosX+1][m_MatrixPosY] > 0 || m_Grid[m_MatrixPosX+2][m_MatrixPosY-1] > 0 || m_Grid[m_MatrixPosX+2][m_MatrixPosY-2] > 0)
                                m_MoveRight = false;
                        }
                        else
                            m_MoveRight = false;
                    }
                } 
                else
                    m_MoveRight = false;
            break;
            
            case "Tower":
                if(!IsStageLimit("Left"))
                {
                    if(m_Piece.GetRotateValue() == 0 || m_Piece.GetRotateValue() == 180)
                    {
                        if(m_MatrixPosX > 0)
                        {
                            if(m_Grid[m_MatrixPosX-1][m_MatrixPosY] > 0 || m_Grid[m_MatrixPosX-1][m_MatrixPosY-1] > 0 || m_Grid[m_MatrixPosX-1][m_MatrixPosY-2] > 0 || m_Grid[m_MatrixPosX-1][m_MatrixPosY-3] > 0)
                                m_MoveLeft = false;
                        }
                        else
                            m_MoveLeft = false;
                    }
                    else if(m_Piece.GetRotateValue() == 90 || m_Piece.GetRotateValue() == 270)
                    {
                        if(m_MatrixPosX > 0)
                        {
                            if(m_Grid[m_MatrixPosX-1][m_MatrixPosY] > 0)
                                m_MoveLeft = false;
                        }
                        else
                            m_MoveLeft = false;
                    }
                }
                else
                    m_MoveLeft = false;
                
                if(!IsStageLimit("Right"))
                {
                    if(m_Piece.GetRotateValue() == 0 || m_Piece.GetRotateValue() == 180)
                    {
                        if(m_MatrixPosX < 9)
                        {
                            if(m_Grid[m_MatrixPosX+1][m_MatrixPosY] > 0 || m_Grid[m_MatrixPosX+1][m_MatrixPosY-1] > 0 || m_Grid[m_MatrixPosX+1][m_MatrixPosY-2] > 0 || m_Grid[m_MatrixPosX+1][m_MatrixPosY-3] > 0)
                                m_MoveRight = false;
                        }
                        else
                            m_MoveRight = false;
                    }
                    else if(m_Piece.GetRotateValue() == 90 || m_Piece.GetRotateValue() == 270)
                    {
                        if(m_MatrixPosX < 9)
                        {
                            if(m_Grid[m_MatrixPosX+1][m_MatrixPosY] > 0)
                                m_MoveRight = false;
                        }
                        else
                            m_MoveRight = false;
                    }
                } 
                else
                    m_MoveRight = false;
            break;
        }
        //CheckBoard();
    }
    
    //TO DO: TERMINAR ESSA PARTE
    public void RotatePiece()
    {
        m_Piece.Rotate();
        
        switch(m_CurrentPiece)
        {
            case "T":
                if(m_Piece.GetRotateValue() == 0)
                {
                    if(m_MatrixPosX > 7)
                    {
                        m_MatrixPosX = 8;
                        m_PosX = 20;
                    }
                    else if(m_MatrixPosX < 2)
                    {
                        m_MatrixPosX = 1;
                        m_PosX = -15;
                    }
                }
                else if(m_Piece.GetRotateValue() == 90)
                {
                    if(m_MatrixPosX > 7)
                    {
                        m_MatrixPosX = 9;
                        m_PosX = 25;
                    }
                    else if(m_MatrixPosX < 2)
                    {
                        m_MatrixPosX = 1;
                        m_PosX = -15;
                    }
                }
                else if(m_Piece.GetRotateValue() == 180)
                {
                    if(m_MatrixPosX > 6)
                    {
                        m_MatrixPosX = 7;
                        m_PosX = 15;
                    }
                    else if(m_MatrixPosX < 2)
                    {
                        m_MatrixPosX = 0;
                        m_PosX = -20;
                    }
                }
                else if(m_Piece.GetRotateValue() == 270)
                {
                    if(m_MatrixPosX > 6)
                    {
                        m_MatrixPosX = 8;
                        m_PosX = 20;
                    }
                    else if(m_MatrixPosX < 1)
                    {
                        m_MatrixPosX = 0;
                        m_PosX = -20;
                    }
                }
            break;
            
            case "Tower":
                if(m_Piece.GetRotateValue() == 90 || m_Piece.GetRotateValue() == 270)
                {
                    if(m_MatrixPosX > 6)
                    {
                        m_MatrixPosX = 6;
                        m_PosX = 10;
                    }
                }
            break;
            
            case "Zr":
                if(m_Piece.GetRotateValue() == 0 || m_Piece.GetRotateValue() == 180)
                {
                    if(m_MatrixPosX > 6)
                    {
                        m_MatrixPosX = 7;
                        m_PosX = 15;
                    }
                    else if(m_MatrixPosX < 2)
                    {
                        m_MatrixPosX = 0;
                        m_PosX = -20;
                    }
                }
                else if(m_Piece.GetRotateValue() == 90 || m_Piece.GetRotateValue() == 270)
                {
                    if(m_MatrixPosX < 9 && m_MatrixPosX > 6)
                    {
                        m_MatrixPosX = 9;
                        m_PosX = 25;
                    }
                    else if(m_MatrixPosX < 2)
                    {
                        m_MatrixPosX = 1;
                        m_PosX = -15;
                    }
                }
            break;
            
            case "Zl":
                if(m_Piece.GetRotateValue() == 0 || m_Piece.GetRotateValue() == 180)
                {
                    if(m_MatrixPosX > 7)
                    {
                        m_MatrixPosX = 8;
                        m_PosX = 20;
                    }
                    else if(m_MatrixPosX < 2)
                    {
                        m_MatrixPosX = 1;
                        m_PosX = -15;
                    }
                }
                else if(m_Piece.GetRotateValue() == 90 || m_Piece.GetRotateValue() == 270)
                {
                    if(m_MatrixPosX < 9 && m_MatrixPosX > 7)
                    {
                        m_MatrixPosX = 8;
                        m_PosX = 20;
                    }
                    else if(m_MatrixPosX < 2)
                    {
                        m_MatrixPosX = 0;
                        m_PosX = -20;
                    }
                }
            break;
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private boolean IsStageLimit(String _direction)
    {
        boolean temp = false;
        
        if(_direction == "Left")
        {
            if(m_CurrentPiece == "Quad")
                if(m_MatrixPosX == 0)
                    temp = true;
            else if(m_CurrentPiece == "Zl")
            {
                switch(m_Piece.GetRotateValue())
                {
                    case 90:
                    case 270:
                        if(m_MatrixPosX == 0)
                            temp = true;
                    break;
                    default:
                        if(m_MatrixPosX == 1)
                            temp = true;
                            break;
                }
            }
            else if(m_CurrentPiece == "Zr")
            {
                switch(m_Piece.GetRotateValue())
                {
                    case 90:
                    case 270:
                        if(m_MatrixPosX == 1)
                            temp = true;
                            break;
                    default:
                        if(m_MatrixPosX == 0)
                            temp = true;
                            break;
                }
            }
            else if(m_CurrentPiece == "Tower")
            {
                switch(m_Piece.GetRotateValue())
                {
                    case 90:
                    case 270:
                        if(m_MatrixPosX == 0)
                            temp = true;
                            break;
                    default:
                        if(m_MatrixPosX == 0)
                            temp = true;
                            break;
                }
            }
            else if(m_CurrentPiece == "T")
            {
                switch(m_Piece.GetRotateValue())
                {
                    case 0:
                        if(m_MatrixPosX == 1)
                            temp = true;
                            break;
                    case 90:
                        if(m_MatrixPosX == 1)
                            temp = true;
                            break;
                    case 180:
                        if(m_MatrixPosX == 0)
                            temp = true;
                            break;
                    case 270:
                        if(m_MatrixPosX == 0)
                            temp = true;
                            break;
                }
            }
            else if(m_CurrentPiece == "Pl")
            {
                switch(m_Piece.GetRotateValue())
                {
                    case 0:
                        if(m_MatrixPosX == 1)
                            temp = true;
                            break;
                    case 90:
                        if(m_MatrixPosX == 0)
                            temp = true;
                            break;
                    case 180:
                        if(m_MatrixPosX == 0)
                            temp = true;
                            break;
                    case 270:
                        if(m_MatrixPosX == 0)
                            temp = true;
                            break;
                }
            }
            else if(m_CurrentPiece == "Pr")
            {
                switch(m_Piece.GetRotateValue())
                {
                    case 0:
                        if(m_MatrixPosX == 0)
                            temp = true;                            
                            break;
                    case 90:
                        if(m_MatrixPosX == 2)
                            temp = true;                            
                            break;
                    case 180:
                        if(m_MatrixPosX == 0)
                            temp = true;                                
                            break;
                    case 270:
                        if(m_MatrixPosX == 0)
                            temp = true;                            
                            break;
                }
            }
            else if(m_CurrentPiece == "Block")
            {
                if(m_MatrixPosX == 0)
                    temp = true;
            }   
        }   
        else
        {
            if(m_CurrentPiece == "Quad")
            {
                if(m_MatrixPosX == 8)
                    temp = true;
            }
            else if(m_CurrentPiece == "Zl")
            {
                switch(m_Piece.GetRotateValue())
                {
                    case 90:
                    case 270:
                        if(m_MatrixPosX == 8)
                            temp = true;         
                            break;
                    default:
                        if(m_MatrixPosX == 8)
                            temp = true;         
                            break;
                }
            }
            else if(m_CurrentPiece == "Zr")
            {
                switch(m_Piece.GetRotateValue())
                {
                    case 90:
                    case 270:
                        if(m_MatrixPosX == 9)
                            temp = true;         
                            break;
                    default:
                        if(m_MatrixPosX == 7)
                            temp = true;         
                            break;
                }
            }
            else if(m_CurrentPiece == "Tower")
            {
                switch(m_Piece.GetRotateValue())
                {
                    case 90:
                    case 270:
                        if(m_MatrixPosX == 6)
                            temp = true;         
                            break;
                    default:
                        if(m_MatrixPosX == 9)
                            temp = true;         
                            break;
                }
            }
            else if(m_CurrentPiece == "T")
            {
                switch(m_Piece.GetRotateValue())
                {
                    case 0:
                        if(m_MatrixPosX == 8)
                            temp = true;         
                            break;
                    case 90:
                        if(m_MatrixPosX == 9)
                            temp = true;         
                            break;
                    case 180:
                        if(m_MatrixPosX == 7)
                            temp = true;         
                            break;
                    case 270:
                        if(m_MatrixPosX == 8)
                            temp = true;         
                            break;
                }
            }
            else if(m_CurrentPiece == "Pl")
            {
                switch(m_Piece.GetRotateValue())
                {
                    case 0:
                        if(m_MatrixPosX == 9)
                            temp = true;       
                            break;
                    case 90:
                        if(m_MatrixPosX == 7)
                            temp = true;
                            break;
                    case 180:
                        if(m_MatrixPosX == 8)
                            temp = true;
                            break;
                    case 270:
                        if(m_MatrixPosX == 7)
                            temp = true;
                            break;
                }
            }
            else if(m_CurrentPiece == "Pr")
            {
                switch(m_Piece.GetRotateValue())
                {
                    case 0:
                        if(m_MatrixPosX == 8)
                            temp = true;         
                            break;
                    case 90:
                        if(m_MatrixPosX == 9)
                            temp = true;         
                            break;
                    case 180:
                        if(m_MatrixPosX == 8)
                            temp = true;         
                            break;
                    case 270:
                        if(m_MatrixPosX == 7)
                            temp = true;         
                            break;
                }
            }
            else if(m_CurrentPiece == "Block")
            {
                if(m_MatrixPosX == 9)
                    temp = true;
            }
        }
        
        return temp;
    }
    
    private void CheckMoveLeft()
    {
        if(!IsStageLimit("Left"))
        {
            switch(m_CurrentPiece)
            {
                case "Quad":
                    if(m_Grid[m_MatrixPosX-1][m_MatrixPosY] > 0 || m_Grid[m_MatrixPosX-1][m_MatrixPosY-1] > 0)
                        m_MoveLeft = false;
                    else
                        m_MoveLeft = true;
                    break;
                case "Zl":
                    if(m_Piece.GetRotateValue() == 90 && m_MatrixPosX > 0 || m_Piece.GetRotateValue() == 270 && m_MatrixPosX > 0)
                    {
                        if(m_Grid[m_MatrixPosX-1][m_MatrixPosY] > 0 || m_Grid[m_MatrixPosX-1][m_MatrixPosY-1] > 0 || m_Grid[(m_MatrixPosX+1) - 1][m_MatrixPosY-2] > 0)
                            m_MoveLeft = false;
                        else
                            m_MoveLeft = true; 
                    }
                    else if(m_Piece.GetRotateValue() == 0 && m_MatrixPosX > 1 || m_Piece.GetRotateValue() == 180 && m_MatrixPosX > 1)
                    {
                        if(m_Grid[m_MatrixPosX-1][m_MatrixPosY] > 0 || m_Grid[(m_MatrixPosX-1) - 1][m_MatrixPosY-1] > 0)
                            m_MoveLeft = false;
                        else
                            m_MoveLeft = true;
                    }
                    else
                        m_MoveLeft = false;
                    break;
                case "Zr":
                    if(m_Piece.GetRotateValue() == 0 && m_MatrixPosX > 1 || m_Piece.GetRotateValue() == 180 && m_MatrixPosX > 1)
                    {
                        if(m_Grid[m_MatrixPosX-1][m_MatrixPosY] > 0 || m_Grid[(m_MatrixPosX+1) - 1][m_MatrixPosY-1] > 0)
                            m_MoveLeft = false;
                        else 
                            m_MoveLeft = true;
                    }
                    else if(m_Piece.GetRotateValue() == 90 && m_MatrixPosX > 2 || m_Piece.GetRotateValue() == 270 && m_MatrixPosX > 2)
                    {
                        if(m_Grid[m_MatrixPosX-1][m_MatrixPosY] > 0 || m_Grid[(m_MatrixPosX-1) - 1][m_MatrixPosY-1] > 0 || m_Grid[(m_MatrixPosX-1) - 1][m_MatrixPosY-2] > 0)
                            m_MoveLeft = false;
                        else
                            m_MoveLeft = true;
                    }
                    else
                        m_MoveLeft = false;
                case "Pr":
                    if(m_Piece.GetRotateValue() == 0)
                        if(m_Grid[m_MatrixPosX-1][m_MatrixPosY] > 0 || m_Grid[m_MatrixPosX-1][m_MatrixPosY-1] > 0 || m_Grid[m_MatrixPosX-1][m_MatrixPosY-2] > 0)
                            m_MoveLeft = false;
                        else
                            m_MoveLeft = true;
                    else if(m_Piece.GetRotateValue() == 90)
                        if(m_Grid[m_MatrixPosX-1][m_MatrixPosY] > 0 || m_Grid[(m_MatrixPosX-2) - 1][m_MatrixPosY-1] > 0)
                            m_MoveLeft = false;
                        else
                            m_MoveLeft = true;
                    else if(m_Piece.GetRotateValue() == 180)
                        if(m_Grid[m_MatrixPosX-1][m_MatrixPosY] > 0 || m_Grid[(m_MatrixPosX+1) - 1][m_MatrixPosY-1] > 0 || m_Grid[(m_MatrixPosX+1) - 1][m_MatrixPosY-2] > 0)
                            m_MoveLeft = false;
                        else
                            m_MoveLeft = true;
                    else if(m_Piece.GetRotateValue() == 270)
                        if(m_Grid[m_MatrixPosX-1][m_MatrixPosY] > 0 || m_Grid[m_MatrixPosX-1][m_MatrixPosY-1] > 0)
                            m_MoveLeft = false;
                        else
                            m_MoveLeft = true;
                    break;
                case "Pl":
                    if(m_Piece.GetRotateValue() == 0)
                        if(m_Grid[m_MatrixPosX-1][m_MatrixPosY] > 0 || m_Grid[m_MatrixPosX-1][m_MatrixPosY-1] > 0 || m_Grid[(m_MatrixPosX-1) - 1][m_MatrixPosY-2] > 0)
                            m_MoveLeft = false;
                        else
                            m_MoveLeft = true;
                    else if(m_Piece.GetRotateValue() == 90)
                        if(m_Grid[m_MatrixPosX-1][m_MatrixPosY] > 0 || m_Grid[(m_MatrixPosX+2) - 1][m_MatrixPosY-1] > 0)
                            m_MoveLeft = false;
                        else
                            m_MoveLeft = true;
                    else if(m_Piece.GetRotateValue() == 180)
                        if(m_Grid[m_MatrixPosX-1][m_MatrixPosY] > 0 || m_Grid[m_MatrixPosX-1][m_MatrixPosY-1] > 0 || m_Grid[m_MatrixPosX-1][m_MatrixPosY-2] > 0)
                            m_MoveLeft = false;
                        else
                            m_MoveLeft = true;
                    else if(m_Piece.GetRotateValue() == 270)
                            if(m_Grid[m_MatrixPosX-1][m_MatrixPosY] > 0 || m_Grid[m_MatrixPosX][m_MatrixPosY-1] > 0)
                            m_MoveLeft = false;
                        else
                            m_MoveLeft = true;
                    break;
                case "T":
                    if(m_Piece.GetRotateValue() == 0)
                        if(m_Grid[m_MatrixPosX-1][m_MatrixPosY] > 0 || m_Grid[(m_MatrixPosX-1) -1][m_MatrixPosY-1] > 0)
                            m_MoveLeft = false;
                        else
                            m_MoveLeft = true;
                    else if(m_Piece.GetRotateValue() == 90)
                        if(m_Grid[m_MatrixPosX-1][m_MatrixPosY] > 0 || m_Grid[(m_MatrixPosX-1)-1][m_MatrixPosY-1] > 0 || m_Grid[m_MatrixPosX-1][m_MatrixPosY-2] > 0)
                            m_MoveLeft = false;
                        else
                            m_MoveLeft = true;
                    else if(m_Piece.GetRotateValue() == 180)
                        if(m_Grid[m_MatrixPosX-1][m_MatrixPosY] >0 || m_Grid[(m_MatrixPosX+1)-1][m_MatrixPosY-1] > 0)
                            m_MoveLeft = false;
                        else
                            m_MoveLeft = true;
                    else if(m_Piece.GetRotateValue() == 270)
                        if(m_Grid[m_MatrixPosX-1][m_MatrixPosY] > 0 || m_Grid[m_MatrixPosX-1][m_MatrixPosY-1] > 0 || m_Grid[m_MatrixPosX-1][m_MatrixPosY-2] > 0)
                            m_MoveLeft = false;
                        else
                            m_MoveLeft = true;
                    break;
                case "Tower":
                    if(m_Piece.GetRotateValue() == 0 || m_Piece.GetRotateValue() == 180)
                        if(m_Grid[m_MatrixPosX-1][m_MatrixPosY] > 0 || m_Grid[m_MatrixPosX-1][m_MatrixPosY-1] > 0 || m_Grid[m_MatrixPosX-1][m_MatrixPosY-2] > 0 || m_Grid[m_MatrixPosX-1][m_MatrixPosY-3] > 0)
                            m_MoveLeft = false;
                        else
                            m_MoveLeft = true;
                    else if(m_Piece.GetRotateValue() == 90 || m_Piece.GetRotateValue() == 270)
                        if(m_Grid[m_MatrixPosX-1][m_MatrixPosY] > 0)
                            m_MoveLeft = false;
                        else
                            m_MoveLeft = true;
                    break;
                case "Block":
                    if(m_Grid[m_MatrixPosX-1][m_MatrixPosY] > 0)
                        m_MoveLeft = false;
                    else
                        m_MoveLeft = true;
                    break;
            }
        }
        else
            m_MoveLeft = false;
    }
    
    /* TO DO
    private void CheckMoveRight()
    {
        int [] tempPos;
        else
        {
            if(m_CurrentPiece == "Quad")
            {
                int[] temp = {m_MatrixPosX+1, m_MatrixPosY, m_MatrixPosX+1, m_MatrixPosY-1};
                return temp;
            }
            else if(m_CurrentPiece == "Zl")
            {
                switch(m_Piece.GetRotateValue())
                {
                    case 90:
                    case 270:
                        int[] temp = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX+1, m_MatrixPosY-1, m_MatrixPosX+1, m_MatrixPosY-2};
                        tempPos = temp;
                        break;
                    default:
                        int[] temp1 = {m_MatrixPosX+1, m_MatrixPosY, m_MatrixPosX, m_MatrixPosY-1};
                        tempPos = temp1;
                        break;
                }
                return tempPos;
            }
            else if(m_CurrentPiece == "Zr")
            {
                switch(m_Piece.GetRotateValue())
                {
                    case 90:
                    case 270:
                        int[] temp = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX, m_MatrixPosY-1, m_MatrixPosX-1, m_MatrixPosY-2};
                        tempPos = temp;
                        break;
                    default:
                        int[] temp1 = {m_MatrixPosX+1, m_MatrixPosY, m_MatrixPosX+2, m_MatrixPosY-1};
                        tempPos = temp1;
                        break;
                }
                return tempPos;
            }
            else if(m_CurrentPiece == "Pr")
            {
                switch(m_Piece.GetRotateValue())
                {
                    case 0:
                        int[] temp = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX, m_MatrixPosY-1, m_MatrixPosX+1, m_MatrixPosY-2};
                        tempPos = temp;
                        break;
                    case 90:
                        int[] temp1 = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX, m_MatrixPosY-1};
                        tempPos = temp1;
                        break;
                    case 180:
                        int[] temp2 = {m_MatrixPosX+1, m_MatrixPosY, m_MatrixPosX+1, m_MatrixPosY-1, m_MatrixPosX+1, m_MatrixPosY-2};
                        tempPos = temp2;
                        break;
                    case 270:
                        int[] temp3 = {m_MatrixPosX+2, m_MatrixPosY, m_MatrixPosX, m_MatrixPosY-1};
                        tempPos = temp3;
                        break;
                }
                return tempPos;
            }
            else if(m_CurrentPiece == "Pl")
            {
                switch(m_Piece.GetRotateValue())
                {
                    case 0:
                        int[] temp = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX, m_MatrixPosY-1, m_MatrixPosX, m_MatrixPosY-2};
                        tempPos = temp;
                        break;
                    case 90:
                        int[] temp1 = {m_MatrixPosX+2, m_MatrixPosY, m_MatrixPosX+2, m_MatrixPosY-1};
                        tempPos = temp1;
                        break;
                    case 180:
                        int[] temp2 = {m_MatrixPosX+1, m_MatrixPosY, m_MatrixPosX, m_MatrixPosY-1, m_MatrixPosX, m_MatrixPosY-2};
                        tempPos = temp2;
                        break;
                    case 270:
                        int[] temp3 = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX+2, m_MatrixPosY-1};
                        tempPos = temp3;
                        break;
                }
                return tempPos;
            }
            else if(m_CurrentPiece == "T")
            {
                switch(m_Piece.GetRotateValue())
                {
                    case 0:
                        int[] temp = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX+1, m_MatrixPosY-1};
                        tempPos = temp;
                        break;
                    case 90:
                        int[] temp1 = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX, m_MatrixPosY-1, m_MatrixPosX, m_MatrixPosY-2};
                        tempPos = temp1;
                        break;
                    case 180:
                        int[] temp2 = {m_MatrixPosX+2, m_MatrixPosY, m_MatrixPosX+1, m_MatrixPosY-1};
                        tempPos = temp2;
                        break;
                    case 270:
                        int[] temp3 = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX+1, m_MatrixPosY-1, m_MatrixPosX, m_MatrixPosY-2};
                        tempPos = temp3;
                        break;
                }
                return tempPos;
            }
            else if(m_CurrentPiece == "Tower")
            {
                switch(m_Piece.GetRotateValue())
                {
                    case 90:
                    case 270:
                        int[] temp = {m_MatrixPosX+3, m_MatrixPosY};
                        tempPos = temp;
                        break;
                    default:
                        int[] temp1 = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX, m_MatrixPosY-1, m_MatrixPosX, m_MatrixPosY-2, m_MatrixPosX, m_MatrixPosY-3};
                        tempPos = temp1;
                        break;
                }
                return tempPos;
            }
            else if(m_CurrentPiece == "Block")
            {
                int[] temp = {m_MatrixPosX, m_MatrixPosY};
                return temp;
            }
        }
        return null;
    }
    */
    
    public String tttttt()
    {
        if(t.length > 1)
            return "Matr1: " + t[0] + "  Matr2: " + t[1] + "   Matr3: " + t[2] + "  Matr4: " + t[3];
        else
            return "";
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
            if(m_UpdateTimer >= 15f)
            {
                CheckBoard();
                
                if( m_PosY < 50)
                    m_MatrixPosY++;

                m_PosY -= 5;
                
                m_UpdateTimer = 0;
            }
        }
        else
        {
            if(m_UpdateTimer >= 0.5f)
            {
                CheckBoard();
                
                if( m_PosY < 50)
                    m_MatrixPosY++;

                m_PosY -= 5;
                m_UpdateTimer = 0;
            }
        }
    }
    
    private void UpdateNextPiece()
    {
        m_Piece.DropPiece(43, 10, m_NextPiece, true);
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
    
    private boolean CheckPieceBellow(int _amountPieceToCheck, int[] _positions)
    {
        int x = 0;
        int y = 1;
        
        for(int i = 0; i < _amountPieceToCheck; i++)
        {
            if(m_Grid[_positions[x]][_positions[y] + 1] > 0)
                return true;
            else
            {
                x += 2;
                y += 2;
            }
        }
        
        return false;
    }
    
    private void QuadPlace()
    {
        int [] positionsToCheck = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX + 1, m_MatrixPosY};
        
        if(m_MatrixPosY == (m_Grid[m_MatrixPosX].length - 1))
        {
            m_Grid[m_MatrixPosX][m_MatrixPosY] = 1;
            m_Grid[m_MatrixPosX+1][m_MatrixPosY] = 1;
            m_Grid[m_MatrixPosX][m_MatrixPosY-1] = 1;
            m_Grid[m_MatrixPosX+1][m_MatrixPosY-1] = 1;
            ResetValues();  
        }
        else if(CheckPieceBellow(2, positionsToCheck) && m_MatrixPosY < 19 && m_MatrixPosY > 0)
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
        int [] positionsToCheck = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX+1, m_MatrixPosY-1, m_MatrixPosX-1, m_MatrixPosY-1};
        int [] positionsToCheck90 = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX-1, m_MatrixPosY-1};
        int [] positionsToCheck180 = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX+1, m_MatrixPosY, m_MatrixPosX+2, m_MatrixPosY};
        int [] positionsToCheck270 = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX+1, m_MatrixPosY-1};
        
        switch(m_Piece.GetRotateValue())
        {
            case 0:
                if(m_MatrixPosY == (m_Grid[m_MatrixPosX].length - 1))
                {
                    m_Grid[m_MatrixPosX][m_MatrixPosY] = 2;
                    m_Grid[m_MatrixPosX][m_MatrixPosY-1] = 2;
                    m_Grid[m_MatrixPosX+1][m_MatrixPosY-1] = 2;
                    m_Grid[m_MatrixPosX-1][m_MatrixPosY-1] = 2;
                    ResetValues();  
                }
                else if(CheckPieceBellow(3, positionsToCheck) && m_MatrixPosY < 19 && m_MatrixPosY > 0)
                {
                    m_Grid[m_MatrixPosX][m_MatrixPosY] = 2;
                    m_Grid[m_MatrixPosX][m_MatrixPosY-1] = 2;
                    m_Grid[m_MatrixPosX+1][m_MatrixPosY-1] = 2;
                    m_Grid[m_MatrixPosX-1][m_MatrixPosY-1] = 2;
                    ResetValues();
                }
                break;
               
            case 90:
                if(m_MatrixPosY == (m_Grid[m_MatrixPosX].length - 1))
                {
                    m_Grid[m_MatrixPosX][m_MatrixPosY] = 2;
                    m_Grid[m_MatrixPosX][m_MatrixPosY-1] = 2;
                    m_Grid[m_MatrixPosX-1][m_MatrixPosY-1] = 2;
                    m_Grid[m_MatrixPosX][m_MatrixPosY-2] = 2;
                    ResetValues();  
                }
                else if(CheckPieceBellow(2, positionsToCheck90) && m_MatrixPosY < 19 && m_MatrixPosY > 0)
                {
                    m_Grid[m_MatrixPosX][m_MatrixPosY] = 2;
                    m_Grid[m_MatrixPosX][m_MatrixPosY-1] = 2;
                    m_Grid[m_MatrixPosX-1][m_MatrixPosY-1] = 2;
                    m_Grid[m_MatrixPosX][m_MatrixPosY-2] = 2;
                    ResetValues();
                }
                break;
               
            case 180:
                if(m_MatrixPosY == (m_Grid[m_MatrixPosX].length - 1))
                {
                    m_Grid[m_MatrixPosX][m_MatrixPosY] = 2;
                    m_Grid[m_MatrixPosX+1][m_MatrixPosY] = 2;
                    m_Grid[m_MatrixPosX+1][m_MatrixPosY-1] = 2;
                    m_Grid[m_MatrixPosX+2][m_MatrixPosY] = 2;
                    ResetValues();  
                }
                else if(CheckPieceBellow(3, positionsToCheck180) && m_MatrixPosY < 19 && m_MatrixPosY > 0)
                {
                    m_Grid[m_MatrixPosX][m_MatrixPosY] = 2;
                    m_Grid[m_MatrixPosX+1][m_MatrixPosY] = 2;
                    m_Grid[m_MatrixPosX+1][m_MatrixPosY-1] = 2;
                    m_Grid[m_MatrixPosX+2][m_MatrixPosY] = 2;
                    ResetValues();
                }
                break;
               
            case 270:
                if(m_MatrixPosY == (m_Grid[m_MatrixPosX].length - 1))
                {
                    m_Grid[m_MatrixPosX][m_MatrixPosY] = 2;
                    m_Grid[m_MatrixPosX][m_MatrixPosY-1] = 2;
                    m_Grid[m_MatrixPosX+1][m_MatrixPosY-1] = 2;
                    m_Grid[m_MatrixPosX][m_MatrixPosY-2] = 2;
                    ResetValues();  
                }
                else if(CheckPieceBellow(2, positionsToCheck270) && m_MatrixPosY < 19 && m_MatrixPosY > 0)
                {
                    m_Grid[m_MatrixPosX][m_MatrixPosY] = 2;
                    m_Grid[m_MatrixPosX][m_MatrixPosY-1] = 2;
                    m_Grid[m_MatrixPosX+1][m_MatrixPosY-1] = 2;
                    m_Grid[m_MatrixPosX][m_MatrixPosY-2] = 2;
                    ResetValues();
                }
                break;
        }
    }
    
    private void ZlPlace()
    {
        int [] positionsToCheck = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX+1, m_MatrixPosY, m_MatrixPosX-1, m_MatrixPosY-1};
        int [] positionsToCheck90 = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX+1, m_MatrixPosY-1};
        
        switch(m_Piece.GetRotateValue())
        {
            case 90:
            case 270:
                if(m_MatrixPosY == (m_Grid[m_MatrixPosX].length - 1))
                {
                    m_Grid[m_MatrixPosX][m_MatrixPosY] = 3;
                    m_Grid[m_MatrixPosX][m_MatrixPosY-1] = 3;
                    m_Grid[m_MatrixPosX+1][m_MatrixPosY-1] = 3;
                    m_Grid[m_MatrixPosX+1][m_MatrixPosY-2] = 3;
                    ResetValues();  
                }
                else if(CheckPieceBellow(2, positionsToCheck90) && m_MatrixPosY < 19 && m_MatrixPosY > 0)
                {
                    m_Grid[m_MatrixPosX][m_MatrixPosY] = 3;
                    m_Grid[m_MatrixPosX][m_MatrixPosY-1] = 3;
                    m_Grid[m_MatrixPosX+1][m_MatrixPosY-1] = 3;
                    m_Grid[m_MatrixPosX+1][m_MatrixPosY-2] = 3;
                    ResetValues();
                }
                break;
             
            default:
                if(m_MatrixPosY == (m_Grid[m_MatrixPosX].length - 1))
                {
                    m_Grid[m_MatrixPosX][m_MatrixPosY] = 3;
                    m_Grid[m_MatrixPosX][m_MatrixPosY-1] = 3;
                    m_Grid[m_MatrixPosX+1][m_MatrixPosY] = 3;
                    m_Grid[m_MatrixPosX-1][m_MatrixPosY-1] = 3;
                    ResetValues();  
                }
                else if(CheckPieceBellow(3, positionsToCheck) && m_MatrixPosY < 19 && m_MatrixPosY > 0)
                {
                    m_Grid[m_MatrixPosX][m_MatrixPosY] = 3;
                    m_Grid[m_MatrixPosX][m_MatrixPosY-1] = 3;
                    m_Grid[m_MatrixPosX+1][m_MatrixPosY] = 3;
                    m_Grid[m_MatrixPosX-1][m_MatrixPosY-1] = 3;
                    ResetValues();
                }
                break;
        }
    }
    
    private void ZrPlace()
    {
        int [] positionsToCheck = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX+1, m_MatrixPosY, m_MatrixPosX+2, m_MatrixPosY-1};
        int [] positionsToCheck90 = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX-1, m_MatrixPosY-1};
        
        switch(m_Piece.GetRotateValue())
        {
            case 90:
            case 270:
                if(m_MatrixPosY == (m_Grid[m_MatrixPosX].length - 1))
                {
                    m_Grid[m_MatrixPosX][m_MatrixPosY] = 4;
                    m_Grid[m_MatrixPosX][m_MatrixPosY-1] = 4;
                    m_Grid[m_MatrixPosX-1][m_MatrixPosY-1] = 4;
                    m_Grid[m_MatrixPosX-1][m_MatrixPosY-2] = 4;
                    ResetValues();  
                }
                else if(CheckPieceBellow(2, positionsToCheck90) && m_MatrixPosY < 19 && m_MatrixPosY > 0)
                {
                    m_Grid[m_MatrixPosX][m_MatrixPosY] = 4;
                    m_Grid[m_MatrixPosX][m_MatrixPosY-1] = 4;
                    m_Grid[m_MatrixPosX-1][m_MatrixPosY-1] = 4;
                    m_Grid[m_MatrixPosX-1][m_MatrixPosY-2] = 4;
                    ResetValues();
                }
                break;
                
            default:
                if(m_MatrixPosY == (m_Grid[m_MatrixPosX].length - 1))
                {
                    m_Grid[m_MatrixPosX][m_MatrixPosY] = 4;
                    m_Grid[m_MatrixPosX+1][m_MatrixPosY] = 4;
                    m_Grid[m_MatrixPosX+1][m_MatrixPosY-1] = 4;
                    m_Grid[m_MatrixPosX+2][m_MatrixPosY-1] = 4;
                    ResetValues();  
                }
                else if(CheckPieceBellow(3, positionsToCheck) && m_MatrixPosY < 19 && m_MatrixPosY > 0)
                {
                    m_Grid[m_MatrixPosX][m_MatrixPosY] = 4;
                    m_Grid[m_MatrixPosX+1][m_MatrixPosY] = 4;
                    m_Grid[m_MatrixPosX+1][m_MatrixPosY-1] = 4;
                    m_Grid[m_MatrixPosX+2][m_MatrixPosY-1] = 4;
                    ResetValues();
                }
                break;
        }
    }
    
    private void PlPlace()
    {
        int [] positionsToCheck = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX-1, m_MatrixPosY-2};
        int [] positionsToCheck90 = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX+1, m_MatrixPosY, m_MatrixPosX+2, m_MatrixPosY};
        int [] positionsToCheck180 = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX+1, m_MatrixPosY};
        int [] positionsToCheck270 = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX+1, m_MatrixPosY-1, m_MatrixPosX+2, m_MatrixPosY-1};
        
        switch(m_Piece.GetRotateValue())
        {
            case 0:
                if(m_MatrixPosY == (m_Grid[m_MatrixPosX].length - 1))
                {
                    m_Grid[m_MatrixPosX][m_MatrixPosY] = 5;
                    m_Grid[m_MatrixPosX][m_MatrixPosY-1] = 5;
                    m_Grid[m_MatrixPosX][m_MatrixPosY-2] = 5;
                    m_Grid[m_MatrixPosX-1][m_MatrixPosY-2] = 5;
                    ResetValues();  
                }
                else if(CheckPieceBellow(2, positionsToCheck) && m_MatrixPosY < 19 && m_MatrixPosY > 0)
                {
                    m_Grid[m_MatrixPosX][m_MatrixPosY] = 5;
                    m_Grid[m_MatrixPosX][m_MatrixPosY-1] = 5;
                    m_Grid[m_MatrixPosX][m_MatrixPosY-2] = 5;
                    m_Grid[m_MatrixPosX-1][m_MatrixPosY-2] = 5;
                    ResetValues();
                }
                break;
                
            case 90:
                if(m_MatrixPosY == (m_Grid[m_MatrixPosX].length - 1))
                {
                    m_Grid[m_MatrixPosX][m_MatrixPosY] = 5;
                    m_Grid[m_MatrixPosX+1][m_MatrixPosY] = 5;
                    m_Grid[m_MatrixPosX+2][m_MatrixPosY] = 5;
                    m_Grid[m_MatrixPosX+2][m_MatrixPosY-1] = 5;
                    ResetValues();  
                }
                else if(CheckPieceBellow(3, positionsToCheck90) && m_MatrixPosY < 19 && m_MatrixPosY > 0)
                {
                    m_Grid[m_MatrixPosX][m_MatrixPosY] = 5;
                    m_Grid[m_MatrixPosX+1][m_MatrixPosY] = 5;
                    m_Grid[m_MatrixPosX+2][m_MatrixPosY] = 5;
                    m_Grid[m_MatrixPosX+2][m_MatrixPosY-1] = 5;
                    ResetValues();
                }
                break;
                
            case 180:
                if(m_MatrixPosY == (m_Grid[m_MatrixPosX].length - 1))
                {
                    m_Grid[m_MatrixPosX][m_MatrixPosY] = 5;
                    m_Grid[m_MatrixPosX+1][m_MatrixPosY] = 5;
                    m_Grid[m_MatrixPosX][m_MatrixPosY-1] = 5;
                    m_Grid[m_MatrixPosX][m_MatrixPosY-2] = 5;
                    ResetValues();  
                }
                else if(CheckPieceBellow(2, positionsToCheck180) && m_MatrixPosY < 19 && m_MatrixPosY > 0)
                {
                    m_Grid[m_MatrixPosX][m_MatrixPosY] = 5;
                    m_Grid[m_MatrixPosX+1][m_MatrixPosY] = 5;
                    m_Grid[m_MatrixPosX][m_MatrixPosY-1] = 5;
                    m_Grid[m_MatrixPosX][m_MatrixPosY-2] = 5;
                    ResetValues();
                }
                break;
                
                case 270:
                if(m_MatrixPosY == (m_Grid[m_MatrixPosX].length - 1))
                {
                    m_Grid[m_MatrixPosX][m_MatrixPosY] = 5;
                    m_Grid[m_MatrixPosX][m_MatrixPosY-1] = 5;
                    m_Grid[m_MatrixPosX+1][m_MatrixPosY-1] = 5;
                    m_Grid[m_MatrixPosX+2][m_MatrixPosY-1] = 5;
                    ResetValues();  
                }
                else if(CheckPieceBellow(3, positionsToCheck270) && m_MatrixPosY < 19 && m_MatrixPosY > 0)
                {
                    m_Grid[m_MatrixPosX][m_MatrixPosY] = 5;
                    m_Grid[m_MatrixPosX][m_MatrixPosY-1] = 5;
                    m_Grid[m_MatrixPosX+1][m_MatrixPosY-1] = 5;
                    m_Grid[m_MatrixPosX+2][m_MatrixPosY-1] = 5;
                    ResetValues();
                }
                break;
        }
    }
    
    private void PrPlace()
    {
        int [] positionsToCheck = {m_MatrixPosX, m_MatrixPosY};
        int [] positionsToCheck90 = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX-1, m_MatrixPosY-1, m_MatrixPosX-2, m_MatrixPosY-1};
        int [] positionsToCheck180 = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX+1, m_MatrixPosY};
        int [] positionsToCheck270 = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX+1, m_MatrixPosY, m_MatrixPosX+2, m_MatrixPosY};
        
        switch(m_Piece.GetRotateValue())
        {
            case 0:
                if(m_MatrixPosY == (m_Grid[m_MatrixPosX].length - 1))
                {
                    m_Grid[m_MatrixPosX][m_MatrixPosY] = 6;
                    m_Grid[m_MatrixPosX][m_MatrixPosY-1] = 6;
                    m_Grid[m_MatrixPosX][m_MatrixPosY-2] = 6;
                    m_Grid[m_MatrixPosX+1][m_MatrixPosY-2] = 6;
                    ResetValues();  
                }
                else if(CheckPieceBellow(1, positionsToCheck) && m_MatrixPosY < 19 && m_MatrixPosY > 0)
                {
                    m_Grid[m_MatrixPosX][m_MatrixPosY] = 6;
                    m_Grid[m_MatrixPosX][m_MatrixPosY-1] = 6;
                    m_Grid[m_MatrixPosX][m_MatrixPosY-2] = 6;
                    m_Grid[m_MatrixPosX+1][m_MatrixPosY-2] = 6;
                    ResetValues();
                }
                break;
                
            case 90:
                if(m_MatrixPosY == (m_Grid[m_MatrixPosX].length - 1))
                {
                    m_Grid[m_MatrixPosX][m_MatrixPosY] = 6;
                    m_Grid[m_MatrixPosX][m_MatrixPosY-1] = 6;
                    m_Grid[m_MatrixPosX-1][m_MatrixPosY-1] = 6;
                    m_Grid[m_MatrixPosX-2][m_MatrixPosY-1] = 6;
                    ResetValues();  
                }
                else if(CheckPieceBellow(3, positionsToCheck90) && m_MatrixPosY < 19 && m_MatrixPosY > 0)
                {
                    m_Grid[m_MatrixPosX][m_MatrixPosY] = 6;
                    m_Grid[m_MatrixPosX][m_MatrixPosY-1] = 6;
                    m_Grid[m_MatrixPosX-1][m_MatrixPosY-1] = 6;
                    m_Grid[m_MatrixPosX-2][m_MatrixPosY-1] = 6;
                    ResetValues();
                }
                break;
                
            case 180:
                if(m_MatrixPosY == (m_Grid[m_MatrixPosX].length - 1))
                {
                    m_Grid[m_MatrixPosX][m_MatrixPosY] = 6;
                    m_Grid[m_MatrixPosX+1][m_MatrixPosY] = 6;
                    m_Grid[m_MatrixPosX+1][m_MatrixPosY-1] = 6;
                    m_Grid[m_MatrixPosX+1][m_MatrixPosY-2] = 6;
                    ResetValues();  
                }
                else if(CheckPieceBellow(2, positionsToCheck180) && m_MatrixPosY < 19 && m_MatrixPosY > 0)
                {
                    m_Grid[m_MatrixPosX][m_MatrixPosY] = 6;
                    m_Grid[m_MatrixPosX+1][m_MatrixPosY] = 6;
                    m_Grid[m_MatrixPosX+1][m_MatrixPosY-1] = 6;
                    m_Grid[m_MatrixPosX+1][m_MatrixPosY-2] = 6;
                    ResetValues();
                }
                break;
                
            case 270:
                if(m_MatrixPosY == (m_Grid[m_MatrixPosX].length - 1))
                {
                    m_Grid[m_MatrixPosX][m_MatrixPosY] = 6;
                    m_Grid[m_MatrixPosX][m_MatrixPosY-1] = 6;
                    m_Grid[m_MatrixPosX+1][m_MatrixPosY] = 6;
                    m_Grid[m_MatrixPosX+2][m_MatrixPosY] = 6;
                    ResetValues();  
                }
                else if(CheckPieceBellow(3, positionsToCheck270) && m_MatrixPosY < 19 && m_MatrixPosY > 0)
                {
                    m_Grid[m_MatrixPosX][m_MatrixPosY] = 6;
                    m_Grid[m_MatrixPosX][m_MatrixPosY-1] = 6;
                    m_Grid[m_MatrixPosX+1][m_MatrixPosY] = 6;
                    m_Grid[m_MatrixPosX+2][m_MatrixPosY] = 6;
                    ResetValues();
                }
                break;
        }
    }
    
    private void TowerPlace()
    {
        int [] positionsToCheck = {m_MatrixPosX, m_MatrixPosY};
        int [] positionsToCheck90 = {m_MatrixPosX, m_MatrixPosY, m_MatrixPosX+1, m_MatrixPosY, m_MatrixPosX+2, m_MatrixPosY, m_MatrixPosX+3, m_MatrixPosY};
        
        switch(m_Piece.GetRotateValue())
        {
            case 90:
            case 270:
                if(m_MatrixPosY == (m_Grid[m_MatrixPosX].length - 1))
                {
                    m_Grid[m_MatrixPosX][m_MatrixPosY] = 7;
                    m_Grid[m_MatrixPosX+1][m_MatrixPosY] = 7;
                    m_Grid[m_MatrixPosX+2][m_MatrixPosY] = 7;
                    m_Grid[m_MatrixPosX+3][m_MatrixPosY] = 7;
                    ResetValues();  
                }
                else if(CheckPieceBellow(4, positionsToCheck90) && m_MatrixPosY < 19 && m_MatrixPosY > 0)
                {
                    m_Grid[m_MatrixPosX][m_MatrixPosY] = 7;
                    m_Grid[m_MatrixPosX+1][m_MatrixPosY] = 7;
                    m_Grid[m_MatrixPosX+2][m_MatrixPosY] = 7;
                    m_Grid[m_MatrixPosX+3][m_MatrixPosY] = 7;
                    ResetValues();
                }
                break;
            default:
                if(m_MatrixPosY == (m_Grid[m_MatrixPosX].length - 1))
                {
                    m_Grid[m_MatrixPosX][m_MatrixPosY] = 7;
                    m_Grid[m_MatrixPosX][m_MatrixPosY - 1] = 7;
                    m_Grid[m_MatrixPosX][m_MatrixPosY - 2] = 7;
                    m_Grid[m_MatrixPosX][m_MatrixPosY - 3] = 7;
                    ResetValues();  
                }
                else if(CheckPieceBellow(1, positionsToCheck) && m_MatrixPosY < 19 && m_MatrixPosY > 0)
                {
                    m_Grid[m_MatrixPosX][m_MatrixPosY] = 7;
                    m_Grid[m_MatrixPosX][m_MatrixPosY - 1] = 7;
                    m_Grid[m_MatrixPosX][m_MatrixPosY - 2] = 7;
                    m_Grid[m_MatrixPosX][m_MatrixPosY - 3] = 7;
                    ResetValues();
                }
                break;
        }
    }
    
    private void BlockPlace()
    {
        int [] positionsToCheck = {m_MatrixPosX, m_MatrixPosY};
        
        if(m_MatrixPosY == (m_Grid[m_MatrixPosX].length - 1))
        {
            m_Grid[m_MatrixPosX][m_MatrixPosY] = 8;
            ResetValues();  
        }
        else if(CheckPieceBellow(1, positionsToCheck) && m_MatrixPosY < 19 && m_MatrixPosY > 0)
        {
            m_Grid[m_MatrixPosX][m_MatrixPosY] = 8;
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
                if(m_Grid[x][y] > 0)
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
        m_Piece.RestartRotate();
        m_PosX = 5;
        m_PosY = 30;
        m_MatrixPosX = 5;
        m_MatrixPosY = 4;
        m_CurrentPiece = m_NextPiece;
        m_NextPiece = NextPiece();
    }
    
    public boolean IsGameOver()
    {
        if(m_MatrixPosY == 0)
        {
            if(m_Grid[m_MatrixPosX][m_MatrixPosY] > 0)
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

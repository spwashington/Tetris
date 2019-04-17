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
        m_PosY = 45;
        m_Grid = new int[10][20];
        m_NextPiece = NextPiece();
        m_Piece = new Piece(_openGL2, m_NextPiece);
        m_UpdateTimer = 0;
        
        CreateBoard();
    }
    
    public int GetHighScore()
    {
        return m_HighScore;
    }
    
    public void MoveLeft()
    {
        if(m_PosX > -20)
            m_PosX -= 5;
    }
    
    public void MoveRight()
    {
        if(m_PosX < 25)
            m_PosX += 5;
    }
    
    public void FastDropPiece()
    {
        if(m_PosY < 19)
            m_PosY -= 5;
    }
    
    public void Execute()
    {
        m_Piece.DrawPiece(m_Grid);
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
        m_Piece.DropPiece(m_PosX, m_PosY, m_NextPiece);
        Update();
    }
    
    private void Update()
    {
        m_UpdateTimer += 0.1f;
        
        if(m_UpdateTimer >= 5f)
        {
            m_PosY -= 5;
            m_UpdateTimer = 0;
        }
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

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;
import java.awt.Color;
import java.util.Random;
import textura.Textura;

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
    private int m_PieceRotate;
    
    private Textura m_Texture;
    private int m_TextureAmount;
    public static final String m_Face = "tex/0.png";
    private float m_Limit;
    private int m_Filter;
    private int m_Wrap;
    private int m_Mode;
    
    public Piece(GL2 _openGL2, String _pieceType)
    {
        m_GL2 = _openGL2;
        m_Glut = new GLUT();
        m_PosX = -20;
        m_PosY = 45;
        m_PieceLimit = new int[2];
        m_PieceRotate = 0;
        
        m_Texture = null;
        m_TextureAmount = 1;
        m_Filter = GL2.GL_LINEAR; 
        m_Wrap = GL2.GL_REPEAT;  
        m_Mode = GL2.GL_DECAL;
        m_Limit = 1;
        m_Texture = new Textura(m_TextureAmount);
    }
    
    public void SetTexture()
    {
        m_Texture.setAutomatica(true);
        m_Texture.setFiltro(m_Filter);
        m_Texture.setModo(m_Mode);
        m_Texture.setWrap(m_Wrap);  
        m_Texture.gerarTextura(m_GL2, m_Face, 0);
        m_GL2.glTexCoord2f(0.0f, -1.0f); 
        m_GL2.glTexCoord2f(1.0f, -1.0f); 
        m_GL2.glTexCoord2f(1.0f, 0.0f); 
        m_GL2.glTexCoord2f(0.0f, 0.0f); 
    }
    
    public void Rotate()
    {
        if(m_PieceRotate >= 270)
            m_PieceRotate = 0;
        else
            m_PieceRotate += 90;
    }
    
    public int GetRotateValue()
    {
        return m_PieceRotate;
    }
    
    public void SetRotateValue(int _value)
    {
        m_PieceRotate = _value;
    }
    
    public void RestartRotate()
    {
        m_PieceRotate = 0;
    }
    
    public void DropPiece(int _posX, int _posY, String _pieceType)
    {
        DefinePiece(_pieceType, _posX, _posY);
    }
    
    public void DropPiece(int _posX, int _posY, String _pieceType, boolean _onlyDraw)
    {
        DefinePiece(_pieceType, _posX, _posY, _onlyDraw);
    }
    
    
    private void DefinePiece(String _pieceType, int _posX, int _posY)
    {
        SetTexture();
        switch(_pieceType)
        {
            case "Block":
                DrawBlock(_posX, _posY);
                break;
            case "Quad":
                DrawQuad(_posX, _posY);
                break;
            case "Zl":
                DrawZL(_posX, _posY, false);
                break;
            case "Zr":
                DrawZR(_posX, _posY, false);
                break;
            case "Tower":
                DrawTower(_posX, _posY, false);
                break;
            case "T":
                DrawT(_posX, _posY, false);
                break;
            case "Pl":
                DrawPL(_posX, _posY, false);
                break;
            case "Pr":
                DrawPR(_posX, _posY, false);
                break;
        }
        m_Texture.desabilitarTextura(m_GL2, 0);
    }
    
    private void DefinePiece(String _pieceType, int _posX, int _posY, boolean _onlyDraw)
    {
        SetTexture();
        switch(_pieceType)
        {
            case "Block":
                DrawBlock(_posX, _posY);
                break;
            case "Quad":
                DrawQuad(_posX, _posY);
                break;
            case "Zl":
                DrawZL(_posX, _posY, _onlyDraw);
                break;
            case "Zr":
                DrawZR(_posX, _posY, _onlyDraw);
                break;
            case "Tower":
                DrawTower(_posX, _posY, _onlyDraw);
                break;
            case "T":
                DrawT(_posX, _posY, _onlyDraw);
                break;
            case "Pl":
                DrawPL(_posX, _posY, _onlyDraw);
                break;
            case "Pr":
                DrawPR(_posX, _posY, _onlyDraw);
                break;
        }
        m_Texture.desabilitarTextura(m_GL2, 0);
    }
    
    private void DrawBlock(int _posX, int _posY)
    {
        m_GL2.glPushMatrix();
        m_GL2.glTranslatef(_posX, _posY, 0);
        m_GL2.glColor3f(1, 1, 1);
        m_Glut.glutSolidCube(5);
        m_GL2.glPopMatrix();
    }
    
    private void DrawPL(int _posX, int _posY, boolean _onlyDraw)
    {
        if(_onlyDraw)
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
        else
        {
            switch(m_PieceRotate)
            {
                case 0:
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
                    break;

                case 90:
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
                    m_GL2.glTranslatef(_posX + 10, _posY, 0);
                    m_GL2.glColor3f(1, 1, 1);
                    m_Glut.glutSolidCube(5);
                    m_GL2.glPopMatrix();

                    m_GL2.glPushMatrix();
                    m_GL2.glTranslatef(_posX + 10, _posY + 5, 0);
                    m_GL2.glColor3f(1, 1, 1);
                    m_Glut.glutSolidCube(5);
                    m_GL2.glPopMatrix();
                    break;

                case 180:
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
                    m_GL2.glTranslatef(_posX, _posY + 10, 0);
                    m_GL2.glColor3f(1, 1, 1);
                    m_Glut.glutSolidCube(5);
                    m_GL2.glPopMatrix();
                    break;

                case 270:
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
                    m_GL2.glTranslatef(_posX + 10, _posY + 5, 0);
                    m_GL2.glColor3f(1, 1, 1);
                    m_Glut.glutSolidCube(5);
                    m_GL2.glPopMatrix();
                    break;
            }
        }
    }
    
    private void DrawPR(int _posX, int _posY, boolean _onlyDraw)
    {
        if(_onlyDraw)
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
        else
        {
            switch(m_PieceRotate)
            {
                case 0:
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
                    break;

                case 90:
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
                    m_GL2.glTranslatef(_posX - 5, _posY + 5, 0);
                    m_GL2.glColor3f(1, 1, 1);
                    m_Glut.glutSolidCube(5);
                    m_GL2.glPopMatrix();

                    m_GL2.glPushMatrix();
                    m_GL2.glTranslatef(_posX - 10, _posY + 5, 0);
                    m_GL2.glColor3f(1, 1, 1);
                    m_Glut.glutSolidCube(5);
                    m_GL2.glPopMatrix();
                    break;

                case 180:
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
                    m_GL2.glTranslatef(_posX + 5, _posY + 10, 0);
                    m_GL2.glColor3f(1, 1, 1);
                    m_Glut.glutSolidCube(5);
                    m_GL2.glPopMatrix();
                    break;

                case 270:
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
                    m_GL2.glTranslatef(_posX + 5, _posY, 0);
                    m_GL2.glColor3f(1, 1, 1);
                    m_Glut.glutSolidCube(5);
                    m_GL2.glPopMatrix();

                    m_GL2.glPushMatrix();
                    m_GL2.glTranslatef(_posX + 10, _posY, 0);
                    m_GL2.glColor3f(1, 1, 1);
                    m_Glut.glutSolidCube(5);
                    m_GL2.glPopMatrix();
                    break; 
            }
        }
    }
    
    private void DrawT(int _posX, int _posY, boolean _onlyDraw)
    {
        if(_onlyDraw)
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
        else
        {
            switch(m_PieceRotate)
            {
                case 0:
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
                    break;

                case 90:
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
                    m_GL2.glTranslatef(_posX - 5, _posY + 5, 0);
                    m_GL2.glColor3f(1, 1, 1);
                    m_Glut.glutSolidCube(5);
                    m_GL2.glPopMatrix();

                    m_GL2.glPushMatrix();
                    m_GL2.glTranslatef(_posX, _posY + 10, 0);
                    m_GL2.glColor3f(1, 1, 1);
                    m_Glut.glutSolidCube(5);
                    m_GL2.glPopMatrix();
                    break;

                case 180:
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
                    m_GL2.glTranslatef(_posX + 10, _posY, 0);
                    m_GL2.glColor3f(1, 1, 1);
                    m_Glut.glutSolidCube(5);
                    m_GL2.glPopMatrix();

                    m_GL2.glPushMatrix();
                    m_GL2.glTranslatef(_posX + 5, _posY + 5, 0);
                    m_GL2.glColor3f(1, 1, 1);
                    m_Glut.glutSolidCube(5);
                    m_GL2.glPopMatrix();
                    break;

                case 270:
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
                    m_GL2.glTranslatef(_posX, _posY + 10, 0);
                    m_GL2.glColor3f(1, 1, 1);
                    m_Glut.glutSolidCube(5);
                    m_GL2.glPopMatrix();
                    break;
            }
        }
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
    
    private void DrawZL(int _posX, int _posY, boolean _onlyDraw)
    {
        if(_onlyDraw)
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
        else
        {
            switch(m_PieceRotate)
            {
                case 90:
                case 270:
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
                    m_GL2.glTranslatef(_posX + 5, _posY + 10, 0);
                    m_GL2.glColor3f(1, 1, 1);
                    m_Glut.glutSolidCube(5);
                    m_GL2.glPopMatrix();
                    break;

                default:
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
                    break;
            }
        }
    }
    
    private void DrawZR(int _posX, int _posY, boolean _onlyDraw)
    {
        if(_onlyDraw)
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
        else
        {
            switch(m_PieceRotate)
            {
                case 90:
                case 270:
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
                    m_GL2.glTranslatef(_posX - 5, _posY + 5, 0);
                    m_GL2.glColor3f(1, 1, 1);
                    m_Glut.glutSolidCube(5);
                    m_GL2.glPopMatrix();

                    m_GL2.glPushMatrix();
                    m_GL2.glTranslatef(_posX - 5, _posY + 10, 0);
                    m_GL2.glColor3f(1, 1, 1);
                    m_Glut.glutSolidCube(5);
                    m_GL2.glPopMatrix();
                    break;

                default:
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
                    break;
            }
        }
    }
    
    private void DrawTower(int _posX, int _posY, boolean _onlyDraw)
    {
        if(_onlyDraw)
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
        else
        {
            switch(m_PieceRotate)
            {
                case 90:
                case 270:
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
                    m_GL2.glTranslatef(_posX + 10, _posY, 0);
                    m_GL2.glColor3f(1, 1, 1);
                    m_Glut.glutSolidCube(5);
                    m_GL2.glPopMatrix();

                    m_GL2.glPushMatrix();
                    m_GL2.glTranslatef(_posX + 15, _posY, 0);
                    m_GL2.glColor3f(1, 1, 1);
                    m_Glut.glutSolidCube(5);
                    m_GL2.glPopMatrix();
                    break;

                default:
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
                    break;
            }
        }
    }
    
    public void DrawPieceInBoard(int[][] _board)
    {
        for(int x = 0; x < _board.length; x++)
        {
            for(int y = 0; y < _board[x].length; y++)
            {
                switch(_board[x][y])
                {
                    case 1:
                        AddPieceInBoard(GetBoardPos(x, y)[0], GetBoardPos(x, y)[1], 0, 1, 0);
                        break;
                    case 2:
                        AddPieceInBoard(GetBoardPos(x, y)[0], GetBoardPos(x, y)[1], 1, 0, 0);
                        break;
                    case 3:
                        AddPieceInBoard(GetBoardPos(x, y)[0], GetBoardPos(x, y)[1], 0, 0, 1);
                        break;
                    case 4:
                        AddPieceInBoard(GetBoardPos(x, y)[0], GetBoardPos(x, y)[1], 1, 1, 0);
                        break;
                    case 5:
                        AddPieceInBoard(GetBoardPos(x, y)[0], GetBoardPos(x, y)[1], 0, 1, 1);
                        break;
                    case 6:
                        AddPieceInBoard(GetBoardPos(x, y)[0], GetBoardPos(x, y)[1], 1, 0, 1);
                        break;
                    case 7:
                        AddPieceInBoard(GetBoardPos(x, y)[0], GetBoardPos(x, y)[1], 0.80f, 0.66f, 0.49f);
                        break;
                    case 8:
                        AddPieceInBoard(GetBoardPos(x, y)[0], GetBoardPos(x, y)[1], 1f, 0.75f, 0.80f);
                        break;
                }
            }
        }
    }
    
    private void AddPieceInBoard(int _posX, int _posY, float _R, float _G, float _B)
    {
        m_GL2.glPushMatrix();
        m_GL2.glTranslatef(_posX, _posY, 0);
        m_GL2.glColor3f(_R, _G, _B);
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

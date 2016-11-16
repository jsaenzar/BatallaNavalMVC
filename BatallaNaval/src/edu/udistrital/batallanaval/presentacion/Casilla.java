package edu.udistrital.batallanaval.presentacion;

/**
 *   
 *
 * @author Leonardo Orejuela and Leonardo Saenz
 */
public class Casilla extends Actor {
    protected int vx;
    protected boolean Ocupado;
    private int tipo;
      
    public Casilla(Escenario stage) {
        super(stage);
        setSpriteName("celda.png");
        Ocupado = false;
    }
      
    public void act() {
        x+=vx;
        if (x < 0 || x > Escenario.WIDTH)
          vx = -vx;
    }
    
    public int getVx()
    { 
        return vx; 
    }
    
    public void setVx(int i) 
    {
          vx = i;
      }

    public boolean isOcupado() {
        return Ocupado;
    }

    public void setOcupado(boolean Ocupado) {
        this.Ocupado = Ocupado;
    }

    void setTipo(int tipo) {
        this.tipo = tipo;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getTipo() {
        return tipo;
    }     
}

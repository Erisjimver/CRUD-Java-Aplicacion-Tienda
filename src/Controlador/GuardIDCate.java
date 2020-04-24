package Controlador;
public class GuardIDCate {
    
private final String nombre;
private final String id ;
  
public GuardIDCate(String nombre, String id ) {
    this.nombre=nombre;
    this.id=id;
  }
 
public String getID(){
    return id ;
  }
 
@Override
  public String toString() {
    return nombre ;
  }
    
}

package Controlador;
public class GuardIDE {
    
private final String nombre;
private final String id ;
  
public GuardIDE(String nombre , String id ) {
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

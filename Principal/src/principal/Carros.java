
package principal;


public class Carros {
    
    private String marca;
    private String modelo;
    private float precio;
    private int anho;
    private String color;
    private String tipo;
    
    
    
    
    
     //Metodo Constructor:
    public Carros(String marca, String modelo,String color,float precio,int anho) 
    {
       this.marca = marca;
       this.modelo = modelo;
       this.precio = precio;
       this.color=color;
       this.anho=anho;
    }
    
    //Encapsulación de los datos
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public float getPrecio() {
        return precio;
    }

    public int getAnho() {
        return anho;
    }

    public void setAnho(int anho) {
        this.anho = anho;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
     //Metodo encargado de mostrar en pantalla los datos del auto con el precio más bajo
    public String mostrarDatos(){
    return "Marca: "+marca+"\n" +"Modelo: "+modelo+"\n"+"Color: "+color+ "\n"+"Año:"+anho+"\n" +"Precio: "+"$"+precio;
    }

    public float tofloat()
    {
        return this.precio;
    }

   
   
    
}


package principal;


public class Motos implements Comparador {
    
    private String marcaMoto;
    private String modeloMoto;
    private String colorMoto;
    private float precioMoto;
    private int anhoMoto;
    
    
    
    //Metodo Constructor:
    
    public Motos(String marcaMoto, String modeloMoto,String colorMoto,int anhoMoto, float precioMoto) 
    {
        this.marcaMoto = marcaMoto;
        this.modeloMoto = modeloMoto;
        this.precioMoto = precioMoto;
        this.anhoMoto=anhoMoto;
        this.colorMoto=colorMoto;
    }
        //Encapsulación de los datos

    public String getMarcaMoto() {
        return marcaMoto;
    }

    public void setMarcaMoto(String marcaMoto) {
        this.marcaMoto = marcaMoto;
    }

    public String getModeloMoto() {
        return modeloMoto;
    }

    public void setModeloMoto(String modeloMoto) {
        this.modeloMoto = modeloMoto;
    }

    public float getPrecioMoto() {
        return precioMoto;
    }

    public String getColorMoto() {
        return colorMoto;
    }

    public void setColorMoto(String colorMoto) {
        this.colorMoto = colorMoto;
    }

    public int getAnhoMoto() {
        return anhoMoto;
    }

    public void setAnhoMoto(int anhoMoto) {
        this.anhoMoto = anhoMoto;
    }
    
    
    
//Metodo encargado de mostrar en pantalla los datos de la moto con el precio más bajo

    public String datosMotos(){
    return "Marca: "+marcaMoto+"\n" +"Modelo: "+modeloMoto+"\n"+"Color: "+colorMoto+"\n"+"Año: "+anhoMoto+"\n"+"Precio: "+"$"+precioMoto;
    }

     public float tofloat(){
         
          return this.precioMoto;
    
     }
     
       
    @Override
    public boolean mayorque(Motos o) {
        
        Motos mot = (Motos) o;
        return this.tofloat()>mot.tofloat();
    }

    @Override
    public boolean menorQue(Motos o) {
        Motos mot = (Motos) o;
        return this.tofloat()<mot.tofloat();
    }

    @Override
    public boolean igualQue(Motos o) {
        Motos mot = (Motos) o;
        return this.tofloat()==mot.tofloat();
    }
    
    @Override
    public boolean mayorIgual(Motos o) {
        
        Motos mot = (Motos) o;
        return this.tofloat()>=mot.tofloat();
       
    }

    @Override
    public boolean menorIgual(Motos o) {
      Motos mot = (Motos) o;
      return this.tofloat() <= mot.tofloat();
        
    }

        
    
    
}

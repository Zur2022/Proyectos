package principal;


public class CamionesPesados extends Carros{
    private int capacidad;
    private String contieneFurgon;
    
    //Metodo Constructor
    public CamionesPesados(String marca,String modelo,String color,float precio,int anho,int capacidad,String contieneFurgon)
    {
    super(marca,modelo,color,precio,anho);
    this.capacidad=capacidad;
    this.contieneFurgon=contieneFurgon;
    }

    public int getCapacidad() {
        return capacidad;
    }
 
    public String mostrarDatosCamiones()
    {
    return "Marca: "+getMarca()+"\n" +"Modelo: "+getModelo()+"\n" +"Precio: "+"$"+getPrecio()+
            "\n"+"Año: "+getAnho()+"\n"+"Color: "+getColor()+"\n"+"Capacidad en toneladas: "+capacidad+"\n"+"Contiene furgon? "+contieneFurgon;
    }
    //Autor: Rodrigo Neftali Aguilar Uribe
}
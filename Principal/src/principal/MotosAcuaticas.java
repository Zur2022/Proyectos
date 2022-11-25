/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

/**
 *
 * @author Marielos
 */
public class MotosAcuaticas extends Motos {
    
    private int potencia;
    private String llantas;
    
    public MotosAcuaticas (String marcaMoto,String modeloMoto,String colorMoto,int anhoMoto, float precioMoto,int potencia , String llantas)
    {
        super(marcaMoto,modeloMoto,colorMoto,anhoMoto,precioMoto);
        this.potencia = potencia;
        this.llantas = llantas;
        
    }
    public String mostrarDatosMotos()
    {
    return "Marca: "+getMarcaMoto()+"\n" +"Modelo: "+getModeloMoto()+"\n" +"Precio: "+"$"+getPrecioMoto()+
            "\n"+"Año:"+getAnhoMoto()+"\n"+"Color: "+getColorMoto()+"\n"+"Potencia maxima : "+potencia+"\n"+"Contiene llantas? "+llantas;
    }

    public int getPotencia() {
        return potencia;
    }
    
    
}

package principal;

import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
public class Principal {

//A continuación se crea un metodo en el cual se almacenaran los vehiculos ingresados y su precio
//Calculando así, el vehiculo cuyo precio es el menor de todos.

    public static int indiceCarrosMBarato(Carros vehiculos[]){
    float precio;
    int indice=0;
    
    precio=vehiculos[0].getPrecio();
    for(int i=1;i<vehiculos.length;i++){
        if(vehiculos[i].getPrecio()<precio){
           precio=vehiculos[i].getPrecio();
           indice=i;
        }
    }
    return indice;
    }
    public static int indiceCamionPesado(CamionesPesados vehiculos2[]){
    int capacidad;
    int indice2=0;
    
    capacidad=vehiculos2[0].getCapacidad();
    for(int i=1;i>vehiculos2.length;i++){
        if(vehiculos2[i].getCapacidad()>capacidad){
           capacidad=vehiculos2[i].getCapacidad();
           indice2=i;
        }
    }
    return indice2;
    }
     public static int indicepotenciamoto(MotosAcuaticas motocicletas2[]){
    int potencia;
    int indice3=0;
    
    potencia=motocicletas2[0].getPotencia();
    for(int i=1;i>motocicletas2.length;i++){
        if(motocicletas2[i].getPotencia()>potencia){
           potencia=motocicletas2[i].getPotencia();
           indice3=i;
        }
    }
    return indice3;
    }
    //Main principal(contiene los diferentes procesos que realizara los respectivos metodos
    
    public static void main(String[] args) {
        Scanner OpcionesMenu=new Scanner(System.in);
        boolean salida=false;
        
        while(!salida)
        {char respuesta;
        System.out.println("\n A continuacion elija la opción que desee: ");
        System.out.println("1.Calcular el auto con el precio mas barato");
        System.out.println("2.Calcular la moto con el precio mas barata");
        System.out.println("3.Busqueda Secuencial de precios autos");
        System.out.println("4.Busqueda Binaria de precios motos");
        System.out.println("5.Arreglo para agregar");
        System.out.println("6.Arreglo para eliminar");
        System.out.println("7.Arreglo para editar");
        System.out.println("8.Ordenar los datos");
        System.out.println("9. Calcular el camion más pesado");
        System.out.println("A. Calcular la moto acuatica con menor potencia de turbina");
        System.out.println("B. Arreglo polimorfico de carro");
        System.out.println("C. Arreglo polimorfico de moto");
        System.out.println("D. Uso de Interfaces como comparadores");
        System.out.println("E. Cerrar el programa\n");
        respuesta=OpcionesMenu.next().toUpperCase().charAt(0);   
        switch(respuesta)
        {
            case '1':
       Scanner entrada= new Scanner(System.in);
       String marca,modelo,color;
       float precio;
       int numCarros,indiceBarato,anho,preciob,preciobe;
       try{
       System.out.print("Digite la cantidad de vehiculos: ");
       numCarros=entrada.nextInt();
       
       //Instanciamiento del objeto llamado "Vehiculos" perteneciente a la clase "Carros"
       
       Carros vehiculos[]= new Carros[numCarros];
       
       //A continuación se pide al usuario que ingrese los registros de los autos que desea almacenar.
       
       for(int i=0;i<numCarros;i++){
           entrada.nextLine();
           System.out.println("Digite las caracteristicas del Carro "+(i+1)+":");
           System.out.print("\n"+"Introdusca la Marca del carro: ");
           marca=entrada.nextLine();
           System.out.print("Introdusca el Modelo del carro: ");
           modelo=entrada.nextLine();
           System.out.print("Introdusca el color del carro: ");
           color=entrada.nextLine();
           System.out.print("Introdusca el año del carro: ");
           anho=entrada.nextInt();
           System.out.print("Introdusca el precio del carro: ");
           precio=entrada.nextInt();
         
           
           vehiculos[i]=new Carros(marca ,modelo ,color,precio,anho);
       
       }
       
       indiceBarato=indiceCarrosMBarato(vehiculos);
       
       System.out.println("\n"+"El coche mas barato es: ");
       System.out.println(vehiculos[indiceBarato].mostrarDatos()); }
       catch(Exception error)
       {
           System.out.println("Valor invalido!!, Por favor digite nuevamente");
       }
        //Luego se repite el mismo proceso donde pide que ingrese los datos pero esta vez de motos.
       //Almacenandolos en la clase Motos.
       break;
       
       case '2':
           
       Scanner entrada2= new Scanner(System.in);
       String marcaMoto,modeloMoto,colorMoto;
       float precioMoto;
       int numMotos,indiceBarata,anhoMoto;
       try{
       System.out.print("\n"+"Digite la cantidad de Motos: ");
       numMotos=entrada2.nextInt();
       
       Motos Motocicletas[]= new Motos[numMotos];
       
       for(int i=0;i<numMotos;i++){
           entrada2.nextLine();
           System.out.println("Digite las caracteristicas de la Moto "+(i+1)+":");
           System.out.print("\n"+"Introdusca la Marca de la moto: ");
           marcaMoto=entrada2.nextLine();
           System.out.print("Introdusca el Modelo de la moto: ");
           modeloMoto=entrada2.nextLine();
           System.out.print("Introdusca el color de la moto: ");
           colorMoto=entrada2.nextLine();
           System.out.print("Introdusca el año de la moto: ");
           anhoMoto=entrada2.nextInt();
           System.out.print("Introdusca el precio de la moto: ");
           precioMoto=entrada2.nextFloat();
           
           Motocicletas[i]=new Motos(marcaMoto ,modeloMoto ,colorMoto,anhoMoto,precioMoto);
           
       }
       indiceBarata=indiceMotosBarata(Motocicletas);
       System.out.println("\n"+"La moto mas barata es: ");
       System.out.println(Motocicletas[indiceBarata].datosMotos());}
       catch(Exception error)
       {
            System.out.println("Valor invalido!!, Por favor digite nuevamente");
       
       }
       break;
       
       case '3':
           
           //BUSQUEDA DE MANERA SECUENCIAL:
       Scanner entrada3=new Scanner(System.in);
       try{
       System.out.println("Ingrese la cantidad de autos: ");
       int cantidad=entrada3.nextInt();
       int[] arreglo=new int[cantidad];
       
       for(int i=0;i<arreglo.length;i++)
       {
       System.out.print("Ingrese el precio del auto para la posicion "+i+" del arreglo: ");
       arreglo[i]=entrada3.nextInt();
       }
       System.out.println("El contenido del arreglo es: ");
       for (int y=0;y<arreglo.length;y++)
       {
       System.out.println(" arreglo["+y+"] = " + arreglo[y]);
       }
      Scanner num=new Scanner(System.in); 
      
      System.out.println("Ingrese el precio del auto que busca: ");
      preciob=num.nextInt();
      
      int num_buscado=preciob;
       for (int e=0;e<arreglo.length;e++)
       {
       if(arreglo[e]==num_buscado)
       {
       System.out.println("El precio buscado esta en la posición: "+e);
       
       }
       
       }
       }catch(Exception error)
       {
            System.out.println("Valor invalido!!, Por favor digite nuevamente");
       
       }
       break;
       case '4':
           //BUSQUEDA BINARIA:
       Scanner entrada4=new Scanner(System.in);
       try{
       System.out.println("Ingrese la cantidad de motos: ");
       int tamano=entrada4.nextInt();
       int[] arreglo2=new int[tamano];
       
       for(int i=0;i<arreglo2.length;i++)
       {
       System.out.print("Ingrese el precio de la moto para la posicion "+i+" del arreglo: ");
       arreglo2[i]=entrada4.nextInt();
       }
       System.out.println("El contenido del arreglo es: ");
       for (int t=0;t<arreglo2.length;t++)
       {
       System.out.println(" arreglo["+t+"] = " + arreglo2[t]);
       }
      Scanner numero=new Scanner(System.in); 
      
      System.out.println("Ingrese el precio de la moto que busca: ");
      preciobe=numero.nextInt();
       
      int numBuscado=preciobe;
      int inferior=0;
      int centro;
      int superior=tamano-1;
      
      while(inferior<=superior)
      {
      centro=(superior+inferior)/2;
      if(arreglo2[centro]==numBuscado)
      {
      System.out.println("El numero buscado esta en la posición: "+centro);
      break;
      }else if(numBuscado<arreglo2[centro])
      {superior=centro -1;
              }
      else
      {inferior=centro+1;
              }
      }
       }catch(Exception error)
       {
           System.out.println("Valor invalido!!, Por favor digite nuevamente");
       
       }
      break;
       case '5':
      
      ArrayList lista=new ArrayList();
      lista.add("Toyota");
      lista.add("Nissan");
      lista.add("Honda");
      lista.add("Audi");
      lista.add("BMW");
      
            try{
           System.out.println("Este es el arreglo: "+lista.toString());
           System.out.println("Agregue una marca mas de auto: ");
           
           Scanner marcaAuto=new Scanner(System.in);
           String marcaIngresada=marcaAuto.nextLine();
           lista.add(marcaIngresada);
           System.out.println("Este es el nuevo arreglo: "+lista.toString());}
            catch(Exception error)
            {
               System.out.println("Valor invalido!!, Por favor digite nuevamente"); 
            
            }
     break;
     
       case '6':
           
           ArrayList eliminar=new ArrayList();
      eliminar.add("Toyota");
      eliminar.add("Nissan");
      eliminar.add("Honda");
      eliminar.add("Audi");
      eliminar.add("BMW");
            
            try{
           System.out.println("Este es el arreglo: "+eliminar.toString());
           System.out.println("Ingrese la marca del auto que quiere eliminar: ");
           
           Scanner marcaAutos=new Scanner(System.in);
           String marcaBorrada=marcaAutos.nextLine();
           eliminar.remove(marcaBorrada);
           System.out.println("Este es el nuevo arreglo: "+eliminar.toString());}
            catch(Exception error)
            {
                 System.out.println("Valor invalido!!, Por favor digite nuevamente");
            
            }
     break;
     
     case '7':
     ArrayList editar=new ArrayList();
      editar.add("Rojo");
      editar.add("Azul");
      editar.add("Verde");
      editar.add("Negro");
      editar.add("Gris");
     
           String editarColor;
           int posicion;
           try{
           System.out.println("Este es el arreglo: "+editar.toString());
           
           Scanner editarPosicion=new Scanner(System.in);
           System.out.println("Ingrese la posición del color del auto: ");
           posicion=editarPosicion.nextInt();
           posicion=posicion-1;
           
           Scanner editarMarca=new Scanner(System.in);
           System.out.println("Ingrese el nuevo color del auto que quiere editar: ");
           editarColor=editarMarca.nextLine();
           
           
           editar.set(posicion,editarColor);
           System.out.println("Este es el nuevo arreglo: "+editar.toString());}
           catch(Exception error)
           {
               System.out.println("Valor invalido!!, Por favor digite nuevamente");
           
           }
     
     break;
     
     case '8':
         System.out.println("Arreglo original con precios de carros desordenados en bodega:");
          int [] garaje ={3500 ,2320,1000,1200,4100,1000,3600,10000,7120,3000,8500};
          System.out.println(Arrays.toString(garaje));
          System.out.println("Arreglo ordenado de los precios de los carros en bodega:" + Arrays.toString(ordenarporseleccion()));
          System.out.println("*********************************************************************************************************");
          System.out.println("Arreglo original con precios de Motos desordenados en bodega:");
          int [] garajem ={4000 ,3455,9901,7289,1100,1000,3640,11000,7120,3030,8600};
          System.out.println(Arrays.toString(garaje));
          System.out.println("Arreglo ordenado de los precios de las Motos en bodega:" + Arrays.toString(ordenarporseleccionMotos())); 
        
          break;
        
     case '9':
       Scanner entrada5= new Scanner(System.in);
       int capacidad,indicePesado,numCamiones;
       String contieneFurgon;
       try{
       System.out.print("Digite la cantidad de camiones pesados: ");
       numCamiones=entrada5.nextInt();
       
       CamionesPesados vehiculos2[]= new CamionesPesados[numCamiones];
       
    //A continuación se pide al usuario que ingrese los registros de los autos que desea almacenar.
       
       for(int i=0;i<numCamiones;i++){
           entrada5.nextLine();
           System.out.println("Digite las caracteristicas del Camion "+(i+1)+":");
           System.out.print("\n"+"Introdusca la Marca del camion: ");
           marca=entrada5.nextLine();
           System.out.print("Introdusca el Modelo del camion: ");
           modelo=entrada5.nextLine();
           System.out.print("Introdusca el color del camion: ");
           color=entrada5.nextLine();
           System.out.print("Introdusca el precio del camion: ");
           precio=entrada5.nextInt();
           System.out.print("Introdusca el año del camion: ");
           anho=entrada5.nextInt();
           System.out.print("Introdusca la capacidad del camion(En toneladas)");
           capacidad=entrada5.nextInt();
           System.out.print("El camion contiene furgon? ");
           contieneFurgon=entrada5.nextLine();
         
           
           vehiculos2[i]=new CamionesPesados(marca,modelo,color,precio,anho,capacidad,contieneFurgon);
         
        }
       indicePesado=indiceCamionPesado(vehiculos2);
       
       System.out.println("\n"+"El coche mas pesado es: ");
       System.out.println(vehiculos2[indicePesado].mostrarDatosCamiones());}
       catch(Exception error)
       {
           System.out.println("Valor invalido!!, Por favor digite nuevamente");
       
       }
        break;
     case 'A':
        Scanner entrada6 =new Scanner(System.in);
        int potencia , nummotosacuaticas,indicepotencia;
        String llantas;
        try{
        System.out.println("Por favor ingrese el numero de motos acuaticas que desea: ");
        nummotosacuaticas= entrada6.nextInt();
        MotosAcuaticas motocicletas2[] = new MotosAcuaticas[nummotosacuaticas];
        
        for(int i=0;i<nummotosacuaticas;i++){
           entrada6.nextLine();
           System.out.println("Digite las caracteristicas de la Moto Acuatica "+(i+1)+":");
           System.out.print("\n"+"Introdusca la Marca de la moto: ");
           marcaMoto=entrada6.nextLine();
           System.out.print("Introdusca el Modelo de la moto acuatica: ");
           modeloMoto=entrada6.nextLine();
           System.out.print("Introdusca el color de moto acuatica: ");
           colorMoto=entrada6.nextLine();
           System.out.print("Introdusca el año de la moto acuatica: ");
           anhoMoto=entrada6.nextInt();
           System.out.print("Introdusca el precio de la moto acuatica: ");
           precioMoto=entrada6.nextInt();
           System.out.print("Introdusca la potencia de la Moto acuatica: ");
           potencia=entrada6.nextInt();
           System.out.print("La moto tiene llantas de respaldo?? ");
           llantas=entrada6.nextLine();
           
           motocicletas2[i]=new MotosAcuaticas( marcaMoto, modeloMoto, colorMoto, anhoMoto, precioMoto, potencia ,llantas);
         }
         indicepotencia=indicepotenciamoto(motocicletas2);
         System.out.println("\n" + "La moto con menos potencia en turbinas es: ");
         System.out.println(motocicletas2[indicepotencia].mostrarDatosMotos());}
        catch(Exception error)
        {
            System.out.println("Valor invalido!!, Por favor digite nuevamente");
        
        }
         break;
         
     case 'B':
         
         Carros Agregar []=new Carros[1];
         Scanner carro1 = new Scanner(System.in);
         Scanner carro2 = new Scanner(System.in);
         Scanner carro3 = new Scanner(System.in);
         Scanner carro4 = new Scanner(System.in);
         Scanner carro5 = new Scanner(System.in);
         try{
         System.out.println("Digite la marca del carro: ");
         marca=carro1.nextLine();
         System.out.println("Digite la modelo del carro: ");
         modelo=carro2.nextLine();
         System.out.println("Digite la color del carro: ");
         color=carro3.nextLine();
         System.out.println("Digite la precio del carro: ");
         precio=carro4.nextFloat();
         System.out.println("Digite la año del carro: ");
         anho=carro5.nextInt();
         
          Agregar[0]=new Carros(marca,modelo,color,precio,anho);
         System.out.println("Datos del carro: "+Agregar[0].getMarca()+","+Agregar[0].getModelo()+","+Agregar[0].getColor()+
                 ","+Agregar[0].getPrecio()+","+Agregar[0].getAnho()+",");}
         catch(Exception error)
         {
             System.out.println("Valor invalido!!, Por favor digite nuevamente");
         
         }
    break;  
    
    case 'C':
         
         Carros Agregar2[]=new Carros[2];
         Scanner carro6 = new Scanner(System.in);
         Scanner carro7 = new Scanner(System.in);
         Scanner carro8 = new Scanner(System.in);
         Scanner carro9 = new Scanner(System.in);
         Scanner carro10 = new Scanner(System.in);
         try{
         System.out.println("Digite la marca de la moto: ");
         marca=carro6.nextLine();
         System.out.println("Digite la modelo de la moto: ");
         modelo=carro7.nextLine();
         System.out.println("Digite la color de la moto: ");
         color=carro8.nextLine();
         System.out.println("Digite la precio de la moto: ");
         precio=carro9.nextFloat();
         System.out.println("Digite la año de la moto: ");
         anho=carro10.nextInt();
         
         Agregar2[0]=new Carros(marca,modelo,color,precio,anho);
         System.out.println("Datos de la Moto: "+Agregar2[0].getMarca()+","+Agregar2[0].getModelo()+","+Agregar2[0].getColor()+
                 ","+Agregar2[0].getPrecio()+","+Agregar2[0].getAnho()+",");}
         catch(Exception error)
         {
             System.out.println("Valor invalido!!, Por favor digite nuevamente");
         
         }
    break; 
    case 'D':
    float preciomoto1;
    float preciomoto2;
        Motos v[] = new Motos[2];
        Scanner moto = new Scanner(System.in);
        System.out.println("Comparador de precios en motocicletas");
        System.out.println("\n Ingrese el precio de la moto 1:");
        preciomoto1=moto.nextFloat();
        v[0]= new Motos ("Yamaha","123","Rojo", (int) preciomoto1, 2020);
        System.out.println("\n Ingrese el precio de la moto 2:");
        preciomoto2=moto.nextFloat();
        v[1]= new Motos ( "Yamaha","123","Rojo",(int) preciomoto2,2020);
        System.out.println("El precio de la moto 1 es " + (int) preciomoto1 + " es igual que el de la moto 2:" +(int) preciomoto2 + " " + v[0].igualQue(v[1]));
        System.out.println("El precio de la moto 1 es "+(int) preciomoto1 + "mayor o igual que el de la moto 2 " +(int) preciomoto2+ v[0].mayorIgual(v[1]));
        System.out.println("El precio de la moto 1 es "+(int)preciomoto1 + "mayor que el de la moto 2 " + (int) preciomoto2 + v[0].mayorque(v[1]));
        System.out.println("El precio de la moto 1 es "+(int)preciomoto1+ "menor que el de la  moto 2 " + (int)preciomoto2+ v[0].menorQue(v[1]));
        System.out.println("El precio de la moto 1 es "+(int)preciomoto1 + "menor o igual que la moto 2 " + (int) preciomoto2+ v[0].menorIgual(v[1]));
        
    break;
    
    case 'E':
         System.out.println("Gracias regrese pronto");
         salida=true;
         break;
     default:
         System.out.println("Esa opción no existe");
         break;    
        }
   
        
    }
    }
    public static int indiceMotosBarata(Motos Motocicletas[]){
    float precio;
    int indice=0;
    
    precio=Motocicletas[0].getPrecioMoto();
    for(int i=1;i<Motocicletas.length;i++){
        if(Motocicletas[i].getPrecioMoto()<precio){
           precio=Motocicletas[i].getPrecioMoto();
           indice=i;
        }
    }
    return indice;
    }
    public static int[] ordenarporseleccion ()
        {
        
        int [] garaje ={3500 ,2320,1000,1200,4100,1000,3600,10000,7120,3000,8500};
        int n= garaje.length , iMenor;
        
  
        for( int i = 0; i < n-1; i++)
        {
            iMenor = i;
            for (int j=i+1; j<n; j++) if(garaje[j]<garaje[iMenor]) iMenor=j;
              int aux= garaje[i];
            if(i != iMenor)
                garaje[i] = garaje[iMenor];
            garaje[iMenor]=aux;
        }    
        return garaje;
        } 
    public static int[] ordenarporseleccionMotos ()
        {
        
        int [] garajem ={4000 ,3455,9901,7289,1100,1000,3640,11000,7120,3030,8600};
        int n= garajem.length , iMenor;
        
  
        for( int i = 0; i < n-1; i++)
        {
            iMenor = i;
            for (int j=i+1; j<n; j++) if(garajem[j]<garajem[iMenor]) iMenor=j;
              int temporal = garajem[i];
            if(i != iMenor)
                garajem[i] = garajem[iMenor];
            garajem[iMenor]= temporal;
        }    
        return garajem;
        } 
      
}
/*// Autores: Rodrigo Neftali Aguilar Uribe(Arreglos y Metodos de busqueda)
              Salvador Arturo Manzur Rodriguez(Metodos de ordenamiento e Interface)
Universidad Catolica De El Salvador
Ingenieria En Sistemas Informaticos
Porgramación Orientada a Objetos/*/
/**
 * La clase representa a un parking de una ciudad europea
 * que dispone de dos tarifas de aparcamiento para los clientes
 * que lo usen: la tarifa regular (que incluye una tarifa plana para
 * entradas "tempranas") y la tarifa comercial para clientes que trabajan
 * cerca del parking, aparcan un nº elevado de horas y se benefician de esta 
 * tarifa más económica
 * (leer enunciado)
 * 
 * @author Xabier Ruiz Melero
 */
public class Parking
{
    // Declaración de constantes // 
    private final char REGULAR = 'R';
    private final char COMERCIAL = 'C';
    private final double PRECIO_BASE_REGULAR = 2.0;
    private final double PRECIO_MEDIA_REGULAR_HASTA11 = 3.0;
    private final double PRECIO_MEDIA_REGULAR_DESPUES11 = 5.0;  
    private final int HORA_INICIO_ENTRADA_TEMPRANA = 6 * 60;
    private final int HORA_FIN_ENTRADA_TEMPRANA = 8 * 60 + 30;
    private final int HORA_INICIO_SALIDA_TEMPRANA = 15 * 60;
    private final int HORA_FIN_SALIDA_TEMPRANA = 18 * 60;
    private final double PRECIO_TARIFA_PLANA_REGULAR = 15.0;
    private final double PRECIO_PRIMERAS3_COMERCIAL = 5.00;
    private final double PRECIO_MEDIA_COMERCIAL = 3.00;
    private String nombre;
    private int cliente;
    private double importeTotal;
    private int regular;
    private int comercial;
    private int clientesLunes;
    private int clientesSabado;
    private int clientesDomingo;
    private int clienteMaximoComercial;
    private double importeMaximoComercial;
    /**
     * Inicializa el parking con el nombre indicada por el parámetro.
     * El resto de atributos se inicializan a 0 
     */
    public Parking(String queNombre) { // Constructor con valor "nombre" modificable y los demas valores a 0
        nombre = queNombre;
        cliente = 0;
        importeTotal = 0;
        regular = 0;
        comercial = 0;
        clientesLunes = 0;
        clientesSabado = 0;
        clientesDomingo = 0;
        clienteMaximoComercial = 0;
        importeMaximoComercial = 0;
    }

    /**
     * accesor para el nombre del parking
     *  
     */
    public String getNombre() { // Accesor
        return nombre;
    }

    /**
     * mutador para el nombre del parking
     *  
     */
    public void setNombre(String nuevoNombre) { // Mutador
        nombre = nuevoNombre;
    }

    /**
     *  Recibe cuatro parámetros que supondremos correctos:
     *    tipoTarifa - un carácter 'R' o 'C'
     *    entrada - hora de entrada al parking
     *    salida – hora de salida del parking
     *    dia – nº de día de la semana (un valor entre 1 y 7)
     *    
     *    A partir de estos parámetros el método debe calcular el importe
     *    a pagar por el cliente y mostrarlo en pantalla 
     *    y  actualizará adecuadamente el resto de atributos
     *    del parking para poder mostrar posteriormente (en otro método) las estadísticas
     *   
     *    Por simplicidad consideraremos que un cliente entra y sale en un mismo día
     *    
     *    (leer enunciado del ejercicio)
     */
    public void facturarCliente(char tipoTarifa, int entrada, int salida, int dia) {
        int horasEntrada = entrada / 100; // Cálculo de las horas de entrada
        int minutosEntrada = entrada % 100; // Cálculo de los minutos de entrada
        int horasSalida = salida / 100; // Cálculo de las horas de salida
        int minutosSalida = salida % 100; // Cálculo de los minutos de salida 

        String minutoCero = "0";
        String minutosEntradaConCero = minutoCero + minutosEntrada;
        String minutosSalidaConCero = minutoCero + minutosSalida;

        int tarifaTemprana = 15; // Se le aplica un valor por defecto a tarifaTemprana
        double tarifaRegular = (PRECIO_BASE_REGULAR + (((salida - entrada) / 50) * 5 )); // Se le aplica un valor por defecto a tarifaRegular
        int tresHoras = 5; // Se le aplica un valor por defecto a tresHoras
        int mediasHoras = (5 + ((((salida - entrada) - 300) / 50) * 3)); // Se le aplica un valor por defecto a mediasHoras

        double importe;
        importe = tarifaTemprana + tarifaRegular + tresHoras + mediasHoras;
        importeTotal = importe;

        String s1 = ":"; // Declaración de una variable local de tipo String y se define por los dos puntos de la hora
        String s2 = ""; // Declaración de una variable local de tipo String y se define la entrada completa
        String s3 = ""; // Declaración de una variable local de tipo String y se define la salida completa

        if (minutosEntrada < 10) { // Si los minutos son menores a 10, se añadirá un 0 al principio.
            s2 = horasEntrada + s1 + minutosEntradaConCero;}
        else {
            s2 = horasEntrada + s1 + minutosEntrada;
        }

        if (minutosSalida < 10) { // Si los minutos son menores a 10, se añadirá un 0 al principio.
            s3 = horasSalida + s1 + minutosSalidaConCero;}
        else {
            s3 = horasSalida + s1 + minutosSalida; 
        }

        cliente++;

        switch (tipoTarifa){ // System.out.println se repite ya que los valores de "importe a pagar" no son los mismos.
            case 'R': if (entrada >= HORA_INICIO_ENTRADA_TEMPRANA || entrada < HORA_FIN_ENTRADA_TEMPRANA && salida >= HORA_INICIO_SALIDA_TEMPRANA || salida < HORA_FIN_SALIDA_TEMPRANA){ // En el caso de R... (Tarifa Regular)
                System.out.println("***************************************");
                System.out.println("Cliente nº: " + cliente);
                System.out.println("Hora entrada: " + s2);
                System.out.println("Hora salida: " + s3);
                System.out.println("Tarifa a aplicar: " + tipoTarifa);
                System.out.println("Importe a pagar: " + tarifaTemprana + "€");
                System.out.println("***************************************");

                regular++;
            }
            else if (entrada < HORA_INICIO_ENTRADA_TEMPRANA || entrada >= HORA_FIN_ENTRADA_TEMPRANA && salida < HORA_INICIO_SALIDA_TEMPRANA || salida >= HORA_FIN_SALIDA_TEMPRANA){ 
                System.out.println("***************************************");
                System.out.println("Cliente nº: " + cliente);
                System.out.println("Hora entrada: " + s2);
                System.out.println("Hora salida: " + s3);
                System.out.println("Tarifa a aplicar: " + tipoTarifa);
                System.out.println("Importe a pagar: " + tarifaRegular + "€");
                System.out.println("***************************************"); 

                regular++;
            } 
            break;
            case 'C': if ((salida - entrada) <= 300){ // En el caso de C... (Tarifa Comercial)
                System.out.println("***************************************");
                System.out.println("Cliente nº: " + cliente);
                System.out.println("Hora entrada: " + s2);
                System.out.println("Hora salida: " + s3);
                System.out.println("Tarifa a aplicar: " + tipoTarifa);
                System.out.println("Importe a pagar: " + tresHoras + "€");
                System.out.println("***************************************");

                comercial++;
            }
            else if ((salida - entrada) > 300){
                System.out.println("***************************************");
                System.out.println("Cliente nº: " + cliente);
                System.out.println("Hora entrada: " + s2);
                System.out.println("Hora salida: " + s3);
                System.out.println("Tarifa a aplicar: " + tipoTarifa);
                System.out.println("Importe a pagar: " + mediasHoras + "€");
                System.out.println("***************************************");

                comercial++;
            }
            if (comercial == 1) { // Incremento de las variables (clienteMaximoComercial e importeMaximoComercial)
                clienteMaximoComercial = cliente;
                importeMaximoComercial = mediasHoras;
            }
            else {
                if (importeMaximoComercial < mediasHoras) {
                    clienteMaximoComercial = cliente;
                    importeMaximoComercial = mediasHoras;
                }
            }
            break;
        }
    }

    /**
     * Muestra en pantalla las estadísticas sobre el parking  
     *   
     * (leer enunciado)
     *  
     */
    public void printEstadísticas() { // Impresión de las estadísticas recogidas por el programa
        System.out.println("***********************************************");
        System.out.println("Importe total entre todos los clientes: " + importeTotal);
        System.out.println("Nº clientes tarifa regular: " + regular);
        System.out.println("Nº clientes tarifa comercial: " + comercial);
        System.out.println("Cliente tarifa comerical con factura máxima fue el " + clienteMaximoComercial + " y pagó " + importeMaximoComercial);
        System.out.println("***********************************************");
    }

    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que más clientes han utilizado el parking - "SÁBADO"   "DOMINGO" o  "LUNES"
     */
    public String diaMayorNumeroClientes() { // Se representa con un String, el día que mas clientes ha tenido el parking, 
        if (clientesSabado > clientesLunes && clientesSabado > clientesDomingo){
            return "SABADO";}
        else if (clientesDomingo > clientesLunes){
            return "DOMINGO";}
        else{
            return "LUNES";
        }
    }
}
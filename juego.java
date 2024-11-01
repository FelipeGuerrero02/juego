import java.util.Scanner; // Importa la clase Scanner para permitir la entrada de datos del usuario

class Personaje { // Clase base que representa a un personaje
    String nombre; // Atributo para almacenar el nombre del personaje
    int vida; // Atributo para almacenar la vida del personaje
    int fuerza; // Atributo para almacenar la fuerza del personaje
    int defensa; // Atributo para almacenar la defensa del personaje

    public Personaje(String nombre, int vida, int fuerza, int defensa) { // Constructor de la clase Personaje
        this.nombre = nombre; // Asigna el nombre al atributo
        this.vida = vida; // Asigna la vida al atributo
        this.fuerza = fuerza; // Asigna la fuerza al atributo
        this.defensa = defensa; // Asigna la defensa al atributo
    }

    public void atacar(Personaje objetivo) { // Método para atacar a otro personaje
        int danio = this.fuerza - objetivo.defensa; // Calcula el daño considerando la defensa del objetivo
        if (danio > 0) { // Si el daño es mayor que 0
            objetivo.recibirDanio(danio); // Llama al método para que el objetivo reciba daño
            System.out.println(this.nombre + " ataca a " + objetivo.nombre + " y le hace " + danio + " de dano."); // Muestra el resultado del ataque
        } else {
            System.out.println(this.nombre + " ataca a " + objetivo.nombre + " pero no hace dano."); // Muestra que no se hizo daño
        }
    }

    public void recibirDanio(int danio) { // Método para recibir daño
        this.vida -= danio; // Resta el daño a la vida del personaje
        if (this.vida < 0) this.vida = 0; // Si la vida es menor que 0, se establece en 0
    }

    public boolean estaVivo() { // Método para verificar si el personaje está vivo
        return this.vida > 0; // Devuelve true si la vida es mayor que 0
    }
}

class Heroe extends Personaje { // Clase Heroe que hereda de Personaje
    public Heroe(String nombre, int vida, int fuerza, int defensa) { // Constructor de la clase Heroe
        super(nombre, vida, fuerza, defensa); // Llama al constructor de la clase base
    }

    public void usarPoderEspecial(Personaje objetivo) { // Método para usar un poder especial
        int danio = this.fuerza * 2 - objetivo.defensa; // Calcula el daño del poder especial
        if (danio > 0) { // Si el daño es mayor que 0
            objetivo.recibirDanio(danio); // Llama al método para que el objetivo reciba daño
            System.out.println(this.nombre + " usa un poder especial y le hace " + danio + " de dano a " + objetivo.nombre + "."); // Muestra el resultado del poder especial
        } else {
            System.out.println(this.nombre + " usa un poder especial, pero no hace dano."); // Muestra que no se hizo daño
        }
    }
}

class Villano extends Personaje { // Clase Villano que hereda de Personaje
    public Villano(String nombre, int vida, int fuerza, int defensa) { // Constructor de la clase Villano
        super(nombre, vida, fuerza, defensa); // Llama al constructor de la clase base
    }
}

public class juego { // Clase principal del juego
    public static void main(String[] args) { // Método principal que inicia la ejecución del programa
        Scanner scanner = new Scanner(System.in); // Crea un objeto Scanner para leer la entrada del usuario

        // Pedir nombre del heroe
        System.out.print("Ingresa el nombre del heroe: "); // Pide al usuario que ingrese el nombre del héroe
        String nombreHeroe = scanner.nextLine(); // Lee el nombre del héroe
        Heroe heroe = new Heroe(nombreHeroe, 100, 20, 5); // Crea un objeto Heroe con nombre, vida, fuerza y defensa

        // Pedir nombre del villano
        System.out.print("Ingresa el nombre del villano: "); // Pide al usuario que ingrese el nombre del villano
        String nombreVillano = scanner.nextLine(); // Lee el nombre del villano
        Villano villano = new Villano(nombreVillano, 80, 15, 3); // Crea un objeto Villano con nombre, vida, fuerza y defensa

        while (heroe.estaVivo() && villano.estaVivo()) { // Bucle que continúa mientras ambos personajes estén vivos
            System.out.println("\nTurno del heroe:"); // Indica que es el turno del héroe
            System.out.println("1. Ataque normal"); // Opción de ataque normal
            System.out.println("2. Poder especial"); // Opción de usar un poder especial
            System.out.print("Elige una accion: "); // Pide al usuario que elija una acción
            int opcion = scanner.nextInt(); // Lee la opción elegida por el usuario

            if (opcion == 1) { // Si el usuario elige ataque normal
                heroe.atacar(villano); // El héroe ataca al villano
            } else if (opcion == 2) { // Si el usuario elige poder especial
                heroe.usarPoderEspecial(villano); // El héroe usa un poder especial contra el villano
            } else {
                System.out.println("Opcion no valida. Ataque normal por defecto."); // Mensaje de opción no válida
                heroe.atacar(villano); // El héroe ataca por defecto
            }

            System.out.println(villano.nombre + " tiene " + villano.vida + " de vida."); // Muestra la vida restante del villano

            if (villano.estaVivo()) { // Si el villano sigue vivo
                System.out.println("\nTurno del villano:"); // Indica que es el turno del villano
                villano.atacar(heroe); // El villano ataca al héroe
                System.out.println(heroe.nombre + " tiene " + heroe.vida + " de vida."); // Muestra la vida restante del héroe
            }
        }

        if (heroe.estaVivo()) { // Si el héroe está vivo al final
            System.out.println(heroe.nombre + " ha ganado!"); // Mensaje de victoria del héroe
        } else {
            System.out.println(villano.nombre + " ha ganado!"); // Mensaje de victoria del villano
        }

        scanner.close(); // Cierra el objeto Scanner
    }
}

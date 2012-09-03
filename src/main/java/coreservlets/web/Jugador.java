package coreservlets;

public class Jugador implements Comparable<Jugador> {

    private String nombre = null;
    private int elo = 1500;
    private int numPartidas = 0;
    
    public Jugador() {
        
    }
    
    public Jugador(String nombre) {
        this.nombre = nombre;
    }
    

    public String getNombre() {
        return nombre;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + (this.nombre != null ? this.nombre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Jugador other = (Jugador) obj;
        if ((this.nombre == null) ? (other.nombre != null) : !this.nombre.equals(other.nombre)) {
            return false;
        }
        return true;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getElo() {
        return elo;
    }

    public void setElo(int elo) {
        this.elo = elo;
    }

    public int getNumPartidas() {
        return numPartidas;
    }

    public void setNumPartidas(int numPartidas) {
        this.numPartidas = numPartidas;
    }

    @Override
    public int compareTo(Jugador o) {
        Integer elo1 = this.getElo();
        Integer elo2 = o.getElo();
        return elo2.compareTo(elo1);
    }
    
}

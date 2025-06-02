package org.example.model.entities;

import org.example.model.entities.enums.Difficulty;

public class Room {

    private int idRoom;  // esto cómo lo conectamos a la base de datos?
    private int idEscaperoom_ref;  // esto cómo lo conectamos a la base de datos?
    private String name;  // 45 max, gestionar
    private Difficulty dificulty;
    private float price;  // 2 decimales max, gestionar

    // BORRAR LUEGO, ESTOY PROBANDO COSAS


    public Room(int idRoom, int idEscaperoom_ref, String name, Difficulty dificulty, float price) {
        this.idRoom = idRoom;
        this.idEscaperoom_ref = idEscaperoom_ref;
        this.name = name;
        this.dificulty = dificulty;
        this.price = price;
    }

    /// ///

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public int getIdEscaperoom_ref() {
        return idEscaperoom_ref;
    }

    public void setIdEscaperoom_ref(int idEscaperoom_ref) {
        this.idEscaperoom_ref = idEscaperoom_ref;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Difficulty getDificulty() {
        return dificulty;
    }

    public void setDificulty(Difficulty dificulty) {
        this.dificulty = dificulty;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Id de la habitación: " + idRoom + "\nNombre: " + name + "\nDificultad: " + dificulty.getDescription()
                + "\nPrecio: " + price + " euros";
    }

    // Validaciones (que no esté vacío, que el tipo de dato sea el que toca (esto quizás utils),
    // que no se pase de los caracteres máximos que acepta la base de datos...

}
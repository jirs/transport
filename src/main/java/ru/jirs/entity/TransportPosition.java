package ru.jirs.entity;

import javax.persistence.*;

@Entity
public class TransportPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "x")
    private int x;
    @Column(name = "y")
    private int y;

    public TransportPosition() {
    }

    public TransportPosition(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransportPosition that = (TransportPosition) o;

        if (id != that.id) return false;
        if (x != that.x) return false;
        return y == that.y;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + x;
        result = 31 * result + y;
        return result;
    }
}

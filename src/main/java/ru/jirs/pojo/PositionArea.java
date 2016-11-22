package ru.jirs.pojo;

public class PositionArea {
    private int xFrom;
    private int xTo;
    private int yFrom;
    private int yTo;

    public PositionArea() {}

    public PositionArea(int xFrom, int xTo, int yFrom, int yTo) {
        this.xFrom = xFrom;
        this.xTo = xTo;
        this.yFrom = yFrom;
        this.yTo = yTo;
    }

    public int getxFrom() {
        return xFrom;
    }

    public int getxTo() {
        return xTo;
    }

    public int getyFrom() {
        return yFrom;
    }

    public int getyTo() {
        return yTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PositionArea that = (PositionArea) o;

        if (xFrom != that.xFrom) return false;
        if (xTo != that.xTo) return false;
        if (yFrom != that.yFrom) return false;
        return yTo == that.yTo;

    }

    @Override
    public int hashCode() {
        int result = xFrom;
        result = 31 * result + xTo;
        result = 31 * result + yFrom;
        result = 31 * result + yTo;
        return result;
    }
}

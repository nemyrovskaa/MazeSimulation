package com.maze.robot.finding_path;

import java.util.Objects;

public class Position <T> {
    public T x;
    public T y;

    public Position(T x, T y) {
        this.x = x;
        this.y = y;
    }

    // overridden methods equals and hashCode for proper comparison
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Position))
            return false;
        Position<?> position = (Position<?>) o;
        return Objects.equals(x, position.x) && Objects.equals(y, position.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

package UtilityFunction;

@FunctionalInterface
public interface performAction<T> {
    void set(T item);
}
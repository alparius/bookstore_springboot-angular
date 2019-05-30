package edu.mpp.bookstore2.core.utils;

public class MyPair<A,B>
{
    private A first;
    private B second;

    public MyPair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public A getFirst() {
        return this.first;
    }
    public void setFirst(A first) {
        this.first = first;
    }

    public B getSecond() {
        return this.second;
    }
    public void setSecond(B second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "(" + this.first + "," + this.second + ')';
    }
}

package model;

public interface Identifiable<TID> {

    TID getID();

    void setID(TID id);
}

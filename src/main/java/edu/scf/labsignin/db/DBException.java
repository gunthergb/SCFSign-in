package edu.scf.labsignin.db;

//Some problem with the database request due to the state of the DB
public class DBException extends RuntimeException {
    public DBException(String msg) {
        super(msg);
    }
}

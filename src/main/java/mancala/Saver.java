package mancala;

import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

public class Saver implements Serializable{
    
    public static void saveObject(Serializable toSave, String filename) throws IOException{
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream objectDest = new ObjectOutputStream(fileOut);
            objectDest.writeObject(toSave);
            objectDest.close();
            fileOut.close();
            System.out.println("Object has been serialized :)");
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
    }

    public static Serializable loadObject(String filename) throws IOException, ClassNotFoundException {
        Serializable obj = null;
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream inputDest = new ObjectInputStream(fileIn);
            obj = (Serializable) inputDest.readObject();
            inputDest.close();
            fileIn.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        
        return obj;
    }

}

package mancala;

import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

public class Saver implements Serializable{
    
    private static final long serialVersionUID = -1738658540040834404L;

    public static void saveObject(final Serializable toSave, final String filename) throws IOException{
        final FileOutputStream fileOut = new FileOutputStream(filename);
        final ObjectOutputStream objectDest = new ObjectOutputStream(fileOut);
        try {
            objectDest.writeObject(toSave);
            System.out.println("Object has been serialized :)");
        } catch (IOException err) {
            System.out.println(err.getMessage());
        } finally {
            objectDest.close();
            fileOut.close();
        }
    }

    public static Serializable loadObject(final String filename) throws IOException, ClassNotFoundException {
        Serializable obj = null;
        final FileInputStream fileIn = new FileInputStream(filename);
        final ObjectInputStream inputDest = new ObjectInputStream(fileIn);
        try {
            obj = (Serializable) inputDest.readObject();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException err) {
            System.out.println(err.getMessage());
        } finally {
            inputDest.close();
            fileIn.close();
        }
        
        return obj;
    }

}

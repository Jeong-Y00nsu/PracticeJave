package user;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserFileRepository implements UserRepository{

    static final private String FILE_DIR = "./user.dat";
    @Override
    public List<User> read() throws Exception {
        List<User> list = new ArrayList<User>();
        ObjectInputStream ois = null;
        try{
            File file = new File(FILE_DIR);
            if(!file.exists()) return list;

            ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));

            while(ois.read()!=-1){
                User user = (User)ois.readObject();
                list.add(user);
            }
        } catch(FileNotFoundException e){
            e.printStackTrace();
            throw new Exception(e);
        } catch(IOException e){
            e.printStackTrace();
            throw new Exception(e);
        } catch(ClassNotFoundException e){
            e.printStackTrace();
            throw new Exception(e);
        }finally {
            try{
                if(ois!=null) ois.close();
            } catch (IOException e){
                e.printStackTrace();
                throw new Exception(e);
            }
        }
        return list;
    }

    @Override
    public void write(List<User> users) throws Exception {
        ObjectOutputStream oos = null;
        try{
            File file = new File(FILE_DIR);
            oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
            for (User user : users){
                oos.writeObject(user);
                oos.flush();
            }
        } catch(NullPointerException e){
            e.printStackTrace();
            throw new Exception(e);
        } catch(IOException e){
            e.printStackTrace();
            throw new Exception(e);
        } finally {
            try{
                if(oos!=null) oos.close();
            } catch (IOException e){
                e.printStackTrace();
                throw new Exception(e);
            }
        }
    }
}

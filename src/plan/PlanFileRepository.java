package plan;

import user.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PlanFileRepository implements PlanRepository{

    @Override
    public void write(List<Plan> plans, User user) throws Exception{

        ObjectOutputStream oos = null;
        try{
            File file = new File(user.getPlanFileUrl());
            oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
            for (Plan plan : plans){
                oos.writeObject(plan);
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

    @Override
    public List<Plan> read(User user) throws Exception{
        List<Plan> list = new ArrayList<Plan>();
        ObjectInputStream ois = null;
        try{
            File file = new File(user.getPlanFileUrl());
            ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
            while(ois.read()!=-1){
                Plan plan = (Plan)ois.readObject();
                list.add(plan);
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
}

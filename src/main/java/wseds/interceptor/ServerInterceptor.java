
package wseds.interceptor;

import java.io.Serializable;
import java.util.Iterator;
import wseds.util.Misc;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

public class ServerInterceptor extends EmptyInterceptor {
    // private int updates;
    // private int creates;
    // private int loads;

    // Called before an object is deleted.
    @Override
    public void onDelete(Object entity,
            Serializable id,
            Object[] state,
            String[] propertyNames,
            Type[] types) {        
        System.out.println(ServerInterceptor.class.getName() + ".afterCompletion() method called: row deleted from " + 
                           entity.getClass() + " table at " + Misc.getDateAndTimeAsString() + ".");
        
        // Perform any auditing here.
    }

   // Called when an object is detected to be 'dirty', i.e. changed, during an update.
   @Override
   public boolean onFlushDirty(Object entity,
                               Serializable id,
                               Object[] currentState,
                               Object[] previousState,
                               String[] propertyNames,
                               Type[] types) {
      System.out.println(ServerInterceptor.class.getName() + ".onFlushDirty() method called: row updated in " + 
                         entity.getClass() + " table.");   
      
      // Perform any auditing here.
      
      return true;       
    }

    // Called just before an object is initialised.
    @Override
    public boolean onLoad(Object entity,
                          Serializable id,
                          Object[] state,
                          String[] propertyNames,
                          Type[] types) { 
        System.out.println(ServerInterceptor.class.getName() + ".onLoad() method called: row inserted into " + 
                           entity.getClass() + " table at " + Misc.getDateAndTimeAsString() + ".");
        
        // Perform any auditing here.
        
        return true;
    }
   
    // Called before an object is saved.
    @Override
    public boolean onSave(Object entity,
                          Serializable id,
                          Object[] state,
                          String[] propertyNames,
                          Type[] types) { 
        System.out.println(ServerInterceptor.class.getName() + ".onFSave() method called: row inserted into " + 
                           entity.getClass() + " table at " + Misc.getDateAndTimeAsString() + ".");
        
        // Perform any auditing here.
        
        /* try {
            System.out.println("Entity: " +  entity.getClass() + ".");
            System.out.println("Serializable: " +  id + ".");
            System.out.println("State:");
            for (Object obj : state) {
                System.out.println(obj.toString());
            }
            System.out.println("Properties:");
            for (String property : propertyNames) {
                System.out.println(property);
            }
            System.out.println("Types:");
            for (Type type : types) {
                System.out.println(type.toString());
            } 
        }
        catch (NullPointerException e) {
            
            // Do nothing: simply catch any exceptions.
            
        } */              
        return true;        
    }
    
    // Called before database commit.
    @Override
    public void preFlush(Iterator iterator) {
        System.out.println(ServerInterceptor.class.getName() + ".preFlush() method called: preFlush database before commit.");
        
        // Perform any auditing of operations here.
    }

    // Called after database commit.
    @Override
    public void postFlush(Iterator iterator) {
        System.out.println(ServerInterceptor.class.getName() + ".postFlush() method called: postFlush database after commit.");
        
        // Perform any auditing of operations here.
    }
}
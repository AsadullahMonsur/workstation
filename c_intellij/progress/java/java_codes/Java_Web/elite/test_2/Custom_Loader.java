import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Custom_Loader extends ClassLoader {
   public void invokeClassMethod
        (String classBinName, String methodName){

      try {
         ClassLoader classLoader = this.getClass().getClassLoader();
         Class<?> loadedMyClass = classLoader.loadClass(classBinName);

         Constructor<?> constructor = loadedMyClass.getConstructor();
         Object myClassObject = constructor.newInstance();

         Method method = loadedMyClass.getMethod(methodName);
         method.invoke(myClassObject);
        } 
       catch (Exception e) {
            e.printStackTrace();
        }
    }
}

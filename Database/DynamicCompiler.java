/**
 * DO NOT UNDER ANY CIRCUMSTANCES TOUCH THIS!
 * <p>
 * Listen here. I don't know how this works. Neither does god.
 * <p>
 * As the number one rule says: If it works dont ouch it. :)
 * <p>
 * Ok maybe you can touch it. Not using it rn. Might later
 * <p>
 * Felt cute so I created this. :>
 */


package Database;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class DynamicCompiler {
    public static void compile(String str) throws Exception {

        // Write the code to a .java file in the current directory
        String fileName = "DynamicClass.java";
        try (FileWriter writer = new FileWriter(fileName);
             PrintWriter out = new PrintWriter(writer)) {
            out.println(str);
        }


        // Compile the .java file
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int result = compiler.run(null, null, null, fileName);
        if (result != 0) {
            throw new RuntimeException("Compilation failed");
        }

        // Load the compiled class dynamically using URLClassLoader
        File currentDirectory = new File(".");
        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { currentDirectory.toURI().toURL() });
        Class<?> dynamicClass = Class.forName("DynamicClass", true, classLoader);

        // Create an instance and invoke the 'run' method
        Object instance = dynamicClass.getDeclaredConstructor().newInstance();
        Method method = dynamicClass.getMethod("run");
        method.invoke(instance);
    }
}

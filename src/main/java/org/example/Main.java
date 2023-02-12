package org.example;

import org.openjdk.nashorn.api.scripting.NashornScriptEngineFactory;

import javax.script.*;
import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws ScriptException, NoSuchMethodException, IOException {

        // create a script engine manager
        NashornScriptEngineFactory factory = new NashornScriptEngineFactory();
        // create a Nashorn script engine
        ScriptEngine engine = factory.getScriptEngine();
        // Create map that it will represent our object
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("Danny McCann", 200);
        map.put("Hilda Ogden", 300);
        map.put("Don Baldwin", 330);

        /**
         * Load the javascript function from the JS file.
         * The content of the function:
         *
         * function printProperties(obj) {
         *     print(obj)
         *     var total = 0;
         *     for (var prop in obj) {
         *         print("key: " + prop + " value: " + obj[prop]);
         *         total = total + obj[prop];
         *     }
         *     return total;
         * }
         *
         */
        try (InputStream is = Main.class.getClassLoader().getResourceAsStream("javascript_function.js");
             Reader reader = new InputStreamReader(is)) {

            engine.eval(reader);
            Invocable invocable = (Invocable) engine;

            /**
             * Call the function passing the function name and the map as argument.
             * Now the function will consider the Java Map as Javascript Object.
             * It will print the key and values then it will sum up the values
             */
            Object funcResult = invocable.invokeFunction("printProperties", map);

            System.out.println("Class: " + funcResult.getClass() + " value: " + funcResult);
        }

    }
}
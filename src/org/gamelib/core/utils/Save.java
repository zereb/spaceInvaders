package org.gamelib.core.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.util.List;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;



public class Save {

    public static final String EXT =".save";

    private String homeDirectory;
    private Path path;
    private List<String> arguments;
    private HashMap<String, String> hm = new HashMap<>();

   public Save(String save) {
        save = save + EXT;
        path = Paths.get(save);
        try {
            init(save);
        } catch (IOException ex) {
            System.err.println(ex.toString());
            createSave();
        }
    }

    private void init(String data) throws IOException {
        arguments = Files.readAllLines(Paths.get(data), StandardCharsets.UTF_8);
        for (String argument : arguments) {
            String name = argument.substring(0, argument.indexOf("="));
            String value = argument.substring(argument.indexOf("=") + 1, argument.indexOf(";"));
            hm.put(name, value);
        }

    }

    private boolean createSave() {
        try {
            Files.createFile(path);
        } catch (IOException ex) {
            System.err.println(ex.toString());
            return false;
        }
        return true;
    }

    private boolean createSave(Path new_config) {
        try {
            Files.createFile(new_config);
        } catch (IOException ex) {
            System.err.println(ex.toString());
            return false;
        }
        return true;
    }

    public int getSaveValueInt(String name){
        try {
            return  Integer.parseInt(hm.get(name));
        } catch (NumberFormatException e) {
            return 0;
        }

    }

    public String getSaveValue(String name){
        if(!hm.containsKey(name)){
            setSaveValue(name, "");
        }
        return  hm.get(name);
    }


    public void setSaveValue(String name, String value){
        hm.put(name, value);
        if(saveData()) System.out.println("value saved: "+ path.toFile().getAbsolutePath());
    }

    public boolean isConfigEmpty(){
        return arguments.isEmpty();
    }

    private boolean saveData() {
        try {
            Files.deleteIfExists(path);
            createSave();
            PrintWriter pw = new PrintWriter(new FileOutputStream(path.toFile()));
            for (Map.Entry<String, String> entry : hm.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                pw.println(key + "=" + value + ";");
            }
            pw.close();
        } catch (IOException ex) {
            System.err.println(ex.toString());
            return false;
        }

        return true;
    }

    public boolean saveData(String path) {
        Path new_config=Paths.get(path);
        try {
            Files.deleteIfExists(new_config);
            createSave();
            PrintWriter pw = new PrintWriter(new FileOutputStream(new_config.toFile()));
            for (Map.Entry<String, String> entry : hm.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
               // System.err.println("Writing: "+key + "=" + value + ";");
                pw.println(key + "=" + value + ";");
            }
            pw.close();
        } catch (IOException ex) {
            System.err.println(ex.toString());
            return false;
        }

        return true;
    }


    public Path getConfigPath() {
        return path;
    }

}

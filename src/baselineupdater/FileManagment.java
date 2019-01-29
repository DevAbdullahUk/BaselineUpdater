/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baselineupdater;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Abdullah.Janjua
 */
public class FileManagment {
    private String path;
    private List<String> list = new ArrayList<>();

    public FileManagment(String path) {
        this.path = correctTheFilePath(path);
        getListOfFiles();
    }

    private String correctTheFilePath(String path) {
        return path += (System.getProperty("os.name").contains("Windows")) ? "\\" : "/";
    }

    private void getListOfFiles() {
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                list.add(listOfFile.getName());
            }
        }
    }

    public void deleteFilesFrom(String path) throws Exception {
        list.forEach(fileName -> {
            
            new File(correctTheFilePath(path) + fileName).delete();
        });
    }

    public void copyFilesTo(String path) throws Exception {
        list.forEach(fileName -> {
            try {
                Files.copy(
                        new File(this.path + fileName).toPath(),
                        new File(correctTheFilePath(path) + fileName).toPath());
            } catch (IOException ex) {
                System.out.println(ex);
            }
        });
    }


}

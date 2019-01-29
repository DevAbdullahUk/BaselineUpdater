/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baselineupdater;

/**
 *
 * @author Abdullah.Janjua
 */
public class FilePath {

    private static String filePath;

    public FilePath(){ }
    public FilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

}

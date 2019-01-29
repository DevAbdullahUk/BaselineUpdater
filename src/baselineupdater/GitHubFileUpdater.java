/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baselineupdater;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Abdullah.Janjua
 */
public class GitHubFileUpdater {

    private boolean IsTheError = false;
    private String path;

    
    public GitHubFileUpdater (String path){
        this.path = path;
    }
    
    private String runCommand(String command) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash", "-c", command);
        
        try {
            Process process = processBuilder.start();
            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }
            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.exit(0);
                IsTheError = false;
                return output.toString();
            } else {
                IsTheError = true;
                return "The process is still running!";
            }
        } catch (IOException e) {
            IsTheError = true;
            return e.toString();
        } catch (InterruptedException e) {
            IsTheError = true;
            return e.toString();
        }
    }

    private String correctTheFilePath(String path) {
        return path += (System.getProperty("os.name").contains("Windows")) ? "\\" : "/";
    }

    private String getTheDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

 
    public boolean getStatus(){
        return IsTheError;
    }
    
    public String commit() {
        String command = "cd " + correctTheFilePath(path)
                + "; git commit -m \"  " + getTheDate() + " \" ";
        return runCommand(command);
    }

    public String add() {
        String command = "cd " + correctTheFilePath(path)
                + "; add " + correctTheFilePath(path);
        return runCommand(command);
    }

    public String push() {
        String command = "cd " + correctTheFilePath(path)
                + "; push ";
        return runCommand(command);
    }

}

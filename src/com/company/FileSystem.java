package com.company;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class FileSystem {
    public String filename;


    /**
     * constructor
     */
    public FileSystem() {
        filename = "";
    }


    /**
     * method to open a window to show the saved slots, so the user can choose the game to open
     * @return  the name of the file chosen by the user
     */
    public String openFile() {

        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        String curDir = System.getProperty("user.dir"); //"user.dir" is the current working directory, not the home directory
        //System.out.println(curDir);
        jfc.setCurrentDirectory(new File(curDir));

        jfc.setDialogTitle("Select the game");
        jfc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT files", "txt");
        jfc.addChoosableFileFilter(filter);

        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            filename = jfc.getSelectedFile().getName();
        }
        else if (returnValue == 1) {
            filename = "empty";
        }

        return filename;

    }



}

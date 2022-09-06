package command;

import lombok.SneakyThrows;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Rm extends Command {
    public Rm(Context context) {
        super(context);
    }

    @SneakyThrows
    @Override
    public String execute(List<String> args) {
        if (args.isEmpty()) return "Error. Enter name of file/directory to remove.";

        File directory = context.getCurrentDirectory();
        return deleteDirectory(new File(directory, args.get(0)));
    }

    public static String deleteDirectory(File directory) {

        File[] files = directory.listFiles();

        if(directory.isDirectory()) {
            if(files != null) {
                for(File file : files) {
                    deleteDirectory(file);
                }
            }
        }

        if(directory.delete()) {
            return directory.getName() + " is deleted";
        }
        else {
            return "Directory not deleted";
        }
    }
}

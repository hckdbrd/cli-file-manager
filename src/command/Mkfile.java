package command;

import lombok.SneakyThrows;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Mkfile extends Command {
    public Mkfile(Context context) {
        super(context);
    }

    @SneakyThrows
    @Override
    public String execute(List<String> args) {
        File directory = context.getCurrentDirectory();
        File[] allFiles = directory.listFiles();
        if (allFiles == null) {
            return "'mkfile' command uses argument with name of file with its extension.";
        }
        if (Arrays.stream(allFiles).anyMatch(x -> x.getName().equals(args.get(0)))){
            return "Such file exists. Please, choose another name.";
        }
        if (args.get(0).contains(".")) {
            Files.createFile(Path.of(context.getCurrentDirectory() + String.format("/%s",args.get(0))));
            return String.format("File '%s' is created",args.get(0));
        } else {
            return "Can't create file with no extension!\nWrite after file name '.' and extension name.";
        }
    }
}

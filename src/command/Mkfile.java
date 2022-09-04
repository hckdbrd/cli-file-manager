package command;

import lombok.SneakyThrows;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Mkfile extends Command {
    public Mkfile(Context context) {
        super(context);
    }

    @SneakyThrows
    @Override
    public String execute(List<String> args) {
        if (args.get(0).contains(".")) {
            Files.createFile(Path.of(context.getCurrentDirectory() + String.format("/%s",args.get(0))));
            return String.format("File '%s' is created",args.get(0));
        } else {
            return "Can't create file with no extension!\n Write after file name '.' and extension name.";
        }
    }
}

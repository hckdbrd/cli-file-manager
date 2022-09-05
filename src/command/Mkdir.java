package command;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Mkdir extends Command {
    public Mkdir(Context context) {
        super(context);
    }

    @SneakyThrows
    @Override
    public String execute(List<String> args) {
        if (args.get(0).matches("\\w*[\\/:*?\"<>|]+\\w*")) {
            return "Can't create folder with such name!\nWrite folder name without special symbols: '\\/:*?\"<>|'.";
        } else {
            Files.createDirectory(Path.of(context.getCurrentDirectory() + String.format("/%s",args.get(0))));
            return String.format("Folder '%s' is created.",args.get(0));
        }
    }
}

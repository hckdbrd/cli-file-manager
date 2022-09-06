package command;

import lombok.SneakyThrows;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Edit extends Command {
    public Edit(Context context) {
        super(context);
    }

    @SneakyThrows
    @Override
    public String execute(List<String> args) {
        File directory = context.getCurrentDirectory();
        File[] allFiles = directory.listFiles();
        if (allFiles != null) {
            if (Arrays.stream(allFiles).anyMatch(x -> x.getName().equals(args.get(0)))) {
                String content = Files.readString(Path.of(directory + "/" + args.get(0)));

            }
        }

        return String.format("File '%s' edited successfully."); // TODO
    }
}

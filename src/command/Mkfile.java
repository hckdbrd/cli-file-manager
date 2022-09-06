package command;

import lombok.SneakyThrows;

import java.io.File;
import java.util.List;

public class Mkfile extends Command {

    public Mkfile(Context context) {
        super(context);
    }

    @SneakyThrows
    @Override
    public String execute(List<String> args) {
        if (args.isEmpty()) {
            return "Error. Enter file name after 'mkfile' command.";
        }
        File path = context.getCurrentDirectory();
        File file = new File(path.getAbsolutePath(), args.get(0));
        if (file.createNewFile()) {
            return "File " + file.getName() + " created.";
        } else {
            return "Error. File "+ file.getName() + " already exists.";
        }
    }
}

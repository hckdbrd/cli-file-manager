package command;

import java.io.File;
import java.util.List;

public class Mkdir extends Command {

    public Mkdir(Context context) {
        super(context);
    }

    @Override
    public String execute(List<String> args) {
        if (args.isEmpty()) {
            return "Error. Enter directory name after 'mkdir' command.";
        }
        File path = context.getCurrentDirectory();
        if (args.get(0).matches("\\w*[\\/:*?\"<>|]+\\w*")) {
            return "Error. Directory name includes invalid symbols: \\/:*?\"<>|+ .";
        }
        File theDir = new File(path.getAbsolutePath(), args.get(0));
        if (theDir.mkdir()) {
            return theDir.getName() + " is created.";
        } else {
            return theDir.getName() + " already exist in " + path.getAbsolutePath() + " directory.";
        }
    }
}

package command;

import java.io.File;
import java.util.List;

public class Tree extends Command{

    public Tree(Context context) {
        super(context);
    }

    @Override
    public String execute(List<String> args) {
        File file = context.getCurrentDirectory();
        File[] allFiles = file.listFiles();
        String log = "";

        if (allFiles != null) {
            //TODO
        } else {
            //TODO
        }

        return log;
    }
    //------------------------------------------------
    private static String createTree(File[] allFiles) {
        StringBuilder tree = new StringBuilder();
        tree.append(".\n");
        for (File each : allFiles) {
            if (each.isFile()) {
                printName(tree, each);
            } else {
                printName(tree, each);
//                        tree.append("│\t ").append(each.getName()).append(execute());
            }
        }
        return tree.toString();
    }


    private static void printName(StringBuilder tree, File each) {
        tree.append("├── ").append(each.getName()).append("\n");
    }
}

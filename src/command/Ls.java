package command;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Ls extends Command {

    public Ls(Context context) {
        super(context);
    }

    @SuppressWarnings("all")
    @Override
    public String execute(List<String> args) {
        File file = context.getCurrentDirectory();
        File[] allFiles = file.listFiles();
        StringBuilder log = new StringBuilder("\n");

        if (allFiles != null) {
            Arrays.sort(allFiles);
            if (args.isEmpty()) {
                log.append(shiftList(allFiles, null));
            } else {
                int shift = Integer.parseInt(args.get(0));
                log.append(shiftList(allFiles, shift));
            }
        }
        return log.toString();
    }

    private String shiftList(File[] allFiles, Integer columns) {
        if (columns == null) columns = 3;
        int rows = (int) Math.ceil( (float) allFiles.length / columns);
        int biggestWord = Arrays.stream(allFiles).mapToInt(x -> x.getName().length()).max().getAsInt();

        StringBuilder tree = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = i; j < allFiles.length; j += rows) {
                if (allFiles[i].getName().length() == biggestWord) {
                    tree.append(allFiles[j].getName()).append("\t");
                } else {
                    tree
                            .append(allFiles[j].getName())
                            .append("\s"
                                    .repeat(biggestWord - allFiles[j].getName().length()))
                            .append("\t");
                }
            }
            tree.append("\n");
        }
        return tree.toString();
    }
}

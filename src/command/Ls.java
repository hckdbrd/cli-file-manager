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
        File directory = context.getCurrentDirectory();
        File[] allFiles = directory.listFiles();
        StringBuilder log = new StringBuilder("\n");

        if (allFiles != null) {
            Arrays.sort(allFiles);
            if (args.isEmpty()) {
                log.append(shiftList(allFiles, null));
            } else if (args.contains("rw")) {
                log.append(listModes(allFiles));
            } else {
                int shift = Integer.parseInt(args.get(0));
                log.append(shiftList(allFiles, shift));
            }
        }
        return log.toString();
    }

    private String shiftList(File[] allFiles, Integer columns) {
        if (columns == null) columns = 3;
        int rows = (int) Math.ceil((float) allFiles.length / columns);
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

    private String listModes(File[] allFiles) {
        int biggestWord = Arrays.stream(allFiles).mapToInt(x -> x.getName().length()).max().getAsInt();

        StringBuilder tree = new StringBuilder();
        for (File file : allFiles) {
            if (file.getName().length() == biggestWord) {
                tree.append(file.getName()).append("\t");
            } else {
                tree
                        .append(file.getName())
                        .append("\s"
                                .repeat(biggestWord - file.getName().length()))
                        .append("\t");
                if (file.canRead()) {
                    tree.append("r");
                } else {
                    tree.append("-");
                }
                if (file.canWrite()) {
                    tree.append("w");
                } else {
                    tree.append("-");
                }
            }
            tree.append("\n");
        }
        return tree.toString();
    }


}

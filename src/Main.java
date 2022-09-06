import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import command.Command;
import command.Context;

import java.io.File;
import java.util.*;

public class Main {
//    File Manager
//1. View file (TXT) in console
//3. Add text to specific location **
//5.  LS size / r / w / extension (srwe | rswe | etc...)
//10. Tree 2/3/4/5 .. n  DEPTH -- view file system as a tree ***
// 11. Clear
// 12. Chmod

    public static void main(String[] args) throws Exception {
        Context context = new Context(null, new File(System.getenv().get("PWD")));
        Map<String, Command> commands = getCommands(context);
        context.setCommandMap(commands);

        performGreetings();
        performCommands(context, commands);
    }

    private static void performGreetings() {
        System.out.print("""

                Welcome to COMMANDER!
                [ Enter 'help' to see all commands. ]
                [ To close - use 'quit' or 'exit'.  ]
                """);
    }

    private static void performCommands(Context context, Map<String, Command> commands) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print(getCurrentDirectory(context));
            String line = scanner.nextLine();

            if (StringUtils.isBlank(line)) {
                continue;
            }

            List<String> allArguments = Arrays.asList(line.split(" "));

            String commandName = allArguments.get(0);
            if (commandName.equals("quit") || commandName.equals("exit")) {
                System.out.println("[ Shutdown..........................]");
                break;
            }

            Command command = commands.getOrDefault(commandName, new Command(context) {

                @Override
                public String execute(List<String> args) {
                    return "Command " + line + " is unknown";
                }
            });
            System.out.println(
                    command.execute(allArguments.subList(1, allArguments.size()))
            );
        }
    }

    private static String getCurrentDirectory(Context context) {
        String regex = ".*(/[-_\\wWdD]+/[-_\\wWdD]+/[-_\\wWdD]+$)";
        String ANSI_YELLOW = "\u001B[36m";
        String ANSI_RESET = "\u001B[0m";
        return ANSI_YELLOW
                + context.getCurrentDirectory().toString().replaceFirst(regex, "...$1") + " : "
                + ANSI_RESET;
    }

    @SneakyThrows
    private static Map<String, Command> getCommands(Context context) {
        Reflections reflection = new Reflections("command", Scanners.SubTypes);
        Set<Class<? extends Command>> allClasses = reflection.getSubTypesOf(Command.class);

        Map<String, Command> commandNameToFunction = new LinkedHashMap<>();
        for (Class<? extends Command> each : allClasses) {
            Command instance = each
                    .getDeclaredConstructor(Context.class)
                    .newInstance(context);
            commandNameToFunction
                    .put(each.getSimpleName().toLowerCase(), instance);
        }
        return commandNameToFunction;
    }
}
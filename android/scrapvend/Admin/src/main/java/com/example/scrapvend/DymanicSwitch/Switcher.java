package com.example.scrapvend.DymanicSwitch;

import java.util.HashMap;
import java.util.Map;

public class Switcher  {

    private Map<String, Command> caseCommands;

    private Command defaultCommand;

    private Command getCaseCommandByCaseId(String caseId) {
        if (caseCommands.containsKey(caseId)) {
            return caseCommands.get(caseId);
        } else {
            return defaultCommand;
        }
    }

    public Switcher() {
        caseCommands = new HashMap<String, Command>();

        setDefaultCaseCommand(new DoNothingCommand());
    }

    public void addCaseCommand(String caseId, Command caseCommand) {
        caseCommands.put(caseId, caseCommand);
    }

    public void setDefaultCaseCommand(Command defaultCommand) {
        if (defaultCommand != null) {
            this.defaultCommand = defaultCommand;
        }
    }

    public void on(String  caseId) {
        Command command = getCaseCommandByCaseId(caseId);

        command.execute();
    }

}

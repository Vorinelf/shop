package com.shop.sport.command;

import java.util.Arrays;

public enum CommandType {
    CREATE(CreateCommand.getInstance()),
    DELETE(DeleteCommand.getInstance()),
    TO_EDIT(ToEditCommand.getInstance()),
    EDIT(EditCommand.getInstance()),
    ALL(AllCommand.getInstance());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public static Command findCommand(String name) {
        return Arrays.stream(CommandType.values())
                .filter(type -> type.name().toLowerCase().equals(name))
                .map(type -> type.command)
                .findAny()
                .orElse(getDefault());
    }

    public static Command getDefault() {
        return ALL.command;
    }


}

package org.spacebattle.domain.actions.impl;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spacebattle.domain.actions.ICommand;

import java.lang.reflect.Type;

@EqualsAndHashCode
@AllArgsConstructor
public class LogException implements ICommand {
    private final Logger log = LoggerFactory.getLogger(LogException.class);

    public static final String PATTERN = "Command {} threw: {}";
    private Type type;
    private Exception e;

    @Override
    public void execute() {
        log.warn(PATTERN, type, e.getMessage());
    }
}

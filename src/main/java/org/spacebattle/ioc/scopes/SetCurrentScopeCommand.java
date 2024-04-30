package org.spacebattle.ioc.scopes;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.spacebattle.domain.actions.ICommand;

@NoArgsConstructor
@AllArgsConstructor
public class SetCurrentScopeCommand implements ICommand {
    private Object scope;

    @Override
    public void execute() {
        InitCommand.getCURRENT_SCOPES().set(scope);
    }
}

package org.spacebattle;

import lombok.extern.log4j.Log4j2;
import org.spacebattle.domain.actions.ICommand;
import org.spacebattle.exceptions.handlers.ExceptionHandler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.util.concurrent.TimeUnit.MICROSECONDS;

@Log4j2
public class Main {

    public static final BlockingQueue<ICommand> queue = new LinkedBlockingQueue<>(100);
    public static final AtomicBoolean RUNNING = new AtomicBoolean(true);

    public static void main(String[] args) throws Exception {
        while(RUNNING.get() || !queue.isEmpty()) {
            ICommand cmd = queue.poll(100, MICROSECONDS);
            if (cmd == null) continue;
            try {
                cmd.execute();
            } catch (Exception e) {
                ExceptionHandler.handle(cmd, e).execute();
            }
        }
    }
}

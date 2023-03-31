package unit.mock;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.exceptions.RateLimitedException;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import net.dv8tion.jda.api.utils.Result;
import net.dv8tion.jda.api.utils.concurrent.DelayedCompletableFuture;

import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.*;

public class MockCommandListUpdateAction implements CommandListUpdateAction {
    private JDA jda;

    public MockCommandListUpdateAction(JDA jda) {
        this.jda = jda;
    }

    @Override
    public CommandListUpdateAction timeout(long l, TimeUnit timeUnit) {
        return this;
    }

    @Override
    public CommandListUpdateAction deadline(long l) {
        return this;
    }

    @Override
    public void queue() {
        return;
    }

    @Override
    public void queue(Consumer<? super List<Command>> success) {
        return;
    }

    @Override
    public void queue(Consumer<? super List<Command>> consumer, Consumer<? super Throwable> consumer1) {
        return;
    }

    @Override
    public List<Command> complete() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public List<Command> complete(boolean b) throws RateLimitedException {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public CompletableFuture<List<Command>> submit() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public CompletableFuture<List<Command>> submit(boolean b) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public RestAction<Result<List<Command>>> mapToResult() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public <O> RestAction<O> map(Function<? super List<Command>, ? extends O> map) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public RestAction<List<Command>> onSuccess(Consumer<? super List<Command>> consumer) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public RestAction<List<Command>> onErrorMap(Function<? super Throwable, ? extends List<Command>> map) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public RestAction<List<Command>> onErrorMap(Predicate<? super Throwable> condition, Function<? super Throwable, ? extends List<Command>> map) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public RestAction<List<Command>> onErrorFlatMap(Function<? super Throwable, ? extends RestAction<? extends List<Command>>> map) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public RestAction<List<Command>> onErrorFlatMap(Predicate<? super Throwable> condition, Function<? super Throwable, ? extends RestAction<? extends List<Command>>> map) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public <O> RestAction<O> flatMap(Function<? super List<Command>, ? extends RestAction<O>> flatMap) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public <O> RestAction<O> flatMap(Predicate<? super List<Command>> condition, Function<? super List<Command>, ? extends RestAction<O>> flatMap) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public <U, O> RestAction<O> and(RestAction<U> other, BiFunction<? super List<Command>, ? super U, ? extends O> accumulator) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public <U> RestAction<Void> and(RestAction<U> other) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public RestAction<List<List<Command>>> zip(RestAction<? extends List<Command>> first, RestAction<? extends List<Command>>... other) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public RestAction<List<Command>> delay(Duration duration) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public RestAction<List<Command>> delay(Duration duration, ScheduledExecutorService scheduler) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public RestAction<List<Command>> delay(long delay, TimeUnit unit) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public RestAction<List<Command>> delay(long delay, TimeUnit unit, ScheduledExecutorService scheduler) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public DelayedCompletableFuture<List<Command>> submitAfter(long delay, TimeUnit unit) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public DelayedCompletableFuture<List<Command>> submitAfter(long delay, TimeUnit unit, ScheduledExecutorService executor) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public List<Command> completeAfter(long delay, TimeUnit unit) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public ScheduledFuture<?> queueAfter(long delay, TimeUnit unit) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public ScheduledFuture<?> queueAfter(long delay, TimeUnit unit, Consumer<? super List<Command>> success) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public ScheduledFuture<?> queueAfter(long delay, TimeUnit unit, Consumer<? super List<Command>> success, Consumer<? super Throwable> failure) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public ScheduledFuture<?> queueAfter(long delay, TimeUnit unit, ScheduledExecutorService executor) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public ScheduledFuture<?> queueAfter(long delay, TimeUnit unit, Consumer<? super List<Command>> success, ScheduledExecutorService executor) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public ScheduledFuture<?> queueAfter(long delay, TimeUnit unit, Consumer<? super List<Command>> success, Consumer<? super Throwable> failure, ScheduledExecutorService executor) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public JDA getJDA() {
        return jda;
    }

    @Override
    public CommandListUpdateAction setCheck(BooleanSupplier booleanSupplier) {
        return null;
    }

    @Override
    public BooleanSupplier getCheck() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public CommandListUpdateAction addCheck(BooleanSupplier booleanSupplier) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public CommandListUpdateAction addCommands(Collection<? extends CommandData> collection) {
        return this;
    }

    @Override
    public CommandListUpdateAction addCommands(CommandData... commands) {
        return this;
    }


}

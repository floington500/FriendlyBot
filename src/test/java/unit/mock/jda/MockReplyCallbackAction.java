package unit.mock.jda;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.IMentionable;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.exceptions.RateLimitedException;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.components.LayoutComponent;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyCallbackAction;
import net.dv8tion.jda.api.utils.FileUpload;

import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

public class MockReplyCallbackAction implements ReplyCallbackAction {
    public String lastMessage;

    @Override
    public ReplyCallbackAction closeResources() {
        return this;
    }

    @Override
    public ReplyCallbackAction setEphemeral(boolean b) {
        return this;
    }

    @Override
    public JDA getJDA() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public ReplyCallbackAction setCheck(BooleanSupplier booleanSupplier) {
        return this;
    }

    @Override
    public void queue(Consumer<? super InteractionHook> consumer, Consumer<? super Throwable> consumer1) {

    }

    @Override
    public InteractionHook complete(boolean b) throws RateLimitedException {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public CompletableFuture<InteractionHook> submit(boolean b) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public ReplyCallbackAction addContent(String s) {
        this.lastMessage = s;

        return this;
    }

    @Override
    public ReplyCallbackAction addEmbeds(Collection<? extends MessageEmbed> collection) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public ReplyCallbackAction addComponents(Collection<? extends LayoutComponent> collection) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public ReplyCallbackAction addFiles(Collection<? extends FileUpload> collection) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public String getContent() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public List<MessageEmbed> getEmbeds() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public List<LayoutComponent> getComponents() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public List<FileUpload> getAttachments() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public boolean isSuppressEmbeds() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public Set<String> getMentionedUsers() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public Set<String> getMentionedRoles() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public EnumSet<Message.MentionType> getAllowedMentions() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public boolean isMentionRepliedUser() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public ReplyCallbackAction setTTS(boolean b) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public ReplyCallbackAction setSuppressedNotifications(boolean b) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public ReplyCallbackAction setContent(String s) {
        lastMessage = s;
        return this;
    }

    @Override
    public ReplyCallbackAction setEmbeds(Collection<? extends MessageEmbed> collection) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public ReplyCallbackAction setComponents(Collection<? extends LayoutComponent> collection) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public ReplyCallbackAction setSuppressEmbeds(boolean b) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public ReplyCallbackAction setFiles(Collection<? extends FileUpload> collection) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public ReplyCallbackAction mentionRepliedUser(boolean b) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public ReplyCallbackAction setAllowedMentions(Collection<Message.MentionType> collection) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public ReplyCallbackAction mention(Collection<? extends IMentionable> collection) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public ReplyCallbackAction mentionUsers(Collection<String> collection) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public ReplyCallbackAction mentionRoles(Collection<String> collection) {
        throw new RuntimeException("Method does not exist!");
    }
}

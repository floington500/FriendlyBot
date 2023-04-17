package unit.mock.jda;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.concrete.*;
import net.dv8tion.jda.api.entities.emoji.RichCustomEmoji;
import net.dv8tion.jda.api.entities.sticker.StickerPack;
import net.dv8tion.jda.api.entities.sticker.StickerSnowflake;
import net.dv8tion.jda.api.entities.sticker.StickerUnion;
import net.dv8tion.jda.api.hooks.IEventManager;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.managers.AudioManager;
import net.dv8tion.jda.api.managers.DirectAudioController;
import net.dv8tion.jda.api.managers.Presence;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.CacheRestAction;
import net.dv8tion.jda.api.requests.restaction.CommandEditAction;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import net.dv8tion.jda.api.requests.restaction.GuildAction;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.dv8tion.jda.api.utils.cache.CacheView;
import net.dv8tion.jda.api.utils.cache.SnowflakeCacheView;
import okhttp3.OkHttpClient;
import unit.mock.jda.MockCommandListUpdateAction;

import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MockJDA implements JDA {
    @Override
    public Status getStatus() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public EnumSet<GatewayIntent> getGatewayIntents() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public EnumSet<CacheFlag> getCacheFlags() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public boolean unloadUser(long l) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public long getGatewayPing() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public JDA awaitStatus(Status status, Status... statuses) throws InterruptedException {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public boolean awaitShutdown(long l, TimeUnit timeUnit) throws InterruptedException {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public int cancelRequests() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public ScheduledExecutorService getRateLimitPool() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public ScheduledExecutorService getGatewayPool() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public ExecutorService getCallbackPool() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public OkHttpClient getHttpClient() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public DirectAudioController getDirectAudioController() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public void setEventManager(IEventManager iEventManager) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public void addEventListener(Object... objects) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public void removeEventListener(Object... objects) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public List<Object> getRegisteredListeners() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public RestAction<List<Command>> retrieveCommands(boolean b) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public RestAction<Command> retrieveCommandById(String s) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public RestAction<Command> upsertCommand(CommandData commandData) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public CommandListUpdateAction updateCommands() {
        return new MockCommandListUpdateAction(this);
    }

    @Override
    public CommandEditAction editCommandById(String s) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public RestAction<Void> deleteCommandById(String s) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public RestAction<List<RoleConnectionMetadata>> retrieveRoleConnectionMetadata() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public RestAction<List<RoleConnectionMetadata>> updateRoleConnectionMetadata(Collection<? extends RoleConnectionMetadata> collection) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public GuildAction createGuild(String s) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public RestAction<Void> createGuildFromTemplate(String s, String s1, Icon icon) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public CacheView<AudioManager> getAudioManagerCache() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public SnowflakeCacheView<User> getUserCache() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public List<Guild> getMutualGuilds(User... users) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public List<Guild> getMutualGuilds(Collection<User> collection) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public CacheRestAction<User> retrieveUserById(long l) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public SnowflakeCacheView<Guild> getGuildCache() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public Set<String> getUnavailableGuilds() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public boolean isUnavailable(long l) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public SnowflakeCacheView<Role> getRoleCache() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public SnowflakeCacheView<ScheduledEvent> getScheduledEventCache() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public SnowflakeCacheView<PrivateChannel> getPrivateChannelCache() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public CacheRestAction<PrivateChannel> openPrivateChannelById(long l) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public SnowflakeCacheView<RichCustomEmoji> getEmojiCache() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public RestAction<StickerUnion> retrieveSticker(StickerSnowflake stickerSnowflake) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public RestAction<List<StickerPack>> retrieveNitroStickerPacks() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public IEventManager getEventManager() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public SelfUser getSelfUser() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public Presence getPresence() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public ShardInfo getShardInfo() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public String getToken() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public long getResponseTotal() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public int getMaxReconnectDelay() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public void setAutoReconnect(boolean b) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public void setRequestTimeoutRetry(boolean b) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public boolean isAutoReconnect() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public boolean isBulkDeleteSplittingEnabled() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public void shutdown() {
        throw new RuntimeException("Method does not exist!");

    }

    @Override
    public void shutdownNow() {
        throw new RuntimeException("Method does not exist!");

    }

    @Override
    public RestAction<ApplicationInfo> retrieveApplicationInfo() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public JDA setRequiredScopes(Collection<String> collection) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public String getInviteUrl(Permission... permissions) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public String getInviteUrl(Collection<Permission> collection) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public ShardManager getShardManager() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public RestAction<Webhook> retrieveWebhookById(String s) {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public SnowflakeCacheView<StageChannel> getStageChannelCache() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public SnowflakeCacheView<ThreadChannel> getThreadChannelCache() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public SnowflakeCacheView<Category> getCategoryCache() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public SnowflakeCacheView<TextChannel> getTextChannelCache() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public SnowflakeCacheView<NewsChannel> getNewsChannelCache() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public SnowflakeCacheView<VoiceChannel> getVoiceChannelCache() {
        throw new RuntimeException("Method does not exist!");
    }

    @Override
    public SnowflakeCacheView<ForumChannel> getForumChannelCache() {
        throw new RuntimeException("Method does not exist!");
    }
}

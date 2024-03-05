package tt.jda;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import javax.security.auth.login.LoginException;

import javax.security.auth.login.LoginException;

public class BotTestingMessage extends ListenerAdapter {

    private final String BOT_TOKEN = "MTIxNDA3NjAxNzkwMDI1NzI4MA.G-okEo.DCNFHLEoQ86-lYLc2Ot9w0XIFSZ08RDGdfo-G8";
    private final String CHANNEL_NAME = "da-bot-home";

    public BotTestingMessage() throws LoginException {
        JDABuilder builder = JDABuilder.createDefault(BOT_TOKEN);
        // Enable the MESSAGE_CONTENT intent
        builder.enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES, GatewayIntent.MESSAGE_CONTENT);
        // Set member cache policy to CACHE_NONE to avoid unnecessary caching
        builder.setMemberCachePolicy(MemberCachePolicy.NONE);
        builder.addEventListeners(this); // Register the bot as an event listener
        builder.build();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        MessageChannel channel = event.getChannel();

        if (message.equalsIgnoreCase("TesterTester")) {
            // Check if the message was sent in the channel named "da-bot-home"
            if (event.isFromGuild() && event.getGuild().getTextChannelsByName(CHANNEL_NAME, true).size() > 0) {
                MessageChannel homeChannel = event.getGuild().getTextChannelsByName(CHANNEL_NAME, true).get(0);
                homeChannel.sendMessage("I received the message 'TesterTester'!").queue();
            } else {
                channel.sendMessage("I received the message 'TesterTester', but could not find the home channel.").queue();
            }
        }
    }

    public static void main(String[] args) {
        try {
            BotTestingMessage bot = new BotTestingMessage();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }
}
package tt.jda;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

public class MainBot extends ListenerAdapter{


    private static final String BOT_TOKEN = "MTIxNDA3NjAxNzkwMDI1NzI4MA.G-okEo.DCNFHLEoQ86-lYLc2Ot9w0XIFSZ08RDGdfo-G8";
    private static final String channel = "da-bot-home";

    private static JDA jda;

    public MainBot() throws LoginException {
        JDABuilder builder = JDABuilder.createDefault(BOT_TOKEN);

        builder.enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT);

        builder.setMemberCachePolicy(MemberCachePolicy.NONE);

        jda = builder.build();
    }

    public JDA getJDA() {
        return jda;
    }

    public String getToken(){
        return BOT_TOKEN;
    }


    public void ifMessageSendMessage(String ifMessage, String sendMessage){
        new CustomListener(jda, channel, sendMessage, ifMessage);
    }
    
    //       TESTTTTTTT
    // public void main(String[] args) {
    //     try {
    //         MainBot bot = new MainBot();
    //         bot.ifMessageSendMessage("Test", "<:grey_building:1215116257783848970>");
    //     } catch (LoginException e) {
    //         e.printStackTrace();
    //     }
    // }

    public static void main(String[] args) {
        try {
            new MainBot();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }
}

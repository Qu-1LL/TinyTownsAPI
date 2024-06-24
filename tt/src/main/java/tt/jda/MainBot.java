package tt.jda;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import tt.Game;
import tt.buildings.Cottage;
import tt.buildings.green.Tavern;
import tt.buildings.grey.Well;
import tt.buildings.orange.Chapel;
import tt.buildings.red.Farm;
import tt.buildings.yellow.Theatre;
import tt.buildings.navy.Factory;

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

    public void makeMyTowns() {
        new TownMaker(jda, channel);
    }

    
    
    // public static void main(String[] args) {
    //     try {
    //         MainBot bot = new MainBot();
    //         bot.ifMessageSendMessage("Test", "<:grey_building:1215116257783848970>");
    //     } catch (LoginException e) {
    //         e.printStackTrace();
    //     }
    // }

    // public static void main(String[] args) {
    //     try {
    //         new MainBot();
    //     } catch (LoginException e) {
    //         e.printStackTrace();
    //     }
    // }

    // public static void main (String[] args) {
    //     try {
    //         Main bot = new Main();
    //         bot.makeMyTowns();
    //     } catch (LoginException e) {
    //         e.printStackTrace();
    //     }
    // }

    public static void main(String[] args) {
        try {
            MainBot bot = new MainBot();
            // Game game = new Game(new Cottage(),new Theatre(),new Chapel(),new Farm(),new Tavern(),new Factory(),new Well());
            // game.setTT(new TTDiscord(jda,game));
            // game.start();
            System.out.println("Text Channels: "+jda.getTextChannels());
            System.out.println("jda: "+jda);
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }
}

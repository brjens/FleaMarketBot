package org.example;

import javax.security.auth.login.LoginException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.interactions.commands.OptionType;

public class App extends ListenerAdapter {
    public static void main(String[] args) throws LoginException {
        // ssave Token.java as a varibale callen token
        Token token = new Token();
        JDABuilder builder = JDABuilder.createDefault(token.getBotToken()).addEventListeners(new App()).enableIntents(
                GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.MESSAGE_CONTENT);
        builder.build();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            System.err.println("Received message from bot: " + event.getMessage().getContentRaw());
            return;
        }
        String message = event.getMessage().getContentRaw();
        System.out.println("Received message: " + message);

        if (message.equalsIgnoreCase("!hello")) {
            try {
                event.getChannel().sendMessage("Hello, " + event.getAuthor().getName() + "!").queue();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String command = event.getName();

        if (command.equals("hello")) {
            String usrTag = event.getUser().getEffectiveName();
            event.reply("Hello, " + usrTag + "!").setEphemeral(true).queue();
        }

        if (command.equals("price")) {
            // get the option from the slash command
            String itemName = event.getOption("item").getAsString();
            // get the api response
            String apiresponse = apiResponse(itemName);
            // values from the api
            String price = getPrice(itemName, apiresponse);
            String name = getName(itemName, apiresponse);
            // reply a contatinated String with the name and price
            event.reply("The price of a " + name + " is " + price + " RUB").setEphemeral(true).queue();
        }
    }

    // get the price
    private String getPrice(String itemName, String apiResponse) {
        String price = "";
        price = JsonParser.getItemString(apiResponse, "avg24hPrice");
        return price;
    }

    // get the name
    private String getName(String itemName, String apiResponse) {
        String name = "";
        name = JsonParser.getItemString(apiResponse, "name");
        return name;
    }

    // get the api response
    private String apiResponse(String itemName) {
        TarkovAPI tarkovAPI = new TarkovAPI();
        String response = "";
        try {
            response = tarkovAPI.getJsonString(itemName);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return response;
    }

    // this build the list of commands
    @Override
    public void onGuildReady(GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();
        commandData.add(Commands.slash("hello", "get welcome message"));

        commandData.add(Commands.slash("price", "get price of an item")
                .addOptions(new OptionData(OptionType.STRING, "item", "item name", true)));

        event.getGuild().updateCommands().addCommands(commandData).queue();
    }

}

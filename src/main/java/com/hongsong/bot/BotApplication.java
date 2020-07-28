package com.hongsong.bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BotApplication extends ListenerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(BotApplication.class);
    private final Properties properties;

    public BotApplication() {
        properties = new Properties();
    }

    public Properties getProperties() {
        return properties;
    }

    public void loadProperties(String path) throws IOException {
        InputStream inputStream = getClass().getResourceAsStream(path);
        properties.load(inputStream);
        inputStream.close();
    }

    public static void main(String[] args) throws LoginException, InterruptedException {
        BotApplication botApplication = new BotApplication();

        try {
            logger.info(">>>>> Application load properties");
            botApplication.loadProperties("/application.yml");
        } catch (IOException e) {
            logger.error("!!!!! Application load properties error : ", e);
        }
        Properties properties = botApplication.getProperties();

        JDA jda = JDABuilder.createDefault(properties.getProperty("token")).build();
        jda.awaitReady();
        jda.getTextChannelsByName("general", true).forEach(textChannel -> textChannel.sendMessage("이 몸, 등장.").queue());
    }
}

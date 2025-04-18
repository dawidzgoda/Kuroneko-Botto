package com.kuroneko.config;

import com.kuroneko.interaction.ButtonInteractionManager;
import com.kuroneko.interaction.SlashInteractionManager;
import com.kuroneko.message.CustomMessageManager;
import com.kuroneko.misc.VoiceChannelUpdateHandler;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Bot {

    private final JDA JDA;

    public Bot(SlashInteractionManager sim,
               VoiceChannelUpdateHandler vcuh,
               CustomMessageManager cmm,
               ButtonInteractionManager bim) {
        JDA = JDABuilder.createDefault(ConfigLoader.getConfig().getDiscordToken())
                .enableIntents(GatewayIntent.getIntents(GatewayIntent.ALL_INTENTS))
                .enableCache(CacheFlag.EMOJI, CacheFlag.VOICE_STATE)
                .addEventListeners(sim)
                .addEventListeners(vcuh)
                .addEventListeners(cmm)
                .addEventListeners(bim)
                .setActivity(Activity.listening("Ride on Time"))
                .build();
    }

    @Bean
    public JDA getJDA() {
        return JDA;
    }
}

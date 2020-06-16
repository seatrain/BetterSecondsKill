package com.seatrain.bettersecondskill.function.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GsonConfiguration {

  @Bean
  public Gson getGson() {
    Gson gson = new GsonBuilder().create();

    return gson;
  }
}

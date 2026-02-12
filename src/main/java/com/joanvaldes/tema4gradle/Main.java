package com.joanvaldes.tema4gradle;

import dev.langchain4j.model.openai.OpenAiChatModel;

public class Main {
    public static void main(String[] args) {
        // El TOKEN no es necesario para interactuar con modelos locales
        final String TOKEN = "PEGA_AQUI_TU_TOKEN";

        var model = OpenAiChatModel.builder()
                .baseUrl("https://localhost:11434/v1")
                .apiKey(TOKEN)
                .modelName("llama3.1:8b")
                .build();
        String respuesta = model.chat("Cuéntame un chiste");
        System.out.println(respuesta);
    }

}

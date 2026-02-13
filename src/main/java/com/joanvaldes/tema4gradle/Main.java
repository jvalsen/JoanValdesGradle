package com.joanvaldes.tema4gradle;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.openai.OpenAiChatModel;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // El TOKEN no es necesario para interactuar con modelos locales
        final String TOKEN = "PEGA_AQUI_TU_TOKEN";

        var model = OpenAiChatModel.builder()
                .baseUrl("http://localhost:11434/v1")
                .apiKey(TOKEN)
                .modelName("llama3.2:1b")
                .build();

        List<ChatMessage> history = new ArrayList<>();

        // Interacción 1
        history.add(new UserMessage("Hola, soy Joan"));
        AiMessage respuesta = model.chat(history).aiMessage();
        history.add(respuesta);

        // Interacción 2
        history.add(new UserMessage("¿Recuerdas cómo me llamo?"));
        respuesta = model.chat(history).aiMessage();
        history.add(respuesta);

        System.out.println(respuesta.text());




        var modelA = OpenAiChatModel.builder()
                .baseUrl("http://localhost:11434/v1")
                .apiKey(TOKEN)
                .modelName("llama3.2:1b")
                .build();

        var modelB = OpenAiChatModel.builder()
                .baseUrl("http://localhost:11434/v1")
                .apiKey(TOKEN)
                .modelName("gemma:2b")
                .build();

        List<ChatMessage> historyA = new ArrayList<>();
        List<ChatMessage> historyB = new ArrayList<>();

        // Personalidades
        historyA.add(new SystemMessage("Eres un experto en informática."));
        historyB.add(new SystemMessage("Eres un crítico analítico ."));

        // Pregunta inicial del debate
        historyA.add(new UserMessage("¿Cuál es tu opinión sobre la exploración del espacio?"));

        // IA A responde
        AiMessage respuestaA = modelA.chat(historyA).aiMessage();
        historyA.add(respuestaA);

        System.out.println("IA A responde:");
        System.out.println(respuestaA.text());
        System.out.println(" ");

        // IA B responde a IA A
        historyB.add(new UserMessage("La otra IA ha dicho: " + respuestaA.text() +
                ". ¿Qué opinas al respecto?"));

        AiMessage respuestaB = modelB.chat(historyB).aiMessage();
        historyB.add(respuestaB);

        System.out.println("IA B responde:");
        System.out.println(respuestaB.text());
        System.out.println(" ");
    }
}

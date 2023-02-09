package com.example.demo.service;

import com.example.demo.configuration.MyConfig;
import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class GptChatService {

  @Autowired
  private MyConfig myConfig;

  public String chat(String prompt, String user) {
    return this.chatCore(myConfig.getOpenAIModel(), prompt, user);
  }

  /**
   * @param model
   * @param prompt
   * @param user
   * @return
   */
  public String chatCore(String model, String prompt, String user) {
    StringBuilder result = new StringBuilder();
    try {
      OpenAiService service = new OpenAiService(myConfig.getOpenAIToken(), 60000);
      CompletionRequest completionRequest = CompletionRequest.builder()
              .model(model)
              .prompt(prompt)
              .temperature(0.9)
              .maxTokens(10000)
              .n(1)
              //.stream(true)
              .frequencyPenalty(0.0)
              //.presencePenalty(0.6)
              //.echo(true)
              .user(user)
              .build();
      List<CompletionChoice> choices = service.createCompletion(completionRequest).getChoices();
      for (CompletionChoice choice : choices) {
        result.append(choice.getText());
        log.info("choice=" + choice);
      }
    } catch (Exception e) {
      log.error("chatCore异常", e);
      result.append(e.getMessage());
    }
    return result.toString();
  }
}

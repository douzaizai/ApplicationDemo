
package com.example.demo.controller;

import com.example.demo.service.CheckSignatureService;
import com.example.demo.service.GptChatService;
import com.example.demo.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Slf4j
@RestController
@RequestMapping("/wechat")
public class MessageController {
  @Autowired
  private MessageService messageService;

  @Autowired
  private GptChatService gptChatService;

  @Autowired
  private CheckSignatureService checkSignatureService;

  @PostMapping(value = "/callback")
  public void callback(HttpServletRequest request, HttpServletResponse response) {
    try {
      request.setCharacterEncoding("utf-8");
      response.setCharacterEncoding("utf-8");
      String result = messageService.messageRequest(request);
      PrintWriter out = response.getWriter();
      out.write(result);
    } catch (Exception e) {
      log.error("callback error", e);
    }
  }


  /**
   * 微信验证第三方服务接口
   *
   * @param req
   * @param resp
   */
  @RequestMapping(value = "/callback", method = RequestMethod.GET)
  public void callback_check(HttpServletRequest req, HttpServletResponse resp) {
    try {
      // 获取微信服务器传来的相关参数
      String signature = req.getParameter("signature");
      String timestamp = req.getParameter("timestamp");
      String nonce = req.getParameter("nonce");
      String echoStr = req.getParameter("echostr");
      // 展示微信服务器传来的相关参数
      log.info("signature=" + signature + ",timestamp=" + timestamp + ",nonce=" + nonce + ",echostr=" + echoStr);
      PrintWriter out = resp.getWriter();
      //调用比对signature的方法，实现对token和传入的参数进行hash算法后的结果比对
      if (checkSignatureService.checkSignature(signature, timestamp, nonce)) {
        // 验证通过
        log.info("callback check success!!!");
        out.print(echoStr);
      }
    } catch (Exception e) {
      log.error("验证微信服务器是否有效异常", e);
    }
  }


  /**
   * callbackGptTest 回调接口测试
   *
   * @param request
   * @param response
   * @throws IOException
   */
  @RequestMapping(value = "/callbackGptTest", method = RequestMethod.GET)
  public void callbackGptTest(HttpServletRequest request, HttpServletResponse response) {
    try {
      request.setCharacterEncoding("utf-8");
      response.setContentType("text/html; charset=utf-8");
      // 接收查询参数
      String model = request.getParameter("model");
      String prompt = request.getParameter("prompt");
      String user = request.getParameter("user");
      // 返回前端值
      String result = gptChatService.chatCore(model, prompt, user);
      PrintWriter out = response.getWriter();
      out.print(result);
      out.flush();
    } catch (Exception e) {
      log.error("callbackGptTest测试接口异常", e);
      try {
        PrintWriter out = response.getWriter();
        out.print(e);
        out.flush();
      } catch (Exception e2) {
      }

    }
  }

}

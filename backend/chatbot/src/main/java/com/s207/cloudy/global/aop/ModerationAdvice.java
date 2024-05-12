package com.s207.cloudy.global.aop;

import com.s207.cloudy.domain.chatbot.common.dto.ChatReq;
import com.s207.cloudy.domain.members.entity.Member;
import com.s207.cloudy.global.error.enums.ErrorCode;
import com.s207.cloudy.global.infra.modertation.ModerationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import com.s207.cloudy.global.infra.modertation.ModerationException;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
@Aspect
@RequiredArgsConstructor
public class ModerationAdvice {

    private final ModerationService moderationService;

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void postMapping(){}

    @Pointcut("within(com.s207.cloudy.domain.recommend.api.RecommendController) && @annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void recommendControllerGetMapping(){}

    private Map<String, Object> extractMemberAndChatReq(Object[] args){
        Map<String, Object> data = new HashMap<>();

        for(Object arg : args){

            if(arg instanceof ChatReq){
                data.put("chatReq", arg);

            }
            if(arg instanceof Member){
                data.put("member", arg);
            }
        }

        return data;

    }

    private Map<String, Object> extractQuery(Object[] args){
        Map<String, Object> data = new HashMap<>();

        for(Object arg : args){

            if(arg instanceof String){
                data.put("query", arg);

            }
            if(arg instanceof Member){
                data.put("member", arg);
            }
        }

        return data;

    }

    @Around("postMapping()")
    public Object chatbotModerationAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        var data = extractMemberAndChatReq(proceedingJoinPoint.getArgs());

        var member = (Member) data.get("member");
        var chatReq = (ChatReq) data.get("chatReq");

        if(moderationService.isHarmful(chatReq.getInputData())){
            log.info("질문 규칙 위반 :: member={} messge={}", member.getId(), chatReq.getInputData());
            throw new ModerationException(ErrorCode.HARMFUL_CONTENTS);
        }

        return proceedingJoinPoint.proceed();
    }

    @Around("recommendControllerGetMapping()")
    public Object recommendModerationAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        var data = extractQuery(proceedingJoinPoint.getArgs());

        var member = (Member) data.get("member");
        var query = (String) data.get("query");

        if(moderationService.isHarmful(query)){
            log.info("질문 규칙 위반 :: member={} messge={}", member.getId(), query);
            throw new ModerationException(ErrorCode.HARMFUL_CONTENTS);
        }

        return proceedingJoinPoint.proceed();
    }


}

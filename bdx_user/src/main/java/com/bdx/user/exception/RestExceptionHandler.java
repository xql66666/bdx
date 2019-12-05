package com.bdx.user.exception;

import constants.ResultCodeBase;
import constants.TipConstBase;
import entity.ResponseEntity;
import exception.core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: 磊大大
 * @date: 2019/10/14 16:43
 * 统一异常处理类
 */
@ControllerAdvice
public class RestExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    /**
     * 未登陆异常
     * @param request
     * @param response
     * @param ex
     */
    @ExceptionHandler(NotLoginException.class)
    @ResponseBody
    public ResponseEntity handleInvalidRequestError(NotLoginException ex) {
        logger.error("", ex);
        return new ResponseEntity(ResultCodeBase.CODE_ERROR_USER_NOT_LOGIN, ex.getMessage());

    }

    /**
     * 业务类异常
     * 状态码在所在业务地模块根据业务异常具体定义
     * @param request
     * @param response
     * @param ex
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResponseEntity handleBusinessException(BusinessException ex) {
        logger.error("用户操作错误导致的业务异常", ex);
        return new ResponseEntity(ResultCodeBase.CODE_EXCEPTION, ex.getMessage());
    }

    /**
     * Token异常
     * @param request
     * @param response
     * @param ex
     */
    @ExceptionHandler(TokenErrorException.class)
    @ResponseBody
    public ResponseEntity handleSignErrorException(TokenErrorException ex) {
        logger.error("", ex);
        return new ResponseEntity(ResultCodeBase.CODE_ERROR_USER_TOKEN_ERROR, TipConstBase.OPERATION_TOKEN_ERROR);
    }

    /**
     *无效参数异常
     * @param request
     * @param response
     * @param ex
     */
    @ExceptionHandler(InvalidRequestException.class)
    @ResponseBody
    public ResponseEntity handleInvalidRequestException(InvalidRequestException ex) {
        logger.error("", ex);
        return new ResponseEntity(ResultCodeBase.CODE_BAD_REQUEST, ex.getMessage());
    }


    /**
     * 其他异常
     * @param request
     * @param response
     * @param ex
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity handleException(Exception ex) {
        logger.error("", ex);
        return new ResponseEntity<String>(ResultCodeBase.CODE_EXCEPTION, TipConstBase.OPERATION_SYS_SO_BUSY, ex.getMessage());

    }

    /**
     * 没有此api异常
     * @param request
     * @param response
     * @param ex
     */
    @ExceptionHandler(NoSuchApiMethodException.class)
    @ResponseBody
    public ResponseEntity handleApiMethodException(NoSuchApiMethodException ex) {
        logger.error("", ex);
        return new ResponseEntity<String>(ResultCodeBase.CODE_EXCEPTION, TipConstBase.OPERATION_GET_ERROR, ex.getMessage());

    }


}

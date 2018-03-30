package me.alfod.basecontroller;

import com.aixuexi.thor.except.ResponseDataException;
import com.aixuexi.thor.response.ResultData;
import com.aixuexi.thor.util.Page;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 返回数据类型
 *
 * @author yangdong
 * 使用 com.aixuexi.thor.response.ResultBody 代替
 */
@Deprecated
public class ResultBody extends ResultData implements Serializable {
    private static Logger logger = LoggerFactory.getLogger(ResultBody.class);

    private static final String RPC_EXCEPTION_MSG = "服务器异常";

    private Object body;

    public ResultBody() {
        super();
    }

    /**
     * 带参构造函数
     *
     * @param status       状态
     * @param errorMessage 信息
     * @param errorCode    错误码
     */
    public ResultBody(final int status, final String errorMessage, final int errorCode) {
        super(status,  errorMessage,  errorCode);
    }

    /**
     * 带参构造函数(默认错误)
     *
     * @param errorMessage 信息
     * @param errorCode    错误码
     */
    public ResultBody(final String errorMessage, final int errorCode) {
        super(ResultBody.STATUS_ERROR,  errorMessage,  errorCode);
    }

    /**
     * 带参构造函数(默认正确)
     *
     * @param body 数据
     */
    public ResultBody(final Object body) {
        super();
        setBody(body);
        if (this.body == null) {
            logger.error("ResultData.body is null.");
        }
    }

    public static ResultBody successed() {
        return new ResultBody();
    }

    public static ResultBody successed(Object data) {
        ResultBody resultBody = new ResultBody();
        resultBody.setBody(data);
        return resultBody;
    }

    public static ResultBody successed(String mesg, Object data) {
        ResultBody resultBody = new ResultBody();
        resultBody.setErrorMessage(mesg);
        resultBody.setBody(data);
        return resultBody;
    }

    public static ResultBody failed(String mesg) {
        ResultBody resultBody = new ResultBody();
        resultBody.setErrorMessage(rpcExceptionHandler(mesg));
        resultBody.setStatus(STATUS_ERROR);
        return resultBody;
    }
    public static ResultBody failed(String mesg , Object body) {
        ResultBody resultBody = new ResultBody();
        resultBody.setErrorMessage(rpcExceptionHandler(mesg));
        resultBody.setStatus(STATUS_ERROR);
        resultBody.setBody(body);
        return resultBody;
    }

    @Override
    public Object getBody() {
        if (this.body == null) {
            return new HashMap<String, Object>();
        } else {
            return body;
        }
    }

    @Override
    public ResultBody setBody(Object body) {
        if (body == null) {
            Map<String, Object> map = new HashMap<>(1);
            map.put("object", null);
            this.body = map;
            return this;
        }
        if (body instanceof List
                || body.getClass().isArray()) {
            Map<String, Object> map = new HashMap<>(1);
            map.put("list", body);
            this.body = map;
            return this;
        }
        if (body instanceof Page
                || body instanceof com.gaosi.api.matrix.model.po.Page) {
            this.body = body;
            return this;
        }
        //when body is object, add map
        Map<String, Object> map = new HashMap<>(1);
        map.put("object", body);
        this.body = map;
        return this;
    }


    @Override
    public boolean putKV(String key, Object value) {
        if (key != null && value != null) {
            if (body == null) {
                body = new HashMap<>();
            }
            if (body instanceof Map) {
                ((Map) body).put(key, value);
                return true;
            } else {
                throw new ResponseDataException();
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "ResultBody{" +
                "body=" + body +
                '}';
    }

    @Override
    public String toJsonString() {
        return JSON.toJSONString(this);
    }

    /**
     * RPC服务异常信息包装 不直接将后端异常堆栈细节返回到客户端
     * RPC_EXCEPTION_MSG = "服务器异常";
     *
     * @param rpcMsg rpc响应信息
     * @return
     */
    private static String rpcExceptionHandler(String rpcMsg){
        return rpcMsg.contains("Exception:") ? RPC_EXCEPTION_MSG : rpcMsg;
    }
}

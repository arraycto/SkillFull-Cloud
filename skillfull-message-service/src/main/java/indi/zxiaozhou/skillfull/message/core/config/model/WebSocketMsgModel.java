// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.message.core.config.model;

import indi.zxiaozhou.skillfull.corecommon.base.model.UserAndAuthModel;
import indi.zxiaozhou.skillfull.corewebflux.utils.ContextHolderUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.FluxSink;

import java.io.Serializable;

/**
 * @program: webflux-websocket-chat-websocket-chat
 * @description:
 * @author: 71ang~
 * @create: 2020-07-14 13:39
 * @vsersion: V1.0
 */
@Slf4j
@Data
public class WebSocketMsgModel implements Serializable {
    private static final long serialVersionUID = 3126044575672218399L;
    private FluxSink<WebSocketMessage> sink;
    private WebSocketSession session;
    private String userId;
    private String systemId;
    private String token;

    public WebSocketMsgModel(FluxSink<WebSocketMessage> sink, WebSocketSession session, String token) {
        this.sink = sink;
        this.session = session;
        this.token = token;
        this.systemId = ContextHolderUtils.getUserTokenSubInfoModel(token).getSystemId();
        UserAndAuthModel userAndAuthModel = ContextHolderUtils.getUserAndAuthModel(token);
        this.userId = userAndAuthModel.getUserId();
    }

    /**
     * 发送消息字符串需要由MessageUtils的getWebSocketMsg()方法去构建
     *
     * @param data ${@link String} 待发送消息
     * @author zxiaozhou
     * @date 2021-01-25 16:10
     */
    public void sendData(String data) {
        sink.next(session.textMessage(data));
    }
}
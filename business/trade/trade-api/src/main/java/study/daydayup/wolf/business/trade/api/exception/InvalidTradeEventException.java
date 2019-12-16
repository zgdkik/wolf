package study.daydayup.wolf.business.trade.api.exception;

import study.daydayup.wolf.common.lang.exception.SystemException;

/**
 * study.daydayup.wolf.business.trade.api.exception
 *
 * @author Wingle
 * @since 2019/12/16 7:48 下午
 **/
public class InvalidTradeEventException extends SystemException {
    public InvalidTradeEventException(String msg) {
        super(160001, msg);
    }
}

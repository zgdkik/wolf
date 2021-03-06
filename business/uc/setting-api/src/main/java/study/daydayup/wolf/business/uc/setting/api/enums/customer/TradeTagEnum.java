package study.daydayup.wolf.business.uc.setting.api.enums.customer;

import lombok.Getter;
import study.daydayup.wolf.business.uc.setting.api.enums.StatusEnum;

/**
 * study.daydayup.wolf.business.trade.api.domain.enums
 * range( 30 ~ 50 )
 * @author Wingle
 * @since 2019/10/5 11:07 AM
 **/
@Getter
public enum TradeTagEnum implements StatusEnum {
    OVERDUE_TIMEOUT(36, "trade.loan.overdue.timeout"),
    REPAID_TIMEOUT(36, "trade.loan.repaid.timeout"),
    REFUSED_TIMEOUT(35, "trade.loan.refused.timeout"),

    REPAYING(32, "trade.loan.repaying"),
    LOANING(31, "trade.loan.loaning"),

    FIRST_TRADE(30, "trade.first")
    ;

    private int code;
    private String name;
    TradeTagEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}

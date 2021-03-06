package study.daydayup.wolf.business.trade.buy.biz.base.context;

import lombok.Getter;
import study.daydayup.wolf.business.trade.api.dto.buy.base.request.BuyRequest;
import study.daydayup.wolf.business.trade.api.domain.enums.TradeTypeEnum;
import study.daydayup.wolf.common.util.lang.EnumUtil;

/**
 * study.daydayup.wolf.business.trade.buy.biz.base.context
 *
 * @author Wingle
 * @since 2019/12/17 9:18 下午
 **/
@Getter
public class BuyContextBuilder {
    private BuyContext context;
    private BuyRequest request;

    public BuyContextBuilder(BuyRequest request) {
        context = BuyContext.builder()
                .request(request)
                .build();
        this.request = request;
    }

    public static BuyContext build(BuyRequest request) {
        BuyContextBuilder builder = new BuyContextBuilder(request);
        builder.formatContext();

        return builder.getContext();
    }

    public void formatContext() {
        context.setBuyer(request.getBuyer());

        TradeTypeEnum tradeType = EnumUtil.codeOf(request.getTradeType(), TradeTypeEnum.class);
        context.setTradeType(tradeType);
    }
}

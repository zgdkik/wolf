package study.daydayup.wolf.business.pay.biz.service.india.razorpay;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.pay.api.config.india.RazorConfig;
import study.daydayup.wolf.business.pay.api.enums.PaymentStateEnum;
import study.daydayup.wolf.business.pay.api.exception.DoublePayingException;
import study.daydayup.wolf.business.pay.api.exception.InvalidEpiResponseException;
import study.daydayup.wolf.business.pay.api.exception.InvalidPayConfigException;
import study.daydayup.wolf.business.pay.biz.service.AbstractPaymentCreator;
import study.daydayup.wolf.business.pay.biz.service.PaymentCreator;
import study.daydayup.wolf.common.lang.ds.ObjectMap;
import study.daydayup.wolf.common.util.collection.ArrayUtil;
import study.daydayup.wolf.common.util.lang.BeanUtil;
import study.daydayup.wolf.common.util.lang.DecimalUtil;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * study.daydayup.wolf.business.pay.biz.service.india.razorpay
 *
 * @author Wingle
 * @since 2020/2/28 10:44 上午
 **/
@Component
public class RazorCreator extends AbstractPaymentCreator implements PaymentCreator {
    private static final String CURRENCY = "INR";
    private static final String[] STATUS_ARRAY = new String[] {
            "created", "attempted", "paid"
    };

    @Resource
    private RazorConfig config;

    private int amount;

    public void callPayApi() {
        JSONObject options = new JSONObject();
        options.put("amount", getAmount());
        options.put("currency", CURRENCY);
        options.put("receipt", payment.getPaymentNo());
        options.put("payment_capture", true);

        try {
            RazorpayClient client = new RazorpayClient(config.getKeyId(), config.getKeySecret());
            Order order = client.Orders.create(options);
            parseOrder(order);
        } catch (RazorpayException e) {
            throw new InvalidPayConfigException("Razorpay init fail");
        }

    }

    private void parseOrder(Order order) {
        if (!validOrder(order)) {
            throw new InvalidEpiResponseException("Razorpay fail");
        }

        setResponseAttachment(order);
        setResponseAttachment(order);
    }

    private void setPaymentAttachment(Order order) {
        if (BeanUtil.equals("paid", (String)order.get("status"))) {
            throw new DoublePayingException(payment.getTradeNo());
        }

        if (BeanUtil.equals("attempted", (String)order.get("status"))) {
            payment.setState(PaymentStateEnum.PAYING.getCode());
        }

        ObjectMap map = new ObjectMap();
        map.set("order_id", order.get("id"))
                .set("created_at", order.get("created_at"));

        payment.setAttachment(map.toJson());
    }

    private void setResponseAttachment(Order order) {
        attachment.set("data-key", config.getKeyId());
        attachment.set("data-amount", amount);
        attachment.set("data-currency", CURRENCY);
        attachment.set("data-order_id", order.get("id"));

        attachment.set("data-buttontext", config.getButtonText());
        attachment.set("data-name", config.getCompanyName());
        attachment.set("data-description", config.getCompanyDescription());
        attachment.set("data-image", config.getCompanyLogo());

        attachment.set("data-prefill.name", config.getPrefillName());
        attachment.set("data-prefill.email", config.getPrefillEmail());
        attachment.set("data-theme.color", config.getThemeColor());
    }

    private boolean validOrder(Order order) {
        if (null == order) {
            return false;
        }

        if (!order.has("status") || !order.has("id")) {
            return false;
        }

        if (!BeanUtil.equals(order.get("amount"), amount)) {
            return false;
        }

        if (!payment.getPaymentNo().equals(order.get("receipt"))) {
            return false;
        }

        if (!ArrayUtil.inArray((String)order.get("status"), STATUS_ARRAY)) {
            return false;
        }

        return true;
    }



    private int getAmount() {
        BigDecimal amount = request.getAmount();
        amount = amount.multiply(BigDecimal.valueOf(100));

        this.amount = DecimalUtil.toInt(amount);
        return this.amount;
    }

}

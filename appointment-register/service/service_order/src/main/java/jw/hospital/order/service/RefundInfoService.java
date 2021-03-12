package jw.hospital.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jw.hospital.model.order.PaymentInfo;
import jw.hospital.model.order.RefundInfo;

public interface RefundInfoService extends IService<RefundInfo> {

    /**
     * 保存退款记录
     * @param paymentInfo
     */
    RefundInfo saveRefundInfo(PaymentInfo paymentInfo);

}

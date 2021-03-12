package jw.hospital.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jw.hospital.model.order.OrderInfo;
import jw.hospital.vo.order.OrderCountQueryVo;
import jw.hospital.vo.order.OrderCountVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper extends BaseMapper<OrderInfo> {

    //查询预约统计数据的方法
    List<OrderCountVo> selectOrderCount(@Param("vo") OrderCountQueryVo orderCountQueryVo);
}

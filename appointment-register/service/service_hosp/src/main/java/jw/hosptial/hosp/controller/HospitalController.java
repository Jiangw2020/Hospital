//package jw.hosptial.hosp.controller;
//
//import io.swagger.annotations.ApiOperation;
//import jw.hosptial.common.result.Result;
//import jw.hosptial.hosp.service.HospitalService;
//import jw.hosptial.model.hosp.Hospital;
//import jw.hosptial.vo.hosp.HospitalQueryVo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/admin/hosp/hospital")
////@CrossOrigin
//public class HospitalController {
//
//    @Autowired
//    private HospitalService hospitalService;
//
//    //医院列表(条件查询分页)
//    @GetMapping("list/{page}/{limit}")
//    public Result listHosp(@PathVariable Integer page,
//                           @PathVariable Integer limit,
//                           HospitalQueryVo hospitalQueryVo) {
//        Page<Hospital> pageModel = hospitalService.selectHospPage(page,limit,hospitalQueryVo);
//        List<Hospital> content = pageModel.getContent();
//        long totalElements = pageModel.getTotalElements();
//
//        return Result.ok(pageModel);
//    }
//
//    //更新医院上线状态
//    @ApiOperation(value = "更新医院上线状态")
//    @GetMapping("updateHospStatus/{id}/{status}")
//    public Result updateHospStatus(@PathVariable String id,@PathVariable Integer status) {
//        hospitalService.updateStatus(id,status);
//        return Result.ok();
//    }
//
//    //医院详情信息
//    @ApiOperation(value = "医院详情信息")
//    @GetMapping("showHospDetail/{id}")
//    public Result showHospDetail(@PathVariable String id) {
//        Map<String, Object> map = hospitalService.getHospById(id);
//        return Result.ok(map);
//    }
//}

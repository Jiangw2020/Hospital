//package jw.hosptial.hosp.controller;
//
//import io.swagger.annotations.ApiOperation;
//import jw.hosptial.common.result.Result;
//import jw.hosptial.hosp.service.DepartmentService;
//import jw.hosptial.vo.hosp.DepartmentVo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/admin/hosp/department")
////@CrossOrigin
//public class DepartmentController {
//
//    @Autowired
//    private DepartmentService departmentService;
//
//    //根据医院编号，查询医院所有科室列表
//    @ApiOperation(value = "查询医院所有科室列表")
//    @GetMapping("getDeptList/{hoscode}")
//    public Result getDeptList(@PathVariable String hoscode) {
//        List<DepartmentVo> list = departmentService.findDeptTree(hoscode);
//        return Result.ok(list);
//    }
//}

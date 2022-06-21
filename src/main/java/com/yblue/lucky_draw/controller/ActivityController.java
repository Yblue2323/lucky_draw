package com.yblue.lucky_draw.controller;

import com.yblue.lucky_draw.config.annotation.NotDuplicate;
import com.yblue.lucky_draw.domain.Activity;
import com.yblue.lucky_draw.dto.ActivityDTO;
import com.yblue.lucky_draw.dto.DeleteBatchDTO;
import com.yblue.lucky_draw.service.impl.ActivityServiceImpl;
import com.yblue.lucky_draw.util.JsonResult;
import com.yblue.lucky_draw.util.ResultEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Api(tags = "抽奖活动")
@RestController
@RequestMapping("/activity")
@AllArgsConstructor
public class ActivityController {

    private final ActivityServiceImpl activityService;

    @NotDuplicate
    @PostMapping("/saveOrUpdateActivity")
    @ApiOperation(value = "编辑活动", notes = "新增或者修改活动")
    public JsonResult saveOrUpdateActivity(@RequestBody @Valid ActivityDTO activityDTO) {
        activityService.saveOrUpdateActivity(activityDTO);
        return new JsonResult(ResultEnum.SUCCESS);
    }


    @PostMapping("/deleteActivity")
    @ApiOperation(value = "批量删除活动", notes = "根据活动ids删除活动")
    public JsonResult deleteActivity(@RequestBody DeleteBatchDTO deleteBatchDTO) {
        activityService.deleteActivity(deleteBatchDTO.getIds());
        return new JsonResult(ResultEnum.SUCCESS);
    }

    @GetMapping("/listActivityByUser")
    @ApiOperation(value = "获取用户参与的所有活动", notes = "获取用户参与的所有活动(删除,未删除)")
    public JsonResult<List<Activity>> listActivityByUser(Integer isDelete) {
        List<Activity> activities = activityService.listActivityByUser(isDelete);
        return new JsonResult<>(activities, ResultEnum.SUCCESS);
    }

    @GetMapping("/listActivityByCreator")
    @ApiOperation(value = "获取用户创建的所有活动", notes = "可根据创建时间获取")
    public JsonResult<List<Activity>> listActivityByCreator(Date createTime) {
        List<Activity> activities = activityService.listActivityByCreator(createTime);
        return new JsonResult<>(activities, ResultEnum.SUCCESS);
    }
}

package com.yblue.lucky_draw.controller;


import com.yblue.lucky_draw.service.impl.LuckyDrawService;
import com.yblue.lucky_draw.util.JsonResult;
import com.yblue.lucky_draw.util.ResultEnum;
import com.yblue.lucky_draw.vo.GitVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@Api(tags = "抽奖")
@RestController
@AllArgsConstructor
public class LucyDrawController {

    private final LuckyDrawService luckyDrawService;

    @GetMapping("/luckDraw/{activityId}")
    @ApiOperation(value = "抽奖", notes = "抽奖")
    public JsonResult luckDraw(@PathVariable Long activityId) {

        GitVO gitVO = luckyDrawService.luckyDraw(activityId);

        return new JsonResult<>(gitVO, ResultEnum.SUCCESS);
    }
}

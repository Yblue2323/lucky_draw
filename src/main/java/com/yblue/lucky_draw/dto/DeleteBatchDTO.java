package com.yblue.lucky_draw.dto;

import lombok.Data;

import java.util.List;

/**
 * 通用批量删除DTO
 */
@Data
public class DeleteBatchDTO {

    List<Long> ids;
}

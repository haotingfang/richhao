package com.example.web.controller.goods;

import com.example.common.annotation.Log;
import com.example.common.core.domain.AjaxResult;
import com.example.common.core.domain.TableDataInfo;
import com.example.common.core.domain.entity.Card;
import com.example.common.core.domain.entity.Role;
import com.example.common.core.domain.entity.Card;
import com.example.common.enums.BusinessType;
import com.example.common.enums.OperatorType;
import com.example.common.utils.StringUtils;
import com.example.dao.service.CardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("会员卡模块")
@RestController
@RequestMapping("/goods/card")
public class CardController {

    private static final Logger logger = LoggerFactory.getLogger(CardController.class);

    @Autowired
    private CardService cardService;

    @Log(title = "会员卡模块" , businessType = BusinessType.OTHER , operatorType = OperatorType.MANAGE )
    @ApiOperation("会员卡列表")
    @PreAuthorize("hasAuthority('goods:card:list')")
    @GetMapping("/list")
    public TableDataInfo list(Card card, Integer pageSize , Integer pageNum)
    {
        logger.info("会员卡模块 Card:[{}] ",card);
        TableDataInfo tableDataInfo = cardService.queryAllByLimit(card,pageSize,pageNum);
        logger.info("会员卡列表 tableDataInfo:[{}] ",tableDataInfo.toString());
        return tableDataInfo;
    }

    @Log(title = "会员卡编辑" , businessType = BusinessType.OTHER , operatorType = OperatorType.MANAGE )
    @ApiOperation("会员卡编辑")
    @PreAuthorize("hasAuthority('goods:card:query')")
    @GetMapping(value = { "/", "/{cardId}" })
    public AjaxResult list(@PathVariable(value = "cardId" , required = false ) Long cardId)
    {
        logger.info("会员卡编辑 cardId:[{}] ",cardId);
        AjaxResult ajaxResult = AjaxResult.success();
        //会员卡信息
        Card card = cardService.queryById(cardId);
        ajaxResult.put("data" , card);
        logger.info("会员卡编辑 ajaxResult:[{}] ",ajaxResult.toString());
        return ajaxResult;
    }

    @Log(title = "会员卡删除" , businessType = BusinessType.UPDATE , operatorType = OperatorType.MANAGE )
    @ApiOperation("会员卡删除")
    @PreAuthorize("hasAuthority('goods:card:remove')")
    @DeleteMapping("/{cardIds}")
    public AjaxResult deleteRole(@PathVariable Long[] cardIds) {
        logger.info("会员卡删除 cardIds:[{}] ", cardIds);
        int rows = cardService.deleteByIds(cardIds);
        AjaxResult ajaxResult = rows > 0 ? AjaxResult.success() : AjaxResult.error();
        logger.info("会员卡删除 ajaxResult:[{}] ", ajaxResult.toString());
        return ajaxResult;
    }

    @Log(title = "会员卡修改" , businessType = BusinessType.UPDATE , operatorType = OperatorType.MANAGE)
    @ApiOperation("会员卡修改")
    @PreAuthorize("hasAnyAuthority('goods:card:edit')")
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody Card card){
        logger.info("会员卡修改 start  card:"+card);
        AjaxResult ajaxResult =  cardService.update(card);
        logger.info("会员卡修改 end Card:"+ajaxResult.toString());
        return ajaxResult;
    }

    @Log(title = "会员卡新增" , businessType = BusinessType.UPDATE , operatorType = OperatorType.MANAGE)
    @ApiOperation("会员卡新增")
    @PreAuthorize("hasAnyAuthority('goods:card:edit')")
    @PostMapping
    public AjaxResult add(@Validated @RequestBody Card card){
        logger.info("会员卡新增 start  card:"+card);
        AjaxResult ajaxResult =  cardService.insert(card);
        logger.info("会员卡新增 end Card:"+ajaxResult.toString());
        return ajaxResult;
    }

}

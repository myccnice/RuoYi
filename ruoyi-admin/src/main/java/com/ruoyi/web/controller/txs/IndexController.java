package com.ruoyi.web.controller.txs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.constant.PageTag;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableData;
import com.ruoyi.common.json.JSONObject;
import com.ruoyi.txs.domain.TxsOrder;
import com.ruoyi.txs.service.TxsCountService;
import com.ruoyi.txs.service.TxsOrderService;

/**
 * 为首页提供数据接口
 *
 * @author: wangpeng
 * @date: 2022年4月8日 下午4:08:17
 */
@RestController
@RequestMapping("/txs/index")
public class IndexController extends BaseController {

    @Autowired
    private TxsCountService txsCountService;
    @Autowired
    private TxsOrderService orderService;

    @PostMapping("/photograph")
    public TableData<TxsOrder> notPhotograph(TxsOrder param) {
        startPage();
        List<TxsOrder> list = txsCountService.notPhotographOrderList();
        return TableData.getInfo(list);
    }

    @PostMapping("/choose")
    public TableData<TxsOrder> notChoosePhoto(TxsOrder param) {
        startPage();
        List<TxsOrder> list = txsCountService.notChoosePhotoOrderList();
        return TableData.getInfo(list);
    }

    @PostMapping("/finished")
    public TableData<TxsOrder> notFinished(TxsOrder param) {
        startPage();
        List<TxsOrder> list = txsCountService.notFinishedOrderList();
        return TableData.getInfo(list);
    }

    @GetMapping("/count")
    public JSONObject getCount() {
        Integer photograph = orderService.queryNotPhotograph().size();

        Integer choose = orderService.queryNotChoosePhoto().size();

        Integer finished = orderService.notFinishedOrderList().size();

        JSONObject json = new JSONObject();
        json.put(PageTag.NOT_PHOTOGRAPH, photograph);
        json.put(PageTag.NOT_CHOOSE_PHOTO, choose);
        json.put(PageTag.NOT_FINISHED, finished);
        return json;
    }
}

package com.ruoyi.web.controller.txs;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableData;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.txs.domain.TxsOrder;
import com.ruoyi.txs.service.TxsOrderService;
import com.ruoyi.txs.service.TxsSetMealService;

/**
 * 订单信息
 * @author: wangpeng
 * @date: 2022年1月18日 下午3:45:57
 */
@Controller
@RequestMapping("/txs/order")
public class OrderController extends BaseController {

    private String prefix = "txs/order";
    private static final String TITLE = "订单管理";

    @Autowired
    private TxsOrderService orderService;
    @Autowired
    private TxsSetMealService tsmService;

    @GetMapping()
    @RequiresPermissions("txs:order:view")
    public String order() {
        return prefix + "/index";
    }

    @ResponseBody
    @PostMapping("/list")
    @RequiresPermissions("txs:order:list")
    public TableData<TxsOrder> list(TxsOrder param) {
        startPage();
        List<TxsOrder> list = orderService.selectList(param);
        return TableData.getInfo(list);
    }

    @Log(title = TITLE, businessType = BusinessType.EXPORT)
    @RequiresPermissions("txs:order:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TxsOrder param) {
        List<TxsOrder> list = orderService.selectList(param);
        ExcelUtil<TxsOrder> util = new ExcelUtil<TxsOrder>(TxsOrder.class);
        return util.exportExcel(list, "订单数据");
    }

    @GetMapping("/add")
    public String add(ModelMap mmap) {
        mmap.put("setMeals", tsmService.getEnabledList(null));
        return prefix + "/add";
    }

    @RequiresPermissions("txs:order:add")
    @Log(title = TITLE, businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated TxsOrder param) {
        String loginName = getLoginName();
        param.setCreateBy(loginName);
        param.setUpdateBy(loginName);
        countCache.clear();
        return toAjax(orderService.insert(param));
    }

    @RequiresPermissions("txs:order:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        TxsOrder order = orderService.selectById(id);
        mmap.put("setMeals", tsmService.getEnabledList(order.getSetMealId()));
        mmap.put("order", order);
        return prefix + "/edit";
    }

    @RequiresPermissions("txs:order:edit")
    @Log(title = TITLE, businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated TxsOrder param) {
        param.setUpdateBy(getLoginName());
        countCache.clear();
        return toAjax(orderService.update(param));
    }

    @RequiresPermissions("txs:order:remove")
    @Log(title = TITLE, businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        try {
            return toAjax(orderService.deleteByIds(ids));
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }
}

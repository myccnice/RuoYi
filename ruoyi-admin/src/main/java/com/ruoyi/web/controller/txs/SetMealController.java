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
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableData;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.txs.domain.TxsSetMeal;
import com.ruoyi.txs.service.TxsSetMealService;

/**
 * 套餐信息
 * @author: wangpeng
 * @date: 2022年1月20日 上午11:40:04
 */
@Controller
@RequestMapping("/txs/setmeal")
public class SetMealController extends BaseController {

    private String prefix = "txs/setmeal";
    private static final String TITLE = "套餐管理";

    @Autowired
    private TxsSetMealService txsSetMealService;

    @GetMapping()
    @RequiresPermissions("txs:setmeal:view")
    public String user() {
        return prefix + "/index";
    }

    @ResponseBody
    @PostMapping("/list")
    @RequiresPermissions("txs:setmeal:list")
    public TableData<TxsSetMeal> list(TxsSetMeal param) {
        startPage();
        List<TxsSetMeal> list = txsSetMealService.selectList(param);
        return TableData.getInfo(list);
    }

    @Log(title = TITLE, businessType = BusinessType.EXPORT)
    @RequiresPermissions("txs:setmeal:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TxsSetMeal param) {
        List<TxsSetMeal> list = txsSetMealService.selectList(param);
        ExcelUtil<TxsSetMeal> util = new ExcelUtil<TxsSetMeal>(TxsSetMeal.class);
        return util.exportExcel(list, "套餐数据");
    }

    @RequiresPermissions("txs:setmeal:remove")
    @Log(title = TITLE, businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        try {
            return toAjax(txsSetMealService.deleteByIds(ids));
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @RequiresPermissions("txs:setmeal:add")
    @Log(title = TITLE, businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated TxsSetMeal param) {
        if (UserConstants.POST_NAME_NOT_UNIQUE.equals(txsSetMealService.checkUnique(param))) {
            return error("新增套餐'" + param.getName() + "'失败，套餐名称已存在");
        }
        String loginName = getLoginName();
        param.setCreateBy(loginName);
        param.setUpdateBy(loginName);
        return toAjax(txsSetMealService.insert(param));
    }

    @RequiresPermissions("txs:setmeal:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        mmap.put("post", txsSetMealService.selectById(id));
        return prefix + "/edit";
    }

    @RequiresPermissions("txs:post:edit")
    @Log(title = TITLE, businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated TxsSetMeal param) {
        if (UserConstants.POST_NAME_NOT_UNIQUE.equals(txsSetMealService.checkUnique(param))) {
            return error("修改套餐'" + param.getName() + "'失败，套餐名称已存在");
        }
        param.setUpdateBy(getLoginName());
        return toAjax(txsSetMealService.update(param));
    }

    @PostMapping("/checkNameUnique")
    @ResponseBody
    public String checkPostNameUnique(TxsSetMeal param) {
        return txsSetMealService.checkUnique(param);
    }
}

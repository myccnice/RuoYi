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
import com.ruoyi.txs.domain.TxsCustomer;
import com.ruoyi.txs.service.TxsCustomerService;

/**
 * 客户信息
 * @author: wangpeng
 * @date: 2022年1月18日 下午3:45:57
 */
@Controller
@RequestMapping("/txs/customer")
public class CustomerController extends BaseController {

    private String prefix = "txs/customer";
    private static final String TITLE = "客户管理";
    private static final String PHONE_ALREADY_EXISTS = "客户电话已存在!";

    @Autowired
    private TxsCustomerService service;

    @GetMapping()
    @RequiresPermissions("txs:customer:view")
    public String user() {
        return prefix + "/index";
    }

    @ResponseBody
    @PostMapping("/list")
    @RequiresPermissions("txs:customer:list")
    public TableData<TxsCustomer> list(TxsCustomer param) {
        startPage();
        List<TxsCustomer> list = service.selectList(param);
        return TableData.getInfo(list);
    }

    @Log(title = TITLE, businessType = BusinessType.EXPORT)
    @RequiresPermissions("txs:customer:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TxsCustomer param) {
        List<TxsCustomer> list = service.selectList(param);
        ExcelUtil<TxsCustomer> util = new ExcelUtil<TxsCustomer>(TxsCustomer.class);
        return util.exportExcel(list, "客户数据");
    }

    @RequiresPermissions("txs:customer:remove")
    @Log(title = TITLE, businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        try {
            return toAjax(service.deleteByIds(ids));
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @RequiresPermissions("txs:customer:add")
    @Log(title = TITLE, businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated TxsCustomer param) {
        if (UserConstants.POST_NAME_NOT_UNIQUE.equals(service.checkUnique(param))) {
            return error(PHONE_ALREADY_EXISTS);
        }
        String loginName = getLoginName();
        param.setCreateBy(loginName);
        param.setUpdateBy(loginName);
        return toAjax(service.insert(param));
    }

    @RequiresPermissions("txs:customer:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        mmap.put("customer", service.selectById(id));
        return prefix + "/edit";
    }

    @RequiresPermissions("txs:customer:edit")
    @Log(title = TITLE, businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated TxsCustomer param) {
        if (UserConstants.POST_NAME_NOT_UNIQUE.equals(service.checkUnique(param))) {
            return error(PHONE_ALREADY_EXISTS);
        }
        param.setUpdateBy(getLoginName());
        return toAjax(service.update(param));
    }

    @ResponseBody
    @PostMapping("/checkPhomeUnique")
    public String checkPhomeUnique(TxsCustomer param) {
        return service.checkUnique(param);
    }
}

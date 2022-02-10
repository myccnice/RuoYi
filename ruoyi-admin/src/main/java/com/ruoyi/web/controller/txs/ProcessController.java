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
import com.ruoyi.txs.domain.TxsProcess;
import com.ruoyi.txs.service.TxsProcessService;

/**
 * 流程管理API接口层
 *
 * @author: wangpeng
 * @date: 2022年1月24日 下午12:54:13
 */
@Controller
@RequestMapping("/txs/process")
public class ProcessController extends BaseController {

    private String prefix = "txs/process";
    private static final String TITLE = "流程管理";
    private static final String PHONE_ALREADY_EXISTS = "流程名称已存在!";

    @Autowired
    private TxsProcessService service;

    @GetMapping()
    @RequiresPermissions("txs:process:view")
    public String user() {
        return prefix + "/index";
    }

    @ResponseBody
    @PostMapping("/list")
    @RequiresPermissions("txs:process:list")
    public TableData<TxsProcess> list(TxsProcess param) {
        startPage();
        List<TxsProcess> list = service.selectList(param);
        return TableData.getInfo(list);
    }

    @Log(title = TITLE, businessType = BusinessType.EXPORT)
    @RequiresPermissions("txs:process:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TxsProcess param) {
        List<TxsProcess> list = service.selectList(param);
        ExcelUtil<TxsProcess> util = new ExcelUtil<TxsProcess>(TxsProcess.class);
        return util.exportExcel(list, "流程数据");
    }

    @RequiresPermissions("txs:process:remove")
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

    @RequiresPermissions("txs:process:add")
    @Log(title = TITLE, businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated TxsProcess param) {
        if (UserConstants.POST_NAME_NOT_UNIQUE.equals(service.checkUnique(param))) {
            return error(PHONE_ALREADY_EXISTS);
        }
        String loginName = getLoginName();
        param.setCreateBy(loginName);
        param.setUpdateBy(loginName);
        return toAjax(service.insert(param));
    }

    @RequiresPermissions("txs:process:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        mmap.put("process", service.selectById(id));
        return prefix + "/edit";
    }

    @RequiresPermissions("txs:process:edit")
    @Log(title = TITLE, businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated TxsProcess param) {
        if (UserConstants.POST_NAME_NOT_UNIQUE.equals(service.checkUnique(param))) {
            return error(PHONE_ALREADY_EXISTS);
        }
        param.setUpdateBy(getLoginName());
        return toAjax(service.update(param));
    }

    @ResponseBody
    @PostMapping("/checkUnique")
    public String checkUnique(TxsProcess param) {
        return service.checkUnique(param);
    }
}

package com.ms.jansing.pp.controller;

import com.ms.jansing.common.editor.LocalDateEditor;
import com.ms.jansing.pp.entity.Fund;
import com.ms.jansing.pp.service.FundService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by jansing on 17-7-22.
 */
@RestController
@RequestMapping("/fund/api")
public class FundController {
    @Resource
    private FundService fundService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDate.class, new LocalDateEditor());
    }

    @ModelAttribute
    private Fund get(@RequestParam(required = false) String id, HttpServletRequest req) {
        return StringUtils.isNotBlank(id) ? fundService.get(id) : new Fund();
    }

    private Map<String, Object> base(Map<String, Object> res, Callable callable) {
        try {
            callable.call();
            res.put("action", "ok");
        } catch (Exception e) {
            res.put("action", "fail");
            res.put("msg", e.getMessage());
            e.printStackTrace();
        }
        return res;
    }

    @RequestMapping(value = "/funds", method = RequestMethod.GET)
    public Map<String, Object> funds() {
        Map<String, Object> res = new HashMap<>();
        base(res, new Callable() {
            @Override
            public Object call() throws Exception {
                res.put("funds", fundService.findList());
                return null;
            }
        });
        return res;
    }

    @RequestMapping(value = "/fund/{id}", method = RequestMethod.GET)
    public Map<String, Object> getOne(@PathVariable String id) {
        Map<String, Object> res = new HashMap<>();
        base(res, new Callable() {
            @Override
            public Object call() throws Exception {
                res.put("fund", fundService.get(id));
                return null;
            }
        });
        return res;
    }

    @RequestMapping(value = "/fund", method = RequestMethod.PUT)
    public Map<String, Object> update(Fund fund) {
        Map<String, Object> res = new HashMap<>();
        base(res, new Callable() {
            @Override
            public Object call() throws Exception {
                fundService.save(fund);
                return null;
            }
        });
        return res;
    }

    @RequestMapping(value = "/fund", method = RequestMethod.POST)
    public Map<String, Object> save(Fund fund) {
        Map<String, Object> res = update(fund);
        res.put("id", fund.getId());
        return res;
    }

    @RequestMapping(value = "/fund/{id}", method = RequestMethod.DELETE)
    public Map<String, Object> delete(@PathVariable String id) {
        Map<String, Object> res = new HashMap<>();
        base(res, new Callable() {
            @Override
            public Object call() throws Exception {
                fundService.delete(id);
                return null;
            }
        });
        return res;
    }
}

package com.zx.spingbootmicrowebflux.databinding;

import com.zx.spingbootmicrowebflux.bean.User;
import com.zx.spingbootmicrowebflux.databinding.editor.MultiDateParseEditor;
import com.zx.spingbootmicrowebflux.error.enums.ZXErrorCode;
import com.zx.spingbootmicrowebflux.error.exception.ZXException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zhouxin
 * @since 2019/3/4
 */
@RestController
@RequestMapping("/databinding")
public class DataBindingController {

    @RequestMapping(value="/person", method= RequestMethod.GET)
    public User person(User user, BindingResult bindingResult) {
        doPerson(user, bindingResult);
        return user;
    }

    @RequestMapping(value="/person2", method= RequestMethod.POST)
    public void person2(@RequestBody User user, BindingResult bindingResult) {
        doPerson(user, bindingResult);
    }

    private void doPerson(User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ZXException(ZXErrorCode.YOUR_ERROR, bindingResult.getAllErrors().toString());
        }
        System.out.println(bindingResult);
        System.out.println(user);
    }

    /**
     * binder.registerCustomEditor 好像只能用GET?
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // 格式过滤
        binder.addValidators(new UserValidator());
        // 格式转换
//        binder.registerCustomEditor(Integer.class,new MyCustomNumberEditor());

        List<DateTimeFormatter> dateFormats = new LinkedList<>();
        dateFormats.add(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        dateFormats.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        dateFormats.add(DateTimeFormatter.ofPattern("yyyy+MM+dd HH:mm:ss"));
        binder.registerCustomEditor(LocalDateTime.class,"localDateTime",new MultiDateParseEditor(dateFormats,false));
    }
}

package org.Investors.system;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
class CrashController {

    @RequestMapping(method = RequestMethod.GET, path = "/oops")
    public String triggerException() {
        throw new RuntimeException("Expected: controller used to showcase what "
                + "happens when an exception is thrown");
    }

}
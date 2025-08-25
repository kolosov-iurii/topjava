package ru.javawebinar.topjava.web.user;

import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.to.UserTo;
import springfox.documentation.annotations.ApiIgnore;

import java.net.URI;

import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Controller
public class ProfileRestController extends AbstractUserController {

    public User get(@AuthenticationPrincipal @ApiIgnore AuthorizedUser authUser) {
        return super.get(authUser.getId());
    }

    public void delete(@AuthenticationPrincipal @ApiIgnore AuthorizedUser authUser) {
        super.delete(authUser.getId());
    }

    public void update(User user) {
        super.update(user, authUserId());
    }
}
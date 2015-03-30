package kr.ac.korea.dilab.playing.fvs.interfaces.controller;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.PagingParameters;
import org.springframework.social.facebook.api.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

/**
 * Created by okcomputer on 3/30/2015.
 */
@Controller
@RequestMapping("/")
public class StartController {

    private Facebook facebook;

    @Inject
    public StartController(Facebook facebook) {
        this.facebook = facebook;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String helloFacebook(PagingParameters page, Model model) {
        if (!facebook.isAuthorized()) {
            return "redirect:/start";
        }

        model.addAttribute(facebook.userOperations().getUserProfile());
        PagedList<Post> homeFeed;
        if (page == null) {
            homeFeed = facebook.feedOperations().getHomeFeed();
        } else {
            homeFeed = facebook.feedOperations().getHomeFeed(page);
        }
        PagingParameters prevPage = homeFeed.getPreviousPage();
        PagingParameters nextPage = homeFeed.getNextPage();
        model.addAttribute("feed", homeFeed);
        model.addAttribute("prevPage", prevPage);
        model.addAttribute("nextPage", nextPage);

        String nextPageStr = "?limit=" + nextPage.getLimit() + "&offset=" + nextPage.getOffset() + "&since=" + nextPage.getSince() + "&until=" + nextPage.getUntil() + "&after=" + nextPage.getAfter() + "&before=" + nextPage.getBefore();
        model.addAttribute("nextPageStr", nextPageStr);

        if (prevPage != null) {
            String prevPageStr = "?limit=" + prevPage.getLimit() + "&offset=" + prevPage.getOffset() + "&since=" + prevPage.getSince() + "&until=" + prevPage.getUntil() + "&after=" + nextPage.getAfter() + "&before=" + nextPage.getBefore();
            model.addAttribute("prevPageStr", prevPageStr);
        }
        return "stream";
    }
}

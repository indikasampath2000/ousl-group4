package ousl.group4.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import ousl.group4.model.PromotionCampaign;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PromotionalCampaignController {

    @RequestMapping(method = RequestMethod.GET, value = "/email-promotion.html")
    public String initEmailForm(ModelMap modelMap){
        PromotionCampaign promotionCampaign = new PromotionCampaign();
        modelMap.put("promotionCampaign", promotionCampaign);

        return "email-promotion";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sms-promotion.html")
    public String initSmsForm(ModelMap modelMap){
        PromotionCampaign promotionCampaign = new PromotionCampaign();
        modelMap.put("promotionCampaign", promotionCampaign);

        return "sms-promotion";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create.html")
    public String processForm(@ModelAttribute("promotionCampaign")PromotionCampaign promotionCampaign, BindingResult result){

        List<MultipartFile> files = promotionCampaign.getFiles();

        if(null != files && files.size() > 0) {
            for (MultipartFile multipartFile : files) {

                String fileName = multipartFile.getOriginalFilename();

            }
        }
        return "";
    }
}

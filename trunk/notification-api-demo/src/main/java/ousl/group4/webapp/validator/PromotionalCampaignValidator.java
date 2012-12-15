package ousl.group4.webapp.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ousl.group4.model.PromotionCampaign;
import ousl.group4.webapp.util.SpreadSheetUtil;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class PromotionalCampaignValidator implements Validator{
    @Override
    public boolean supports(Class<?> aClass) {
        return PromotionCampaign.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        String phoneNumberPattern = "^([0][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9])$";

        int counter = 1;

        Pattern mailPattern = Pattern.compile(emailPattern);
        Pattern smsPattern = Pattern.compile(phoneNumberPattern);

        ValidationUtils.rejectIfEmpty(errors, "user", "promotion.err.user", "Field is not selected");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "message", "promotion.err.message", "Field is empty");

        PromotionCampaign promotionCampaign = (PromotionCampaign) o;
        if(promotionCampaign.getUser() != null){
            if(promotionCampaign.getUser().equalsIgnoreCase("U")){
                if(promotionCampaign.getSpreadsheet().getSize() == 0){
                    errors.rejectValue("spreadsheet","promotion.err.spreadsheet");
                }
                String spreadsheetFileName = promotionCampaign.getSpreadsheet().getOriginalFilename();
                if(!spreadsheetFileName.substring(spreadsheetFileName.lastIndexOf(".")).equalsIgnoreCase(".xls")){
                    errors.rejectValue("spreadsheet","promotion.err.spreadsheet.invalid");
                }else {
                    try {
                        List<String> list = SpreadSheetUtil.readSpreadSheet(promotionCampaign.getSpreadsheet().getInputStream());
                        for(String s : list){
                            if(promotionCampaign.getType().equalsIgnoreCase("email")){
                                if(!mailPattern.matcher(s).matches()){
                                    errors.rejectValue("spreadsheet", "promotion.err.email.invalid", new Object[]{counter}, "Invalid data type");
                                }
                            }
                            if(promotionCampaign.getType().equalsIgnoreCase("sms")){
                                if(!smsPattern.matcher(s).matches()){
                                    errors.rejectValue("spreadsheet", "promotion.err.sms.invalid", new Object[]{counter}, "Invalid data type");
                                }
                            }
                            counter++;
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if(promotionCampaign.getSchedule()){
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "scheduleDate", "promotion.err.email.scheduleDate", "Field is empty");
        }
    }
}

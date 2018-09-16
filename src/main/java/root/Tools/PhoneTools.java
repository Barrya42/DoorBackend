package root.Tools;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class PhoneTools
{
    public static String preparePhone(String phone) throws IncorrectPhoneFormat
    {
        String result = phone.replaceAll("\\D", "");
        if (result.length() != 11 || (!result.startsWith("79") && !result.startsWith("89")))
        {
            throw new IncorrectPhoneFormat(phone);
        }
        return result;
    }


}
@ResponseStatus(HttpStatus.BAD_REQUEST)
class IncorrectPhoneFormat extends RuntimeException
{
    IncorrectPhoneFormat(String phone)
    {
        super("Phone format is incorrect: " + phone);
    }
}
import com.firebase.security.token.TokenGenerator;
import com.firebase.security.token.TokenOptions;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gunther on 1/30/17.
 */
public class Token {


    public static String genToken() {
        Map<String, Object> authPayload = new HashMap<String, Object>();
        authPayload.put("uid", "1");
        authPayload.put("rip","noob");

        TokenOptions tokenOptions = new TokenOptions();

        TokenGenerator tokenGenerator = new TokenGenerator("qQBqeaumLqKB1kgIiDvtcxVBMSsKXLT0v5I8dAjM");
        String token = tokenGenerator.createToken(authPayload, tokenOptions);

        return token;
    }
}

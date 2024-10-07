package lk.ijse.notecollectorspringmvc.util;

import java.util.Base64;
import java.util.UUID;

public class AppUtil {

    public static String generateNoteId(){
        return "NOTE-" + UUID.randomUUID();
    }

    public static String generateUserId(){
        return "USER-" + UUID.randomUUID();
    }

    // convert an image to Base64
    public static String profilePicToBase64(byte[] profilePic) {

        // byte collection eka base 64 valata convert karanava. ita passe eka string vidiyata encode karanava. byte collection eka aragena eken base 64 eakata giyama encode and decode dekama karaganna puluvan
        return Base64.getEncoder().encodeToString(profilePic);
    }
}

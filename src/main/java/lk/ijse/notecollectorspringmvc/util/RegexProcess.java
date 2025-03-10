package lk.ijse.notecollectorspringmvc.util;

import java.util.regex.Pattern;

public class RegexProcess {

    public static boolean noteIdMatcher(String noteId) {
        String regexForNoteID = "^NOTE-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForNoteID);
        return regexPattern.matcher(noteId).matches();
    }

    public static boolean userIdMatcher(String userId) {
        String regexForUserID = "^USER-[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";
        Pattern regexPattern = Pattern.compile(regexForUserID);
        return regexPattern.matcher(userId).matches();
    }
}

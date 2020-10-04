package CSCI5308.GroupFormationTool.AccessControlTest;

import CSCI5308.GroupFormationTool.AccessControl.IUpdatePassword;
import CSCI5308.GroupFormationTool.AccessControl.IUser;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UpdatePasswordTest {

    @Test
    public void updatePasswordTest()
    {
        List<String> failedPasswordValidationList = new ArrayList<>();
        IUser user = AccessControlAbstractFactoryMock.instance().getUser();
        user.setBannerID("B00123456");
        user.setPassword("aaaabbbb");
        IUpdatePassword updatePassword = AccessControlAbstractFactoryMock.instance().getUpdatePasswordMock();
        assertTrue(updatePassword.updatePassword(failedPasswordValidationList,user));

        failedPasswordValidationList.add("max length");
        assertFalse(updatePassword.updatePassword(failedPasswordValidationList,user));
    }
}

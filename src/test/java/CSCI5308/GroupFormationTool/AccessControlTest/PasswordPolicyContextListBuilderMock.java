package CSCI5308.GroupFormationTool.AccessControlTest;

import CSCI5308.GroupFormationTool.AccessControl.*;
import CSCI5308.GroupFormationTool.PasswordPolicy.Context;
import CSCI5308.GroupFormationTool.PasswordPolicy.IPasswordPolicyContextListBuilder;

import java.util.ArrayList;
import java.util.List;

public class PasswordPolicyContextListBuilderMock implements IPasswordPolicyContextListBuilder
{
    public List<Context> createAllPasswordPolicyList(IUser user)
    {
        List<Context> contextList = new ArrayList<>();

        Context minLengthPolicy = new Context();
        minLengthPolicy.setStrategy(AccessControlAbstractFactoryMock.instance().getMinimumLengthPolicyMock());
        contextList.add(minLengthPolicy);

        Context maxLengthPolicy = new Context();
        maxLengthPolicy.setStrategy(AccessControlAbstractFactoryMock.instance().getMaximumLengthPolicyMock());
        contextList.add(maxLengthPolicy);

        Context minLowercase = new Context();
        minLowercase.setStrategy(AccessControlAbstractFactoryMock.instance().getMinimumLowercasePolicyMock());
        contextList.add(minLowercase);

        Context minSymbolOrSpecialCharacter = new Context();
        minSymbolOrSpecialCharacter.setStrategy(AccessControlAbstractFactoryMock.instance().getMinimumSymbolPolicyMock());
        contextList.add(minSymbolOrSpecialCharacter);

        Context minUppercase = new Context();
        minUppercase.setStrategy(AccessControlAbstractFactoryMock.instance().getMinimumUppercasePolicyMock());
        contextList.add(minUppercase);

        Context passwordHistory = new Context();
        passwordHistory.setStrategy(AccessControlAbstractFactoryMock.instance().getPasswordHistoryMock(user));
        contextList.add(passwordHistory);

        Context charactersNotAllowed = new Context();
        charactersNotAllowed.setStrategy(AccessControlAbstractFactoryMock.instance().getCharactersNotAllowedPolicyMock());
        contextList.add(charactersNotAllowed);
        return contextList;
    }
}

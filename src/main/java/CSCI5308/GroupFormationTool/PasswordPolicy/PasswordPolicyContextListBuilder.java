package CSCI5308.GroupFormationTool.PasswordPolicy;

import CSCI5308.GroupFormationTool.AccessControl.IUser;

import java.util.ArrayList;
import java.util.List;

public class PasswordPolicyContextListBuilder implements IPasswordPolicyContextListBuilder
{
    @Override
    public List<Context> createAllPasswordPolicyList(IUser user)
    {
        List<Context> contextList = new ArrayList<>();

        Context minLengthPolicy = new Context();
        minLengthPolicy.setStrategy(PasswordPolicyAbstractFactory.instance().getMinimumLengthPolicy());
        contextList.add(minLengthPolicy);

        Context maxLengthPolicy = new Context();
        maxLengthPolicy.setStrategy(PasswordPolicyAbstractFactory.instance().getMaximumLengthPolicy());
        contextList.add(maxLengthPolicy);

        Context minLowercase = new Context();
        minLowercase.setStrategy(PasswordPolicyAbstractFactory.instance().getMinimumLowercasePolicy());
        contextList.add(minLowercase);

        Context minSymbolOrSpecialCharacter = new Context();
        minSymbolOrSpecialCharacter.setStrategy(PasswordPolicyAbstractFactory.instance().getMinimumSymbolPolicy());
        contextList.add(minSymbolOrSpecialCharacter);

        Context minUppercase = new Context();
        minUppercase.setStrategy(PasswordPolicyAbstractFactory.instance().getMinimumUppercasePolicy());
        contextList.add(minUppercase);

        Context passwordHistory = new Context();
        passwordHistory.setStrategy(PasswordPolicyAbstractFactory.instance().getPasswordHistory(user));
        contextList.add(passwordHistory);

        Context charactersNotAllowed = new Context();
        charactersNotAllowed.setStrategy(PasswordPolicyAbstractFactory.instance().getCharactersNotAllowedPolicy());
        contextList.add(charactersNotAllowed);
        return contextList;
    }
}

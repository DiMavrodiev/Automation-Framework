package com.automationframework.core.generator.generators.money;

import com.automationframework.core.generator.annotation.Facade;
import com.automationframework.core.generator.generators.location.CountryGenerator;
import com.automationframework.core.generator.generators.location.PostcodeGenerator;
import com.automationframework.core.generator.generators.location.StreetGenerator;
import com.automationframework.core.generator.model.location.Country;
import com.automationframework.core.generator.model.money.Card;
import com.automationframework.core.generator.model.money.CardType;
import com.automationframework.core.generator.Generator;
import com.automationframework.core.generator.generators.person.NameGenerator;

/**
 * Created By: Dimitar Mavrodiev
 * Date: 7/19/18
 */

@Facade(accessor = "card")
public class CardGenerator extends Generator<Card> {
    private StreetGenerator street;
    private NameGenerator name;
    private CardNumberGenerator card;
    private CardTypeGenerator cardType;
    private CVVGenerator cvv;
    private ExpireDateGenerator expiry;
    private IssueDateGenerator issue;
    private CountryGenerator country;
    private PostcodeGenerator postcode;

    public CardGenerator() {
        this.street = new StreetGenerator().houseNumber();
        this.name = new NameGenerator();
        this.card = new CardNumberGenerator();
        this.cvv = new CVVGenerator();
        this.expiry = new ExpireDateGenerator();
        this.issue = new IssueDateGenerator();
        this.country = new CountryGenerator();
        this.postcode = new PostcodeGenerator();
        this.cardType = new CardTypeGenerator();
    }

    @Override
    public Card gen() {
        Card card = new Card();
        CardType _cardType = cardType.common().gen();
        card.setBillingAddress(street.gen());
        card.setCardNumber(this.card.cardType(_cardType).gen());
        card.setCvv(cvv.gen());
        Country _country = country.genAsCountry();
        card.setCountry(_country.getName());
        card.setExpiryDate(expiry.gen());
        card.setIssueDate(issue.gen());
        card.setCardType(_cardType.getFullname());
        card.setName(name.gen());
        card.setPostcode(postcode.country(_country.getPrefix()).gen());
        return card;
    }

}
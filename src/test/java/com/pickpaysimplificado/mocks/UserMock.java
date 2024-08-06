package com.pickpaysimplificado.mocks;

import com.pickpaysimplificado.entities.User;
import com.pickpaysimplificado.enums.UserType;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * The type Person mock.
 */
public class UserMock {

    public static User USER = new User(
            UUID.randomUUID(),
            "User",
            "Common",
            "09837055006",
            "common@email.com",
            "password",
            new BigDecimal(10),
            UserType.COMMON
    );

    public static User USER2 = new User(
            UUID.randomUUID(),
            "User",
            "Merchant",
            "30999650068",
            "merchant@email.com",
            "password",
            new BigDecimal(10),
            UserType.MERCHANT
    );

    public static User USER_CREATION = new User(
            "User",
            "Common",
            "09837055006",
            "common@email.com",
            "password",
            new BigDecimal(10),
            UserType.COMMON
    );
}

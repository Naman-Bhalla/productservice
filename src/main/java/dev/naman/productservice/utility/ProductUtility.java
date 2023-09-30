package dev.naman.productservice.utility;

import java.util.UUID;

public class ProductUtility {

    public static UUID createUuidFromString(String idStr) {
        return UUID.fromString(idStr);
    }
}

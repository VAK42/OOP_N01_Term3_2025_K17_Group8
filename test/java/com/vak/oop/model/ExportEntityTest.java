package com.vak.oop.model;

import com.vak.oop.model.ExportEntity;
import com.vak.oop.model.ProductEntity;
import com.vak.oop.model.UserEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ExportEntityTest {

    @Test
    public void testExportEntitySettersAndGetters() {
        ExportEntity export = new ExportEntity();

        UUID epid = UUID.randomUUID();
        ProductEntity product = new ProductEntity();
        UserEntity user = new UserEntity();
        Double pdprice = 100.0;
        Integer pdquantity = 5;
       // Double pdtotalprice = 500.0;
        LocalDateTime date = LocalDateTime.now();

        // Setter methods
        export.setProduct(product);
        export.setPdprice(pdprice);
        export.setPdquantity(pdquantity);
       // export.setPdtotalprice(pdtotalprice);
        export.setDate(date);

        // Assume you have setter for user
        // Otherwise, use reflection or constructor-based injection
        try {
            java.lang.reflect.Field userField = ExportEntity.class.getDeclaredField("user");
            userField.setAccessible(true);
            userField.set(export, user);
        } catch (Exception e) {
            fail("Could not set user via reflection");
        }

        // Getter methods
        assertEquals(product, export.getProduct());
        assertEquals(pdprice, export.getPdprice());
        assertEquals(pdquantity, export.getPdquantity());
       // assertEquals(pdtotalprice, export.getPdtotalprice());
        assertEquals(date, export.getDate());
        assertEquals(user, export.getUser());
    }
}

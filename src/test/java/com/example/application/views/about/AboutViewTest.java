package com.example.application.views.about;

import com.example.application.AbstractAppTest;
import com.vaadin.flow.component.UI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.github.mvysny.kaributesting.v10.LocatorJ._assertOne;
import static org.junit.jupiter.api.Assertions.*;

class AboutViewTest extends AbstractAppTest {
    @BeforeEach
    public void login() {
        login("admin", "admin", Arrays.asList("admin"));
    }

    @Test
    public void smokeTest() {
        UI.getCurrent().navigate(AboutView.class);
        _assertOne(AboutView.class);
    }

    @Test
    public void userCantSeeThis() {
        login("user", "user", Arrays.asList("user"));
        final RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            UI.getCurrent().navigate(AboutView.class);
        });
        if (!isProductionMode()) {
            assertTrue(ex.getMessage().contains("Access is denied by annotations on the view"), ex.getMessage());
        } else {
            // no error message in production mode. This is by design: Vaadin avoids giving info to a potential attacker.
        }
    }
}

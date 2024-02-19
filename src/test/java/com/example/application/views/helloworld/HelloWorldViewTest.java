package com.example.application.views.helloworld;

import com.example.application.AbstractAppTest;
import com.github.mvysny.kaributesting.v10.NotificationsKt;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.github.mvysny.kaributesting.v10.LocatorJ.*;

public class HelloWorldViewTest extends AbstractAppTest {
    @BeforeEach
    public void login() {
        login("user", "user", Arrays.asList("user"));
    }

    @Test
    public void smokeTest() {
        UI.getCurrent().navigate(HelloWorldView.class);
        _assertOne(HelloWorldView.class);
    }

    /**
     * See {@link AbstractAppTest.MyTestConfiguration} which configures a
     * different implementation for {@link HelloService}.
     */
    @Test
    public void sayHelloWithMockBean() {
        UI.getCurrent().navigate(HelloWorldView.class);
        _setValue(_get(TextField.class, spec -> spec.withLabel("Your name")), "Martin");
        _click(_get(Button.class, spec -> spec.withText("Say hello")));
        NotificationsKt.expectNotifications("Goodbye, Martin");
    }
}

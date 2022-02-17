package com.tugrulaslan.service;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class XssSanitizerServiceTest {

    private final XssSanitizerService sanitizerService = new XssSanitizerService();

    @Test
    public void shouldRemoveOnClickAttributes() {
        //given
        String input = "<p><a href='http://h4ck3rz-53cr3t-ar3a.com' onclick='stealCookies()' onfocus='stealCookies()'>Click me</a></p>";

        //when
        String sanitize = sanitizerService.sanitize(input);

        //then
        assertThat(sanitize).isEqualTo("<p><a href=\"http://h4ck3rz-53cr3t-ar3a.com\">Click me</a></p>");
    }

    @Test
    public void shouldRemoveScriptElements() {
        //given
        String input = "<div> hello <a href=\"tugrulaslan.com\" onclick=giveMeYourData()>click me</a><script>alert('')</script></div>";

        //when
        String sanitize = sanitizerService.sanitize(input);

        //then
        assertThat(sanitize).isEqualTo("<div> hello <a href=\"tugrulaslan.com\">click me</a></div>");
    }
}
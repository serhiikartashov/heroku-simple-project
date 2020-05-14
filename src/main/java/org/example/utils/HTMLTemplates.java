package org.example.utils;

import j2html.tags.ContainerTag;
import org.apache.catalina.realm.GenericPrincipal;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Objects;

import static j2html.TagCreator.*;

public class HTMLTemplates {

    public static final ContainerTag HEAD = head(
            title("Welcome To Template Project"),
            script().withSrc("https://www.googletagmanager.com/gtag/js?id=UA-165975475-1").attr("async"),
            script("window.dataLayer = window.dataLayer || [];\n" +
                    "    function gtag(){dataLayer.push(arguments);}\n" +
                    "    gtag('js', new Date());\n" +
                    "\n" +
                    "    gtag('config', 'UA-165975475-1');"),
            meta().attr("charset", "UTF-8"),
            meta().attr("name", "UTF-8").attr("content", "width=device-width, initial-scale=1.0"),
            meta().attr("http-equiv", "X-UA-Compatible").attr("content", "ie=edge"),
            link()
                    .withRel("stylesheet")
                    .withHref("https://use.fontawesome.com/releases/v5.13.0/css/all.css"),
            link()
                    .withRel("stylesheet")
                    .withHref("https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css")
                    .attr("integrity", "sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh")
                    .attr("crossorigin", "anonymous"),
            link()
                    .withRel("stylesheet")
                    .withHref("/css/style.css"),
            script().withSrc("https://code.jquery.com/jquery-3.5.0.min.js")
                    .attr("integrity", "sha256-xNzN2a4ltkB44Mc/Jz3pT4iU1cmeR0FkXs4pru/JxaQ=")
                    .attr("crossorigin", "anonymous"),
            script().withSrc("https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js")
                    .attr("integrity", "sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo")
                    .attr("crossorigin", "anonymous"),
            script().withSrc("https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js")
                    .attr("integrity", "sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6")
                    .attr("crossorigin", "anonymous")
//            script().withSrc("https://kit.fontawesome.com/aac0f778d8.js")
//                    .attr("crossorigin", "anonymous")
    );

    public static ContainerTag NAV(HttpServletRequest request) {
        return nav(
                div(
                        a("LoopLAB").withHref("/").withClass("navbar-brand"),
                        button(
                                span().withClass("navbar-toggler-icon")
                        ).withClass("navbar-toggler").attr("data-toggle", "collapse").attr("data-target", "#navbarCollapse"),
                        div(
                                ul(
                                        li(a("Home").withHref("/home").withClass("nav-link")),
                                        li(a("Template1").withHref("/template1").withClass("nav-link")),
                                        li(a("Files").withHref("/files").withClass("nav-link")),
                                        iff(Objects.requireNonNullElse(
                                                ((GenericPrincipal) request.getSession().getAttribute("principal")),
                                                new GenericPrincipal("anonymous", "anonymous", List.of())
                                                ).hasRole("admin"),
                                                li(a("Admin").withHref("/secure").withClass("nav-link"))),
                                        iff(Objects.isNull(request.getSession().getAttribute("principal")),
                                                li(a("Login").withHref("/login").withClass("nav-link"))),
                                        iff(Objects.nonNull(request.getSession().getAttribute("principal")),
                                                li(a("Logout").withHref("/logout").withClass("nav-link")))
                                        ).withClasses("navbar-nav", "ml-auto")
                        ).withClasses("collapse", "navbar-collapse").withId("navbarCollapse")
                ).withClasses("container")
        ).withClasses("navbar", "navbar-expand-sm", "bg-dark navbar-dark", "fixed-top");
    }

    public static final ContainerTag FOOTER = footer(
            div(
                    div(
                            div(
                                    h3("LoopLAB"),
                                    p("Copyright @").with(span().withId("year")),
                                    button("Contact Us").withClasses("btn", "btn-primary")
                                            .attr("data-toggle", "modal")
                                            .attr("data-target", "#contactModal")
                            ).withClasses("col", "text-center", "py-4")
                    ).withClasses("row")
            ).withClasses("container")
    ).withId("main-footer").withClasses("bg-dark");

    public static ContainerTag LOGINFORM(HttpServletRequest request) {
        return header(
                div(
                        div(
                                div(
                                        h2("Hello, please log in:"), br(), br(),
                                        form(
                                                p(), strong("Please Enter Your User Name: "),
                                                input().withType("text").withName("username").attr("size", 25),
                                                p(), p(), strong("Please Enter Your Password: "),
                                                input().withType("password").withName("passwd").attr("size", 15),
                                                p(),p(),
                                                input().withType("submit").withValue("Submit"),
                                                // reset doesn't work for now
                                                input().withType("reset").withValue("Reset")
                                        ).withAction("login").withMethod("post")
                                ).withClass("row")
                        ).withClasses("home-inner", "container")
                ).withClass("dark-overlay")
        ).withId("home-section");
    }
}

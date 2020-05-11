package org.example.controller.templates;

import j2html.tags.ContainerTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static j2html.TagCreator.*;
import static org.example.utils.HTMLTemplates.*;

@WebServlet(name = "template1", urlPatterns = "/template1", loadOnStartup = 1)
public class Template1Controller extends HttpServlet {

    private static Logger log = LoggerFactory.getLogger(Template1Controller.class);

    /**
     * <script src="https://kit.fontawesome.com/aac0f778d8.js" crossorigin="anonymous"></script>
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.info("Visited Welcome page!");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        ContainerTag homeHtml = html(HEAD,
                body(
                        NAV(request),
                        header(
                                div(
                                        div(
                                                div(
                                                        div(
                                                                h1(text("Build "), strong("social profiles"), text(" and gain revenue "), strong("profits")).withClass("display-4"),
                                                                div(
                                                                        div(i().withClasses("fas", "fa-check", "fa-2x")).withClasses("p-4", "align-self-start"),
                                                                        div("Lorem ipsum dolor sit, amet consectetur adipisicing elit. Sed, tempore iusto in minima facere dolorem!").withClasses("p-4", "align-self-end")
                                                                ).withClass("d-flex"),
                                                                div(
                                                                        div(i().withClasses("fas", "fa-check", "fa-2x")).withClasses("p-4", "align-self-start"),
                                                                        div("Lorem ipsum dolor sit, amet consectetur adipisicing elit. Sed, tempore iusto in minima facere dolorem!").withClasses("p-4", "align-self-end")
                                                                ).withClass("d-flex"),
                                                                div(
                                                                        div(i().withClasses("fas", "fa-check", "fa-2x")).withClasses("p-4", "align-self-start"),
                                                                        div("Lorem ipsum dolor sit, amet consectetur adipisicing elit. Sed, tempore iusto in minima facere dolorem!").withClasses("p-4", "align-self-end")
                                                                ).withClass("d-flex")
                                                        ).withClasses("col-lg-8", "d-none", "d-lg-block"),
                                                        div(
                                                                div(
                                                                        div(
                                                                                h3("Sign Up Today"),
                                                                                p("Please fill out this form to register"),
                                                                                form(
                                                                                        div(input().withType("text").withClasses("form-control form-control-lg").withPlaceholder("Username")).withClass("form-group"),
                                                                                        div(input().withType("email").withClasses("form-control form-control-lg").withPlaceholder("Email")).withClass("form-group"),
                                                                                        div(input().withType("password").withClasses("form-control form-control-lg").withPlaceholder("Password")).withClass("form-group"),
                                                                                        div(input().withType("password").withClasses("form-control form-control-lg").withPlaceholder("Confirm Password")).withClass("form-group")
                                                                                )
                                                                        ).withClass("card-body")
                                                                ).withClasses("card bg-primary text-center card-form")
                                                        ).withClass("col-lg-4")
                                                ).withClass("row")
                                        ).withClasses("home-inner", "container")
                                ).withClass("dark-overlay")
                        ).withId("home-section"),
                        FOOTER
                )
        );
        response.getWriter().println(homeHtml.render());
    }

    public static final ContainerTag HEAD = head(
            title("Welcome Java 9 Group"),
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

//    public static final ContainerTag NAV = nav(
//            div(
//                    a("LoopLAB").withHref("/").withClass("navbar-brand"),
//                    button(
//                            span().withClass("navbar-toggler-icon")
//                    ).withClass("navbar-toggler").attr("data-toggle", "collapse").attr("data-target", "#navbarCollapse"),
//                    div(
//                            ul(
//                                    li(a("Home").withHref("#home").withClass("nav-link")),
//                                    li(a("Explore").withHref("#explore-head-section").withClass("nav-link")),
//                                    li(a("Create").withHref("#create-head-section").withClass("nav-link")),
//                                    li(a("Share").withHref("#share-head-section").withClass("nav-link"))
//                            ).withClasses("navbar-nav", "ml-auto")
//                    ).withClasses("collapse", "navbar-collapse").withId("navbarCollapse")
//            ).withClasses("container")
//    ).withClasses("navbar", "navbar-expand-sm", "bg-dark navbar-dark", "fixed-top");

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
}
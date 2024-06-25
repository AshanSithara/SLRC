package lk.rupavahini.PPUManagement.webconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Configuration
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
//        HttpSession session = request.getSession();


        String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
//            session.invalidate();
            return;
        }
        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(Authentication authentication) {
        String url = "/login?error=true";

        // Fetch the roles from Authentication object
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles = new ArrayList<String>();
        for (GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
        }
        // check user role and decide the redirect URL
        if (roles.contains("Admin")) {
            url = "/mainWindow";
        } else if (roles.contains("Employee")) {
            url = "/mainWindow";
        } else if (roles.contains("Producer")) {
            url = "/mainWindow";
        }else if (roles.contains("Sponsor")) {
            url = "/mainWindow";
        }
        else if (roles.contains("Editor")) {
            url = "/mainWindow";
        }
<<<<<<< HEAD
        else if (roles.contains("Booking Officer")) {
            url = "/mainWindow";
        }
        else if (roles.contains("Casset library admin")) {
            url = "/mainWindow";
        }
        else if (roles.contains("Engineer")) {
            url = "/mainWindow";
        }
        else if (roles.contains("Camera man")) {
            url = "/mainWindow";
        }

=======
>>>>>>> 4609734 (Initial commit)

        return url;
    }
}

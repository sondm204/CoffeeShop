package com.swp.coffeeshop.filter;

import com.swp.coffeeshop.models.GuestUser;
import com.swp.coffeeshop.models.User;
import com.swp.coffeeshop.services.Cart.CartService;
import com.swp.coffeeshop.services.User.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class CustomFilter extends OncePerRequestFilter {
    UserService userService;
    CartService cartService;

    public CustomFilter(UserService userService, CartService cartService) {
        this.userService = userService;
        this.cartService = cartService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Object object = session.getAttribute("username");
        User user = null;
        if (object != null) {
            String username = (String) object;
            user = userService.findByUsername(username);
            session.setAttribute("user", user);
            session.setAttribute("cartSize", cartService.getAllCartByUserId(user.getId()).size());
        } else {
            saveGuestUser(request, response);
        }

        filterChain.doFilter(request, response);
    }

    private void saveGuestUser(HttpServletRequest request, HttpServletResponse response) {
        String trackingId = "";
        Cookie[] cookie = request.getCookies();
        if (cookie != null) {
            for (Cookie c : cookie) {
                if (c.getName().equals("TrackingID")) {
                    trackingId = c.getValue();
                }
            }
        }

        if (trackingId.isEmpty()) {
            trackingId = java.util.UUID.randomUUID().toString();
            Cookie cookieTrackingId = new Cookie("TrackingID", trackingId);
            cookieTrackingId.setMaxAge(60 * 60 * 24 * 30);
            cookieTrackingId.setPath("/");
            response.addCookie(cookieTrackingId);
            userService.saveGuestUser(trackingId);
        }
        GuestUser guest = userService.getGuestUserByTrackingId(trackingId);
        request.getSession().setAttribute("guest", guest);
        request.getSession().setAttribute("cartSize", cartService.getAllCartByTrackingId(trackingId).size());
    }
}

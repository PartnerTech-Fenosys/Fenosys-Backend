/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.tools;

import javax.servlet.http.HttpServletRequest;

public class UtilityFenosys {

    public static String GenerarUrl(HttpServletRequest request) {

        String url = request.getRequestURL().toString();

        return url.replace(request.getServletPath(), "");
    }
}

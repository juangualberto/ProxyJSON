/*
 * This software is GNU.
 * Author: jgutierrez AT iesvirgendelcarmen.com
 */
package com.iesvdc.acceso.jsonproxy;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.*; 


/**
 * Este Servlet es un sencillo proxy JSON (jsonp) que podemos usar, por ejemplo,
 * para convertir canales RSS en JSON.
 * @author juangu
 */
public class RssServlet extends HttpServlet {
    /**
     * Este sencillo método lee un InputStream a un String usando un Scanner
     */
    String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // processRequest(request, response);
        String jsonString;
        String jsonPrettyPrintString = "{\"error\":\"no he podido procesar el JSON correctamente\"}";
        
        try {
            if (request.getParameter("url") != null) {
                URL xmlURL = new URL(request.getParameter("url"));
                jsonString = convertStreamToString(xmlURL.openStream());
                JSONObject xmlJSONObj = XML.toJSONObject(jsonString);
                jsonPrettyPrintString = xmlJSONObj.toString(4);
            } else {
                jsonPrettyPrintString = "{\"error\":\"debe usar el parámetro con nombre *url* en la consulta\"}";
            }
        } catch (JSONException je) {
            System.out.println(je.toString());
            jsonPrettyPrintString = "{\"error\":\"no he podido procesar el JSON correctamente\"}";
        } finally {
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.println(jsonPrettyPrintString);
        }
    }
}

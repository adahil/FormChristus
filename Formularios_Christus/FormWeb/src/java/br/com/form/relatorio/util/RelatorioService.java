/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.form.relatorio.util;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Classe do Projeto Guardiao - Criado em 17/05/2013 -
 *
 * @author Gilmário
 */
@WebServlet(name = "RelatorioServlet", urlPatterns = {"/RelatorioServlet.pdf"})
public class RelatorioService extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("application/pdf", "Content-Type");
        response.setContentType("application/pdf");
        ServletOutputStream ouputStream = null;
        try {
            HttpSession session = request.getSession(true);
            byte[] bytes = (byte[]) session.getAttribute(RelatorioSession.CHAVE_RELATORIO);
            response.setContentLength(bytes.length);
            ouputStream = response.getOutputStream();
            ouputStream.write(bytes, 0, bytes.length);
            ouputStream.flush();
            session.removeAttribute(RelatorioSession.CHAVE_RELATORIO);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ouputStream != null) {
                ouputStream.close();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

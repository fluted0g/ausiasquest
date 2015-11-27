/*
 * Copyright (c) 2015 by Rafael Angel Aznar Aparici (rafaaznar at gmail dot com)
 * 
 * openAUSIAS: The stunning micro-library that helps you to develop easily 
 *             AJAX web applications by using Java and jQuery
 * openAUSIAS is distributed under the MIT License (MIT)
 * Sources at https://github.com/rafaelaznar/openAUSIAS
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package net.daw.service.specific.implementation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.daw.service.generic.implementation.TableServiceGenImpl;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import net.daw.bean.specific.implementation.DocumentoBean;
import net.daw.bean.specific.implementation.CuestionarioBean;
import net.daw.connection.implementation.BoneConnectionPoolImpl;
import net.daw.dao.specific.implementation.DocumentoDao;
import net.daw.helper.statics.AppConfigurationHelper;
import net.daw.helper.statics.ParameterCook;

public class DocumentoService extends TableServiceGenImpl {

    public DocumentoService(HttpServletRequest request) {
        super(request);
    }

    public String getcontenido(Integer id) throws Exception {
        String data;
        Connection oConnection = null;
        DocumentoBean oDocumentoBean;
        try {
            oConnection = new BoneConnectionPoolImpl().newConnection();
            oDocumentoBean = new DocumentoBean();
            oDocumentoBean.setId(id);
            DocumentoDao oDocumentoDao = new DocumentoDao(oConnection);
            oDocumentoBean = oDocumentoDao.get(oDocumentoBean, AppConfigurationHelper.getJsonDepth());

        } catch (Exception e) {
            throw new ServletException("GetContenido: View Error: " + e.getMessage());
        }
        oConnection.close();
        return "{\"data\":\"" + oDocumentoBean.getContenido() + "\"}";
    }
    
    public String getcuestionario() throws Exception {

        Connection oConnection = new BoneConnectionPoolImpl().newConnection();

        DocumentoDao oDocumentoDao = new DocumentoDao(oConnection);
        ArrayList<CuestionarioBean> alDocumentoBean = new ArrayList();
        int id = ParameterCook.prepareId(oRequest);

        
        DocumentoBean oDocumentoBean = new DocumentoBean();
        oDocumentoBean.setId(id);
        
        alDocumentoBean = oDocumentoDao.getCuestionario(oDocumentoBean);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("dd/MM/yyyy");
        Gson gson = gsonBuilder.create();
        String data = "{\"status\":200,\"message\":" + gson.toJson(alDocumentoBean) + "}";

        return data;
    }
}

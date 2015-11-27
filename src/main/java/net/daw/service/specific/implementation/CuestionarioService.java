/*
 * Copyright (c) 2015 by Rafael Angel Aznar Aparici (rafaaznar at gmail dot com)
 * 
 * openAUSIAS: The stunning micro-library that helps you to develop easily 
 * AJAX web applications by using Java and jQuery
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
 * 
 */
package net.daw.service.specific.implementation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import net.daw.bean.specific.implementation.CuestionarioBean;
import net.daw.bean.specific.implementation.DocumentoBean;
import net.daw.connection.implementation.BoneConnectionPoolImpl;
import net.daw.dao.specific.implementation.CuestionarioDao;
import net.daw.dao.specific.implementation.DocumentoDao;
import net.daw.helper.statics.ExceptionBooster;
import net.daw.helper.statics.FilterBeanHelper;
import net.daw.helper.statics.JsonMessage;
import net.daw.helper.statics.ParameterCook;
import net.daw.service.generic.implementation.TableServiceGenImpl;

/**
 *
 * @author a047087313b
 */
public class CuestionarioService extends TableServiceGenImpl {

    public CuestionarioService(HttpServletRequest request) {
        super(request);
    }
    
    public String get() throws Exception {

        Connection oConnection = new BoneConnectionPoolImpl().newConnection();
        CuestionarioDao oCuestionarioDao = new CuestionarioDao(oConnection);
        
        int id = ParameterCook.prepareId(oRequest);
        
        DocumentoBean oDocumentoBean = new DocumentoBean();
        oDocumentoBean.setId(id);
        ArrayList<CuestionarioBean> alCuestionarioBean = new ArrayList();
        alCuestionarioBean = oCuestionarioDao.getCuestionario(oDocumentoBean);
        
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("dd/MM/yyyy");
        Gson gson = gsonBuilder.create();
        String data = "{\"status\":200,\"message\":" + gson.toJson(alCuestionarioBean) + "}";

        return data;

    }
    
    public String getall() throws Exception{
    
        Connection oConnection = new BoneConnectionPoolImpl().newConnection();
        CuestionarioDao oCuestionarioDao = new CuestionarioDao(oConnection);
        ArrayList<CuestionarioBean> alCuestionarioBean = new ArrayList<CuestionarioBean>();
        ArrayList<FilterBeanHelper> alFilterBeanHelper = ParameterCook.prepareFilter(oRequest);
        HashMap<String, String> hmOrder = ParameterCook.prepareOrder(oRequest);
        
        alCuestionarioBean = oCuestionarioDao.getsolocuestionario(alFilterBeanHelper, hmOrder);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("dd/MM/yyyy");
        Gson gson = gsonBuilder.create();
        String data = "{\"status\":200,\"message\":" + gson.toJson(alCuestionarioBean) + "}";
        return data;
    }
    
    @Override
    public String getaggregateviewall() throws Exception {
        if (this.checkpermission("getaggregateviewall")) {
            String data = null;
            try {
                String meta = this.getmetainformation();
                String all = this.getall();
        
                data = "{"
                        + "\"meta\":" + meta
                        + ",\"page\":" + all
             
                        + "}";
                data = JsonMessage.getJson("200", data);
            } catch (Exception ex) {
                ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getAggregateViewAll ERROR: " + ex.getMessage()));
            }
            return data;
        } else {
            return JsonMessage.getJsonMsg("401", "Unauthorized");
        }
    }
}
 //          + ",\"registers\":" + registers
//        String registers = this.getcount();
    


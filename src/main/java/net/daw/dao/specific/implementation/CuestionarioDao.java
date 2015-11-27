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
package net.daw.dao.specific.implementation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import net.daw.bean.group.GroupBeanImpl;
import net.daw.bean.specific.implementation.CuestionarioBean;
import net.daw.bean.specific.implementation.DocumentoBean;
import net.daw.bean.specific.implementation.OpcionBean;
import net.daw.bean.specific.implementation.PreguntaBean;
import net.daw.bean.specific.implementation.TipodocumentoBean;
import net.daw.bean.specific.implementation.TipousuarioBean;
import net.daw.dao.generic.implementation.TableDaoGenImpl;
import net.daw.data.specific.implementation.MysqlDataSpImpl;
import net.daw.helper.statics.FilterBeanHelper;

/**
 *
 * @author juliomiguel
 */
public class CuestionarioDao extends TableDaoGenImpl<CuestionarioBean> {

    public CuestionarioDao(Connection pooledConnection) throws Exception {
        super(pooledConnection);
    }

    public ArrayList<CuestionarioBean> getCuestionario(DocumentoBean oDocumentoBean) throws Exception {

        //Se crea un arraylist de CuestionarioBean
        ArrayList<CuestionarioBean> alCuestionario = new ArrayList();

        //Se pide la conexión y se le asigna la operación a ResultSet (getAllSql)
        MysqlDataSpImpl oMysql = new MysqlDataSpImpl(oConnection);
        if (oDocumentoBean.getId() > 0) {
            if (oMysql.existsOne("select * from documento", oDocumentoBean.getId())) {
                ResultSet result = oMysql.getAllSql("select * from documento");

                //Se recorre la consulta
                if (result != null) {
                    while (result.next()) {
                        if (result.getInt("id") == oDocumentoBean.getId()) {

                            //Se crea un ResultSet para la pregunta y se recorre
                            ResultSet resultpregunta = oMysql.getAllSql("select * from pregunta");

                            while (resultpregunta.next()) {

                                if (result.getInt("id") == resultpregunta.getInt("id_documento")) {
                                    //Se crean instancias de PreguntaDao y PreguntaBean y se asignan parámetros    
                                    PreguntaDao oPreguntaDao = new PreguntaDao(oConnection);
                                    PreguntaBean oPreguntaBean = new PreguntaBean();
                                    oPreguntaBean.setId(resultpregunta.getInt("id"));

                                    oPreguntaBean = oPreguntaDao.get(oPreguntaBean, 1);
                                    GroupBeanImpl oGroupBeanImpl = new GroupBeanImpl();
                                    oGroupBeanImpl.setBean(oPreguntaBean);
                                    oGroupBeanImpl.setMeta(oPreguntaDao.getmetainformation());
                                    //Se crea un ResultSet para la opción y se recorre
                                    ResultSet resultopcion = oMysql.getAllSql("select * from opcion");
                                    while (resultopcion.next()) {
                                        if (resultpregunta.getInt("id") == resultopcion.getInt("id_pregunta")) {
                                            //Se crea instancia de CuestionarioBean y se asignan parámetros    
                                            CuestionarioBean oCuestionarioBean = new CuestionarioBean();
                                            oCuestionarioBean.setId_documento(result.getInt("id"));
                                            oCuestionarioBean.setTitulo(result.getString("titulo"));

                                            oCuestionarioBean.setObj_pregunta(oGroupBeanImpl);
                                            oCuestionarioBean.setId_pregunta(resultopcion.getInt("id_pregunta"));
                                            oCuestionarioBean.setDescripcionOpcion(resultopcion.getString("descripcion"));
                                            oCuestionarioBean.setDescripcionPregunta(resultpregunta.getString("descripcion"));
                                            OpcionDao oOpcionDao = new OpcionDao(oConnection);
                                            OpcionBean oOpcionBean = new OpcionBean();
                                            oOpcionBean.setId(resultopcion.getInt("id"));
                                            oOpcionBean = oOpcionDao.get(oOpcionBean, 1);
                                            oCuestionarioBean.setId_opcion(resultopcion.getInt("id"));
                                            GroupBeanImpl oGroupBeanImplOpcion = new GroupBeanImpl();
                                            oGroupBeanImplOpcion.setBean(oOpcionBean);
                                            oGroupBeanImplOpcion.setMeta(oOpcionDao.getmetainformation());
                                            oCuestionarioBean.setObj_opcion(oGroupBeanImplOpcion);
                                            alCuestionario.add(oCuestionarioBean);

                                        }

                                    }

                                }

                            }

                        }
                    }
                }
            }

        }
        return alCuestionario;
    }

    public ArrayList<CuestionarioBean> getsolocuestionario(ArrayList<FilterBeanHelper> alFilter, HashMap<String, String> hmOrder) throws Exception {

        MysqlDataSpImpl oMysql = new MysqlDataSpImpl(oConnection);
        ArrayList<CuestionarioBean> alCuestionario = new ArrayList<>();

        ResultSet resultdocumento = oMysql.getAllSql("select * from documento");
        if (resultdocumento != null) {
           
            while (resultdocumento.next()) {                 
                ResultSet resultpregunta = oMysql.getAllSql("select * from pregunta");
                int i = 0;
                while (resultpregunta.next()) {
                    if (resultpregunta.getInt("id_documento") == resultdocumento.getInt("id")) {
                        
                        if (i == 0) {
                          
                            i=0;
                            PreguntaDao oPreguntaDao = new PreguntaDao(oConnection);
                            PreguntaBean oPreguntaBean = new PreguntaBean();
                            oPreguntaBean.setId(resultpregunta.getInt("id"));

                            oPreguntaBean = oPreguntaDao.get(oPreguntaBean, 1);
                            GroupBeanImpl oGroupBeanImpl = new GroupBeanImpl();
                            oGroupBeanImpl.setBean(oPreguntaBean);
                            oGroupBeanImpl.setMeta(oPreguntaDao.getmetainformation());

                            ResultSet resultopcion = oMysql.getAllSql("select * from opcion");
                            int j = 0;
                            while (resultopcion.next()) {
                                if (resultopcion.getInt("id_pregunta") == resultpregunta.getInt("id")) {
                                    if (j == 0) {
                                      j = 0;
                                        CuestionarioBean oCuestionarioBean = new CuestionarioBean();
                                        oCuestionarioBean.setId_documento(resultdocumento.getInt("id"));
                                        oCuestionarioBean.setTitulo(resultdocumento.getString("titulo"));

                                        oCuestionarioBean.setObj_pregunta(oGroupBeanImpl);
                                        oCuestionarioBean.setId_pregunta(resultopcion.getInt("id_pregunta"));
                                        oCuestionarioBean.setDescripcionOpcion(resultopcion.getString("descripcion"));
                                        oCuestionarioBean.setDescripcionPregunta(resultpregunta.getString("descripcion"));
                                        OpcionDao oOpcionDao = new OpcionDao(oConnection);
                                        OpcionBean oOpcionBean = new OpcionBean();
                                        oOpcionBean.setId(resultopcion.getInt("id"));
                                        oOpcionBean = oOpcionDao.get(oOpcionBean, 1);
                                        oCuestionarioBean.setId_opcion(resultopcion.getInt("id"));
                                        GroupBeanImpl oGroupBeanImplOpcion = new GroupBeanImpl();
                                        oGroupBeanImplOpcion.setBean(oOpcionBean);
                                        oGroupBeanImplOpcion.setMeta(oOpcionDao.getmetainformation());
                                        oCuestionarioBean.setObj_opcion(oGroupBeanImplOpcion);
                                        alCuestionario.add(oCuestionarioBean);
                                    }
                                   j++;
                                }
                            }
                        }
                        i++;
                    }
                }
            }

        }

        return alCuestionario;
    }
}

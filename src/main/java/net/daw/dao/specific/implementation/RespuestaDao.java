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

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import net.daw.bean.group.GroupBeanImpl;
import net.daw.bean.meta.MetaBeanGenImpl;
import net.daw.bean.specific.implementation.DocumentoBean;
import net.daw.bean.specific.implementation.OpcionBean;
import net.daw.bean.specific.implementation.PreguntaBean;
import net.daw.bean.specific.implementation.RespuestaBean;
import net.daw.bean.specific.implementation.UsuarioBean;
import net.daw.dao.generic.implementation.TableDaoGenImpl;
import net.daw.data.specific.implementation.MysqlDataSpImpl;
import net.daw.helper.annotations.MethodMetaInformation;
import net.daw.helper.statics.ExceptionBooster;
import net.daw.helper.statics.FilterBeanHelper;
import net.daw.helper.statics.SqlBuilder;

/**
 *
 * @author juliomiguel
 */
public class RespuestaDao extends TableDaoGenImpl<RespuestaBean> {

    public RespuestaDao(Connection pooledConnection) throws Exception {
        super(pooledConnection);
    }

    @Override
    public RespuestaBean get(RespuestaBean oRespuestaBean, Integer expand) throws Exception {
        MysqlDataSpImpl oMysql = new MysqlDataSpImpl(oConnection);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (oRespuestaBean.getId() > 0) {

            if (oMysql.existsOne(strSqlSelectDataOrigin, oRespuestaBean.getId())) {
                String strIdDoc = oMysql.getOne(strSqlSelectDataOrigin, "id_opcion", oRespuestaBean.getId());
                oRespuestaBean.setId_opcion(Integer.parseInt(strIdDoc));

                OpcionDao oOpcionDao = new OpcionDao(oConnection);

                OpcionBean oOpcionBean = new OpcionBean();
                oOpcionBean.setId(oRespuestaBean.getId_opcion());
                oOpcionBean = oOpcionDao.get(oOpcionBean, 1);

                GroupBeanImpl oGroupBeanImpl = new GroupBeanImpl();
                oGroupBeanImpl.setBean(oOpcionBean);
                oGroupBeanImpl.setMeta(oOpcionDao.getmetainformation());
                oRespuestaBean.setObj_opcion(oGroupBeanImpl);

                //Usuario
                String strIdUsu = oMysql.getOne(strSqlSelectDataOrigin, "id_usuario", oRespuestaBean.getId());
                oRespuestaBean.setId_usuario(Integer.parseInt(strIdUsu));

                UsuarioDao oUsuarioDao = new UsuarioDao(oConnection);

                UsuarioBean oUsuarioBean = new UsuarioBean();
                oUsuarioBean.setId(oRespuestaBean.getId());
                oUsuarioBean = oUsuarioDao.get(oUsuarioBean, 1);

                GroupBeanImpl oGroupBeanImplUsu = new GroupBeanImpl();
                oGroupBeanImplUsu.setBean(oUsuarioBean);
                oGroupBeanImplUsu.setMeta(oUsuarioDao.getmetainformation());
                oRespuestaBean.setObj_usuario(oGroupBeanImplUsu);

                //hora
                String strHora = oMysql.getOne(strSqlSelectDataOrigin, "fechaHoraAlta", oRespuestaBean.getId());
                Date date = formatter.parse(strHora);
                oRespuestaBean.setFechaHoraAlta(date);
            }
        }
        try {

            return oRespuestaBean;
        } catch (Exception e) {
            throw new Exception(this.getClass().getName() + ":get ERROR: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<RespuestaBean> getAll(ArrayList<FilterBeanHelper> alFilter, HashMap<String, String> hmOrder) throws Exception {

        MysqlDataSpImpl oMysql = new MysqlDataSpImpl(oConnection);

        ArrayList<RespuestaBean> alRespuestaBean = new ArrayList<>();
        strSqlSelectDataOrigin += SqlBuilder.buildSqlWhere(alFilter);
        strSqlSelectDataOrigin += SqlBuilder.buildSqlOrder(hmOrder);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            ResultSet result = oMysql.getAllSql(strSqlSelectDataOrigin);
            if (result != null) {
                while (result.next()) {
                    RespuestaBean oRespuestaBean = new RespuestaBean();
                    oRespuestaBean.setId(result.getInt("id"));
                    oRespuestaBean.setId_opcion(result.getInt("id_opcion"));
                    oRespuestaBean.setId_usuario(result.getInt("id_usuario"));

                    OpcionDao oOpcionDao = new OpcionDao(oConnection);

                    OpcionBean oOpcionBean = new OpcionBean();
                    oOpcionBean.setId(oRespuestaBean.getId_opcion());
                    oOpcionBean = oOpcionDao.get(oOpcionBean, 1);

                    GroupBeanImpl oGroupBeanImpl = new GroupBeanImpl();
                    oGroupBeanImpl.setBean(oOpcionBean);
                    oGroupBeanImpl.setMeta(oOpcionDao.getmetainformation());
                    oRespuestaBean.setObj_opcion(oGroupBeanImpl);

                    //Usuario
                    UsuarioDao oUsuarioDao = new UsuarioDao(oConnection);

                    UsuarioBean oUsuarioBean = new UsuarioBean();
                    oUsuarioBean.setId(oRespuestaBean.getId());
                    oUsuarioBean = oUsuarioDao.get(oUsuarioBean, 1);

                    GroupBeanImpl oGroupBeanImplUsu = new GroupBeanImpl();
                    oGroupBeanImplUsu.setBean(oUsuarioBean);
                    oGroupBeanImplUsu.setMeta(oUsuarioDao.getmetainformation());
                    oRespuestaBean.setObj_usuario(oGroupBeanImplUsu);

                    //hora
                    String strHora = oMysql.getOne(strSqlSelectDataOrigin, "fechaHoraAlta", oRespuestaBean.getId());
                    if (strHora != null) {
                        Date date = formatter.parse(strHora);
                        oRespuestaBean.setFechaHoraAlta(date);
                    } else if (strHora == null) {
                        Date date = new Date();
                        oRespuestaBean.setFechaHoraAlta(date);
                    }

                    alRespuestaBean.add(oRespuestaBean);
                }
            }

        } catch (Exception ex) {
            throw new Exception(this.getClass().getName() + ":get ERROR: " + ex.getMessage());
        }

        return alRespuestaBean;
    }

    @Override
    public ArrayList<RespuestaBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBeanHelper> alFilter, HashMap<String, String> hmOrder) throws Exception {

        //Crear la conexión SQL
        MysqlDataSpImpl oMySQL = new MysqlDataSpImpl(oConnection);

        strSqlSelectDataOrigin += SqlBuilder.buildSqlWhere(alFilter);
        strSqlSelectDataOrigin += SqlBuilder.buildSqlOrder(hmOrder);

        //Crear una variable result que provenga de oMySQL
        ResultSet result = oMySQL.getPage(strSqlSelectDataOrigin, intRegsPerPag, intPage);

        //Crear un arraylist de RespuestaBean
        ArrayList<RespuestaBean> alRespuestaBean = new ArrayList<>();

        //Recorrer los elementos de result
        while (result.next()) {
            //Crear variable local para asignar los sets de result
            RespuestaBean oRespuestaBean = new RespuestaBean();

            oRespuestaBean.setId(result.getInt("id"));
            oRespuestaBean.setId_opcion(result.getInt("id_opcion"));
            oRespuestaBean.setId_usuario(result.getInt("id_usuario"));

            //crear un dao de opcion y de usuario
            OpcionDao oOpcionDao = new OpcionDao(oConnection);
            UsuarioDao oUsuarioDao = new UsuarioDao(oConnection);

            //un pojo de documento con elid de documento
            OpcionBean oOpcionBean = new OpcionBean();
            oOpcionBean.setId(result.getInt("id_opcion"));
            UsuarioBean oUsuarioBean = new UsuarioBean();
            oUsuarioBean.setId(result.getInt("id_usuario"));

            oOpcionBean = oOpcionDao.get(oOpcionBean, 1);
            oUsuarioBean = oUsuarioDao.get(oUsuarioBean, 1);
            //rellenar el pojo de documento con el dao
            //meter el pojo relleno a la pregunta
            GroupBeanImpl oGroupBeanImpl = new GroupBeanImpl();
            oGroupBeanImpl.setBean(oOpcionBean);
            oGroupBeanImpl.setBean(oUsuarioBean);

            oGroupBeanImpl.setMeta(oOpcionDao.getmetainformation());
            oRespuestaBean.setObj_opcion(oGroupBeanImpl);

            oGroupBeanImpl.setMeta(oUsuarioDao.getmetainformation());
            oRespuestaBean.setObj_usuario(oGroupBeanImpl);

            oRespuestaBean.setFechaHoraAlta(result.getDate("fechaHoraAlta"));
            alRespuestaBean.add(oRespuestaBean);

            //Asignar los datos de la variable local al array de PreguntaBean
        }
        //Devolver el array de PreguntaBean
        return alRespuestaBean;

    }

    @Override
    public int getPages(int intRegsPerPag, ArrayList<FilterBeanHelper> alFilter) throws Exception {
        strSqlSelectDataOrigin += SqlBuilder.buildSqlWhere(alFilter);
        return oMysql.getPages(strSqlSelectDataOrigin, intRegsPerPag);
    }

    @Override
    public RespuestaBean set(RespuestaBean oRespuestaBean) throws Exception {

        MysqlDataSpImpl oMysql = new MysqlDataSpImpl(oConnection);

        try {
            if (oRespuestaBean.getId() == 0) {
                oRespuestaBean.setId(oMysql.insertOne(strTableOrigin));
            }
            oMysql.updateOne(oRespuestaBean.getId(), strTableOrigin, "id_opcion", oRespuestaBean.getId_opcion().toString());
            oMysql.updateOne(oRespuestaBean.getId(), strTableOrigin, "id_usuario", oRespuestaBean.getId_usuario().toString());
            //se pone la fecha y hora del edit 
            Date date = new Date();
            //BUG FECHAS SOLUCIONADO
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            oRespuestaBean.setFechaHoraAlta(date);
            String str_fecha = formatter.format(oRespuestaBean.getFechaHoraAlta());
            oMysql.updateOne(oRespuestaBean.getId(), strTableOrigin, "fechaHoraAlta", str_fecha);
        } catch (Exception e) {
            throw new Exception(this.getClass().getName() + ".set: Error: " + e.getMessage());
        }
        return oRespuestaBean;
    }

    @Override
    public int remove(RespuestaBean oRespuestaBean) throws Exception {

        int result = 0;
        try {
            result = oMysql.removeOne(oRespuestaBean.getId(), strTableOrigin);
        } catch (Exception e) {
            throw new Exception(this.getClass().getName() + ".remove: Error: " + e.getMessage());
        }
        return result;
    }

    @Override
    public ArrayList<MetaBeanGenImpl> getmetainformation() throws Exception {
        ArrayList<MetaBeanGenImpl> alVector = null;
        try {
            Class oRespuestaBeanClass = RespuestaBean.class;
            alVector = new ArrayList<>();
            for (Field field : oRespuestaBeanClass.getDeclaredFields()) {
                Annotation[] fieldAnnotations = field.getDeclaredAnnotations();
                for (Integer i = 0; i < fieldAnnotations.length; i++) {
                    if (fieldAnnotations[i].annotationType().equals(MethodMetaInformation.class)) {
                        MethodMetaInformation fieldAnnotation = (MethodMetaInformation) fieldAnnotations[i];
                        if (!fieldAnnotation.IsIdForeignKey()) {
                            MetaBeanGenImpl oMeta = new MetaBeanGenImpl();
                            oMeta.setName(field.getName());
                            oMeta.setDefaultValue(fieldAnnotation.DefaultValue());
                            oMeta.setDescription(fieldAnnotation.Description());
                            oMeta.setIsId(fieldAnnotation.IsId());
                            oMeta.setIsObjForeignKey(fieldAnnotation.IsObjForeignKey());
                            oMeta.setMaxDecimal(fieldAnnotation.MaxDecimal());
                            oMeta.setMaxInteger(fieldAnnotation.MaxInteger());
                            oMeta.setMaxLength(fieldAnnotation.MaxLength());
                            oMeta.setMinLength(fieldAnnotation.MinLength());
                            oMeta.setMyIdName(fieldAnnotation.MyIdName());
                            oMeta.setReferencesTable(fieldAnnotation.ReferencesTable());
                            oMeta.setIsForeignKeyDescriptor(fieldAnnotation.IsForeignKeyDescriptor());
                            oMeta.setShortName(fieldAnnotation.ShortName());
                            oMeta.setType(fieldAnnotation.Type());
                            oMeta.setUltraShortName(fieldAnnotation.UltraShortName());
                            alVector.add(oMeta);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ExceptionBooster.boost(new Exception(this.getClass().getName() + ":getmetainformation ERROR: " + ex.getMessage()));
        }
        return alVector;
    }

}

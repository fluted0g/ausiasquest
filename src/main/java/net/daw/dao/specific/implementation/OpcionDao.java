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
import java.util.ArrayList;
import java.util.HashMap;
import net.daw.bean.group.GroupBeanImpl;
import net.daw.bean.meta.MetaBeanGenImpl;
import net.daw.bean.specific.implementation.OpcionBean;
import net.daw.bean.specific.implementation.PreguntaBean;
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
public class OpcionDao extends TableDaoGenImpl<OpcionBean> {

    public OpcionDao(Connection pooledConnection) throws Exception {
        super(pooledConnection);
    }

    @Override
    public OpcionBean get(OpcionBean oOpcionBean, Integer expand) throws Exception {
        MysqlDataSpImpl oMysql = new MysqlDataSpImpl(oConnection);
        if (oOpcionBean.getId() > 0) {

            if (oMysql.existsOne(strSqlSelectDataOrigin, oOpcionBean.getId())) {
                String strIdDoc = oMysql.getOne(strSqlSelectDataOrigin, "id_pregunta", oOpcionBean.getId());
                oOpcionBean.setId_pregunta(Integer.parseInt(strIdDoc));
                oOpcionBean.setDescripcion(oMysql.getOne(strSqlSelectDataOrigin, "descripcion", oOpcionBean.getId()));

                PreguntaDao oPreguntaDao = new PreguntaDao(oConnection);
                //un pojo de documento con elid de documento
                PreguntaBean oPreguntaBean = new PreguntaBean();
                oPreguntaBean.setId(oOpcionBean.getId_pregunta());
                oPreguntaBean = oPreguntaDao.get(oPreguntaBean, 1);

                //rellenar el pojo de documento con el dao
                //meter el pojo relleno a la pregunta
                GroupBeanImpl oGroupBeanImpl = new GroupBeanImpl();
                oGroupBeanImpl.setBean(oPreguntaBean);
                oGroupBeanImpl.setMeta(oPreguntaDao.getmetainformation());
                oOpcionBean.setObj_pregunta(oGroupBeanImpl);
            }
        }
        try {

            return oOpcionBean;
        } catch (Exception e) {
            throw new Exception(this.getClass().getName() + ":get ERROR: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<OpcionBean> getAll(ArrayList<FilterBeanHelper> alFilter, HashMap<String, String> hmOrder) throws Exception {

        MysqlDataSpImpl oMysql = new MysqlDataSpImpl(oConnection);

        ArrayList<OpcionBean> alOpcionBean = new ArrayList<>();
        strSqlSelectDataOrigin += SqlBuilder.buildSqlWhere(alFilter);
        strSqlSelectDataOrigin += SqlBuilder.buildSqlOrder(hmOrder);
        try {
            ResultSet result = oMysql.getAllSql(strSqlSelectDataOrigin);
            if (result != null) {
                while (result.next()) {
                    OpcionBean oOpcionBean = new OpcionBean();
                    oOpcionBean.setId(result.getInt("id"));
                    oOpcionBean.setId_pregunta(result.getInt("id_pregunta"));
                    //crear un dao de documento
                    PreguntaDao oPreguntaDao = new PreguntaDao(oConnection);
                    //un pojo de documento con elid de documento
                    PreguntaBean oPreguntaBean = new PreguntaBean();
                    oPreguntaBean.setId(result.getInt("id_pregunta"));
                    oPreguntaBean = oPreguntaDao.get(oPreguntaBean, 1);

                    //rellenar el pojo de documento con el dao
                    //meter el pojo relleno a la pregunta
                    GroupBeanImpl oGroupBeanImpl = new GroupBeanImpl();
                    oGroupBeanImpl.setBean(oPreguntaBean);
                    oGroupBeanImpl.setMeta(oPreguntaDao.getmetainformation());
                    oOpcionBean.setObj_pregunta(oGroupBeanImpl);

                    //sacar del dao de documento los metadatos de documento
                    //meterlos en el pojo de la pregunta tambien
                    oOpcionBean.setDescripcion(result.getString("descripcion"));
                    alOpcionBean.add(oOpcionBean);
                }
            }

        } catch (Exception ex) {
            throw new Exception(this.getClass().getName() + ":get ERROR: " + ex.getMessage());
        }

        return alOpcionBean;
    }

    @Override
    public int getCount(ArrayList<FilterBeanHelper> alFilter) throws Exception {

        MysqlDataSpImpl oMysql = new MysqlDataSpImpl(oConnection);

        strSqlSelectDataOrigin += SqlBuilder.buildSqlWhere(alFilter);
        int counter = 0;

        try {
            counter = oMysql.getCount(strSqlSelectDataOrigin);
        } catch (Exception e) {
            throw new Exception(this.getClass().getName() + ".getCount: Error: " + e.getMessage());
        }
        return counter;
    }

    @Override
    public ArrayList<OpcionBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBeanHelper> alFilter, HashMap<String, String> hmOrder) throws Exception {

        //Crear la conexión SQL
        MysqlDataSpImpl oMySQL = new MysqlDataSpImpl(oConnection);

        strSqlSelectDataOrigin += SqlBuilder.buildSqlWhere(alFilter);
        strSqlSelectDataOrigin += SqlBuilder.buildSqlOrder(hmOrder);

        //Crear una variable result que provenga de oMySQL
        ResultSet result = oMySQL.getPage(strSqlSelectDataOrigin, intRegsPerPag, intPage);

        ArrayList<OpcionBean> alOpcionBean = new ArrayList<>();

        //Recorrer los elementos de result
        while (result.next()) {

            OpcionBean oOpcionBean = new OpcionBean();

            oOpcionBean.setId(result.getInt("id"));
            oOpcionBean.setDescripcion(result.getString("descripcion"));
            oOpcionBean.setId_pregunta(result.getInt("id_pregunta"));

            PreguntaDao oPreguntaDao = new PreguntaDao(oConnection);

            PreguntaBean oPreguntaBean = new PreguntaBean();
            oPreguntaBean.setId(result.getInt("id_pregunta"));

            oPreguntaBean = oPreguntaDao.get(oPreguntaBean, 1);

            GroupBeanImpl oGroupBeanImplOpcion = new GroupBeanImpl();
            oGroupBeanImplOpcion.setBean(oPreguntaBean);
            oGroupBeanImplOpcion.setMeta(oPreguntaDao.getmetainformation());
            oOpcionBean.setObj_pregunta(oGroupBeanImplOpcion);

            alOpcionBean.add(oOpcionBean);

        }

        return alOpcionBean;

    }

    @Override
    public int getPages(int intRegsPerPag, ArrayList<FilterBeanHelper> alFilter) throws Exception {
        strSqlSelectDataOrigin += SqlBuilder.buildSqlWhere(alFilter);
        return oMysql.getPages(strSqlSelectDataOrigin, intRegsPerPag);
    }

    @Override
    public OpcionBean set(OpcionBean oOpcionBean) throws Exception {

        MysqlDataSpImpl oMysql = new MysqlDataSpImpl(oConnection);

        try {
            if (oOpcionBean.getId() == 0) {
                oOpcionBean.setId(oMysql.insertOne(strTableOrigin));
            }
            oMysql.updateOne(oOpcionBean.getId(), strTableOrigin, "id_pregunta", oOpcionBean.getId_pregunta().toString());
            oMysql.updateOne(oOpcionBean.getId(), strTableOrigin, "descripcion", oOpcionBean.getDescripcion());

        } catch (Exception e) {
            throw new Exception(this.getClass().getName() + ".set: Error: " + e.getMessage());
        }
        return oOpcionBean;
    }

    @Override
    public int remove(OpcionBean oOBean) throws Exception {

        int result = 0;
        try {
            result = oMysql.removeOne(oOBean.getId(), strTableOrigin);
        } catch (Exception e) {
            throw new Exception(this.getClass().getName() + ".remove: Error: " + e.getMessage());
        }
        return result;
    }

    @Override
    public ArrayList<MetaBeanGenImpl> getmetainformation() throws Exception {
        ArrayList<MetaBeanGenImpl> alVector = null;
        try {
            Class oOpcionBeanClass = OpcionBean.class;
            alVector = new ArrayList<>();
            for (Field field : oOpcionBeanClass.getDeclaredFields()) {
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

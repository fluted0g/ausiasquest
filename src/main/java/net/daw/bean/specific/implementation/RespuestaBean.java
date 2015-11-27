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
package net.daw.bean.specific.implementation;

import com.google.gson.annotations.Expose;
import java.util.Date;
import net.daw.bean.generic.implementation.BeanGenImpl;
import net.daw.bean.group.GroupBeanImpl;
import net.daw.bean.publicinterface.BeanInterface;
import net.daw.helper.annotations.MethodMetaInformation;
import net.daw.helper.annotations.TableSourceMetaInformation;
import net.daw.helper.statics.MetaEnum;

/**
 *
 * @author juliomiguel
 */

@TableSourceMetaInformation(
        TableName = "respuesta",
        Description = "Respuesta"
)
public class RespuestaBean extends BeanGenImpl implements BeanInterface{
    
     public RespuestaBean() {
        this.id = 0;
    }

    public RespuestaBean(Integer id) {
        this.id = id;
    }
    
    @Expose
    @MethodMetaInformation(
            IsId = true,
            UltraShortName = "Iden.",
            ShortName = "Identif.",
            Description = "Número Identificador",
            Type = MetaEnum.FieldType.Integer,
            DefaultValue = "0"
    )
    private Integer id;
    
    @Expose(serialize = false)
    @MethodMetaInformation(
            UltraShortName = "Opcion",
            ShortName = "Iden. Opcion",
            Description = "Identificador de Opcion",
            IsIdForeignKey = true,
            ReferencesTable = "opcion",
            Type = MetaEnum.FieldType.Integer
    )
    private Integer id_opcion = 0; //important zero-initialize foreign keys

    @Expose(deserialize = false)
    @MethodMetaInformation(
            UltraShortName = "Opcion",
            ShortName = "Opcion",
            Description = "Referencia de la Opcion",
            IsObjForeignKey = true,
            ReferencesTable = "opcion",
            MyIdName = "id_opcion"
    )
    private GroupBeanImpl obj_opcion = null;
    
    @Expose(serialize = false)
    @MethodMetaInformation(
            UltraShortName = "Usuario",
            ShortName = "Usuario",
            Description = "Identificador de Usuario",
            IsIdForeignKey = true,
            ReferencesTable = "usuario",
            Type = MetaEnum.FieldType.Integer
    )
    private Integer id_usuario = 0; //important zero-initialize foreign keys

    @Expose(deserialize = false)
    @MethodMetaInformation(
            UltraShortName = "Usuario",
            ShortName = "Usuario",
            Description = "Referencia al usuario propietario",
            IsObjForeignKey = true,
            ReferencesTable = "usuario",
            MyIdName = "id_usuario"
    )
    private GroupBeanImpl obj_usuario = null;
    
    @Expose
    @MethodMetaInformation(
            UltraShortName = "F.alta",
            ShortName = "Fecha de alta",
            Description = "Fecha de envio opción",
            Type = MetaEnum.FieldType.Date,
            DefaultValue = "01/01/2000",
            IsForeignKeyDescriptor = true
    )
    private Date fechaHoraAlta = new Date();

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the id_opcion
     */
    public Integer getId_opcion() {
        return id_opcion;
    }

    /**
     * @param id_opcion the id_opcion to set
     */
    public void setId_opcion(Integer id_opcion) {
        this.id_opcion = id_opcion;
    }

    /**
     * @return the obj_opcion
     */
    public GroupBeanImpl getObj_opcion() {
        return obj_opcion;
    }

    /**
     * @param obj_opcion the obj_opcion to set
     */
    public void setObj_opcion(GroupBeanImpl obj_opcion) {
        this.obj_opcion = obj_opcion;
    }

    /**
     * @return the id_usuario
     */
    public Integer getId_usuario() {
        return id_usuario;
    }

    /**
     * @param id_usuario the id_usuario to set
     */
    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    /**
     * @return the obj_usuario
     */
    public GroupBeanImpl getObj_usuario() {
        return obj_usuario;
    }

    /**
     * @param obj_usuario the obj_usuario to set
     */
    public void setObj_usuario(GroupBeanImpl obj_usuario) {
        this.obj_usuario = obj_usuario;
    }

    /**
     * @return the fechaHoraAlta
     */
    public Date getFechaHoraAlta() {
        return fechaHoraAlta;
    }

    /**
     * @param fechaHoraAlta the fechaHoraAlta to set
     */
    public void setFechaHoraAlta(Date fechaHoraAlta) {
        this.fechaHoraAlta = fechaHoraAlta;
    }
}

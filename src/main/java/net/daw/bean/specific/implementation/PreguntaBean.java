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
        TableName = "pregunta",
        Description = "Pregunta"
)
public class PreguntaBean extends BeanGenImpl implements BeanInterface{
    
     public PreguntaBean() {
        this.id = 0;
    }

    public PreguntaBean(Integer id) {
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
            UltraShortName = "Iden. Doc",
            ShortName = "Iden. de Documento",
            Description = "Identificador de Documento",
            IsIdForeignKey = true,
            ReferencesTable = "documento",
            Type = MetaEnum.FieldType.Integer
    )
    private Integer id_documento = 0;
    
    @Expose(deserialize = false)
    @MethodMetaInformation(
            UltraShortName = "Iden. Doc",
            ShortName = "Iden. de Documento",
            Description = "Identificador de Documento",
            IsObjForeignKey = true,
            ReferencesTable = "documento",
            MyIdName = "id_documento"
    )
    private GroupBeanImpl obj_documento = null;
    
    @Expose
    @MethodMetaInformation(
            UltraShortName = "Desc.",
            ShortName = "Descripcion",
            Description = "Descripcion de opcion",
            Type = MetaEnum.FieldType.String,
            DefaultValue = "Sin contenido"
    )
    private String descripcion = "";

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
     * @return the id_documento
     */
    public Integer getId_documento() {
        return id_documento;
    }

    /**
     * @param id_documento the id_documento to set
     */
    public void setId_documento(Integer id_documento) {
        this.id_documento = id_documento;
    }

    /**
     * @return the obj_documento
     */
    public GroupBeanImpl getObj_documento() {
        return obj_documento;
    }

    /**
     * @param obj_documento the obj_documento to set
     */
    public void setObj_documento(GroupBeanImpl obj_documento) {
        this.obj_documento = obj_documento;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}

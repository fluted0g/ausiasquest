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
import net.daw.bean.group.GroupBeanImpl;
import net.daw.helper.annotations.MethodMetaInformation;
import net.daw.helper.annotations.TableSourceMetaInformation;
import net.daw.helper.statics.MetaEnum;

/**
 *
 * @author a047087313b
 */

@TableSourceMetaInformation(
        TableName = "respuesta",
        Description = "Respuestas del cuestionario"
)
public class RespuestaBean {
    
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
            IsIdForeignKey = true,
            ReferencesTable = "opcion",
            UltraShortName = "Op.",
            ShortName = "Opc.",
            Description = "Opcion",
            Type = MetaEnum.FieldType.Integer,
            DefaultValue = "0"
    )
    private Integer id_opcion = 0;
    
        @Expose(deserialize = false)
    @MethodMetaInformation(
            UltraShortName = "R. op.",
            ShortName = "Ref. de opción",
            Description = "Referencia a la opción",
            IsObjForeignKey = true,
            ReferencesTable = "opcion",
            MyIdName = "id_opcion"
    )
    private GroupBeanImpl obj_opcion = null;
     
    @Expose(serialize = false)
    @MethodMetaInformation(
            IsIdForeignKey = true,
            ReferencesTable = "usuario",
            UltraShortName = "Us.",
            ShortName = "Usu.",
            Description = "Usuario",
            Type = MetaEnum.FieldType.Integer,
            DefaultValue = "0"
    )
    private Integer id_usuario = 0;
    
        @Expose(deserialize = false)
    @MethodMetaInformation(
            UltraShortName = "Ref. usu.",
            ShortName = "Ref. al usuario",
            Description = "Referencia al usuario",
            IsObjForeignKey = true,
            ReferencesTable = "usuario",
            MyIdName = "id_usuario"
    )
    private GroupBeanImpl obj_usuario = null;
    
    @Expose
    @MethodMetaInformation(
            UltraShortName = "F.alta",
            ShortName = "Fecha de alta",
            Description = "Fecha de creación de la respuesta",
            Type = MetaEnum.FieldType.Date,
            DefaultValue = "01/01/2000"
    )
    private Date fechaHoraAlta = new Date();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdOpcion() {
        return id_opcion;
    }

    public void setIdOpcion(Integer idOpcion) {
        this.id_opcion = idOpcion;
    }

    public Integer getIdUsuario() {
        return id_usuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.id_usuario = idUsuario;
    }

    public Date getFechaHoraAlta() {
        return fechaHoraAlta;
    }

    public void setFechaHoraAlta(Date fechaHoraAlta) {
        this.fechaHoraAlta = fechaHoraAlta;
    }
    
    
}

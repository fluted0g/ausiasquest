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
        TableName = "post",
        Description = "Posts del foro"
)
public class PostBean {
    
    
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
            UltraShortName = "Us.",
            ShortName = "Usu.",
            Description = "Id del usuario",
            IsIdForeignKey = true,
            ReferencesTable = "usuario",
            Type = MetaEnum.FieldType.Integer
    )
    private Integer id_usuario = 0;
    
        @Expose(deserialize = false)
    @MethodMetaInformation(
            UltraShortName = "R. Us.",
            ShortName = "Ref. de usuario",
            Description = "Referencia al usuario",
            IsObjForeignKey = true,
            ReferencesTable = "usuario",
            MyIdName = "id_usuario"
    )
    private GroupBeanImpl obj_usuario = null;
    
    @Expose(serialize = false)
    @MethodMetaInformation(
            UltraShortName = "Doc.",
            ShortName = "Docum.",
            Description = "Identificador del documento",
            IsIdForeignKey = true,
            ReferencesTable = "documento",
            Type = MetaEnum.FieldType.Integer
    )
    private Integer id_documento = 0; 
    
    @Expose(deserialize = false)
    @MethodMetaInformation(
            UltraShortName = "R. Doc.",
            ShortName = "Ref. de documento",
            Description = "Referencia al documento",
            IsObjForeignKey = true,
            ReferencesTable = "documento",
            MyIdName = "id_documento"
    )
    private GroupBeanImpl obj_documento = null;
    
    @Expose
    @MethodMetaInformation(
            UltraShortName = "Fe.",
            ShortName = "Fecha",
            Description = "Fecha",
            Type = MetaEnum.FieldType.Date,
            DefaultValue = "01/01/2000"
    )
    private Date fecha = new Date();

    @Expose
    @MethodMetaInformation(
            UltraShortName = "Msg.",
            ShortName = "Mens.",
            Description = "Mensaje"
    )
    private String mensaje = "";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUsuario() {
        return id_usuario;
    }

    public void setIdUsuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Integer getIdDocumento() {
        return id_documento;
    }

    public void setIdDocumento(Integer id_documento) {
        this.id_documento = id_documento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
    
}

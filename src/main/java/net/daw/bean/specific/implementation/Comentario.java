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
        TableName = "comentario",
        Description = "Comentario"
)
public class Comentario extends BeanGenImpl implements BeanInterface{
    
     
    public Comentario() {
        this.id = 0;
    }

    public Comentario(Integer id) {
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
    
    @Expose
    @MethodMetaInformation(
            UltraShortName = "Con.",
            ShortName = "Contenido",
            Description = "Contenido del Blog",
            Type = MetaEnum.FieldType.String,
            DefaultValue = "Sin Contenido"//,
         //   IsForeignKeyDescriptor = true
    )
    private String contenido = "";
    
    @Expose
    @MethodMetaInformation(
            UltraShortName = "Nom.",
            ShortName = "NomAutor.",
            Description = "Nombre de Autor",
            Type = MetaEnum.FieldType.String,
            IsForeignKeyDescriptor = true
    )
    private String nombreautor = "";
    
    @Expose(deserialize = false)
    @MethodMetaInformation(
            UltraShortName = "Doc.",
            ShortName = "Ident Documento",
            Description = "Identificador de documento",
            IsObjForeignKey = true,
            ReferencesTable = "documento",
            Type = MetaEnum.FieldType.Integer
    )
    private Integer id_documento = null;
}

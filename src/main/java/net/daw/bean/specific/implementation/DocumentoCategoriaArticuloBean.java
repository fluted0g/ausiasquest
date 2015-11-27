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
import net.daw.bean.group.GroupBeanImpl;
import net.daw.helper.annotations.MethodMetaInformation;
import net.daw.helper.annotations.TableSourceMetaInformation;
import net.daw.helper.statics.MetaEnum;

/**
 *
 * @author a047087313b
 */

@TableSourceMetaInformation(
        TableName = "documentoCategoriaArticulo",
        Description = "Tabla intermedia documento-categoria"
)
public class DocumentoCategoriaArticuloBean {
    
    
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
            ReferencesTable = "documento",
            UltraShortName = "Doc.",
            ShortName = "Docum.",
            Description = "Documento",
            Type = MetaEnum.FieldType.Integer,
            DefaultValue = "0"
    )
    private Integer id_documento = 0;
    
        @Expose(deserialize = false)
    @MethodMetaInformation(
            UltraShortName = "R. doc.",
            ShortName = "Ref. de documento",
            Description = "Referencia al documento",
            IsObjForeignKey = true,
            ReferencesTable = "documento",
            MyIdName = "id_documento"
    )
    private GroupBeanImpl obj_documento = null;
    
    @Expose(serialize = false)
    @MethodMetaInformation(
            IsIdForeignKey = true,
            ReferencesTable = "categoriaarticulo",
            UltraShortName = "Cat.",
            ShortName = "Categ.",
            Description = "Categoría",
            Type = MetaEnum.FieldType.Integer,
            DefaultValue = "0"
    )
    private Integer id_categoriaArticulo = 0;
    
        @Expose(deserialize = false)
    @MethodMetaInformation(
            UltraShortName = "R. cat-art.",
            ShortName = "Ref. a cat-art.",
            Description = "Referencia a categoriaarticulo",
            IsObjForeignKey = true,
            ReferencesTable = "categoriaarticulo",
            MyIdName = "id_categoriaarticulo"
    )
    private GroupBeanImpl obj_categoriaarticulo = null;
    
    @Expose
    @MethodMetaInformation(
            UltraShortName = "Desc.",
            ShortName = "Descrip.",
            Description = "Descripción",
            Type = MetaEnum.FieldType.String
    )
    private String descripcion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdDocumento() {
        return id_documento;
    }

    public void setIdDocumento(Integer idDocumento) {
        this.id_documento = idDocumento;
    }

    public Integer getIdCategoriaArticulo() {
        return id_categoriaArticulo;
    }

    public void setIdCategoriaArticulo(Integer idCategoriaArticulo) {
        this.id_categoriaArticulo = idCategoriaArticulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}

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
import net.daw.helper.annotations.SelectSourceMetaInformation;
import net.daw.helper.statics.MetaEnum;

/**
 *
 * @author juliomiguel
 */
@SelectSourceMetaInformation( 
        SqlSelect = "SELECT pregunta.id_documento, documento.titulo, pregunta.id, pregunta.descripcion, opcion.id, opcion.descripcion FROM pregunta, documento, opcion WHERE pregunta.id_documento = documento.id AND opcion.id_pregunta = pregunta.id GROUP BY pregunta.id_documento",
        Description = "Documento"
)
public class CuestionarioBean extends BeanGenImpl implements BeanInterface {
     
      
    @Expose
    @MethodMetaInformation(
            UltraShortName = "Iden.",
            ShortName = "Iden. Heredado",
            Description = "Número Identificador Documento",
            Type = MetaEnum.FieldType.Integer,
            DefaultValue = "0"
    )
    private Integer id_documento = 0;
    


    @Expose
    @MethodMetaInformation(
            UltraShortName = "Tít.",
            ShortName = "Título",
            Description = "Título del documento",
            Type = MetaEnum.FieldType.String,
            MinLength = 1,
            MaxLength = 255,
            DefaultValue = "Sin título",
            IsForeignKeyDescriptor = true
    )
    private String titulo = "";
    
    @Expose(serialize = false)
    @MethodMetaInformation(
            UltraShortName = "Iden. Preg",
            ShortName = "Iden. de Pregunta",
            Description = "Identificador de Pregunta",
            IsIdForeignKey = true,
            ReferencesTable = "pregunta",
            Type = MetaEnum.FieldType.Integer
    )
    private Integer id_pregunta = 0;
    
         
    @Expose(deserialize = false)
    @MethodMetaInformation(
            UltraShortName = "Iden. Preg",
            ShortName = "Iden. de Pregunta",
            Description = "Identificador de Pregunta",
            IsObjForeignKey = true,
            ReferencesTable = "pregunta",
            MyIdName = "id_pregunta"
    )
    private GroupBeanImpl obj_pregunta = null;
    
    @Expose
    @MethodMetaInformation(
            UltraShortName = "Desc.",
            ShortName = "Descripcion",
            Description = "Descripcion de pregunta",
            Type = MetaEnum.FieldType.String,
            DefaultValue = "Sin contenido"
    )
    private String descripcionPregunta = "";

    
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
    
    
    @Expose
    @MethodMetaInformation(
            UltraShortName = "Desc.",
            ShortName = "Descripcion",
            Description = "Descripcion de opcion",
            Type = MetaEnum.FieldType.String,
            DefaultValue = "Sin contenido"
    )
    private String descripcionOpcion = "";

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
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the id_pregunta
     */
    public Integer getId_pregunta() {
        return id_pregunta;
    }

    /**
     * @param id_pregunta the id_pregunta to set
     */
    public void setId_pregunta(Integer id_pregunta) {
        this.id_pregunta = id_pregunta;
    }

    /**
     * @return the obj_pregunta
     */
    public GroupBeanImpl getObj_pregunta() {
        return obj_pregunta;
    }

    /**
     * @param obj_pregunta the obj_pregunta to set
     */
    public void setObj_pregunta(GroupBeanImpl obj_pregunta) {
        this.obj_pregunta = obj_pregunta;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcionOpcion() {
        return descripcionOpcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcionOpcion(String descripcionOpcion) {
        this.descripcionOpcion = descripcionOpcion;
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
     * @return the descripcionPregunta
     */
    public String getDescripcionPregunta() {
        return descripcionPregunta;
    }

    /**
     * @param descripcionPregunta the descripcionPregunta to set
     */
    public void setDescripcionPregunta(String descripcionPregunta) {
        this.descripcionPregunta = descripcionPregunta;
    }
}

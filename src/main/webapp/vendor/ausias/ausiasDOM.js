/* 
 * Copyright (c) 2015 by Rafael Angel Aznar Aparici (rafaaznar at gmail dot com)
 * 
 * openAUSIAS: The stunning micro-library that helps you to develop easily 
 *             AJAX web applications by using Java and jQuery
 * openAUSIAS is distributed under the MIT License (MIT)
 * Sources at https://github.com/rafaelaznar/
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
(function () {
    var dom = window.dom = {};
    dom.html = function (strLabel, strAttr, strText) {
        if (!strAttr)
            strAttr = '';
        else
            strAttr = ' ' + strAttr;
        if (!strText)
            return '<' + strLabel + strAttr + ' />';
        else
            return '<' + strLabel + strAttr + '>' + strText + '</' + strLabel + '>';

    };
    dom.div = function (strAttributes, strText) {
        return dom.html('div', strAttributes, strText);
    };
    dom.a = function (strAttributes, strText) {
        return dom.html('a', strAttributes, strText);
    };
    dom.span = function (strAttributes, strText) {
        return dom.html('span', strAttributes, strText);
    };
    dom.small = function (strAttributes, strText) {
        return dom.html('small', strAttributes, strText);
    };
    dom.h1 = function (strAttributes, strText) {
        return dom.html('h1', strAttributes, strText);
    };
    dom.h2 = function (strAttributes, strText) {
        return dom.html('h2', strAttributes, strText);
    };
    dom.h3 = function (strAttributes, strText) {
        return dom.html('h3', strAttributes, strText);
    };
    dom.h4 = function (strAttributes, strText) {
        return dom.html('h4', strAttributes, strText);
    };
    dom.h5 = function (strAttributes, strText) {
        return dom.html('h5', strAttributes, strText);
    };
    dom.h6 = function (strAttributes, strText) {
        return dom.html('h6', strAttributes, strText);
    };
    dom.p = function (strAttributes, strText) {
        return dom.html('p', strAttributes, strText);
    };
    dom.mark = function (strAttributes, strText) {
        return dom.html('mark', strAttributes, strText);
    };
    dom.del = function (strAttributes, strText) {
        return dom.html('del', strAttributes, strText);
    };
    dom.s = function (strAttributes, strText) {
        return dom.html('s', strAttributes, strText);
    };
    dom.i = function (strAttributes, strText) {
        return dom.html('i', strAttributes, strText);
    };
    dom.u = function (strAttributes, strText) {
        return dom.html('u', strAttributes, strText);
    };
    dom.strong = function (strAttributes, strText) {
        return dom.html('strong', strAttributes, strText);
    };
    dom.m = function (strAttributes, strText) {
        return dom.html('m', strAttributes, strText);
    };
    dom.code = function (strAttributes, strText) {
        return dom.html('code', strAttributes, strText);
    };
    dom.blockquote = function (strAttributes, strText) {
        return dom.html('blockquote', strAttributes, strText);
    };
    dom.footer = function (strAttributes, strText) {
        return dom.html('footer', strAttributes, strText);
    };
    dom.cite = function (strAttributes, strText) {
        return dom.html('cite', strAttributes, strText);
    };
    dom.ul = function (strAttributes, strText) {
        return dom.html('ul', strAttributes, strText);
    };
    dom.li = function (strAttributes, strText) {
        return dom.html('li', strAttributes, strText);
    };
    dom.table = function (strAttributes, strText) {
        return dom.html('table', strAttributes, strText);
    };
    dom.tr = function (strAttributes, strText) {
        return dom.html('tr', strAttributes, strText);
    };
    dom.td = function (strAttributes, strText) {
        return dom.html('td', strAttributes, strText);
    };
    dom.img = function (strAttributes, strText) {
        return dom.html('img', strAttributes, strText);
    };
    dom.br = function () {
        return "<br>\n";
    };
    dom.form = function (strAttributes, strText) {
        return dom.html('form', strAttributes, strText);
    };
    dom.label = function (strAttributes, strText) {
        return dom.html('label', strAttributes, strText);
    };
    dom.select = function (strAttributes, strText) {
        return dom.html('select', strAttributes, strText);
    };
    dom.option = function (strAttributes, strText) {
        return dom.html('option', strAttributes, strText);
    };
    dom.input = function (strAttributes, strText) {
        return dom.html('input', strAttributes, strText);
    };
    dom.textarea = function (strAttributes, strText) {
        return dom.html('textarea', strAttributes, strText);
    };
    dom.button = function (strAttributes, strText) {
        return dom.html('button', strAttributes, strText);
    };
    dom.fieldset = function (strAttributes, strText) {
        return dom.html('fieldset', strAttributes, strText);
    }
})();

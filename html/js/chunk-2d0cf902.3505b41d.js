(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d0cf902"],{"63f5":function(e,t,l){"use strict";l.r(t);var o=function(){var e=this,t=e.$createElement,l=e._self._c||t;return l("div",[l("div",{staticClass:"crumbs"},[l("el-breadcrumb",{attrs:{separator:"/"}},[l("el-breadcrumb-item",[l("i",{staticClass:"el-icon-lx-calendar"}),e._v(" 表单")]),l("el-breadcrumb-item",[e._v("基本表单")])],1)],1),l("div",{staticClass:"container"},[l("div",{staticClass:"form-box"},[l("el-form",{ref:"form",attrs:{model:e.form,"label-width":"80px"}},[l("el-form-item",{attrs:{label:"表单名称"}},[l("el-input",{model:{value:e.form.name,callback:function(t){e.$set(e.form,"name",t)},expression:"form.name"}})],1),l("el-form-item",{attrs:{label:"选择器"}},[l("el-select",{attrs:{placeholder:"请选择"},model:{value:e.form.region,callback:function(t){e.$set(e.form,"region",t)},expression:"form.region"}},[l("el-option",{key:"bbk",attrs:{label:"步步高",value:"bbk"}}),l("el-option",{key:"xtc",attrs:{label:"小天才",value:"xtc"}}),l("el-option",{key:"imoo",attrs:{label:"imoo",value:"imoo"}})],1)],1),l("el-form-item",{attrs:{label:"日期时间"}},[l("el-col",{attrs:{span:11}},[l("el-date-picker",{staticStyle:{width:"100%"},attrs:{type:"date",placeholder:"选择日期"},model:{value:e.form.date1,callback:function(t){e.$set(e.form,"date1",t)},expression:"form.date1"}})],1),l("el-col",{staticClass:"line",attrs:{span:2}},[e._v("-")]),l("el-col",{attrs:{span:11}},[l("el-time-picker",{staticStyle:{width:"100%"},attrs:{placeholder:"选择时间"},model:{value:e.form.date2,callback:function(t){e.$set(e.form,"date2",t)},expression:"form.date2"}})],1)],1),l("el-form-item",{attrs:{label:"城市级联"}},[l("el-cascader",{attrs:{options:e.options},model:{value:e.form.options,callback:function(t){e.$set(e.form,"options",t)},expression:"form.options"}})],1),l("el-form-item",{attrs:{label:"选择开关"}},[l("el-switch",{model:{value:e.form.delivery,callback:function(t){e.$set(e.form,"delivery",t)},expression:"form.delivery"}})],1),l("el-form-item",{attrs:{label:"多选框"}},[l("el-checkbox-group",{model:{value:e.form.type,callback:function(t){e.$set(e.form,"type",t)},expression:"form.type"}},[l("el-checkbox",{attrs:{label:"步步高",name:"type"}}),l("el-checkbox",{attrs:{label:"小天才",name:"type"}}),l("el-checkbox",{attrs:{label:"imoo",name:"type"}})],1)],1),l("el-form-item",{attrs:{label:"单选框"}},[l("el-radio-group",{model:{value:e.form.resource,callback:function(t){e.$set(e.form,"resource",t)},expression:"form.resource"}},[l("el-radio",{attrs:{label:"步步高"}}),l("el-radio",{attrs:{label:"小天才"}}),l("el-radio",{attrs:{label:"imoo"}})],1)],1),l("el-form-item",{attrs:{label:"文本框"}},[l("el-input",{attrs:{type:"textarea",rows:"5"},model:{value:e.form.desc,callback:function(t){e.$set(e.form,"desc",t)},expression:"form.desc"}})],1),l("el-form-item",[l("el-button",{attrs:{type:"primary"},on:{click:e.onSubmit}},[e._v("表单提交")]),l("el-button",[e._v("取消")])],1)],1)],1)])])},a=[],r={name:"baseform",data:function(){return{form:{name:"",region:"",date1:"",date2:"",delivery:!0,type:["步步高"],resource:"小天才",desc:"",options:[]}}},methods:{onSubmit:function(){this.$message.success("提交成功！")}}},s=r,i=l("2877"),c=Object(i["a"])(s,o,a,!1,null,null,null);t["default"]=c.exports}}]);
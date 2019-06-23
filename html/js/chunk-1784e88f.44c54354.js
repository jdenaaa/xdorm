(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-1784e88f"],{5679:function(t,e,l){"use strict";l.r(e);var a=function(){var t=this,e=t.$createElement,l=t._self._c||e;return l("div",{staticClass:"table"},[l("div",{staticClass:"crumbs"},[l("el-breadcrumb",{attrs:{separator:"/"}},[l("el-breadcrumb-item",[l("i",{staticClass:"el-icon-lx-cascades"}),t._v(" 学生管理")]),l("el-breadcrumb-item",[t._v("添加学生")])],1)],1),l("div",{staticClass:"container"},[l("div",{staticClass:"handle-box"},[l("el-button",{staticClass:"handle-del mr10",attrs:{type:"primary",icon:"delete"},on:{click:t.handleDeleteAll}},[t._v("批量删除")]),l("el-select",{staticClass:"handle-select mr10",attrs:{placeholder:"全部学院"},on:{change:t.getStudentByCollegeName},model:{value:t.select_cate,callback:function(e){t.select_cate=e},expression:"select_cate"}},[l("el-option",{staticStyle:{color:"red"}},[t._v("全部学院")]),t._l(t.college,function(t,e){return l("el-option",{key:e,attrs:{label:t.collegeName,value:t.collegeName}})})],2),l("el-input",{staticClass:"handle-input mr10",attrs:{placeholder:"搜素姓名"},model:{value:t.select_word,callback:function(e){t.select_word=e},expression:"select_word"}}),l("el-button",{attrs:{type:"primary",icon:"search"},on:{click:t.search}},[t._v("搜索")])],1),l("el-table",{ref:"multipleTable",staticClass:"table",attrs:{data:t.data,border:""},on:{"selection-change":t.handleSelectionChange}},[l("el-table-column",{attrs:{type:"selection",width:"55",align:"center"}}),l("el-table-column",{attrs:{prop:"stuId",label:"学号",sortable:"",width:"120"}}),l("el-table-column",{attrs:{prop:"name",label:"姓名",width:"100"}}),l("el-table-column",{attrs:{prop:"phone",label:"电话",width:"100"}}),l("el-table-column",{attrs:{prop:"gender",label:"性别",width:"60"}}),l("el-table-column",{attrs:{prop:"roomName",label:"宿舍",width:"100"}}),l("el-table-column",{attrs:{prop:"className",label:"班级",width:"100"}}),l("el-table-column",{attrs:{prop:"majorName",label:"专业",width:"120"}}),l("el-table-column",{attrs:{prop:"collegeName",label:"学院"}}),l("el-table-column",{attrs:{label:"操作",width:"180",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[l("el-button",{attrs:{type:"text",icon:"el-icon-edit"},on:{click:function(l){return t.handleEdit(e.$index,e.row)}}},[t._v("分配宿舍")]),l("el-button",{staticClass:"red",attrs:{type:"text",icon:"el-icon-delete"},on:{click:function(l){return t.handleDelete(e.$index,e.row)}}},[t._v("删除")])]}}])})],1),l("div",{staticClass:"pagination"},[l("el-pagination",{attrs:{background:"",layout:"prev, pager, next",total:t.total},on:{"current-change":t.handleCurrentChange}})],1)],1),l("el-dialog",{attrs:{title:"提示",visible:t.delVisible,width:"300px",center:""},on:{"update:visible":function(e){t.delVisible=e}}},[l("div",{staticClass:"del-dialog-cnt"},[t._v("删除不可恢复，是否确定删除？")]),l("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[l("el-button",{on:{click:function(e){t.delVisible=!1}}},[t._v("取 消")]),l("el-button",{attrs:{type:"primary"},on:{click:t.deleteRow}},[t._v("确 定")])],1)]),l("el-dialog",{attrs:{title:"提示",visible:t.delAllVisible,width:"300px",center:""},on:{"update:visible":function(e){t.delAllVisible=e}}},[l("div",{staticClass:"del-dialog-cnt"},[t._v("批量删除不可恢复，是否确定删除？")]),l("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[l("el-button",{on:{click:function(e){t.delAllVisible=!1}}},[t._v("取 消")]),l("el-button",{attrs:{type:"primary"},on:{click:t.delAll}},[t._v("确 定")])],1)])],1)},s=[],i=(l("7f7f"),{name:"basetable",data:function(){return{url:"http://localhost:8081/student/list",tableData:[],cur_page:1,multipleSelection:[],select_cate:"",select_word:"",del_list:[],del_stuId:"",del_status:"",total:"",college:"",del_stuId_list:[],is_search:!1,editVisible:!1,delVisible:!1,delAllVisible:!1,idx:-1}},created:function(){this.getData()},computed:{data:function(){var t=this;return this.tableData.filter(function(e){for(var l=!1,a=0;a<t.del_list.length;a++)if(e.name===t.del_list[a].name){l=!0;break}if(!l&&e.collegeName.indexOf(t.select_cate)>-1&&(e.name.indexOf(t.select_word)>-1||e.collegeName.indexOf(t.select_word)>-1))return e})}},methods:{handleCurrentChange:function(t){this.cur_page=t,this.getData()},getData:function(){var t=this;this.$axios.post(this.GLOBAL.BASE_URL+"/student/list",{page:this.cur_page}).then(function(e){t.tableData=e.data.list,t.total=e.data.total}),this.$axios.get(this.GLOBAL.BASE_URL+"/college/all").then(function(e){t.college=e.data}),this.$axios.get(this.GLOBAL.BASE_URL+"/dorm/all").then(function(e){t.dormList=e.data})},search:function(){this.is_search=!0},formatter:function(t,e){return t.roomName},filterTag:function(t,e){return e.tag===t},handleEdit:function(t,e){this.idx=t;var l=this.tableData[t];this.$router.push({path:"./editstudent",query:{stuId:l.stuId}})},handleDelete:function(t,e){this.idx=t,this.delVisible=!0,this.del_stuId=this.tableData[t].stuId},handleDeleteAll:function(){var t=this.multipleSelection.length;t<1?this.$message.warning("未选择删除行"):this.delAllVisible=!0},delAll:function(){var t=this,e=this.multipleSelection.length,l="";this.del_list=this.del_list.concat(this.multipleSelection);for(var a=0;a<e;a++)l+=this.multipleSelection[a].name+" ",this.del_stuId_list.push(this.multipleSelection[a].stuId);this.$axios.post(this.GLOBAL.BASE_URL+"/student/batchdelte",{stuIdList:this.del_stuId_list}).then(function(e){var l=e.data;if(200!=l.status)return t.$message.warning("服务器或网络异常"),void location.reload();t.$message.success(l.msg)}),this.$message.success("删除了"+l),this.multipleSelection=[],this.delAllVisible=!1},handleSelectionChange:function(t){this.multipleSelection=t},deleteRow:function(){var t=this;this.$axios.post(this.GLOBAL.BASE_URL+"/student/delete",{stuId:this.del_stuId}).then(function(e){t.del_status=e.data.status,200==t.del_status?(t.tableData.splice(t.idx,1),t.$message.success("删除学号为"+t.del_stuId+"的学生成功")):t.$message.warning(e.data.msg)}),200!=this.del_status&&""!=this.del_status&&this.$message.warning("服务器或网络异常"),this.delVisible=!1},getStudentByCollegeName:function(t){"undefined"!=typeof this.select_cate||(this.select_cate="")}}}),n=i,o=(l("cdcb"),l("2877")),c=Object(o["a"])(n,a,s,!1,null,"b940e246",null);e["default"]=c.exports},"7d73":function(t,e,l){},cdcb:function(t,e,l){"use strict";var a=l("7d73"),s=l.n(a);s.a}}]);
<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-lx-calendar"></i> 管理专业</el-breadcrumb-item>
                <el-breadcrumb-item>添加</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="form-box">
                <el-form ref="form" :model="form" label-width="80px">
                    <el-form-item label="专业名称">
                        <el-input v-model="form.majorName"></el-input>
                    </el-form-item>
                   <el-form-item label="所属学院">
                    <el-radio-group v-model="form.collegeId"  :label-in-value="true" @change="getMajorByCollegeId">
                    <el-radio v-for="(item,index) in collegeList" :key="index" :label="item.collegeId" :value="item.collegeName" border style="width: 180px;">{{item.collegeName}}</el-radio>
                    </el-radio-group>
                </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="onSubmit">提交</el-button>
                        <el-button>取消</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>

        <hr/>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-lx-calendar"></i> 管理专业</el-breadcrumb-item>
                <el-breadcrumb-item>更新</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
         <div class="container">
            <div class="handle-box">
                <el-select v-model="select_cate" placeholder="全部学院" class="handle-select mr10" @change="filterMajorList">
                    <el-option style="color:red;">全部学院</el-option>
                    <el-option v-for="(item,index) in collegeList" :key="index" :label="item.collegeName" :value="item.collegeName"></el-option>
                </el-select>

                <el-input v-model="search_word" placeholder="搜素专业名" class="handle-input mr10"  v-on:input ="search"></el-input>
                <el-button type="primary" icon="search" @click="search">搜索</el-button>
            </div>

            <el-table :data="majorList" border class="table" ref="multipleTable" >
                <el-table-column prop="majorName" label="专业名称" />
                <el-table-column prop="collegeName" label="所属学院" />
                <el-table-column label="操作" width="180" align="center">
                    <template slot-scope="scope">
                        <el-button type="text" icon="el-icon-edit" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                        <el-button type="text" icon="el-icon-delete" class="red" @click="deleteMajor(scope.$index, scope.row)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>

        <!-- 编辑弹出框 -->
        <el-dialog title="编辑" :visible.sync="editVisible" width="30%">
            <el-form ref="updateform" :model="updateform" label-width="50px">
                 <el-form-item label="专业名称">
                    <el-input v-model="updateform.majorName"></el-input>
                </el-form-item>
                <el-form-item label="学院名称">
                <el-select v-model="updateform.collegeId" placeholder="全部学院" class="handle-select mr10" @change="setFormCollege">
                    <el-option v-for="(item,index) in collegeList" :key="index" :label="item.collegeName" :value="item.collegeId"></el-option>
                </el-select>
                </el-form-item>

            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveEdit">确 定</el-button>
            </span>
        </el-dialog>

        <!-- 删除提示框 -->
        <el-dialog title="提示" :visible.sync="delVisible" width="300px" center>
            <div class="del-dialog-cnt">删除不可恢复，是否确定删除？</div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="delVisible = false">取 消</el-button>
                <el-button type="primary" @click="deleteMajorRow">确 定</el-button>
            </span>
        </el-dialog>

    </div>
</template>

<script>
    export default {
        name: 'baseform',
        data: function(){
            return {
               collegeList: '',
                form: {
                    collegeName: '',
                    collegeId:'',
                    majorName:'',
                },

            //下方页面需要的变量
              select_cate: '',
                search_word: '',
                del_majorId: '',  // 点击删除时 获取 该行的 majorId
                del_status: '',  // 删除时返回的状态码 200 表示成功 400 失败
                majorList: '', // 学院列表
                //collegeList: '', // 学院列表
                del_list: [],
                tableData:[],
                editVisible: false,
                delVisible: false,  // 删除框是否可见
                // is_search: false,
                oldMajorList: '', //复制一份 major数据用于筛选操作
                updateform: {
                    collegeId: '',
                    collegeName:'',
                    majorId:'',
                    majorName:''
                },
                idx: -1

            }
        },
         mounted: function() {
            this.getInitData();
        },
        methods: {

             //初始化数据 学院 公寓列表
            getInitData() {
                 //获取专业数据
                   this.$axios.get(this.GLOBAL.BASE_URL + "/major/all").then((res) =>{
                       this.majorList = res.data;
                       this.oldMajorList = res.data;  // 用于筛选作为原数据
                   });

            //获取学院列表
            this.$axios.get(this.GLOBAL.BASE_URL + "/college/all").then(res => {
                this.collegeList = res.data;
            });
            },

            //根据选择的学院 获取 该学院 专业,并设置 表单 collegeId
            getMajorByCollegeId: function(selectCollegeId) {
            this.form.collegeId = selectCollegeId; // 设置 表单 collegeId
            let obj = {};
            obj = this.collegeList.find(item => {
                return item.collegeId === selectCollegeId;
            });
            this.form.collegeName = obj.collegeName;
            },

            onSubmit() {
                if(!this.form.majorName){
                    this.$message.error("请填写专业名称!");
                    return;
                }
                if(!this.form.collegeName){
                    this.$message.error("请选择学院!");
                    return;
                }

                this.$axios.post(this.GLOBAL.BASE_URL + "/major/add",{major:this.form}).then((res) =>{
                    if(res.data.status != 200){
                    this.$message.error(res.data.msg);
                    return;
                }
                this.$message.success("添加专业：" + this.form.majorName + "  成功！");
                this.getInitData();
                });
            },
            
            //下方页面需要的方法
             formatter(row, column) {
                return row.collegeName;
            },
            filterTag(value, row) {
                return row.tag === value;
            },
            handleEdit(index, row) {
                this.idx = index;
                const item = this.majorList[index];
                this.updateform = {
                    collegeId: item.collegeId,
                    collegeName: item.collegeName,
                    majorName:item.majorName,
                    majorId:item.majorId
                }
                this.editVisible = true;
            },
            deleteMajor(index, row) {
                this.idx = index;
                this.delVisible = true;
                this.del_majorId = this.majorList[index].majorId;
            },
 
            // 保存编辑
            saveEdit() {
                this.$set(this.majorList, this.idx, this.updateform);
                this.$axios.post(this.GLOBAL.BASE_URL + "/major/update" ,{newMajor:this.updateform}).then(res =>{
                    if(res.data.status == 200){
                        this.$message.success(`修改专业成功`);
                        this.editVisible = false;
                        // location. reload();  // 刷新
                        // this.getInitData();
                    }else{
                        this.$message.error('修改专业失败：' + res.data.msg);
                        return;
                    }
                });
            },
            // 确定删除
            deleteMajorRow(){
                 this.$axios.post(this.GLOBAL.BASE_URL + "/major/delete" ,{
                     majorId:this.del_majorId}).then((res) => {
                         this.del_status = res.data.status;
                         if(this.del_status == 200){
                             this.majorList.splice(this.idx, 1);
                             this.$message.success('删除编号为'+this.del_majorId+'的专业成功');
                         }else{
                              this.$message.warning('删除编号为'+this.del_majorId+'的专业失败' + res.data.msg);
                         }
                     });
                     if(this.del_status != 200 && this.del_status != ''){
                          this.$message.warning('服务器或网络异常');
                     }
                 this.delVisible = false;
            },

            //编辑时选择学院
            setFormCollege:function(selectCollegeId){
                 let obj = {};
                 obj = this.collegeList.find(item => {
                     return item.collegeId === selectCollegeId;
                 });
                this.updateform.collegeName =  obj.collegeName;
            },

        //按学院名过滤专业列表
        filterMajorList: function(selecteCollegeName){
            this.$message.success("选中：" + selecteCollegeName)
            if(typeof(selecteCollegeName) == "undefined"){
                this.majorList = this.oldMajorList  // 选择全部学院的时候
                return;
            }
            let newMajorList = [];
            this.oldMajorList.find(item => {
                if(item.collegeName == selecteCollegeName){
                    newMajorList.push(item);
                }
            });
            this.majorList = newMajorList;
        },
                //搜索功能实现
        search:function(){
            let newMajorList = [];
            this.oldMajorList.find(item => {
                if(item.majorName.indexOf(this.search_word) != -1){
                    newMajorList.push(item);
                }
            });
            this.majorList = newMajorList;
        }
        }
    }
</script>

<style scoped>
    .handle-box {
        margin-bottom: 20px;
    }

    .handle-select {
        width: 120px;
    }

    .handle-input {
        width: 300px;
        display: inline-block;
    }
    .del-dialog-cnt{
        font-size: 16px;
        text-align: center
    }
    .table{
        width: 100%;
        font-size: 14px;
    }
    .red{
        color: #ff0000;
    }
    .mr10{
        margin-right: 10px;
    }
</style>

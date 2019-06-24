<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-lx-calendar"></i> 班级</el-breadcrumb-item>
                <el-breadcrumb-item>添加</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="form-box">
                <el-form ref="form" :model="form" label-width="80px">
                    <el-form-item label="班级名称">
                        <el-input v-model="form.className"></el-input>
                    </el-form-item>

            <el-form-item label="所属学院">
            <el-radio-group v-model="form.collegeId"  :label-in-value="true" @change="getMajorByCollegeId">
              <el-radio v-for="(item,index) in collegeList" :key="index" :label="item.collegeId" :value="item.collegeName" border style="width: 180px;">{{item.collegeName}}</el-radio>
            </el-radio-group>
          </el-form-item>

          <el-form-item label="选择年级">
            <el-radio-group v-model="gradeId"  :label-in-value="true" @change="setGradeId">
              <el-radio v-for="(item,index) in gradeList" :key="index" :label="item.gradeId" :value="item.gradeName" border>{{item.gradeName}}</el-radio>
            </el-radio-group>
          </el-form-item>

          <el-form-item label="所属专业">
            <el-col  :span="11">
              <el-select v-model="majorId" placeholder="选择专业" :label-in-value="true" @change="setMajorId">
                <el-option v-for="(item,index) in majorList" :key="index" :label="item.majorName" :value="item.majorId"></el-option>
              </el-select>
            </el-col>
          </el-form-item>
                  
                    <el-form-item>
                        <el-button type="primary" @click="onSubmit">提交</el-button>
                        <el-button>取消</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>
        <!--下方页-->
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-lx-calendar"></i> 班级</el-breadcrumb-item>
                <el-breadcrumb-item>管理</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <hr/>
        <div class="container">
            <div class="handle-box">
                <el-select v-model="select_college" placeholder="筛选学院" class="handle-select mr10" @change="filterClassListByCollege">
                    <el-option style="color:red;">全部学院</el-option>
                    <el-option v-for="(item,index) in collegeList" :key="index" :label="item.collegeName" :value="item.collegeName"></el-option>
                </el-select>

                <el-select v-model="selecte_grade" placeholder="筛选年级" class="handle-select mr10" @change="filterClassListByGrade">
                    <el-option style="color:red;">全部年级</el-option>
                    <el-option v-for="(item,index) in gradeList" :key="index" :label="item.gradeName" :value="item.gradeName"></el-option>
                </el-select>

                <el-input v-model="search_word" placeholder="搜素班别" class="handle-input mr10"  v-on:input ="search"></el-input>
                <el-button type="primary" icon="search" @click="search">搜索</el-button>
            </div>

            <el-table :data="classList" border class="table" ref="multipleTable" >
                <el-table-column prop="className" label="班级名称" />
                <el-table-column prop="gradeName" label="所属年级" />
                <el-table-column prop="collegeName" label="所属学院" />
                <el-table-column label="操作" width="180" align="center">
                    <template slot-scope="scope">
                        <el-button type="text" icon="el-icon-edit" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                        <el-button type="text" icon="el-icon-delete" class="red" @click="deleteClass(scope.$index, scope.row)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>

        <!-- 编辑弹出框 -->
        <el-dialog title="编辑" :visible.sync="editVisible" width="30%">
            <el-form ref="updateform" :model="updateform" label-width="50px">
                 <el-form-item label="班别">
                    <el-input v-model="updateform.className"></el-input>
                </el-form-item>
                <el-form-item label="学院">
                <el-select v-model="updateform.collegeId" placeholder="全部学院" class="handle-select mr10" @change="setFormCollege">
                    <el-option v-for="(item,index) in collegeList" :key="index" :label="item.collegeName" :value="item.collegeId"></el-option>
                </el-select>
                </el-form-item>
                <el-form-item label="年级">
                <el-select v-model="updateform.gradeId" placeholder="全部学院" class="handle-select mr10" @change="setFormGrade">
                    <el-option v-for="(item,index) in gradeList" :key="index" :label="item.gradeName" :value="item.gradeId"></el-option>
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
                <el-button type="primary" @click="deleteClassRow">确 定</el-button>
            </span>
        </el-dialog>


    </div>
</template>

<script>
    export default {
        name: 'baseform',
        data: function(){
            return {
                collegeList: "", // 学院列表
                majorList: "", // 根据所选的学院 返回的专业列表
                gradeList:'',
                classList:'',
                gradeId: '',
                majorId:'',
                form: {
                    className: '',
                    collegeId: '',
                    majorId: '',
                    gradeId: '',
                    gradeName: '',
                    collegeName:''
                },
                //下方页变量
                select_college: '',
                selecte_grade:'',
                search_word: '',
                del_classId: '',  // 点击删除时 获取 该行的 classId
                del_status: '',  // 删除时返回的状态码 200 表示成功 400 失败
                //calssList: '', // 学院列表
                //collegeList: '', // 学院列表
                //gradeList: '', //年级列表
                del_list: [],
                tableData:[],
                editVisible: false,
                delVisible: false,  // 删除框是否可见
                // is_search: false,
                oldClassList: '', //复制一份 class数据用于筛选操作
                updateform: {
                     classId:'',
                    gradeId:'',
                    gradeName:'',
                    className:'',
                    collegeId:'',
                    collegeName: '',
                    majorId:''
                },
                idx: -1
            }
        },
         mounted: function() {
            this.getInitData();
        },
        methods: {

        //初始化数据 学院 列表
            getInitData() {
                //获取班级数据
                   this.$axios.get(this.GLOBAL.BASE_URL + "/class/all").then((res) =>{
                       this.classList = res.data;
                       this.oldClassList = res.data;  // 用于筛选作为原数据
                   });
            //获取学院列表
            this.$axios.get(this.GLOBAL.BASE_URL + "/college/all").then(res => {
                this.collegeList = res.data;
            });
            //获取年级列表
            this.$axios.get(this.GLOBAL.BASE_URL + "/grade/all").then(res => {
                this.gradeList = res.data;
            });
            },
            
            //提交表单
            onSubmit() {
                if(!this.form.className){
                    this.$message.error("请填写班级名称");
                    return;
                }
                if(!this.form.collegeId){
                    this.$message.error("请选择学院");
                    return;
                }
                if(!this.form.majorId){
                    this.$message.error("请选择专业");
                    return;
                }
                if(!this.form.gradeId){
                    this.$message.error("请选择年级");
                    return;
                }

                this.$axios.post(this.GLOBAL.BASE_URL + "/class/add",{class:this.form}).then((res) =>{
                    if(res.data.status != 200){
                        this.$message.error(res.data.msg);
                        return;
                    }
                this.$message.success("添加班级：" + this.form.className + "  成功！");
                this.getInitData();   //提交完成后重新获取数据
                });
            },

             //根据选择的学院 获取 该学院 专业,并设置 表单 collegeId
        getMajorByCollegeId: function(selectCollegeId) {
            this.form.collegeId = selectCollegeId; // 设置 表单 collegeId
            this.collegeList.find(item =>{
                if(item.collegeId == selectCollegeId){
                    this.form.collegeName = item.collegeName; // 设置 表单 collegeName
                }
            });
             //请求对应学院的专业
        if (selectCollegeId != "") {
            this.$axios
            .post(this.GLOBAL.BASE_URL + "/major/collegeid/"+selectCollegeId)
            .then(res => {
                this.majorList = res.data;
            });
            }
        },
          //根据majorId和gradeid筛选班级
        setGradeId:function(selectGradeId){
            this.form.gradeId = selectGradeId;
            this.gradeList.find(item =>{
                if (item.gradeId == selectGradeId){
                    this.form.gradeName = item.gradeName;
                }
            });
        },
         //根据选择的专业 获取 该专业的班级,并设置 表单 majorId
        setMajorId: function(selectMajorId) {
            this.form.majorId = selectMajorId;
        },

    //下方页方法
     formatter(row, column) {
                return row.collegeName;
            },
            filterTag(value, row) {
                return row.tag === value;
            },
            handleEdit(index, row) {
                this.idx = index;
                const item = this.classList[index];
                this.updateform = {
                    classId:item.classId,
                    gradeId:item.gradeId,
                    gradeName:item.gradeName,
                    className:item.className,
                    collegeId: item.collegeId,
                    collegeName: item.collegeName,
                    majorId:item.majorId
                }
                this.editVisible = true;
            },
            deleteClass(index, row) {
                this.idx = index;
                this.delVisible = true;
                this.del_classId = this.classList[index].classId;
            },
 
            // 保存编辑
            saveEdit() {
                this.$set(this.classList, this.idx, this.updateform);
                this.$axios.post(this.GLOBAL.BASE_URL + "/class/update" ,{newClass:this.updateform}).then(res =>{
                    if(res.data.status == 200){
                        this.$message.success(`修改班级成功`);
                        this.editVisible = false;
                        // location. reload();  // 刷新
                        // this.getInitData();
                    }else{
                        this.$message.error('修改班级失败：' + res.data.msg);
                        return;
                    }
                });
            },
            // 确定删除
            deleteClassRow(){
                 this.$axios.post(this.GLOBAL.BASE_URL + "/class/delete" ,{
                     classId:this.del_classId}).then((res) => {
                         this.del_status = res.data.status;
                         if(this.del_status == 200){
                             this.classList.splice(this.idx, 1);
                             this.$message.success('删除编号为'+this.del_classId+'的班级成功');
                         }else{
                              this.$message.warning('删除编号为'+this.del_classId+'的专业失败' + res.data.msg);
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
            //编辑时选择年级 设置表单年级 id name
            setFormGrade:function(selectGradeId){
                this.updateform.gradeId = selectGradeId;
                this.gradeList.find(item =>{
                    if(item.gradeId == selectGradeId){
                        this.updateform.gradeName = item.gradeName;
                    }
                });
            },

        //按学院名过滤班级列表
        filterClassListByCollege: function(selecteCollegeName){
            this.$message.success("选中：" + selecteCollegeName)
            if(typeof(selecteCollegeName) == "undefined"){
                this.classList = this.oldClassList  // 选择全部学院的时候
                return;
            }
            let newClassList = [];
            this.oldClassList.find(item => {
                if(item.collegeName == selecteCollegeName){
                    newClassList.push(item);
                }
            });
            this.classList = newClassList;
        },
        filterClassListByGrade:function(selectGradeName){
            if(typeof(selectGradeName) == "undefined"){
                this.classList = this.oldClassList  // 选择全部班级的时候
                return;
            }
            let newClassList = [];
            this.oldClassList.find(item => {
                if(item.gradeName == selectGradeName){
                    newClassList.push(item);
                }
            });
            this.classList = newClassList;
        },

        //搜索功能实现
        search:function(){
            let newClassList = [];
            this.oldClassList.find(item => {
                if(item.className.indexOf(this.search_word) != -1){
                    newClassList.push(item);
                }
            });
            this.classList = newClassList;
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

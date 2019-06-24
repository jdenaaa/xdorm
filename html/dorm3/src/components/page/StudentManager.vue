<template>
    <div class="table">
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-lx-cascades"></i> 学生管理</el-breadcrumb-item>
                <el-breadcrumb-item>添加学生</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-button type="primary" icon="delete" class="handle-del mr10" @click="handleDeleteAll">批量删除</el-button>
                <el-select v-model="select_cate" placeholder="全部学院" class="handle-select mr10" @change="getStudentByCollegeName">
                    <el-option style="color:red;">全部学院</el-option>
                    <el-option v-for="(item,index) in college" :key="index" :label="item.collegeName" :value="item.collegeName"></el-option>
                </el-select>

                <el-input v-model="select_word" placeholder="搜素姓名" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="search" @click="search">搜索</el-button>
            </div>
            <el-table :data="data" border class="table" ref="multipleTable" @selection-change="handleSelectionChange">
                <el-table-column type="selection" width="55" align="center"></el-table-column>
                <el-table-column prop="stuId" label="学号" sortable width="120"/>
                <el-table-column prop="name" label="姓名" width="100"/>
                <el-table-column prop="phone" label="电话" width="100"/>
                <el-table-column prop="gender" label="性别" width="60"/>
                <el-table-column prop="roomName" label="宿舍" width="100"/>
                <el-table-column prop="className" label="班级" width="100"/>
                <el-table-column prop="majorName" label="专业" width="120"/>
                <el-table-column prop="collegeName" label="学院" />
                <el-table-column label="操作" width="180" align="center">
                    <template slot-scope="scope">
                        <el-button type="text" icon="el-icon-edit" @click="handleEdit(scope.$index, scope.row)">分配宿舍</el-button>
                        <el-button type="text" icon="el-icon-delete" class="red" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="pagination">
                <el-pagination background @current-change="handleCurrentChange" layout="prev, pager, next" :total="total">
                </el-pagination>
            </div>
        </div>

        <!-- 编辑弹出框 -->
        <!--<el-dialog title="编辑" :visible.sync="editVisible" width="30%">
            <el-form ref="form" :model="form" label-width="50px">
                <el-form-item label="学号">
                    <el-input v-model="form.stuId"></el-input>
                </el-form-item>
                <el-form-item label="姓名">
                    <el-input v-model="form.name"></el-input>
                </el-form-item>

                <el-form-item label="班级">
                    <el-input v-model="form.className"></el-input>
                </el-form-item>
                <el-form-item label="专业">
                    <el-input v-model="form.majorName"></el-input>
                </el-form-item>
                <el-form-item label="学院">
                    <el-input v-model="form.collegeName"></el-input>
                </el-form-item>  
            </el-form>

            <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveEdit">确 定</el-button>
            </span>
        </el-dialog>-->

        <!-- 删除提示框 -->
        <el-dialog title="提示" :visible.sync="delVisible" width="300px" center>
            <div class="del-dialog-cnt">删除不可恢复，是否确定删除？</div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="delVisible = false">取 消</el-button>
                <el-button type="primary" @click="deleteRow">确 定</el-button>
            </span>
        </el-dialog>


        <!-- 批量删除提示框-->
        <el-dialog title="提示" :visible.sync="delAllVisible" width="300px" center>
            <div class="del-dialog-cnt">批量删除不可恢复，是否确定删除？</div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="delAllVisible = false">取 消</el-button>
                <el-button type="primary" @click="delAll">确 定</el-button>
            </span>
        </el-dialog>

    </div>
</template>

<script>
    export default {
        name: 'basetable',
        data() {
            return {
                //  url: './vuetable.json',
                //   url: 'http://localhost:8081/student/list',
                tableData: [],
                cur_page: 1,
                multipleSelection: [],
                select_cate: '',
                select_word: '',
                del_list: [],
                del_stuId: '',  // 点击删除时 获取 该行的 stuId
                del_status: '',  // 删除时返回的状态码 200 表示成功 400 失败
                total:'',   // 总记录数
                college: '', // 学院列表
                del_stuId_list: [], // 批量删除学生id
                is_search: false,
                editVisible: false,
                delVisible: false,  // 删除框是否可见
                delAllVisible: false,  // 批量删除框是否可见
                // form: {
                //     stuId: '',
                //     collegeId: '',
                //     name: '',
                //     gender: '',
                //     roomName: '',
                //     className:'',
                //     majorName:'',
                //     collegeName:''
                // },
                idx: -1,
            }
        },
        created() {
            this.getData();
        },
        computed: {
            data() {
                return this.tableData.filter((d) => {
                    let is_del = false;
                    for (let i = 0; i < this.del_list.length; i++) {
                        if (d.name === this.del_list[i].name) {
                            is_del = true;
                            break;
                        }
                    }
                    if (!is_del) {
                        if (d.collegeName.indexOf(this.select_cate) > -1 &&
                            (d.name.indexOf(this.select_word) > -1 ||
                                d.collegeName.indexOf(this.select_word) > -1)
                        ) {
                            return d;
                        }
                    }
                })
            }
        },
        methods: {
            // 分页导航
            handleCurrentChange(val) {
                this.cur_page = val;
                this.getData();
            },
            // 获取 easy-mock 的模拟数据
            getData() {
                   this.$axios.post(this.GLOBAL.BASE_URL + "/student/list", {
                       page: this.cur_page
                   }).then((res) => {
                       this.tableData = res.data.list;
                       this.total = res.data.total;
                   });
                   //获取学院数据
                   this.$axios.get(this.GLOBAL.BASE_URL + "/college/all").then((res) =>{
                       this.college = res.data;
                   });
                   //获取公寓列表
                this.$axios.get(this.GLOBAL.BASE_URL + "/dorm/all").then(res => {
                    this.dormList = res.data;
                });
       
            },
            search() {
                this.is_search = true;
            },
            formatter(row, column) {
                return row.roomName;
            },
            filterTag(value, row) {
                return row.tag === value;
            },
            handleEdit(index, row) {
                this.idx = index;
                const item = this.tableData[index];
                this.$router.push({ path: './editstudent', query: {'stuId': item.stuId}});
            },
            handleDelete(index, row) {
                this.idx = index;
                this.delVisible = true;
                this.del_stuId = this.tableData[index].stuId;
            },
            handleDeleteAll(){
                const length = this.multipleSelection.length;
                if(length < 1){
                    this.$message.warning('未选择删除行');
                    return;
                }
                this.delAllVisible = true;
            },
            delAll() {
                const length = this.multipleSelection.length;
                let str = '';
                this.del_list = this.del_list.concat(this.multipleSelection);
                for (let i = 0; i < length; i++) {
                    str += this.multipleSelection[i].name + ' ';
                    this.del_stuId_list.push(this.multipleSelection[i].stuId);
                }
                this.$axios.post(this.GLOBAL.BASE_URL + "/student/batchdelte",
                    {stuIdList:this.del_stuId_list}).then((res) =>{
                    let resdata = res.data;
                    if(resdata.status == 200){
                        this.$message.success(resdata.msg);
                    }else{
                        this.$message.warning('服务器或网络异常');
                        location. reload();  // 刷新
                        return;
                    }
                });
                this.$message.success('删除了' + str);
                this.multipleSelection = [];
                this.delAllVisible = false;
            },
            handleSelectionChange(val) {
                this.multipleSelection = val;
            },
            // 保存编辑
            // saveEdit() {
            //     this.$set(this.tableData, this.idx, this.form);
            //     this.editVisible = false;
            //     this.$message.success(`修改第 ${this.idx+1} 行成功`);
            // },
            // 确定删除
            deleteRow(){
                this.$axios.post(this.GLOBAL.BASE_URL + "/student/delete" ,{
                    stuId:this.del_stuId}).then((res) => {
                        this.del_status = res.data.status;
                        if(this.del_status == 200){
                            this.tableData.splice(this.idx, 1);
                            this.$message.success('删除学号为'+this.del_stuId+'的学生成功');
                        }else{
                             this.$message.warning(res.data.msg);
                        }
                    });
                    if(this.del_status != 200 && this.del_status != ''){
                         this.$message.warning('服务器或网络异常');
                    }
                this.delVisible = false;
            },
            //筛选学院  未完成
            getStudentByCollegeName: function(selectCollegeName){
                if(typeof(this.select_cate) == "undefined"){
                    this.select_cate = "";
                    return;
                }
            },
            
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

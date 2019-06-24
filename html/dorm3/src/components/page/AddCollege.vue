<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-lx-calendar"></i> 管理学院</el-breadcrumb-item>
                <el-breadcrumb-item>添加</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="form-box">
                <el-form ref="form" :model="form" label-width="80px">
                    <el-form-item label="学院名称">
                        <el-input v-model="form.collegeName"></el-input>
                    </el-form-item>
                     <el-form-item>
                        <el-button type="primary" @click="onSubmit">添加</el-button>
                        <el-button>取消</el-button>
                     </el-form-item>
                </el-form>
            </div>
        </div>

        <hr/>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-lx-calendar"></i> 管理学院</el-breadcrumb-item>
                <el-breadcrumb-item>更新</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <el-table :data="collegeList" border class="table" ref="multipleTable" @selection-change="handleSelectionChange">
                <el-table-column prop="collegeName" label="学院名称" />
                <el-table-column label="操作" width="180" align="center">
                    <template slot-scope="scope">
                        <el-button type="text" icon="el-icon-edit" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                        <el-button type="text" icon="el-icon-delete" class="red" @click="deleteCollege(scope.$index, scope.row)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>

        <!-- 编辑弹出框 -->
        <el-dialog title="编辑" :visible.sync="editVisible" width="30%">
            <el-form ref="updateform" :model="updateform" label-width="50px">
                <el-form-item label="学院名称">
                    <el-input v-model="updateform.collegeName"></el-input>
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
                <el-button type="primary" @click="deleteCollegeRow">确 定</el-button>
            </span>
        </el-dialog>

    </div>
</template>

<script>
    export default {
        name: 'baseform',
        data: function(){
            return {
                form: {
                    collegeName: '',
                },

                select_cate: '',
                select_word: '',
                del_collegeId: '',  // 点击删除时 获取 该行的 collegeId
                del_status: '',  // 删除时返回的状态码 200 表示成功 400 失败
                collegeList: '', // 学院列表
                editVisible: false,
                delVisible: false,  // 删除框是否可见
                updateform: {
                    collegeId: '',
                    collegeName:''
                },
                idx: -1
            }
        },
        created() {
            this.getInitData();
        },
        methods: {
            onSubmit() {
                this.$axios.post(this.GLOBAL.BASE_URL + "/college/add", { college: this.form }).then((res) =>{
                if(res.data.status != 200){
                    this.$message.error(res.data.msg);
                    return;
                }
                location. reload();  // 刷新
                this.$message.success("添加：" + this.form.collegeName + "  成功！");
            });
            },


             getInitData() {
                   //获取学院数据
                   this.$axios.get(this.GLOBAL.BASE_URL + "/college/all").then((res) =>{
                       this.collegeList = res.data;
                   });
       
            },
            formatter(row, column) {
                return row.collegeName;
            },
            filterTag(value, row) {
                return row.tag === value;
            },
            handleEdit(index, row) {
                this.idx = index;
                const item = this.collegeList[index];
                this.updateform = {
                    collegeId: item.collegeId,
                    collegeName: item.collegeName
                }
                this.editVisible = true;
            },
            deleteCollege(index, row) {
                this.idx = index;
                this.delVisible = true;
                this.del_collegeId = this.collegeList[index].collegeId;
            },
 
            // 保存编辑
            saveEdit() {
                this.$set(this.collegeList, this.idx, this.updateform);
                this.editVisible = false;
                this.$axios.post(this.GLOBAL.BASE_URL + "/college/update" ,{newCollege:this.updateform});
                this.$message.success(`修改名称为` +this.collegeList[this.idx].collegeName+` 行成功`);
            },
            // 确定删除
            deleteCollegeRow(){
                 this.$axios.post(this.GLOBAL.BASE_URL + "/college/delete" ,{
                     collegeId:this.del_collegeId}).then((res) => {
                         this.del_status = res.data.status;
                         if(this.del_status == 200){
                             this.collegeList.splice(this.idx, 1);
                             this.$message.success('删除编号为'+this.del_collegeId+'的学院成功');
                         }else{
                              this.$message.warning('删除编号为'+this.del_collegeId+'的学院失败' + res.data.msg);
                         }
                     });
                     if(this.del_status != 200 && this.del_status != ''){
                          this.$message.warning('服务器或网络异常');
                     }
                 this.delVisible = false;
            },
        
        }
    }
</script>


<style scoped>
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
</style>



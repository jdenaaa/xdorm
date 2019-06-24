<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-lx-calendar"></i> 公寓管理</el-breadcrumb-item>
                <el-breadcrumb-item>添加</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="form-box">
                <el-form ref="form" :model="form" label-width="80px">
                    <el-form-item label="公寓名称">
                        <el-input v-model="form.dormName"></el-input>
                    </el-form-item>
                    <el-form-item label="公寓层数">
                        <el-input-number v-model="form.floorTotal"></el-input-number>
                    </el-form-item>
                    
                    <el-form-item>
                        <el-button type="primary" @click="onSubmit">添加</el-button>
                        <el-button>取消</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>
        <!----------------下方页------------------------->
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-lx-calendar"></i> 公寓管理</el-breadcrumb-item>
                <el-breadcrumb-item>更新</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">

                <el-table :data="dormList" border class="table" ref="multipleTable">
                <el-table-column prop="dormName" label="公寓名称" width="120"/>
                <el-table-column prop="floorTotal" label="公寓层数" width="100"/>
                <el-table-column label="操作" width="180" align="center">
                    <template slot-scope="scope">
                        <el-button type="text" icon="el-icon-edit" @click="handleEdit(scope.$index, scope.row)">修改</el-button>
                        <el-button type="text" icon="el-icon-delete" class="red" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>

            </div>
        </div>

        <!--编辑弹出框-->
        <el-dialog title="编辑" :visible.sync="editVisible" width="30%">
            <el-form ref="updateform" :model="updateform" label-width="50px">
                <el-form-item label="公寓名称">
                    <el-input v-model="updateform.dormName"></el-input>
                </el-form-item>
                <el-form-item label="公寓层数">
                    <el-input v-model="updateform.floorTotal"></el-input>
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
                <el-button type="primary" @click="deleteRow">确 定</el-button>
            </span>
        </el-dialog>

    </div>
</template>

<script>
    export default {
        name: 'baseform',
        data: function(){
            return {
                dormList:'',
                editVisible: false,
                delVisible: false,  // 删除框是否可见
                del_dormId:'',
                updateform: {
                    dormId:'',
                    dormName: '',
                    floorTotal:'',
                },
                form:{
                    dormName:'',
                    floorTotal:''
                },
                idx: -1
            }
        },
        created() {
            this.getInitData();
        },
        methods: {
            onSubmit() {
                this.$axios.post(this.GLOBAL.BASE_URL + "/dorm/add",{dorm:this.form}).then((res) =>{
                    if(res.data.status == 200){
                        this.$message.success("添加公寓成功");
                        return;
                    }
                    this.$message.error(res.data.msg);                    
                });
            },

              //初始化数据 公寓列表
            getInitData() {
                //获取公寓列表
                this.$axios.get(this.GLOBAL.BASE_URL + "/dorm/all").then(res => {
                    this.dormList = res.data;
                });
            },
            //点击编辑
            handleEdit(index, row) {
                this.idx = index;
                const item = this.dormList[index];
                this.updateform = {
                    dormId:item.dormId,
                    dormName: item.dormName,
                    floorTotal: item.floorTotal
                }
                this.editVisible = true;
            },
            // 保存编辑
            saveEdit() {
                this.$set(this.dormList, this.idx, this.updateform);
                this.editVisible = false;
                this.$axios.post(this.GLOBAL.BASE_URL + "/dorm/update" ,{newDorm:this.updateform}).then((res) =>{
                    if(res.data.status == 200){
                        this.$message.success(`修改成功`)
                        this.editVisible = false;
                        return;
                    }
                    this.$message.error(res.data.msg)
                });
            },
            //删除
            handleDelete(index, row) {
                this.idx = index;
                this.delVisible = true;
                this.del_dormId = this.dormList[index].dormId;
            },
            //确定删除
            deleteRow:function(){
                this.$axios.post(this.GLOBAL.BASE_URL + "/dorm/delete",{dormId:this.del_dormId}).then((res) =>{
                    if(res.data.status == 200){
                        this.$message.success("删除成功");
                        this.delVisible = false
                        this.getInitData();
                        return;
                    }
                this.$message.error(res.data.msg)
                });
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
<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-lx-calendar"></i> 学生管理</el-breadcrumb-item>
                <el-breadcrumb-item>分配宿舍</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <!------------------------------------------------------------------------>
        <div class="container">
                <el-button type="primary" @click="onBack">返回</el-button>
      <div class="form-box">
          <div><h2>学生基本信息</h2></div>
        <el-form ref="form" :model="form" label-width="80px">

          <el-form-item label="姓名：">
            <el-tag v-model="oldStudent.name">{{oldStudent.name}}</el-tag>  学号：
            <el-tag v-model="oldStudent.stuId">{{oldStudent.stuId}}</el-tag>   性别：
             <el-tag v-model="oldStudent.gender">{{oldStudent.gender}}</el-tag>  当前所在宿舍：
             <el-tag>{{oldStudent.roomName}}</el-tag>
          </el-form-item>
          <el-form-item label="学院：">
             <el-tag v-model="oldStudent.collegeName">{{oldStudent.collegeName}}</el-tag> 专业：
             <el-tag v-model="oldStudent.majorName">{{oldStudent.majorName}}</el-tag> 班别：
             <el-tag v-model="oldStudent.className">{{oldStudent.className}}</el-tag> 
          </el-form-item>
          
         <div><h2>重新分配宿舍</h2></div>

          <el-form-item label="分配公寓">
            <el-radio-group v-model="dormId"  :label-in-value="true" @change="getDormFloorByDormId">
              <el-radio v-for="(item,index) in dormList" :key="index" :label="item.dormId" :value="item.dormName" border style="width: 180px;">{{item.dormName}}</el-radio>
            </el-radio-group>
          </el-form-item>

           <el-form-item label="楼层·房间">
            <el-col  :span="11">
              <el-select v-model="floorLevel" placeholder="选择楼层" :label-in-value="true" @change="getRoomByfloor">
                <el-option v-for="(item,index) in floorList" :key="index" :label="item" :value="item"></el-option>
              </el-select>
            </el-col>
            <el-col class="line" :span="2">楼</el-col>
            <el-col  :span="11">
              <el-select v-model="form.roomId" placeholder="选择房间" :label-in-value="true" @change="getRoomInfoById" >
              <el-option v-for="(item,index) in roomList" :key="index" :label="item.roomName" :value="item.roomId" ></el-option>
            </el-select>
            </el-col>
          </el-form-item>
            <div class="plugins-tips">
           <div style="color:red;">{{roomstatus}}</div>
              <div>可住人数：{{currentRoom.bedCount}}</div>
              <div>已住人数：{{currentRoom.memberTotal}}</div>
            </div>
        <!------------------------------------------------------------------------>

                    <el-form-item>
                        <el-button type="primary" @click="onSubmit">提交修改</el-button>
                        <el-button @click="onBack">取消返回</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>

    </div>
</template>

<script>
    export default {
        name: 'baseform',
        data: function(){
            return {
                old_stu_id:'',
                oldStudent:'',
                ////////////////////////////////
                // collegeList: "", // 学院列表
                // majorList: "", // 根据所选的学院 返回的专业列表
                // classList: "", // 根据所选专业 返回的班级列表
                dormList: "",   // 公寓列表
                floorList: "",  // 楼层列表
                roomList: "", // 房间列表
                gradeList:"", // 年级列表
                floorLevel:"",
                selectRoom:"",
                dormId: "",  // 选择的公寓id
                currentRoom:"",
                gradeId: "", // 选择的年级 id
                roomstatus: "",
                form: {
                    stuId: '',
                    name: "",
                    collegeName: "",
                    collegeId: "",
                    majorName: "",
                    majorId: "",
                    className: "",
                    classId: "",
                    gender: "",
                    phone: "",
                    roomId: "",
                    roomName: "",
                },
            }
        },
          mounted: function() {
            this.getInitData();
            
        },
        created(res){
            this.old_stu_id = this.$route.query.stuId;
            // this.$axios.post(this.GLOBAL.BASE_URL + "/student/getstu",{stuId:this.$route.query.stuId}).then((res) =>{
            //     this.oldStudent = res.data;
            // });

        },
        methods: {
                //初始化数据 学院 公寓列表
                getInitData() {
                    //获取学院列表
                    this.$axios.get(this.GLOBAL.BASE_URL + "/college/all").then(res => {
                        this.collegeList = res.data;
                    });
                    //获取公寓列表
                    this.$axios.get(this.GLOBAL.BASE_URL + "/dorm/all").then(res => {
                        this.dormList = res.data;
                    });
                    //获取年级列表
                    this.$axios.get(this.GLOBAL.BASE_URL + "/grade/all").then(res => {
                        this.gradeList = res.data;
                    });
                    this.$axios.post(this.GLOBAL.BASE_URL + "/student/getstu",{stuId:this.old_stu_id}).then((res) =>{
                            this.oldStudent = res.data;
                    });
                    this.stuId = this.oldStudent.stuId;
                },


            onSubmit() {
                this.$axios.post(this.GLOBAL.BASE_URL + "/student/update",{newStudent:this.oldStudent}).then((res) =>{
                    if(res.data.status == 200){
                        this.$message.success("修改房间为：" + this.oldStudent.roomName + "成功");
                        this.reload();
                        return;
                    }
                    this.$message.error("修改房间失败：" + res.data.msg);
                });
            },
            //取消按钮
            onBack(){
                this.$router.back(-1);
            },
    //---------------------------------------------------------

    
  //根据选择的 Dorm id 获取该公寓房间信息
  getDormFloorByDormId: function(selectDormId){
    this.$message("选中公寓：" + selectDormId);
    // 重新选择清除原有数据
    this.floorList = "" 
    this.roomList = "";   
    this.form.roomName = "";  
    this.currentRoom = ""
    //获取该公寓 楼层列表
    this.dormId = selectDormId;
    this.$axios.get(this.GLOBAL.BASE_URL + "/dorm/dormid/" + selectDormId).then((res => {this.floorList = res.data}));
  },
  //根据 楼层获取该楼的房间
  getRoomByfloor:function(floorLevel){
    this.$message("选中的楼层：" + floorLevel);
    // 重新选择清除原有数据
    this.roomList = "";    
    this.form.roomName = "";  
    this.currentRoom = "";
    this.currentRoom = "";
    this.$axios.get(this.GLOBAL.BASE_URL + "/room/" + this.dormId + "/" + floorLevel).then(res =>{
      this.roomList = res.data;
    });
  },
  getRoomInfoById: function(selectRoomId){
    let obj = {};
      obj = this.roomList.find(item => {
        return item.roomId === selectRoomId;
      });
      this.oldStudent.roomName = obj.roomName  // 设置房间
      this.oldStudent.roomId = obj.roomId     // 设置房间id

      if(obj.memberTotal >= obj.bedCount){
        this.roomstatus = "该房间已满员，请选择其他房间";
        this.$message.error("房间" + obj.roomName + "已满员，请选择其他房间");
        this.currentRoom = obj
        return;
      }else{
        this.roomstatus="";
        this.currentRoom = obj
        this.form.roomName = obj.roomName;   // 设置表单roomName
      }
  },
        }
    }
</script>
<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-lx-calendar"></i> 学生表单
        </el-breadcrumb-item>
        <el-breadcrumb-item>添加学生</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container">
      <div class="form-box">
        <el-form ref="form" :model="form" label-width="80px">

          <el-form-item label="学生姓名">
            <el-input v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="学生学号">
            <el-input-number v-model="form.stuId"></el-input-number>
          </el-form-item>


          <el-form-item label="选择学院">
            <el-radio-group v-model="form.collegeId"  :label-in-value="true" @change="getMajorByCollegeId">
              <el-radio v-for="(item,index) in collegeList" :key="index" :label="item.collegeId" :value="item.collegeName" border style="width: 180px;">{{item.collegeName}}</el-radio>
            </el-radio-group>
          </el-form-item>

          <el-form-item label="选择年级">
            <el-radio-group v-model="gradeId"  :label-in-value="true" @change="getClassByGradeId">
              <el-radio v-for="(item,index) in gradeList" :key="index" :label="item.gradeId" :value="item.gradeName" border>{{item.gradeName}}</el-radio>
            </el-radio-group>
          </el-form-item>

          <el-form-item label="专业·班别">
            <el-col  :span="11">
              <el-select v-model="form.majorName" placeholder="选择专业" :label-in-value="true" @change="getClassByMajorId">
                <el-option v-for="(item,index) in majorList" :key="index" :label="item.majorName" :value="item.majorId"></el-option>
              </el-select>
            </el-col>
            <el-col class="line" :span="2">-</el-col>
            <el-col  :span="11">
              <el-select v-model="form.classId" placeholder="选择班级" :label-in-value="true" @change="getClass" >
              <el-option v-for="(item,index) in classList" :key="index" :label="item.className" :value="item.classId" ></el-option>
            </el-select>
            </el-col>
          </el-form-item>
  

          <el-form-item label="性别">
            <el-radio-group v-model="form.gender">
              <el-radio label="男" border></el-radio>
              <el-radio label="女" border></el-radio>
            </el-radio-group>
          </el-form-item>

          <el-form-item label="联系电话">
            <el-input v-model="form.phone"></el-input>
          </el-form-item>

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
          
          <el-form-item>
            <el-button type="primary" @click="onSubmit">提交</el-button>
            <el-button>取消</el-button>
          </el-form-item>
        </el-form>      
      </div>
    </div>
  </div>
  
</template>

<script>
export default {
  name: "baseform",
  data: function() {
    return {
      collegeList: "", // 学院列表
      majorList: "", // 根据所选的学院 返回的专业列表
      classList: "", // 根据所选专业 返回的班级列表
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
        stuId: "",
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
      }
    };
  },
  mounted: function() {
    this.getInitData();
  },
  methods: {
    onSubmit() {
      if(!this.form.name){
        this.$message.error("姓名不能为空");
        return false;
      }
      if(!this.form.stuId){
        this.$message.error("学号不能为空");
        return false;
      }
      if(!this.form.collegeName){
        this.$message.error("请选择学院");
        return false;
      }
      if(!this.form.majorName){
        this.$message.error("请选择专业");
        return false;
      }
       if(!this.form.className){
        this.$message.error("请选择班级");
        return false;
      }
      if(!this.form.gender){
        this.$message.error("请选择性别");
        return false;
      }
      if(!this.form.roomName){
        this.$message.error("请选择房间");
        return false;
      }
      else{
      //提交表单
      this.$axios
        .post(this.GLOBAL.BASE_URL + "/student/add", { student: this.form })
        .then((res) => {
          if(res.data.status != 200){
            this.$message.error(res.data.msg);
            return;
          }
          this.$message.success("添加学生：" + this.form.name + "  成功！");
        });
      }
    },

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
    },
    //根据选择的学院 获取 该学院 专业,并设置 表单 collegeId
    getMajorByCollegeId: function(selectCollegeId) {
       //重新选择学院，清空原有数据
      this.classList = ""; 
      this.form.className = "";
      this.form.majorName = "";
      this.form.className = "";
      this.gradeId = "";
      this.form.collegeId = selectCollegeId; // 设置 表单 collegeId
      let obj = {};
      obj = this.collegeList.find(item => {
        return item.collegeId === selectCollegeId;
      });
      this.form.collegeName = obj.collegeName;
      //请求对应学院的专业
      if (selectCollegeId != "") {
        this.$axios
          .get(this.GLOBAL.BASE_URL + "/major/collegeid/"+selectCollegeId)
          .then(res => {
            this.majorList = res.data;
          });
      }
      this.$message("选择学院");
    },
    //根据选择的专业 获取 该专业的班级,并设置 表单 majorId
    getClassByMajorId: function(selectMajorId) {
      this.$message.success("选中专业");
      //重新选择，清空原有数据
      this.form.className = "";
      this.form.majorId = selectMajorId;
      let obj = {};
      obj = this.majorList.find(item => {
        return item.majorId === selectMajorId;
      });
      this.form.majorName = obj.majorName;
      if (selectMajorId != "") {
        this.$axios
          .post(this.GLOBAL.BASE_URL + "/class/majorid", {
            majorId: selectMajorId
          })
          .then(res => {
            this.classList = res.data;
          });
      }
    },
    // 设置表单 classId
    getClass: function(selectClassId) {
       this.form.classId = selectClassId;
       let obj = {};
           obj = this.classList.find((item)=>{
               return item.classId === selectClassId;
           });
        this.form.className = obj.className;
    },
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
  //根据majorId和gradeid筛选班级
  getClassByGradeId:function(selectGradeId){
      this.$message("选择年级" + selectGradeId);
      //根据年级id 和专业id 请求班级
      this.$axios.get(this.GLOBAL.BASE_URL + "/class/"+this.form.majorId +"/" + this.gradeId).then(res => this.classList = res.data)
  }
  }
};
</script>
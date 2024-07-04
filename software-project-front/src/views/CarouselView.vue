
<template>
  <div class="block">
    <el-header v-if="!loginFlag" style="padding: none; background-color: none">
      <el-menu class="el-menu-demo">
        <el-menu-item style="float: left; font-size: large; font-weight: 900">
          共享雨伞平台
        </el-menu-item>
        <el-menu-item style="float: right">
          <el-button @click="toRegister">注册</el-button>
        </el-menu-item>
        <el-menu-item style="float: right">
          <el-button @click="toLogin">登录</el-button>
        </el-menu-item>
      </el-menu>
    </el-header>
    <el-header v-else>
      <el-menu class="el-menu-demo">
        <el-menu-item style="float: left; font-size: large; font-weight: 900">
          共享雨伞平台
        </el-menu-item>
        <el-menu-item style="float: right">
          <el-button @click="toLoginout">退出登录</el-button>
        </el-menu-item>
        <el-menu-item style="float: right">
          <el-button @click="toHome">个人中心</el-button>
        </el-menu-item>
      </el-menu>
    </el-header>

    <el-carousel height="500px">
      <!-- <el-carousel-item v-for="item in carouselGoods" :key="item.gid">
        <el-image :src="item.gimage" @click="toCarousel"> </el-image>
      </el-carousel-item> -->
      <el-carousel-item style="text-align: center;">
        <el-image style="display: block; margin: 0 auto; width: 100%; height: 100%;" src="https://th.bing.com/th/id/R.793a3d538e650a2189ab6d51bd82fd8f?rik=yQH8tY%2bW4Tzb9w&riu=http%3a%2f%2fwww.gewuer.com%2fPublic%2fuploads%2fimage%2f201612%2f20161219113417_76649.jpg&ehk=bEp1vFDNMTYugfB70E6agz8y%2fgh3UcP2D2St7TsMQa8%3d&risl=&pid=ImgRaw&r=0">
        </el-image>
      </el-carousel-item>

      <el-carousel-item style="text-align: center;">
        <el-image style="display: block; margin: 0 auto; width: 100%; height: 100%;" src="https://th.bing.com/th/id/R.793a3d538e650a2189ab6d51bd82fd8f?rik=yQH8tY%2bW4Tzb9w&riu=http%3a%2f%2fwww.gewuer.com%2fPublic%2fuploads%2fimage%2f201612%2f20161219113417_76649.jpg&ehk=bEp1vFDNMTYugfB70E6agz8y%2fgh3UcP2D2St7TsMQa8%3d&risl=&pid=ImgRaw&r=0">
        </el-image>
      </el-carousel-item>
    </el-carousel>

    <div style="margin: 20px">
      <el-row>
        <el-tabs @tab-click="handleClick" class="tab" v-model="activeName">
          <el-tab-pane
              v-for="(lab, index) in labels"
              :label="lab"
              :key="index"
              :name="names[index]"
          >
            <!-- 卡片 -->
            <el-row style="margin: 10px">
              <el-col v-for="item in goods" :key="item.id" :span="8">
                <el-card :header="item.title" v-if="item.gcount > 0">
                  <el-image :src="item.gimage"> </el-image>
                  <div style="padding: 14px">
                    <div class="bottom clearfix">
                      <span
                          style="
                          display: inline-block;
                          text-align: left;
                          width: 100%;
                        "
                      ><b>数量：</b>{{ item.gcount }} 个</span
                      >
                      <el-button type="text" class="button" @click="onBuy(item)"
                      >租借</el-button
                      >
                    </div>
                  </div>
                </el-card>
              </el-col>
            </el-row>
          </el-tab-pane>
        </el-tabs>
      </el-row>
    </div>
    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="pageLimits"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="itemTotal"
    >
    </el-pagination>
  </div>
</template>

<script>
export default {
  name: "Index",
  data() {
    return {
      activeIndex: "1",
      carouselGoods: [],
      activeName: "all",
      goods: [
        {
          id: 1,
          title: "笃学楼",
          gimage: "https://th.bing.com/th/id/OIP.e50FmnhjA_7V7qco940hoQHaFF?w=1200&h=825&rs=1&pid=ImgDetMain",
          gcontent: "优质材料，经典黑色，耐用防水。",
          gcount: 100,
          glevel: 1
        },
        {
          id: 2,
          title: "经世楼",
          gimage: "https://th.bing.com/th/id/OIP.uGeEptel9JcfpSkyhte7iAHaE8?w=1200&h=800&rs=1&pid=ImgDetMain",
          gcontent: "鲜艳的颜色，适合儿童使用。",
          gcount: 50,
          glevel: 2
        },
        {
          id: 3,
          title: "文理大楼",
          gimage: "https://th.bing.com/th/id/OIP.p_NRM0_x1zsMJDiXlTXtqQHaE8?w=1200&h=800&rs=1&pid=ImgDetMain",
          gcontent: "轻便折叠，随身携带。",
          gcount: 200,
          glevel: 3
        }
      ],
      labels: ["全部"],
      names: ["all"],
      currentPage: 1,
      pageLimits: [10],
      pageSize: 10,
      itemTotal: 10,
      searchName: "",
      loginFlag: false,
      token: null,
      currGoods: {},
      currUser: null,
    };
  },
  methods: {
    toLogin() {
      this.$router.push({ path: "/Login" });
    },
    toRegister() {
      this.$router.push({ path: "/Register" });
    },
    async getGoodsCount(page, limit) {
      try {
        const response = await this.axios.get('http://localhost:8123/api/goods/all');
        const goodsData = response.data; // 返回的数据格式为 [{ id: 1, gcount: 10}, { id: 1, gcount: 10}...]

        this.goods = goodsData; // 更新 goods 数据
        this.itemTotal = goodsData.length; // 更新总数量
        this.carouselGoods = goodsData.slice(0, 4); // 更新轮播图数据

      } catch (error) {
        console.error('Failed to fetch goods data:', error);
        this.$message.error('无法获取商品数据');
      }
    },
    getGoods(page, limit) {
      // Simulating a backend call with the mock data
      console.log("Fetching goods for page", page, "with limit", limit);
      // For simplicity, here we are not making an actual axios request
      // You can implement the logic for fetching data from backend here
      this.itemTotal = this.goods.length;
      this.carouselGoods = this.goods.slice(0, 4);
    },
    handleSizeChange(limit) {
      this.limit = limit;
      this.currentPage = 1;
      this.getGoods(this.currentPage, this.limit);
    },
    handleCurrentChange(val) {
      this.currentPage = val;
      this.getGoods(this.currentPage, this.limit);
    },
    toCarousel() {},
    onBuy(res) {
      console.log(res);
      if (!this.token) {
        this.$message({
          message: "请先登录",
          type: "error",
        });
      } else {
        this.currGoods = res;
        this.$confirm("确认租借后将生成订单信息", "确认信息", {
          distinguishCancelAndClose: true,
          confirmButtonText: "确认",
          cancelButtonText: "取消",
        })
            .then(() => {
              this.buyGoods();
            })
            .catch((action) => {
              this.$message({
                type: "warning",
                message: "已放弃租借",
              });
            });
      }
    },
    toLoginout() {
      console.log(this.token, "<<<<<token");
      this.axios
          .post("http://localhost:8123/api/user/loginout", null, {
            params: {
              token: this.token,
            },
          })
          .then((resp) => {
            console.log(resp);
            localStorage.removeItem("token");
            this.loginFlag = false;
            this.token = null;
            this.$message({
              message: "退出成功",
              type: "success",
            });
          })
          .catch((error) => {
            console.log(error);
          });
    },
    toHome() {
      this.$router.push({ path: "/Home" });
    },
    buyGoods() {
      this.axios.defaults.headers.post["Content-Type"] =
          "application/x-www-form-urlencoded";
      const params = new URLSearchParams();
      params.append("token", this.token);
      params.append("bId", this.currUser.uid);
      params.append("glevel", 1);



      this.axios
          .post("http://localhost:8123/api/order/add", params)
          .then((resp) => {
            console.log(resp);
            this.getGoods();
            this.$message({
              type: "success",
              message: "订单生成成功，请前往个人中心完成支付  ",
            });
            this.currGoods = {};
          })
          .catch((error) => {
            console.log(error);
          });
      this.currGoods = {};
    },
  },

  created() {
    this.getGoods(1, 10);
    let token = localStorage.getItem("token");
    console.log(token);
    if (token === null) {
      this.loginFlag = false;
      this.token = null;
      this.currUser = null;
    } else {
      this.token = token;
      this.loginFlag = true;
      this.currUser = JSON.parse(localStorage.getItem("currUser"));
    }
  },
};
</script>

<style>
.block {
  margin: 5px 80px;
}

.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;

}

.tab {
  margin: 50px;
}
</style>

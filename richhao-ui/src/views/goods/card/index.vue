<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" v-show="showSearch" :inline="true">
      <el-form-item label="卡类型" prop="type">
        <el-select
          v-model="queryParams.type"
          placeholder="卡类型"
          clearable
          size="small"
          style="width: 240px"
        >
          <el-option
            v-for="dict in typeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="门店通用" prop="storeCurrency">
        <el-select
          v-model="queryParams.storeCurrency"
          placeholder="门店通用"
          clearable
          size="small"
          style="width: 240px"
        >
          <el-option
            v-for="dict in storeCurrency"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['goods:card:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['goods:card:remove']"
        >删除</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="cardList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column v-if="show" prop="id" width="120" />
      <el-table-column label="标题" prop="title" width="120" />
      <el-table-column label="类型" prop="type"  width="120" />
      <el-table-column label="总次数" prop="cardCount"  width="120" />
      <el-table-column label="有效期" prop="cardTerm"  width="120" />
      <el-table-column label="门店通过类型" prop="storeCurrency"  width="120" />
      <el-table-column label="价格" prop="price"  width="120" />
      <el-table-column label="备注" align="center" prop="remark" :show-overflow-tooltip="true" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ scope.row.createTime }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['goods:card:edit']"
          >修改</el-button>
<!--
          <el-button
            size="mini"
            type="text"
            icon="el-icon-circle-check"
            @click="handleDataScope(scope.row)"
            v-hasPermi="['goods:card:edit']"
          >数据权限</el-button>
-->
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['goods:card:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改角色配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入标题" />
        </el-form-item>
        <el-form-item label="类型">
          <el-radio-group v-model="form.type">
            <el-radio
              v-for="dict in typeOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="次数" prop="cardCount">
          <el-input v-model="form.cardCount" placeholder="请输入使用次数" />
        </el-form-item>
        <el-form-item label="有效期限(天)" prop="cardTerm">
          <el-input v-model="form.cardTerm" placeholder="有效期限(天)" />
        </el-form-item>
        <el-form-item label="门店通用性">
          <el-radio-group v-model="form.storeCurrency">
            <el-radio
              v-for="dict in storeCurrencyOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="价格" prop="price">
          <el-input v-model="form.price" placeholder="请输入价格" />
        </el-form-item>
        <el-form-item label="状态">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>


  </div>
</template>

<script>
import { listCard, getCard, delCard, addCard, updateCard } from "@/api/goods/card";

export default {
  name: "Card",
  data() {
    return {
      //id 隐藏
      show: false,
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],

      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 角色表格数据
      cardList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否显示弹出层（数据权限）
      // openDataScope: false,
      menuExpand: false,
      menuNodeAll: false,
      deptExpand: true,
      deptNodeAll: false,
      // 状态数据字典
      statusOptions: [],

      // 卡类型数据字典
      typeOptions: [],

      // 门店通用数据字典
      storeCurrencyOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        type: undefined,
        cardTerm: undefined,
        status: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        title: [
          { required: true, message: "标题不能为空", trigger: "blur" }
        ],
        price: [
          { required: true, message: "价格不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("sys_normal_disable").then(response => {
      this.statusOptions = response.data;
    });
    this.getDicts("goods_card_type").then(response => {
      this.typeOptions = response.data;
    });
    this.getDicts("goods_card_store_currency").then(response => {
      this.storeCurrencyOptions = response.data;
    });
  },
  methods: {

    /** 查询角色列表 */
    getList() {
      this.loading = true;
      listCard(this.queryParams).then(
        response => {
          this.cardList = response.rows;
          this.total = response.totalNum;
          this.loading = false;
        }
      );
    },


    // 角色状态修改
    handleStatusChange(row) {
      let text = row.status === "0" ? "启用" : "停用";
      this.$confirm('确认要"' + text + '""' + row.roleName + '"会员卡吗?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return changeCardStatus(row.id, row.status);
        }).then(() => {
          this.msgSuccess(text + "成功");
        }).catch(function() {
          row.status = row.status === "0" ? "1" : "0";
        });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },

    // 表单重置
    reset() {
      if (this.$refs.menu != undefined) {
        this.$refs.menu.setCheckedKeys([]);
      }
      this.menuExpand = false,
      this.menuNodeAll = false,
      this.deptExpand = true,
      this.deptNodeAll = false,
      this.form = {
        title: undefined,
        cardCount: undefined,
        price: undefined,
        type: "1",
        cardTerm: "1",
        storeCurrency: undefined,
        status: "0",
        remark: undefined
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.multiple = !selection.length
    },

    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加会员卡种类";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const cardId = row.id || this.ids
      getCard(cardId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改会员卡种类";
      });
    },

    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            this.form.menus = this.getMenuAllCheckedKeys();
            updateCard(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            this.form.menus = this.getMenuAllCheckedKeys();
            addCard(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              }
            });
          }
        }
      });
    },

    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除会员卡caraz编号为"' + id + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delCard(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
  }
};
</script>

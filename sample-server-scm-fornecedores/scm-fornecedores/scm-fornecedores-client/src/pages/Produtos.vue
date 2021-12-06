<template>
  <q-page padding>
    <div class="q-ma-xl">
      <span>Listar</span>
      <q-toggle
        color="indigo-10"
        v-model="insertUpdateDialog"
        size="xl"
      />
      <span>Inserir</span>
    </div>
    <div v-if="!insertUpdateDialog">
      <q-table
        :rows="listaProdutos"
        :columns="columnsProdutoTable"
      >
        <template v-slot:body-cell-action="props">
            <q-td :props="props">
              <q-btn
                icon="create"
                color="indigo-10"
                size="sm"
                dense
                v-model="props.row.fornecedor"
                @click="openUpdateDialog(props.row.produto)"
              />
              <q-btn
                icon="delete"
                color="negative"
                size="sm"
                dense
                class="q-ml-sm"
                @click="openInactivationDialog(props.row.produto)"
              />
              <q-btn
                icon="restore_from_trash"
                color="positive"
                size="sm"
                dense
                class="q-ml-sm"
                @click="openActivationDialog(props.row.produto)"
              />
            </q-td>
        </template>
      </q-table>
    </div>
    <div v-else>
      <q-card class="card-insert-form">
        <q-form align="center">
          <q-card-section class="row">
            <q-input
              outlined
              class="col-md-12 q-pa-md"
              label="Descrição"
              v-model="produto.descricao"
            />
          </q-card-section>
          <q-card-actions align="center" class="q-pa-md" id="insert-action-btn">
            <q-btn
              v-if="!isUpdate"
              label="Inserir"
              class="q-px-xl"
              color="indigo-10"
              @click="submitProduto"
            />
            <q-btn
              v-else
              label="Atualizar"
              class="q-px-xl"
              color="indigo-10"
              @click="submitProduto"
            />
          </q-card-actions>
        </q-form>
      </q-card>
    </div>
  </q-page>
</template>

<script>
import { defineComponent } from 'vue'

export default defineComponent({
  name: 'produtos',
  data () {
    return {
      columnsProdutoTable: [
        { name: 'action', label: 'Ação', field: 'action', align: 'center', sortable: false },
        { name: 'tipoCadastro', label: 'Tipo Cadastro', field: 'tipoCadastro', align: 'left', sortable: true },
        { name: 'codigo', label: 'Código', field: 'codigo', align: 'left', sortable: true },
        { name: 'descricao', label: 'Descrição', field: 'descricao', align: 'left', sortable: true }
      ],
      produto: {
        codigo: '',
        descricao: ''
      },
      produtos: [],
      listaProdutos: [],
      Produto () {
        return {
          codigo: '',
          descricao: ''
        }
      },
      insertUpdateDialog: false,
      isUpdate: false
    }
  },
  mounted () {
    this.fetchProdutos()
  },
  methods: {
    submitProduto () {
      if (this.isUpdate) {
        this.$axios.put(`http://localhost:9999/api/produto/${this.produto.id}`, this.produto)
          .then(res => {
            this.$q.notify({
              message: 'Atualização realizado com sucesso',
              color: 'positive',
              icon: 'check_circle_outline'
            })
            this.fetchProdutos()
            this.insertUpdateDialog = false
          }).catch(error => {
            console.log(error.response.data)
            this.$q.notify({
              message: error.response.data,
              color: 'negative',
              icon: 'error_outline'
            })
          })
      } else {
        this.$axios.post('http://localhost:9999/api/produto/', this.produto)
          .then(res => {
            this.$q.notify({
              message: 'Cadastro realizado com sucesso',
              color: 'positive',
              icon: 'check_circle_outline'
            })
            this.fetchProdutos()
            this.insertUpdateDialog = false
          }).catch(error => {
            console.log(error.response.data)
            this.$q.notify({
              message: error.response.data,
              color: 'negative',
              icon: 'error_outline'
            })
          })
      }
    },
    mountFornecedorTableData (produtos) {
      this.listaProdutos = produtos.map((produto) => {
        return {
          produto: {
            ...produto
          },
          tipoCadastro: produto.tipoCadastro.descricao,
          id: produto.id,
          codigo: produto.codigo,
          descricao: produto.descricao
        }
      })
    },
    fetchProdutos () {
      this.produtos = this.Produto()
      this.listaProdutos = []
      this.$axios.get('http://localhost:9999/api/produto')
        .then(res => {
          this.produtos = res.data
          this.mountFornecedorTableData(this.produtos)
        })
    },
    openUpdateDialog (produto) {
      if (produto.tipoCadastro.descricao !== 'INATIVO') {
        this.isUpdate = true
        this.insertUpdateDialog = true
        this.produto = produto
      }
    },
    openInactivationDialog (produto) {
      this.$axios.delete(`http://localhost:9999/api/produto/${produto.id}`)
        .then(res => {
          this.$q.notify({
            message: 'Produto desativado com sucesso',
            color: 'positive',
            icon: 'check_circle_outline'
          })
          this.fetchProdutos()
        }).catch(error => {
          console.log(error.response.data)
          this.$q.notify({
            message: `Erro ao desativar produto ${error.response.data}`,
            color: 'negative',
            icon: 'error_outline'
          })
        })
    },
    openActivationDialog (produto) {
      this.$axios.post(`http://localhost:9999/api/produto/${produto.id}`)
        .then(res => {
          this.$q.notify({
            message: 'Produto ativado com sucesso',
            color: 'positive',
            icon: 'check_circle_outline'
          })
          this.fetchProdutos()
        }).catch(error => {
          console.log(error.response.data)
          this.$q.notify({
            message: `Erro ao ativar produto ${error.response.data}`,
            color: 'negative',
            icon: 'error_outline'
          })
        })
    }
  }
})
</script>

<style lang="sass">
.my-sticky-header-table
  /* height or max-height is important */
  height: 500px

  .q-table__top,
  .q-table__bottom,
  thead tr:first-child th
    /* bg color is important for th; just specify one */
    background-color: #c5cae9

  thead tr th
    position: sticky
    z-index: 1
  thead tr:first-child th
    top: 0

  /* this is when the loading indicator appears */
  &.q-table--loading thead tr:last-child th
    /* height of all previous header rows */
    top: 48px
.big-text
  font-size: 24px
.mid-text
  font-size: 18px
</style>

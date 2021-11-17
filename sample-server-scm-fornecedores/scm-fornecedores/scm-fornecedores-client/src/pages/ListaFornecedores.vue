<template>
  <q-page class="container q-pa-xl">
    <div>
      <q-table
        :columns="columns"
        :rows="listaFornecedores"
        class="col my-sticky-header-table"
        row-key="id"
      >
        <template v-slot:body-cell-action="props">
          <q-td :props="props">
            <q-btn
              icon="create"
              color="primary"
              size="sm"
              dense
            />
            <q-btn
              icon="search"
              color="primary"
              size="sm"
              dense
              class="q-ml-sm"
            />
            <q-btn
              icon="delete"
              color="negative"
              size="sm"
              dense
              class="q-ml-sm"
            />
          </q-td>
        </template>
      </q-table>
    </div>
  </q-page>
</template>

<script>
import { defineComponent } from 'vue'

export default defineComponent({
  name: 'ListaFornecedores',
  setup () {
    return {}
  },
  data () {
    return {
      columns: [
        { name: 'id', label: 'ID', field: 'id', align: 'left', sortable: true },
        { name: 'tipoCadastro', label: 'Tipo Cadastro', field: 'tipoCadastro', align: 'left', sortable: true },
        { name: 'razaoSocial', label: 'Razão Social', field: 'razaoSocial', align: 'left', sortable: true },
        { name: 'nomeFantasia', label: 'Nome Fantasia', field: 'nomeFantasia', align: 'left', sortable: true },
        { name: 'cidade', label: 'Cidade', field: 'cidade', align: 'left', sortable: true },
        { name: 'estado', label: 'Estado', field: 'estado', align: 'left', sortable: true },
        { name: 'pais', label: 'Pais', field: 'pais', align: 'left', sortable: true },
        { name: 'codigo', label: 'Código', field: 'codigo', align: 'left', sortable: true },
        { name: 'action', label: 'Ação', field: 'action', align: 'right', sortable: false }
      ],
      listaFornecedores: []
    }
  },
  mounted () {
    this.getFornecedores()
  },
  methods: {
    mountFornecedorTableData (fornecedores) {
      this.listaFornecedores = fornecedores.map((fornecedor) => {
        const newFornecedor = {
          id: fornecedor.id,
          razaoSocial: fornecedor.razaoSocial,
          nomeFantasia: fornecedor.nomeFantasia,
          codigo: fornecedor.codigo,
          cidade: fornecedor.endereco.cidade.descricao,
          estado: fornecedor.endereco.cidade.estado.descricao,
          pais: fornecedor.endereco.cidade.estado.pais.descricao,
          tipoCadastro: fornecedor.tipoCadastro.descricao
        }

        return newFornecedor
      })
      console.log(this.listaFornecedores)
    },
    getFornecedores () {
      this.$axios.get('http://localhost:9999/api/fornecedor/')
        .then((res) => {
          this.listaFornecedores = res.data
          this.mountFornecedorTableData(this.listaFornecedores)
        })
        .catch((err) => {
          console.log(err)
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
</style>

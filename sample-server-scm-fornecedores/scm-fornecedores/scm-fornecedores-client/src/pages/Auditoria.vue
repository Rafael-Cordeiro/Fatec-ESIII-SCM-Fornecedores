<template>
  <q-page padding>
    <q-table
      :columns="colunasTabelaAuditoria"
      :rows="tabelaAuditoria"
      class="col my-sticky-header-table"
      row-key="id"
    />
  </q-page>
</template>

<script>
import { useQuasar } from 'quasar'
import { defineComponent } from 'vue'

export default defineComponent({
  name: 'ListaFornecedores',
  setup () {
    const $q = useQuasar()
    return {
      $q
    }
  },
  data () {
    return {
      auditoria: {
        id: null,
        dtHora: '',
        evento: '',
        tabela: '',
        owner: '',
        coluna: '',
        oldValue: '',
        newValue: '',
        identificador: '',
        bdUser: ''
      },
      tabelaAuditoria: [],
      colunasTabelaAuditoria: [
        { name: 'dtHora', label: 'Data e hora', field: 'dtHora', align: 'left', sortable: true },
        { name: 'evento', label: 'Evento', field: 'evento', align: 'left', sortable: true },
        { name: 'tabela', label: 'Tabela', field: 'tabela', align: 'left', sortable: true },
        { name: 'owner', label: 'Owner', field: 'owner', align: 'left', sortable: true },
        { name: 'coluna', label: 'Coluna', field: 'coluna', align: 'left', sortable: true },
        { name: 'oldValue', label: 'Valor antigo', field: 'oldValue', align: 'left', sortable: true },
        { name: 'newValue', label: 'Valor novo', field: 'newValue', align: 'left', sortable: true },
        { name: 'identificador', label: 'Identificador', field: 'identificador', align: 'left', sortable: true },
        { name: 'bdUser', label: 'Usuário', field: 'bdUser', align: 'left', sortable: true }
      ]
    }
  },
  mounted () {
    this.fetchAuditoria()
  },
  methods: {
    fetchAuditoria () {
      this.$axios.get('http://localhost:9999/api/auditoria/')
        .then(res => {
          this.tabelaAuditoria = res.data.map(aud => {
            return {
              id: null,
              dtHora: aud.dtHora,
              evento: aud.evento,
              tabela: aud.tabela,
              owner: aud.owner,
              coluna: (aud.coluna),
              oldValue: aud.oldValue,
              newValue: aud.newValue,
              identificador: aud.identificador,
              bdUser: aud.bdUser
            }
          })
        })
        .catch(error => {
          console.log(error.response.data)
          this.$q.notify({
            message: `Erro consultar auditoria ${error.response.data}`,
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

<template>
  <q-page padding>
    Página base para listas de entidades
    <lista-fornecedores/>
  </q-page>
</template>

<script>
import ListaFornecedores from 'src/pages/ListaFornecedores.vue'
export default {
  components: { ListaFornecedores },
  name: 'List'
}
</script>

<template>
  <q-page>
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

    </div>
    <div v-else>
      <q-card class="card-insert-form q-ma-xl">
        <q-form align="center">
          <q-card-section class="row">
            <q-input
              outlined
              class="col-md-4 q-pa-md"
              label="Descrição"
            />
            <q-select
              outlined
              class="col-md-4 q-pa-md"
              label="Fornecedor"
              :options="fornecedores"
              option-label="nomeFantasia"
              v-model="fornecedor"
            />
            <div class="col-md-4 q-pa-md">
              <q-input label="Data de Início" outlined v-model="date" mask="date" :rules="['date']">
                <template v-slot:append>
                  <q-icon name="event" class="cursor-pointer">
                    <q-popup-proxy ref="qDateProxy" cover transition-show="scale" transition-hide="scale">
                      <q-date color="indigo-10" v-model="date">
                        <div class="row items-center justify-end">
                          <q-btn v-close-popup label="Close" color="primary" flat />
                        </div>
                      </q-date>
                    </q-popup-proxy>
                  </q-icon>
                </template>
              </q-input>
            </div>
          </q-card-section>
          <q-card-actions align="center" class="q-pa-md" id="insert-action-btn">
            <q-btn
                label="Salvar serviço"
                class="q-px-xl"
                color="indigo-10"
              />
          </q-card-actions>
        </q-form>
      </q-card>
    </div>
  </q-page>
</template>

<script>
import { defineComponent, ref } from 'vue'

export default defineComponent({
  name: 'Servicos',
  data () {
    return {
      date: ref('2021/01/01'),
      fornecedores: [],
      fornecedor: {
        nomeFantasia: ''
      },
      insertUpdateDialog: false
    }
  },
  mounted () {
    this.fetchFornecedores()
  },
  methods: {
    fetchFornecedores () {
      this.$axios.get('http://localhost:9999/api/fornecedor/')
        .then((res) => {
          this.fornecedores = res.data
        })
        .catch((err) => {
          console.log(err)
        })
    }
  }
})
</script>

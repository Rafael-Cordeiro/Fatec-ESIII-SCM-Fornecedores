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
        :rows="listaServicos"
        :columns="columnsServicoTable"
      >
        <template v-slot:body-cell-action="props">
          <q-td :props="props">
            <q-btn
              icon="create"
              color="indigo-10"
              size="sm"
              dense
              v-model="props.row.servico"
              @click="openUpdateDialog(props.row.servico)"
            />
            <q-btn
              icon="delete"
              color="negative"
              size="sm"
              dense
              class="q-ml-sm"
              @click="openDeleteDialog(props.row.servico)"
            />
          </q-td>
        </template>
      </q-table>
    </div>
    <div v-else>
      <q-card class="card-insert-form q-ma-xl">
        <q-form align="center">
          <q-card-section class="row">
            <q-input
              outlined
              class="col-md-4 q-pa-md"
              label="Descrição"
              v-model="servico.descricao"
            />
            <q-select
              outlined
              class="col-md-4 q-pa-md"
              label="Fornecedor"
              :options="combofornecedores"
              option-label="nomeFantasia"
              v-model="servico.fornecedor"
            />
            <div class="col-md-4 q-pa-md">
              <q-input label="Data de Início" outlined v-model="servico.dataInicio" mask="date" :rules="['date']">
                <template v-slot:append>
                  <q-icon name="event" class="cursor-pointer">
                    <q-popup-proxy ref="qDateProxy" cover transition-show="scale" transition-hide="scale">
                      <q-date color="indigo-10" v-model="servico.dataInicio">
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
              v-if="!isUpdate"
              label="Inserir"
              class="q-px-xl"
              color="indigo-10"
              @click="submitServico"
            />
            <q-btn
              v-else
              label="Atualizar"
              class="q-px-xl"
              color="indigo-10"
              @click="submitServico"
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
      servico: {
        codigo: '',
        descricao: '',
        dataInicio: ref('2021/01/01'),
        fornecedor: {
          razaoSocial: '',
          nomeFantasia: '',
          codigo: '',
          documentos: [
            {
              codigo: '',
              tipoDocumento: {
                descricao: 'cnpj'
              }
            },
            {
              codigo: '',
              tipoDocumento: {
                descricao: 'cnae'
              }
            },
            {
              codigo: '',
              tipoDocumento: {
                descricao: 'inscricao-estadual'
              }
            },
            {
              codigo: '',
              tipoDocumento: {
                descricao: 'inscricao-municipal'
              }
            }
          ],
          endereco: {
            cep: '',
            logradouro: '',
            numero: '',
            complemento: '',
            bairro: '',
            cidade: {
              descricao: '',
              estado: {
                descricao: '',
                pais: {
                  descricao: ''
                }
              }
            },
            tipoEndereco: {
              descricao: ''
            },
            tipoLogradouro: {
              descricao: ''
            }
          },
          contatos: [],
          produtos: []
        }
      },
      servicos: [],
      listaServicos: [],
      columnsServicoTable: [
        { name: 'action', label: 'Ação', field: 'action', align: 'center', sortable: false },
        { name: 'codigo', label: 'Código', field: 'codigo', align: 'left', sortable: true },
        { name: 'descricao', label: 'Descrição', field: 'descricao', align: 'left', sortable: true },
        { name: 'dataInicio', label: 'Data de Início', field: 'dataInicio', align: 'right', sortable: true },
        { name: 'fornecedor', label: 'Fornecedor', field: 'fornecedor', align: 'right', sortable: true }
      ],
      combofornecedores: [],
      Servico () {
        return {
          codigo: '',
          descricao: '',
          dataInicio: ref('2021/01/01'),
          fornecedor: {
            razaoSocial: '',
            nomeFantasia: '',
            codigo: '',
            documentos: [
              {
                codigo: '',
                tipoDocumento: {
                  descricao: 'cnpj'
                }
              },
              {
                codigo: '',
                tipoDocumento: {
                  descricao: 'cnae'
                }
              },
              {
                codigo: '',
                tipoDocumento: {
                  descricao: 'inscricao-estadual'
                }
              },
              {
                codigo: '',
                tipoDocumento: {
                  descricao: 'inscricao-municipal'
                }
              }
            ],
            endereco: {
              cep: '',
              logradouro: '',
              numero: '',
              complemento: '',
              bairro: '',
              cidade: {
                descricao: '',
                estado: {
                  descricao: '',
                  pais: {
                    descricao: ''
                  }
                }
              },
              tipoEndereco: {
                descricao: ''
              },
              tipoLogradouro: {
                descricao: ''
              }
            },
            contatos: [],
            produtos: []
          }
        }
      },
      Contato () {
        return {
          nome: '',
          email: '',
          departamento: '',
          telefone: {
            tipoTelefone: {
              descricao: ''
            },
            ddd: '',
            ddi: '',
            numero: '',
            ramal: ''
          }
        }
      },
      insertUpdateDialog: false,
      isUpdate: false
    }
  },
  mounted () {
    this.fetchServicos()
    this.fetchFornecedores()
  },
  watch: {
    insertUpdateDialog () {
      if (!this.insertUpdateDialog) {
        this.servico = this.Servico()
        this.isUpdate = false
      }
    }
  },
  methods: {
    openUpdateDialog (servico) {
      this.isUpdate = true
      this.insertUpdateDialog = true
      this.servico = servico
    },
    openDeleteDialog (servico) {
      this.$axios.delete(`http://localhost:9999/api/servico/${servico.id}`)
        .then(res => {
          this.$q.notify({
            message: 'Serviço excluido com sucesso',
            color: 'positive',
            icon: 'check_circle_outline'
          })
          this.fetchServicos()
          this.insertUpdateDialog = false
        }).catch(error => {
          console.log(error.response.data)
          this.$q.notify({
            message: `Erro ao excluir serviço ${error.response.data}`,
            color: 'negative',
            icon: 'error_outline'
          })
        })
    },
    submitServico () {
      this.servico.dataInicio = this.servico.dataInicio.replace('/', '-')
      this.servico.dataInicio = this.servico.dataInicio.replace('/', '-')
      if (this.isUpdate) {
        this.$axios.put(`http://localhost:9999/api/servico/${this.servico.id}`, this.servico)
          .then(res => {
            this.$q.notify({
              message: 'Cadastro realizado com sucesso',
              color: 'positive',
              icon: 'check_circle_outline'
            })
            this.fetchServicos()
            this.insertUpdateDialog = false
            this.isUpdate = false
            location.reload(true)
          }).catch(error => {
            console.log(error.response.data)
            this.$q.notify({
              message: error.response.data,
              color: 'negative',
              icon: 'error_outline'
            })
          })
      } else {
        this.$axios.post('http://localhost:9999/api/servico/', this.servico)
          .then(res => {
            this.$q.notify({
              message: 'Cadastro realizado com sucesso',
              color: 'positive',
              icon: 'check_circle_outline'
            })
            this.fetchServicos()
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
    mountFornecedorTableData (servicos) {
      this.listaServicos = servicos.map((servico) => {
        return {
          servico: {
            ...servico
          },
          id: servico.id,
          codigo: servico.codigo,
          descricao: servico.descricao,
          dataInicio: servico.dataInicio,
          fornecedor: servico.fornecedor.nomeFantasia
        }
      })
    },
    fetchServicos () {
      this.servicos = this.Servico()
      this.listaServicos = []
      this.$axios.get('http://localhost:9999/api/servico/')
        .then((res) => {
          this.servicos = res.data
          this.servicos.forEach(servico => {
            servico.dataInicio = servico.dataInicio.replace('-', '/')
            servico.dataInicio = servico.dataInicio.replace('-', '/')
          })
          this.mountFornecedorTableData(this.servicos)
        })
    },
    fetchFornecedores () {
      this.$axios.get('http://localhost:9999/api/fornecedor/ativos')
        .then((res) => {
          this.combofornecedores = res.data
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
.big-text
  font-size: 24px
.mid-text
  font-size: 18px
</style>
